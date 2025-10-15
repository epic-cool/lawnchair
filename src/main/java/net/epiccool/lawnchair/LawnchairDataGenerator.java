package net.epiccool.lawnchair;

import net.epiccool.lawnchair.datagen.*;
import net.epiccool.lawnchair.trim.ModTrimMaterials;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class LawnchairDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(LawnchairModelProvider::new);
        pack.addProvider(LawnchairRecipeProvider::new);
        pack.addProvider(LawnchairItemTagProvider::new);
        pack.addProvider(LawnchairBlockTagProvider::new);
        pack.addProvider(LawnchairBlockLootTableProvider::new);
        pack.addProvider(LawnchairEnchantmentProvider::new);
        pack.addProvider(LawnchairRegistryDataGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
    }
}
