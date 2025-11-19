package net.epiccool.lawnchair;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.epiccool.lawnchair.block.ModBlockEntities;
import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.command.CommandUtil;
import net.epiccool.lawnchair.command.EmojiCommand;
import net.epiccool.lawnchair.enchantment.ModEnchantmentEffects;
import net.epiccool.lawnchair.entity.ModEntities;
import net.epiccool.lawnchair.item.ModItems;
import net.epiccool.lawnchair.sound.ModSoundEvents;
import net.epiccool.lawnchair.stat.ModStats;
import net.epiccool.lawnchair.util.ModGameRules;
import net.epiccool.lawnchair.util.Tagger;
import net.epiccool.lawnchair.util.UnnamedHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

//Create steel robots (can do whatever idc i want robots)
//think iron golem but more useful

//Hunger mechanic in peaceful - drain to 5

public class Lawnchair implements ModInitializer {
    public static final String MODID = "lawnchair";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    private static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("lawnchair-config.json");
    //    public static FlowableFluid EVIL_FLUID_STILL;
//    public static FlowableFluid EVIL_FLUID_FLOWING;
//    public static Item EVIL_FLUID_BUCKET;
//    public static Block EVIL_FLUID;

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
        ModGameRules.Initialize();
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
                .executes(CommandUtil::toggleGravity)
        ));

        LOGGER.info("Registering /phase for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("phase")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("phase", IntegerArgumentType.integer(0, 7))
                        .executes(ctx -> CommandUtil.setPhase(ctx, IntegerArgumentType.getInteger(ctx, "phase"))))
        ));

        LOGGER.info("Registering /home for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("home")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ctx -> CommandUtil.executeHome(ctx, null))
                .then(CommandManager.argument("player", EntityArgumentType.player())
                        .executes(ctx -> CommandUtil.executeHome(ctx, EntityArgumentType.getPlayer(ctx, "player"))))
                .then(CommandManager.literal("reset")
                        .executes(ctx -> CommandUtil.resetHome(ctx, null))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> CommandUtil.resetHome(ctx, EntityArgumentType.getPlayer(ctx, "player")))))
                .then(CommandManager.literal("set")
                        .executes(ctx -> CommandUtil.setHome(ctx, null, null))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> CommandUtil.setHome(ctx, EntityArgumentType.getPlayer(ctx, "player"), null))
                                .then(CommandManager.argument("pos", Vec3ArgumentType.vec3())
                                        .executes(ctx -> CommandUtil.setHome(ctx, EntityArgumentType.getPlayer(ctx, "player"),
                                                Vec3ArgumentType.getVec3(ctx, "pos"))))))
        ));

        LOGGER.info("Registering /emoji for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(CommandManager.literal("emoji")
                        .requires(src -> src.hasPermissionLevel(0))
                        .then(CommandManager.argument("category", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    String typed = builder.getRemaining().toLowerCase();
                                    EmojiCommand.EMOJI_CATEGORIES.keySet().forEach(cat -> {
                                        if (cat.toLowerCase().startsWith(typed)) {
                                            builder.suggest(cat);
                                        }
                                    });
                                    if ("random".startsWith(typed)) {
                                        builder.suggest("random");
                                    }
                                    return builder.buildFuture();
                                })
                                .then(CommandManager.argument("emoji", StringArgumentType.word())
                                        .suggests((ctx, builder) -> {
                                            String category = StringArgumentType.getString(ctx, "category").toLowerCase();
                                            Map<String, String> emojiMap = EmojiCommand.EMOJI_CATEGORIES.get(category);
                                            String typedEmoji = builder.getRemaining().toLowerCase();

                                            if (emojiMap != null) {
                                                emojiMap.forEach((key, value) -> {
                                                    if (key.toLowerCase().startsWith(typedEmoji)) {
                                                        builder.suggest(key, Text.literal(value));
                                                    }
                                                });
                                            }

                                            if ("random".startsWith(typedEmoji)) {
                                                builder.suggest("random");
                                            }
                                            return builder.buildFuture();
                                        })
                                        .then(CommandManager.argument("message", StringArgumentType.greedyString())
                                                .executes(ctx -> EmojiCommand.sendEmoji(
                                                        ctx,
                                                        StringArgumentType.getString(ctx, "category").toLowerCase(),
                                                        StringArgumentType.getString(ctx, "emoji").toLowerCase(),
                                                        StringArgumentType.getString(ctx, "message")
                                                ))
                                        )
                                        .executes(ctx -> EmojiCommand.sendEmoji(
                                                ctx,
                                                StringArgumentType.getString(ctx, "category").toLowerCase(),
                                                StringArgumentType.getString(ctx, "emoji").toLowerCase(),
                                                ""
                                        ))
                                )
                                .executes(ctx -> EmojiCommand.sendEmoji(
                                        ctx,
                                        StringArgumentType.getString(ctx, "category").toLowerCase(),
                                        "random",
                                        ""
                                ))
                        )
                        .executes(ctx -> EmojiCommand.sendEmoji(ctx, "random", "random", ""))
                )
        );


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

    public static void loadConfig() {
        if (Files.exists(CONFIG)) {
            try {
                String json = Files.readString(CONFIG);
                LOGGER.info("Loading config for " + MODID);
                ModGameRules.creeperExplosions = !json.contains("false");
                ModGameRules.silkyCreepers = !json.contains("true");
                ModGameRules.minecartScatters = !json.contains("true");
                ModGameRules.bedExplosions = !json.contains("false");
                ModGameRules.naturalExhaustion = !json.contains("false"); //doesnt work
                ModGameRules.slimeSpawning = !json.contains("false"); //doesn't work
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveConfig() {
        try {
            String json = "{\n" +
                    "  \"creeperExplosions\": " + ModGameRules.creeperExplosions + ",\n" +
                    "  \"silkyCreepers\": " + ModGameRules.silkyCreepers + ",\n" +
                    "  \"minecartScatters\": " + ModGameRules.minecartScatters + ",\n" +
                    "  \"naturalExhaustion\": " + ModGameRules.naturalExhaustion + ",\n" +
                    "  \"bedExplosions\": " + ModGameRules.bedExplosions + "\n" +
                    "}";
            Files.writeString(CONFIG, json);
            LOGGER.info("Saved config for " + MODID);
        } catch (IOException e) {
            LOGGER.error("Failed to save config for " + MODID, e);
        }
    }
}
