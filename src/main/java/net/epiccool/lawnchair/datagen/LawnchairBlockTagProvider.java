package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class LawnchairBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public LawnchairBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.IRON_CHAIN_BLOCK)
                .add(ModBlocks.STEEL_BLOCK)
                .add(ModBlocks.CHARCOAL_BLOCK)

                .add(ModBlocks.COPPER_CHAIN_BLOCK)
                .add(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK)
                .add(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK)
                .add(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK)

                .add(ModBlocks.WAXED_COPPER_CHAIN_BLOCK)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK)

                .add(ModBlocks.STEEL_BARS)
                .add(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE)

                .add(ModBlocks.COPPER_BUTTON)
                .add(ModBlocks.EXPOSED_COPPER_BUTTON)
                .add(ModBlocks.WEATHERED_COPPER_BUTTON)
                .add(ModBlocks.OXIDIZED_COPPER_BUTTON)

                .add(ModBlocks.WAXED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON)

                .add(ModBlocks.QUARTZ_BRICKS_STAIRS)
                .add(ModBlocks.QUARTZ_BRICKS_SLAB)
                .add(ModBlocks.NETHERITE_BLOCK_STAIRS)
                .add(ModBlocks.NETHERITE_BLOCK_SLAB);

        valueLookupBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.EVIL_GOOP);

        valueLookupBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.AZALEA_LOG)
                .add(ModBlocks.AZALEA_WOOD)
                .add(ModBlocks.STRIPPED_AZALEA_LOG)
                .add(ModBlocks.STRIPPED_AZALEA_WOOD)
                .add(ModBlocks.UNCOVERED_BIRCH_DOOR)
                .add(ModBlocks.UNCOVERED_BIRCH_TRAPDOOR)
                .add(ModBlocks.AZALEA_PLANKS);

        valueLookupBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.DIRT_SLAB)
                .add(ModBlocks.DIRT_STAIRS)
                .add(ModBlocks.COARSE_DIRT_SLAB)
                .add(ModBlocks.COARSE_DIRT_STAIRS)
                .add(ModBlocks.GRAVEL_SLAB)
                .add(ModBlocks.GRAVEL_STAIRS)
                .add(ModBlocks.SAND_SLAB)
                .add(ModBlocks.SAND_STAIRS)
                .add(ModBlocks.RED_SAND_SLAB)
                .add(ModBlocks.SCORCHED_DIRT)
                .add(ModBlocks.RED_SAND_STAIRS);

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.NETHERITE_BLOCK_STAIRS)
                .add(ModBlocks.NETHERITE_BLOCK_SLAB);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.AZALEA_LOG);

        valueLookupBuilder(BlockTags.INFINIBURN_OVERWORLD)
                .add(ModBlocks.CHARCOAL_BLOCK);

        valueLookupBuilder(ModTags.Blocks.FLOWERS)
                .add(Blocks.ALLIUM)
                .add(Blocks.AZURE_BLUET)
                .add(Blocks.BLUE_ORCHID)
                .add(Blocks.CORNFLOWER)
                .add(Blocks.DANDELION)
                .add(Blocks.OPEN_EYEBLOSSOM)
                .add(Blocks.CLOSED_EYEBLOSSOM)
                .add(Blocks.LILY_OF_THE_VALLEY)
                .add(Blocks.OXEYE_DAISY)
                .add(Blocks.POPPY)
                .add(Blocks.TORCHFLOWER)
                .add(Blocks.ORANGE_TULIP)
                .add(Blocks.PINK_TULIP)
                .add(Blocks.RED_TULIP)
                .add(Blocks.WHITE_TULIP)
                .add(Blocks.LILAC)
                .add(Blocks.PEONY)
                .add(Blocks.PITCHER_PLANT)
                .add(Blocks.ROSE_BUSH)
                .add(Blocks.SUNFLOWER)
                .add(Blocks.CHERRY_LEAVES)
                .add(Blocks.CHORUS_FLOWER)
                .add(Blocks.FLOWERING_AZALEA_LEAVES)
                .add(Blocks.PINK_PETALS)
                .add(Blocks.SPORE_BLOSSOM)
                .add(Blocks.WILDFLOWERS)
                .add(Blocks.CACTUS_FLOWER)
                .add(Blocks.WITHER_ROSE);

        valueLookupBuilder(ModTags.Blocks.LIGHT_EMITTING_BLOCKS_10)
                .add(Blocks.TORCHFLOWER_CROP)
                .add(Blocks.POTTED_TORCHFLOWER)
                .add(Blocks.TORCHFLOWER);

        valueLookupBuilder(ModTags.Blocks.TURN_TO_LAVA)
                .add(Blocks.MAGMA_BLOCK);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.AZALEA_LOG)
                .add(ModBlocks.AZALEA_WOOD)
                .add(ModBlocks.STRIPPED_AZALEA_LOG)
                .add(ModBlocks.STRIPPED_AZALEA_WOOD);

        valueLookupBuilder(BlockTags.DOORS)
                .add(ModBlocks.UNCOVERED_BIRCH_DOOR);

        valueLookupBuilder(BlockTags.MOB_INTERACTABLE_DOORS)
                .add(ModBlocks.UNCOVERED_BIRCH_DOOR);

        valueLookupBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.UNCOVERED_BIRCH_DOOR);

        valueLookupBuilder(BlockTags.TRAPDOORS)
                .add(ModBlocks.UNCOVERED_BIRCH_TRAPDOOR);

        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.UNCOVERED_BIRCH_TRAPDOOR);

        valueLookupBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.MOSS_REPLACEABLE)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.LUSH_GROUND_REPLACEABLE)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.ENDERMAN_HOLDABLE)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.DRY_VEGETATION_MAY_PLACE_ON)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.DRIPSTONE_REPLACEABLE_BLOCKS)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.DIRT)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
                .add(ModBlocks.SCORCHED_DIRT);

        valueLookupBuilder(BlockTags.BUTTONS)
                .add(ModBlocks.COPPER_BUTTON)
                .add(ModBlocks.EXPOSED_COPPER_BUTTON)
                .add(ModBlocks.WEATHERED_COPPER_BUTTON)
                .add(ModBlocks.OXIDIZED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON);

        valueLookupBuilder(ModTags.Blocks.COPPER_BUTTONS)
                .add(ModBlocks.COPPER_BUTTON)
                .add(ModBlocks.EXPOSED_COPPER_BUTTON)
                .add(ModBlocks.WEATHERED_COPPER_BUTTON)
                .add(ModBlocks.OXIDIZED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_BUTTON)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON);
    }
}
