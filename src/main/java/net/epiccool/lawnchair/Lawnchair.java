package net.epiccool.lawnchair;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.epiccool.lawnchair.block.ModBlockEntities;
import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.enchantment.ModEnchantmentEffects;
import net.epiccool.lawnchair.entity.ModEntities;
import net.epiccool.lawnchair.item.ModItems;
import net.epiccool.lawnchair.sound.ModSoundEvents;
import net.epiccool.lawnchair.stat.ModStats;
import net.epiccool.lawnchair.util.Tagger;
import net.epiccool.lawnchair.util.UnnamedHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Create steel robots (can do whatever idc i want robots)
//think iron golem but more useful

//Hunger mechanic in peaceful - drain to 5

public class Lawnchair implements ModInitializer {
    public static final String MODID = "lawnchair";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static boolean creeperExplosions = true;
    public static boolean bedExplosions = true;
    public static boolean slimeSpawning = true; //doesn't work
    private static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("lawnchair-config.json");
    private static final Map<String, BlockPos> PLAYER_HOMES = new HashMap<>();
    //    public static FlowableFluid EVIL_FLUID_STILL;
//    public static FlowableFluid EVIL_FLUID_FLOWING;
//    public static Item EVIL_FLUID_BUCKET;
//    public static Block EVIL_FLUID;


    public static final GameRules.Key<GameRules.BooleanRule> CREEPER_EXPLOSIONS =
            GameRuleRegistry.register("creeperExplosions",
                    GameRules.Category.MOBS,
                    GameRuleFactory.createBooleanRule(true));

    public static final GameRules.Key<GameRules.BooleanRule> BED_EXPLOSIONS =
            GameRuleRegistry.register("bedExplosions",
                    GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {
        loadConfig();
        ModItems.Initialize();
        ModBlocks.Initialize();
        ModEntities.Initialize();
        ModEnchantmentEffects.Initialize();
        Tagger.Initialize();
        ModStats.Initialize();
        ModBlockEntities.Initialize();
        UnnamedHelper.Initialize();
        ModSoundEvents.Initialize();
//        StickyEffectListener.Initialize();
//        ModEffects.Initialize();
//        ModPotions.Initialize();
//        ModRecipes.registerRecipes();

        LOGGER.info("Creating Copper Blocks for " + MODID);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(
                ModBlocks.COPPER_CHAIN_BLOCK,
                ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK
        );
        OxidizableBlocksRegistry.registerOxidizableBlockPair(
                ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK,
                ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK
        );
        OxidizableBlocksRegistry.registerOxidizableBlockPair(
                ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK,
                ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK
        );

        LOGGER.info("Creating Waxed Copper Blocks for " + MODID);
        OxidizableBlocksRegistry.registerWaxableBlockPair(
                ModBlocks.COPPER_CHAIN_BLOCK,
                ModBlocks.WAXED_COPPER_CHAIN_BLOCK
        );
        OxidizableBlocksRegistry.registerWaxableBlockPair(
                ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK,
                ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK
        );
        OxidizableBlocksRegistry.registerWaxableBlockPair(
                ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK,
                ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK
        );
        OxidizableBlocksRegistry.registerWaxableBlockPair(
                ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK,
                ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK
        );


        LOGGER.info("Registering /grav for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("grav")
                .requires(source -> source.hasPermissionLevel(0))
                .executes(Lawnchair::toggleGravity)
        ));

        LOGGER.info("Registering /phase for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("phase")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("phase", IntegerArgumentType.integer(0, 7))
                        .executes(ctx -> setPhase(ctx, IntegerArgumentType.getInteger(ctx, "phase"))))
        ));

        LOGGER.info("Registering /home for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("home")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ctx -> executeHome(ctx, null))
                .then(CommandManager.argument("player", EntityArgumentType.player())
                        .executes(ctx -> executeHome(ctx, EntityArgumentType.getPlayer(ctx, "player"))))
                .then(CommandManager.literal("reset")
                        .executes(ctx -> resetHome(ctx, null))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> resetHome(ctx, EntityArgumentType.getPlayer(ctx, "player")))))
                .then(CommandManager.literal("set")
                        .executes(ctx -> setHome(ctx, null, null))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> setHome(ctx, EntityArgumentType.getPlayer(ctx, "player"), null))
                                .then(CommandManager.argument("pos", Vec3ArgumentType.vec3())
                                        .executes(ctx -> setHome(ctx, EntityArgumentType.getPlayer(ctx, "player"),
                                                Vec3ArgumentType.getVec3(ctx, "pos"))))))
        ));


        LOGGER.info("Registering Villager Trades for " + MODID);
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 32 + random.nextInt(64)),
                    new ItemStack(ModItems.EMERALD_BOOTS, 1), 1, 2, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 56 + random.nextInt(64)),
                    new ItemStack(ModItems.EMERALD_LEGGINGS, 1), 1, 2, 0));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 40 + random.nextInt(64)),
                    new ItemStack(ModItems.EMERALD_HELMET, 1), 1, 2, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64 + random.nextInt(64)),
                    new ItemStack(ModItems.EMERALD_CHESTPLATE, 1), 1, 2, 0));
        });

        //Fluids
