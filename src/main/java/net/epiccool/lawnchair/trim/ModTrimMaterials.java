package net.epiccool.lawnchair.trim;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> STEEL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(Lawnchair.MODID, "steel"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        System.out.println("[DEBUG] ModTrimMaterials.bootstrap called");
        register(registerable, STEEL, Registries.ITEM.getEntry(ModItems.STEEL_INGOT),
                Style.EMPTY.withColor(TextColor.parse("#737373").getOrThrow()));
    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey,
                                   RegistryEntry<Item> item, Style style) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(ArmorTrimAssets.of("steel"),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        System.out.println("[DEBUG] Registering trim material: " + armorTrimKey.getValue());
        registerable.register(armorTrimKey, trimMaterial);
    }
}
