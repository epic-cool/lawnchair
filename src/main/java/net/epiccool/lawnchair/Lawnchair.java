package net.epiccool.lawnchair;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.enchantment.ModEnchantmentEffects;
import net.epiccool.lawnchair.entity.VariantTagger;
import net.epiccool.lawnchair.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Create steel robots (can do whatever idc i want robots)
//think iron golem but more useful

public class Lawnchair implements ModInitializer {
	public static final String MODID = "lawnchair";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static boolean creeperExplosions = true;
    private static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("lawnchair-config.json");

    public static final GameRules.Key<GameRules.BooleanRule> CREEPER_EXPLOSIONS =
            GameRuleRegistry.register("creeperExplosions",
                    GameRules.Category.MOBS,
                    GameRuleFactory.createBooleanRule(true));

    @Override
	public void onInitialize() {
        loadConfig();
        ModItems.Initialize();
//        StickyEffectListener.Initialize();
//        ModEffects.Initialize();
//        ModPotions.Initialize();
        ModBlocks.Initialize();
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

        ModEnchantmentEffects.Initialize();
        VariantTagger.Initialize();

        LOGGER.info("Registering /grav for " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("grav")
                    .requires(source -> source.hasPermissionLevel(0))
                    .executes(Lawnchair::toggleGravity)
            );
        });
    }

    private static int toggleGravity(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveConfig() {
        try {
            Files.writeString(CONFIG, "{ \"creeperExplosions\": " + creeperExplosions + " }");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
