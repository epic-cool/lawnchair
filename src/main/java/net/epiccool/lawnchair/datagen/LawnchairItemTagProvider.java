package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.item.ModItems;
import net.epiccool.lawnchair.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class LawnchairItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public LawnchairItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.STEEL_REPAIR)
                .add(ModItems.STEEL_INGOT);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.STEEL_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.STEEL_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.STEEL_SHOVEL);
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.STEEL_AXE);
        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.STEEL_HOE);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.STEEL_HELMET)
                .add(ModItems.STEEL_CHESTPLATE)
                .add(ModItems.STEEL_LEGGINGS)
                .add(ModItems.STEEL_BOOTS);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.STEEL_INGOT);
    }
}
