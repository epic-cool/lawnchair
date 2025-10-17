package net.epiccool.lawnchair.block;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.block.custom.EvilGoopBlock;
import net.epiccool.lawnchair.block.custom.WarpedWartBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Lawnchair.MODID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));
    }

    private static AbstractBlock.Settings copyLootTable(Block block, boolean copyTranslationKey) {
        AbstractBlock.Settings settings = block.getSettings();
        AbstractBlock.Settings settings2 = AbstractBlock.Settings.create().lootTable(block.getLootTableKey());
        if (copyTranslationKey) {
            settings2 = settings2.overrideTranslationKey(block.getTranslationKey());
        }

        return settings2;
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Blocks for " + Lawnchair.MODID);
    }

    public static final Block CHARCOAL_BLOCK = register("charcoal_block", Block::new, AbstractBlock.Settings.copy(Blocks.COAL_BLOCK), true);
    public static final Block STEEL_BLOCK = register("steel_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block IRON_BLOCK_WITH_ROBOT_CORE = register("iron_block_with_robot_core", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block IRON_CHAIN_BLOCK = register("iron_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_CHAIN), true);
    public static final Block STEEL_BARS = register("steel_bars", PaneBlock::new, AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.IRON).nonOpaque(), true);
    public static final Block UNLIT_TORCH = register("unlit_torch", settings -> new TorchBlock(ParticleTypes.ASH, settings), AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 0).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), false);
	public static final Block UNLIT_WALL_TORCH = register("unlit_wall_torch", settings -> new WallTorchBlock(ParticleTypes.ASH, settings), copyLootTable(UNLIT_TORCH, true).noCollision().breakInstantly().luminance(state -> 0).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), false);
    public static final Block SOUL_JACK_O_LANTERN = register("soul_jack_o_lantern", CarvedPumpkinBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance(state -> 10).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block UNLIT_LANTERN = register("unlit_lantern", LanternBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).solid().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 0).nonOpaque().pistonBehavior(PistonBehavior.DESTROY), true);
//    public static final Block ALLOY_MIXER = register("alloy_mixer", AlloyMixerBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(2F, 2F).sounds(BlockSoundGroup.STONE), true);
    public static final Block WARPED_WART = register("warped_wart", WarpedWartBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.CYAN).noCollision().ticksRandomly().sounds(BlockSoundGroup.NETHER_WART).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block EVIL_GOOP = register("evil_goop", EvilGoopBlock::new, AbstractBlock.Settings.create().strength(0.1F, 0.1F).sounds(BlockSoundGroup.SLIME).luminance(state -> 3).breakInstantly(), true); //todo: prevent spawning

    //copper chain blocks
    public static final Block COPPER_CHAIN_BLOCK = register(
            "copper_chain_block",
            settings -> new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK), true);

    public static final Block EXPOSED_COPPER_CHAIN_BLOCK = register(
            "exposed_copper_chain_block",
            settings -> new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, settings),
            AbstractBlock.Settings.copy(Blocks.EXPOSED_COPPER), true);

    public static final Block WEATHERED_COPPER_CHAIN_BLOCK = register(
            "weathered_copper_chain_block",
            settings -> new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, settings),
            AbstractBlock.Settings.copy(Blocks.WEATHERED_COPPER), true);

    public static final Block OXIDIZED_COPPER_CHAIN_BLOCK = register(
            "oxidized_copper_chain_block",
            settings -> new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, settings),
            AbstractBlock.Settings.copy(Blocks.OXIDIZED_COPPER), true);

    //copper chain blocks - waxed
    public static final Block WAXED_COPPER_CHAIN_BLOCK = register("waxed_copper_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK), true);
    public static final Block WAXED_WEATHERED_COPPER_CHAIN_BLOCK = register("waxed_weathered_copper_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.WEATHERED_COPPER), true);
    public static final Block WAXED_EXPOSED_COPPER_CHAIN_BLOCK = register("waxed_exposed_copper_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.EXPOSED_COPPER), true);
    public static final Block WAXED_OXIDIZED_COPPER_CHAIN_BLOCK = register("waxed_oxidized_copper_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.OXIDIZED_COPPER), true);
}
