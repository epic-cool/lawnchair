package net.epiccool.lawnchair.item;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class ModArmorMaterials {
    static RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));
    public static final RegistryKey<EquipmentAsset> STEEL_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Lawnchair.MODID, "steel"));
    public static final RegistryKey<EquipmentAsset> EMERALD_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Lawnchair.MODID, "emerald"));
    public static final RegistryKey<EquipmentAsset> WOOD_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Lawnchair.MODID, "wood"));
    public static final RegistryKey<EquipmentAsset> GAS_MASK_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Lawnchair.MODID, "gas_mask"));

    public static final ArmorMaterial STEEL_ARMOR_MATERIAL = new ArmorMaterial(500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 3);
    }), 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,0,0, ModTags.Items.STEEL_REPAIR, STEEL_KEY);

    public static final ArmorMaterial EMERALD_ARMOR_MATERIAL = new ArmorMaterial(12, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 2);
        map.put(EquipmentType.CHESTPLATE, 5);
        map.put(EquipmentType.LEGGINGS, 3);
        map.put(EquipmentType.BOOTS, 1);
    }), 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,0,0, ModTags.Items.EMERALD_REPAIR, EMERALD_KEY);

    public static final ArmorMaterial WOOD_ARMOR_MATERIAL = new ArmorMaterial(5, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 1);
        map.put(EquipmentType.CHESTPLATE, 3);
        map.put(EquipmentType.LEGGINGS, 2);
        map.put(EquipmentType.BOOTS, 1);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,0,0, ItemTags.LOGS, WOOD_KEY);

    public static final ArmorMaterial GAS_MASK = new ArmorMaterial(25, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 1);
        map.put(EquipmentType.CHESTPLATE, 1);
        map.put(EquipmentType.LEGGINGS, 1);
        map.put(EquipmentType.BOOTS, 1);
    }), 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,0,0, ModTags.Items.GAS_MASK_REPAIR, GAS_MASK_KEY);
}
