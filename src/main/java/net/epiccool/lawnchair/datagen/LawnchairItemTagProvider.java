package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.item.ModItems;
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
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.STEEL_AXE);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.STEEL_PICKAXE);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.STEEL_SWORD);

        valueLookupBuilder(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.STEEL_SWORD);

        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.STEEL_HOE);

        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.STEEL_SHOVEL);

        valueLookupBuilder(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_HELMET)
                .add(ModItems.STEEL_CHESTPLATE)
                .add(ModItems.STEEL_LEGGINGS)
                .add(ModItems.STEEL_BOOTS);

        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.STEEL_CHESTPLATE);

        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.STEEL_HELMET);

        valueLookupBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.STEEL_LEGGINGS);

        valueLookupBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.STEEL_BOOTS);

        valueLookupBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_CHESTPLATE);

        valueLookupBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_HELMET);

        valueLookupBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_LEGGINGS);

        valueLookupBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_BOOTS);

    }
}
