package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModArmorMaterials;
import net.epiccool.lawnchair.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AZALEA_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SUGAR_CUBE);

        blockStateModelGenerator.registerBars(ModBlocks.STEEL_BARS);

        blockStateModelGenerator.registerTorch(ModBlocks.UNLIT_TORCH, ModBlocks.UNLIT_WALL_TORCH);

        blockStateModelGenerator.registerLantern(ModBlocks.UNLIT_LANTERN);

        blockStateModelGenerator.createLogTexturePool(ModBlocks.AZALEA_LOG).log(ModBlocks.AZALEA_LOG).wood(ModBlocks.AZALEA_WOOD);
        blockStateModelGenerator.createLogTexturePool(ModBlocks.STRIPPED_AZALEA_LOG).log(ModBlocks.STRIPPED_AZALEA_LOG).wood(ModBlocks.STRIPPED_AZALEA_WOOD);

        BlockStateModelGenerator.BlockTexturePool dirtPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DIRT);
        BlockStateModelGenerator.BlockTexturePool coarseDirtPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.COARSE_DIRT);
        BlockStateModelGenerator.BlockTexturePool gravelPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRAVEL);
        BlockStateModelGenerator.BlockTexturePool sandPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SAND);
        BlockStateModelGenerator.BlockTexturePool redSandPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_SAND);
        BlockStateModelGenerator.BlockTexturePool quartzBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.QUARTZ_BRICKS);
        BlockStateModelGenerator.BlockTexturePool netheriteBlockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.NETHERITE_BLOCK);

        registerSlabAndStairs(dirtPool, ModBlocks.DIRT_SLAB, ModBlocks.DIRT_STAIRS);
        registerSlabAndStairs(coarseDirtPool, ModBlocks.COARSE_DIRT_SLAB, ModBlocks.COARSE_DIRT_STAIRS);
        registerSlabAndStairs(gravelPool, ModBlocks.GRAVEL_SLAB, ModBlocks.GRAVEL_STAIRS);
        registerSlabAndStairs(sandPool, ModBlocks.SAND_SLAB, ModBlocks.SAND_STAIRS);
        registerSlabAndStairs(redSandPool, ModBlocks.RED_SAND_SLAB, ModBlocks.RED_SAND_STAIRS);
        registerSlabAndStairs(quartzBricksPool, ModBlocks.QUARTZ_BRICKS_SLAB, ModBlocks.QUARTZ_BRICKS_STAIRS);
        registerSlabAndStairs(netheriteBlockPool, ModBlocks.NETHERITE_BLOCK_SLAB, ModBlocks.NETHERITE_BLOCK_STAIRS);

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
        itemModelGenerator.register(ModItems.RAW_SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BACON, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_BACON, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROBOT_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLIATH_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUCK_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_FROG_LEGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_FROG_LEGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_WRAPPED_HARD_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_WRAPPED_HARD_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_WRAPPED_HARD_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_WRAPPED_HARD_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURPLE_WRAPPED_HARD_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE_WRAPPED_HARD_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_WRAPPED_HARD_CANDY, Models.GENERATED);

        itemModelGenerator.register(ModItems.STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_PICKAXE, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.STEEL_HELMET, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.STEEL_CHESTPLATE, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.STEEL_LEGGINGS, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.STEEL_BOOTS, ModArmorMaterials.STEEL_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

        itemModelGenerator.registerArmor(ModItems.EMERALD_HELMET, ModArmorMaterials.EMERALD_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.EMERALD_CHESTPLATE, ModArmorMaterials.EMERALD_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.EMERALD_LEGGINGS, ModArmorMaterials.EMERALD_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.EMERALD_BOOTS, ModArmorMaterials.EMERALD_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

        itemModelGenerator.registerArmor(ModItems.WOOD_HELMET, ModArmorMaterials.WOOD_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.WOOD_CHESTPLATE, ModArmorMaterials.WOOD_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.WOOD_LEGGINGS, ModArmorMaterials.WOOD_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.WOOD_BOOTS, ModArmorMaterials.WOOD_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

        itemModelGenerator.registerArmor(ModItems.GAS_MASK, ModArmorMaterials.GAS_MASK_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, true);
    }

    private void registerSlabAndStairs(BlockStateModelGenerator.BlockTexturePool pool, Block slab, Block stairs) {
        pool.slab(slab);
        pool.stairs(stairs);
    }

    @Override
    public String getName() {
        return "Model";
    }
}
