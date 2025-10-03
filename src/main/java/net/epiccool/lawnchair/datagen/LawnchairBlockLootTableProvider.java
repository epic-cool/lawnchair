package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LawnchairBlockLootTableProvider extends FabricBlockLootTableProvider {
    public LawnchairBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CHARCOAL_BLOCK);
        addDrop(ModBlocks.STEEL_BLOCK);
        addDrop(ModBlocks.IRON_CHAIN_BLOCK);
        addDrop(ModBlocks.STEEL_BARS);

        addDrop(ModBlocks.COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.WAXED_COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK);
    }
}
