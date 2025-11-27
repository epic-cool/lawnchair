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
        addDrop(ModBlocks.UNLIT_TORCH);
        addDrop(ModBlocks.SOUL_JACK_O_LANTERN);
        addDrop(ModBlocks.UNLIT_LANTERN);
        addDrop(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE);
//        addDrop(ModBlocks.ALLOY_MIXER);
        addDrop(ModBlocks.AZALEA_LOG);
        addDrop(ModBlocks.AZALEA_WOOD);
        addDrop(ModBlocks.STRIPPED_AZALEA_LOG);
        addDrop(ModBlocks.STRIPPED_AZALEA_WOOD);
        addDrop(ModBlocks.AZALEA_PLANKS);
        addDrop(ModBlocks.TNT_STAIRS);
        addDrop(ModBlocks.TNT_SLAB);
        addDrop(ModBlocks.DIRT_STAIRS);
        addDrop(ModBlocks.DIRT_SLAB);
        addDrop(ModBlocks.COARSE_DIRT_STAIRS);
        addDrop(ModBlocks.COARSE_DIRT_SLAB);
        addDrop(ModBlocks.GRAVEL_STAIRS);
        addDrop(ModBlocks.GRAVEL_SLAB);
        addDrop(ModBlocks.SAND_STAIRS);
        addDrop(ModBlocks.SAND_SLAB);
        addDrop(ModBlocks.RED_SAND_STAIRS);
        addDrop(ModBlocks.RED_SAND_SLAB);
        addDrop(ModBlocks.QUARTZ_BRICKS_STAIRS);
        addDrop(ModBlocks.QUARTZ_BRICKS_SLAB);
        addDrop(ModBlocks.SUGAR_CUBE);
        addDrop(ModBlocks.NETHERITE_BLOCK_STAIRS);
        addDrop(ModBlocks.NETHERITE_BLOCK_SLAB);
        addDrop(ModBlocks.FLUORESCENT_LIGHT);
        addDrop(ModBlocks.COPPER_BUTTON);
        addDrop(ModBlocks.EXPOSED_COPPER_BUTTON);
        addDrop(ModBlocks.WEATHERED_COPPER_BUTTON);
        addDrop(ModBlocks.OXIDIZED_COPPER_BUTTON);
        addDrop(ModBlocks.WAXED_COPPER_BUTTON);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_BUTTON);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_BUTTON);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON);

        addDrop(ModBlocks.UNCOVERED_BIRCH_DOOR, doorDrops(ModBlocks.UNCOVERED_BIRCH_DOOR));

        addDropWithSilkTouch(ModBlocks.SCORCHED_DIRT);
        addDropWithSilkTouch(ModBlocks.BLASTED_GLASS);
        addDropWithSilkTouch(ModBlocks.BLASTED_GLASS_PANE);
    }

    @Override
    public String getName() {
        return "LootTables";
    }
}
