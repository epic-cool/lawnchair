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

                .add(ModBlocks.QUARTZ_BRICKS_STAIRS)
                .add(ModBlocks.QUARTZ_BRICKS_SLAB)
                .add(ModBlocks.NETHERITE_BLOCK_STAIRS)
                .add(ModBlocks.NETHERITE_BLOCK_SLAB);

        valueLookupBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.EVIL_GOOP)

                .add(ModBlocks.OAK_LEAVES_STAIRS)
                .add(ModBlocks.OAK_LEAVES_SLAB)
                .add(ModBlocks.SPRUCE_LEAVES_STAIRS)
                .add(ModBlocks.SPRUCE_LEAVES_SLAB)
                .add(ModBlocks.BIRCH_LEAVES_STAIRS)
                .add(ModBlocks.BIRCH_LEAVES_SLAB)
                .add(ModBlocks.JUNGLE_LEAVES_STAIRS)
                .add(ModBlocks.JUNGLE_LEAVES_SLAB)
                .add(ModBlocks.ACACIA_LEAVES_STAIRS)
                .add(ModBlocks.ACACIA_LEAVES_SLAB)
                .add(ModBlocks.DARK_OAK_LEAVES_SLAB)
                .add(ModBlocks.DARK_OAK_LEAVES_STAIRS)
                .add(ModBlocks.MANGROVE_LEAVES_SLAB)
                .add(ModBlocks.MANGROVE_LEAVES_STAIRS)
                .add(ModBlocks.CHERRY_LEAVES_SLAB)
                .add(ModBlocks.CHERRY_LEAVES_STAIRS)
                .add(ModBlocks.PALE_OAK_LEAVES_STAIRS)
                .add(ModBlocks.PALE_OAK_LEAVES_SLAB)
                .add(ModBlocks.AZALEA_LEAVES_STAIRS)
                .add(ModBlocks.AZALEA_LEAVES_SLAB)
                .add(ModBlocks.FLOWERING_AZALEA_LEAVES_STAIRS)
                .add(ModBlocks.FLOWERING_AZALEA_LEAVES_SLAB);

        valueLookupBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.AZALEA_LOG)
                .add(ModBlocks.AZALEA_WOOD)
                .add(ModBlocks.STRIPPED_AZALEA_LOG)
                .add(ModBlocks.STRIPPED_AZALEA_WOOD)
                .add(ModBlocks.AZALEA_PLANKS);

        valueLookupBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.DIRT_SLAB)
                .add(ModBlocks.DIRT_STAIRS)
                .add(ModBlocks.COARSE_DIRT_SLAB)
                .add(ModBlocks.COARSE_DIRT_STAIRS)
                .add(ModBlocks.GRAVEL_SLAB)
                .add(ModBlocks.GRAVEL_STAIRS)
                .add(ModBlocks.SNOW_BLOCK_SLAB)
                .add(ModBlocks.SNOW_BLOCK_STAIRS)
                .add(ModBlocks.SAND_SLAB)
                .add(ModBlocks.SAND_STAIRS)
                .add(ModBlocks.RED_SAND_SLAB)
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

        valueLookupBuilder(ModTags.Blocks.SMALL_FLOWERS)
                .add(Blocks.CACTUS_FLOWER);

        valueLookupBuilder(ModTags.Blocks.TALL_FLOWERS)
                .add(Blocks.PITCHER_PLANT);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.AZALEA_LOG)
                .add(ModBlocks.AZALEA_WOOD)
                .add(ModBlocks.STRIPPED_AZALEA_LOG)
                .add(ModBlocks.STRIPPED_AZALEA_WOOD);
    }
}
