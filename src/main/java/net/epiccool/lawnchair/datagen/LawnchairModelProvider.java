package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.util.Identifier;

public class LawnchairModelProvider extends FabricModelProvider {
    public LawnchairModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHARCOAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK);

        blockStateModelGenerator.registerBars(ModBlocks.STEEL_BARS);
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

        itemModelGenerator.register(ModItems.STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_PICKAXE, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.STEEL_HELMET, ModItems.STEEL_ARMOR_MATERIAL_KEY, Identifier.of(Lawnchair.MODID, "trim"), false);
        itemModelGenerator.registerArmor(ModItems.STEEL_CHESTPLATE, ModItems.STEEL_ARMOR_MATERIAL_KEY, Identifier.of(Lawnchair.MODID, "trim"), false);
        itemModelGenerator.registerArmor(ModItems.STEEL_LEGGINGS, ModItems.STEEL_ARMOR_MATERIAL_KEY, Identifier.of(Lawnchair.MODID, "trim"), false);
        itemModelGenerator.registerArmor(ModItems.STEEL_BOOTS, ModItems.STEEL_ARMOR_MATERIAL_KEY, Identifier.of(Lawnchair.MODID, "trim"), false);
        itemModelGenerator.uploadArmor(Identifier.of(Lawnchair.MODID, "equipment/steel"), Identifier.of(Lawnchair.MODID, "textures/entity/equipment/humanoid/steel.png"), Identifier.of(Lawnchair.MODID, "textures/entity/equipment/humanoid_leggings/steel.png"));
    }

    @Override
    public String getName() {
        return "Lawnchair Model Provider";
    }
}
