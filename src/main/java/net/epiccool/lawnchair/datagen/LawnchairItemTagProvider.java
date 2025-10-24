package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModItems;
import net.epiccool.lawnchair.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
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
        valueLookupBuilder(ModTags.Items.EMERALD_REPAIR)
                .add(Items.EMERALD);
        valueLookupBuilder(ModTags.Items.GAS_MASK_REPAIR)
                .add(Items.IRON_INGOT);

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
                .add(ModItems.STEEL_BOOTS)
                .add(ModItems.EMERALD_HELMET)
                .add(ModItems.EMERALD_CHESTPLATE)
                .add(ModItems.EMERALD_LEGGINGS)
                .add(ModItems.EMERALD_BOOTS);

        valueLookupBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_HELMET)
                .add(ModItems.EMERALD_HELMET);

        valueLookupBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_CHESTPLATE)
                .add(ModItems.EMERALD_CHESTPLATE);

        valueLookupBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_LEGGINGS)
                .add(ModItems.EMERALD_LEGGINGS);

        valueLookupBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.STEEL_BOOTS)
                .add(ModItems.EMERALD_BOOTS);

        valueLookupBuilder(ItemTags.DYEABLE)
                .add(ModItems.GAS_MASK);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.STEEL_INGOT);

        valueLookupBuilder(ModTags.Items.AZALEA_LOGS)
                .add(ModBlocks.AZALEA_LOG.asItem())
                .add(ModBlocks.AZALEA_WOOD.asItem())
                .add(ModBlocks.STRIPPED_AZALEA_LOG.asItem())
                .add(ModBlocks.STRIPPED_AZALEA_WOOD.asItem());
    }
}
