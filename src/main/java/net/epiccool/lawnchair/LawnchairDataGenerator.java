package net.epiccool.lawnchair;

import net.epiccool.lawnchair.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

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
    }
}
