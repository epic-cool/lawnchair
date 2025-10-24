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
                .add(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE);

        valueLookupBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.EVIL_GOOP);

        valueLookupBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.AZALEA_LOG)
                .add(ModBlocks.AZALEA_WOOD)
                .add(ModBlocks.STRIPPED_AZALEA_LOG)
                .add(ModBlocks.STRIPPED_AZALEA_WOOD)
                .add(ModBlocks.AZALEA_PLANKS);

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE);

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