//        EVIL_FLUID_STILL = Registry.register(Registries.FLUID, Identifier.of(MODID, "evil_fluid"), new EvilFluid.Still());
//        EVIL_FLUID_FLOWING = Registry.register(Registries.FLUID, Identifier.of(MODID, "flowing_evil_fluid"), new EvilFluid.Flowing());
//        EVIL_FLUID = Registry.register(Registries.BLOCK, Identifier.of(MODID, "evil_fluid"), new FluidBlock(EVIL_FLUID_STILL, AbstractBlock.Settings.create().mapColor(MapColor.LIME).replaceable().noCollision().ticksRandomly().strength(100.0F).luminance((state) -> 3).pistonBehavior(PistonBehavior.DESTROY).dropsNothing().liquid().sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));
//        EVIL_FLUID_BUCKET = Registry.register(Registries.ITEM, Identifier.of(MODID, "evil_fluid_bucket"),
//                new BucketItem(EVIL_FLUID_STILL, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    }

    private static int toggleGravity(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();

        assert player != null;
        boolean newGravityState = !player.hasNoGravity();
        player.setNoGravity(newGravityState);

        String message = newGravityState ? "commands.lawnchair.grav.disabled" : "commands.lawnchair.grav.enabled";
        context.getSource().sendFeedback(() -> Text.translatable(message), true);

        return Command.SINGLE_SUCCESS;
    }

    public static void loadConfig() {
        if (Files.exists(CONFIG)) {
            try {
                String json = Files.readString(CONFIG);
                LOGGER.info("Loading config for " + MODID);
                creeperExplosions = !json.contains("false");
                bedExplosions = !json.contains("false");
                slimeSpawning = !json.contains("false"); //doesn't work
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveConfig() {
        try {
            String json = "{\n" +
                    "  \"creeperExplosions\": " + creeperExplosions + ",\n" +
                    "  \"bedExplosions\": " + bedExplosions + "\n" +
                    "}";
            Files.writeString(CONFIG, json);
            LOGGER.info("Saved config for " + MODID);
        } catch (IOException e) {
            LOGGER.error("Failed to save config for " + MODID, e);
        }
    }

    private static int executeHome(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity target) {
        ServerPlayerEntity executor = ctx.getSource().getPlayer();
        if (target == null) target = executor;

        assert target != null;
        ServerWorld world = target.getEntityWorld();
        BlockPos home = PLAYER_HOMES.getOrDefault(target.getUuidAsString(), world.getSpawnPoint().getPos());

        if (home == null) {
            ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.not_set"), false);
            return 0;
        }

        assert executor != null;
        executor.teleport(world, home.getX(), home.getY(), home.getZ(), Set.of(), executor.getYaw(), executor.getPitch(), true);
        ServerPlayerEntity finalTarget = target;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.teleported", finalTarget.getName()), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int resetHome(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity target) {
        ServerPlayerEntity executor = ctx.getSource().getPlayer();
        if (target == null) target = executor;

        assert executor != null;
        ServerWorld world = executor.getEntityWorld();
        BlockPos spawn = world.getSpawnPoint().getPos();
        PLAYER_HOMES.put(target.getUuidAsString(), spawn);

        ServerPlayerEntity finalTarget = target;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.reset", finalTarget.getName()), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int setHome(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity target, Vec3d position) {
        if (position == null) return 0;
        ServerPlayerEntity executor = ctx.getSource().getPlayer();
        if (target == null) target = executor;

        BlockPos blockPos = new BlockPos((int) position.x, (int) position.y, (int) position.z);
        assert target != null;
        PLAYER_HOMES.put(target.getUuidAsString(), blockPos);

        ServerPlayerEntity finalTarget = target;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.set", finalTarget.getName(), blockPos.toShortString()), true);
        return Command.SINGLE_SUCCESS;
    }
//todo: make this better, i.e. not change the day. Use a mixin
    private static int setPhase(CommandContext<ServerCommandSource> ctx, int phase) {
        ServerWorld world = ctx.getSource().getWorld();
        phase = Math.floorMod(phase, 8);

        long currentTime = world.getTimeOfDay();
        long days = currentTime / 24000L;
        long newTime = (days - (days % 8) + phase) * 24000L + (currentTime % 24000L);

        world.setTimeOfDay(newTime);
        int finalPhase = phase;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.phase.set", finalPhase, getPhase(finalPhase)), true);
        return Command.SINGLE_SUCCESS;
    }

    private static String getPhase(int phase) {
        return switch (phase) {
            case 0 -> "Full Moon";
            case 1 -> "Waning Gibbous";
            case 2 -> "Last Quarter";
            case 3 -> "Waning Crescent";
            case 4 -> "New Moon";
            case 5 -> "Waxing Crescent";
            case 6 -> "First Quarter";
            case 7 -> "Waxing Gibbous";
            default -> throw new IllegalStateException("Unexpected value: " + phase);
        };
    }
}
