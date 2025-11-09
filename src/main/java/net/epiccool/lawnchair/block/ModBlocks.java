package net.epiccool.lawnchair.block;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.block.custom.EvilGoopBlock;
import net.epiccool.lawnchair.block.custom.SugarCubeBlock;
import net.epiccool.lawnchair.block.custom.SugarCubeBlockItem;
import net.epiccool.lawnchair.block.custom.WarpedWartBlock;
import net.epiccool.lawnchair.block.custom.gravity.ColoredFallingSlabBlock;
import net.epiccool.lawnchair.block.custom.gravity.SandSlabBlock;
import net.epiccool.lawnchair.block.custom.tnt.TntSlabBlock;
import net.epiccool.lawnchair.block.custom.tnt.TntStairsBlock;
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
import net.minecraft.util.ColorCode;
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
        AbstractBlock.Settings settings2 = AbstractBlock.Settings.create().lootTable(block.getLootTableKey());
        if (copyTranslationKey) {
            settings2 = settings2.overrideTranslationKey(block.getTranslationKey());
        }

        return settings2;
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Blocks for " + Lawnchair.MODID);
    }

    //debug
    public static final Block DUMMY_DIRT = register("dummy_dirt", Block::new, AbstractBlock.Settings.copy(Blocks.DIRT), false);


    public static final Block CHARCOAL_BLOCK = register("charcoal_block", Block::new, AbstractBlock.Settings.copy(Blocks.COAL_BLOCK), true);
    public static final Block STEEL_BLOCK = register("steel_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block IRON_BLOCK_WITH_ROBOT_CORE = register("iron_block_with_robot_core", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block IRON_CHAIN_BLOCK = register("iron_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_CHAIN), true);
    public static final Block STEEL_BARS = register("steel_bars", PaneBlock::new, AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.IRON).nonOpaque(), true);
    public static final Block UNLIT_TORCH = register("unlit_torch", settings -> new TorchBlock(ParticleTypes.ASH, settings), AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 0).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), false);
    public static final Block UNLIT_WALL_TORCH = register("unlit_wall_torch", settings -> new WallTorchBlock(ParticleTypes.ASH, settings), copyLootTable(UNLIT_TORCH, true).noCollision().breakInstantly().luminance(state -> 0).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), false);
    public static final Block SOUL_JACK_O_LANTERN = register("soul_jack_o_lantern", CarvedPumpkinBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance(state -> 10).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block UNLIT_LANTERN = register("unlit_lantern", LanternBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).solid().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 0).nonOpaque().pistonBehavior(PistonBehavior.DESTROY), true); //todo: right click w/ f+s to light it. same w/ torch.
    //    public static final Block ALLOY_MIXER = register("alloy_mixer", AlloyMixerBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(2F, 2F).sounds(BlockSoundGroup.STONE), true);
    public static final Block WARPED_WART = register("warped_wart", WarpedWartBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.CYAN).noCollision().ticksRandomly().sounds(BlockSoundGroup.NETHER_WART).pistonBehavior(PistonBehavior.DESTROY), true);
    public static final Block EVIL_GOOP = register("evil_goop", EvilGoopBlock::new, AbstractBlock.Settings.create().strength(0.1F, 0.1F).sounds(BlockSoundGroup.SLIME).luminance(state -> 3).breakInstantly(), true); //todo: prevent spawning
    public static final Block SUGAR_CUBE = register("sugar_cube", SugarCubeBlock::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRAVEL).breakInstantly(), false);
    public static final Block RAINBOW_WOOL = register("rainbow_wool", Block::new, AbstractBlock.Settings.copy(Blocks.WHITE_WOOL), true);

    //wood - azalea
    public static final Block AZALEA_LOG = register("azalea_log", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LOG), true);
    public static final Block AZALEA_WOOD = register("azalea_wood", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_WOOD), true);
    public static final Block STRIPPED_AZALEA_LOG = register("stripped_azalea_log", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD), true);
    public static final Block STRIPPED_AZALEA_WOOD = register("stripped_azalea_wood", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD), true);
    public static final Block AZALEA_PLANKS = register("azalea_planks", Block::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);

    //Slabs
    public static final Block TNT_SLAB = register("tnt_slab", TntSlabBlock::new, AbstractBlock.Settings.create().hardness(5f).sounds(BlockSoundGroup.GRASS), true);
    public static final Block DIRT_SLAB = register("dirt_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.DIRT), true);
    public static final Block COARSE_DIRT_SLAB = register("coarse_dirt_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.COARSE_DIRT), true);
    public static final Block OAK_LEAVES_SLAB = register("oak_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), true);
    public static final Block SPRUCE_LEAVES_SLAB = register("spruce_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.SPRUCE_LEAVES), true);
    public static final Block BIRCH_LEAVES_SLAB = register("birch_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.BIRCH_LEAVES), true);
    public static final Block JUNGLE_LEAVES_SLAB = register("jungle_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.JUNGLE_LEAVES), true);
    public static final Block ACACIA_LEAVES_SLAB = register("acacia_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.ACACIA_LEAVES), true);
    public static final Block DARK_OAK_LEAVES_SLAB = register("dark_oak_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.DARK_OAK_LEAVES), true);
    public static final Block MANGROVE_LEAVES_SLAB = register("mangrove_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.MANGROVE_LEAVES), true);
    public static final Block CHERRY_LEAVES_SLAB = register("cherry_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.CHERRY_LEAVES), true);
    public static final Block PALE_OAK_LEAVES_SLAB = register("pale_oak_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.PALE_OAK_LEAVES), true);
    public static final Block AZALEA_LEAVES_SLAB = register("azalea_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.AZALEA_LEAVES), true);
    public static final Block FLOWERING_AZALEA_LEAVES_SLAB = register("flowering_azalea_leaves_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.FLOWERING_AZALEA_LEAVES), true);
    public static final Block GRAVEL_SLAB = register("gravel_slab", settings -> new ColoredFallingSlabBlock(new ColorCode(-8356741), settings), AbstractBlock.Settings.copy(Blocks.GRAVEL), true);
    public static final Block SNOW_BLOCK_SLAB = register("snow_block_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK), true);
    public static final Block SAND_SLAB = register("sand_slab", settings -> new SandSlabBlock(new ColorCode(14406560), settings), AbstractBlock.Settings.copy(Blocks.SAND), true);
    public static final Block RED_SAND_SLAB = register("red_sand_slab", settings -> new SandSlabBlock(new ColorCode(11098145), settings), AbstractBlock.Settings.copy(Blocks.RED_SAND), true);
    public static final Block QUARTZ_BRICKS_SLAB = register("quartz_bricks_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS), true);
    public static final Block NETHERITE_BLOCK_SLAB = register("netherite_block_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK), true);

    //stairs
    public static final Block TNT_STAIRS = register("tnt_stairs", settings -> new TntStairsBlock(Blocks.TNT.getDefaultState(), settings), AbstractBlock.Settings.create().hardness(5f).sounds(BlockSoundGroup.GRASS), true);
    public static final Block DIRT_STAIRS = register("dirt_stairs", settings -> new StairsBlock(Blocks.DIRT.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.DIRT), true);
    public static final Block COARSE_DIRT_STAIRS = register("coarse_dirt_stairs", settings -> new StairsBlock(Blocks.COARSE_DIRT.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.COARSE_DIRT), true);
    public static final Block OAK_LEAVES_STAIRS = register("oak_leaves_stairs", settings -> new StairsBlock(Blocks.OAK_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), true);
    public static final Block SPRUCE_LEAVES_STAIRS = register("spruce_leaves_stairs", settings -> new StairsBlock(Blocks.SPRUCE_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.SPRUCE_LEAVES), true);
    public static final Block BIRCH_LEAVES_STAIRS = register("birch_leaves_stairs", settings -> new StairsBlock(Blocks.BIRCH_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.BIRCH_LEAVES), true);
    public static final Block JUNGLE_LEAVES_STAIRS = register("jungle_leaves_stairs", settings -> new StairsBlock(Blocks.JUNGLE_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.JUNGLE_LEAVES), true);
    public static final Block ACACIA_LEAVES_STAIRS = register("acacia_leaves_stairs", settings -> new StairsBlock(Blocks.ACACIA_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.ACACIA_LEAVES), true);
    public static final Block DARK_OAK_LEAVES_STAIRS = register("dark_oak_leaves_stairs", settings -> new StairsBlock(Blocks.DARK_OAK_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.DARK_OAK_LEAVES), true);
    public static final Block MANGROVE_LEAVES_STAIRS = register("mangrove_leaves_stairs", settings -> new StairsBlock(Blocks.MANGROVE_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.MANGROVE_LEAVES), true);
    public static final Block CHERRY_LEAVES_STAIRS = register("cherry_leaves_stairs", settings -> new StairsBlock(Blocks.CHERRY_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.CHERRY_LEAVES), true);
    public static final Block PALE_OAK_LEAVES_STAIRS = register("pale_oak_leaves_stairs", settings -> new StairsBlock(Blocks.PALE_OAK_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.PALE_OAK_LEAVES), true);
    public static final Block AZALEA_LEAVES_STAIRS = register("azalea_leaves_stairs", settings -> new StairsBlock(Blocks.AZALEA_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.AZALEA_LEAVES), true);
    public static final Block FLOWERING_AZALEA_LEAVES_STAIRS = register("flowering_azalea_leaves_stairs", settings -> new StairsBlock(Blocks.FLOWERING_AZALEA_LEAVES.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.FLOWERING_AZALEA_LEAVES), true);
    public static final Block GRAVEL_STAIRS = register("gravel_stairs", settings -> new StairsBlock(Blocks.GRAVEL.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.GRAVEL), true);
    public static final Block SNOW_BLOCK_STAIRS = register("snow_block_stairs", settings -> new StairsBlock(Blocks.SNOW_BLOCK.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK), true);
    public static final Block SAND_STAIRS = register("sand_stairs", settings -> new StairsBlock(Blocks.SAND.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.SAND), true);
    public static final Block RED_SAND_STAIRS = register("red_sand_stairs", settings -> new StairsBlock(Blocks.RED_SAND.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.RED_SAND), true);
    public static final Block QUARTZ_BRICKS_STAIRS = register("quartz_bricks_stairs", settings -> new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS), true);
    public static final Block NETHERITE_BLOCK_STAIRS = register("netherite_block_stairs", settings -> new StairsBlock(Blocks.NETHERITE_BLOCK.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK), true);

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

    static {
        Registry.register(
                Registries.ITEM,
                keyOfItem("sugar_cube"),
                new SugarCubeBlockItem(SUGAR_CUBE, new Item.Settings().registryKey(keyOfItem("sugar_cube")).useBlockPrefixedTranslationKey())
        );
    }
}
