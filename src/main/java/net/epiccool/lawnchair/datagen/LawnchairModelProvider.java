package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class LawnchairModelProvider extends FabricModelProvider {
    public LawnchairModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHARCOAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICE_PICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SAUSAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SAUSAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BACON, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_BACON, Models.GENERATED);
    }

    @Override
    public String getName() {
        return "Lawnchair Model Provider";
    }
}
