package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModArmorMaterials;
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
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EVIL_GOOP);

        blockStateModelGenerator.registerBars(ModBlocks.STEEL_BARS);

        blockStateModelGenerator.registerTorch(ModBlocks.UNLIT_TORCH, ModBlocks.UNLIT_WALL_TORCH);

        blockStateModelGenerator.registerLantern(ModBlocks.UNLIT_LANTERN);



//        blockStateModelGenerator.registerCrop(ModBlocks.WARPED_WART, ModBlocks.WARPED_WART.getStateManager().getProperties().);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICE_PICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SAUSAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SAUSAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_HAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_HAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROBOT_CORE, Models.GENERATED);

        itemModelGenerator.register(ModItems.STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_PICKAXE, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.STEEL_HELMET, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.STEEL_CHESTPLATE, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.STEEL_LEGGINGS, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.STEEL_BOOTS, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

    }

    @Override
    public String getName() {
        return "Lawnchair Model Provider";
    }
}
