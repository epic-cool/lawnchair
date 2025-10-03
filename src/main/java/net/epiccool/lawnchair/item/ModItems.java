package net.epiccool.lawnchair.item;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.effect.potion.ModPotions;
import net.epiccool.lawnchair.item.custom.IcePickItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Function;

public class ModItems {
    public static final RegistryKey<ItemGroup> GENERIC_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "item_group"));
    public static final ItemGroup GENERIC_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ICE_PICK))
            .displayName(Text.translatable("itemGroup.lawnchair.generic"))
            .build();

    public static final RegistryKey<ItemGroup> COMBAT_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "combat_group"));
    public static final ItemGroup COMBAT_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.STEEL_SWORD))
            .displayName(Text.translatable("itemGroup.lawnchair.combat"))
            .build();

    public static final RegistryKey<ItemGroup> POTIONS_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "potions_group"));
    public static final ItemGroup POTIONS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.POTION))
            .displayName(Text.translatable("itemGroup.lawnchair.potions"))
            .build();

    public static final RegistryKey<ItemGroup> COPPER_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "copper_group"));
    public static final ItemGroup COPPER_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.COPPER_CHAIN_BLOCK))
            .displayName(Text.translatable("itemGroup.lawnchair.copper"))
            .build();

    public static final TagKey<Item> REPAIRS_STEEL = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Lawnchair.MODID, "steel_tool_materials"));

    public static final RegistryKey<EquipmentAsset> STEEL_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Lawnchair.MODID, "steel"));

    public static final ToolMaterial STEEL_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            525,
            7.0F,
            2.5F,
            28,
            REPAIRS_STEEL
    );
    public static final ArmorMaterial STEEL_ARMOR_MATERIAL = new ArmorMaterial(
            33,
            Map.of(
                    EquipmentType.HELMET, 3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS, 6,
                    EquipmentType.BOOTS, 3), 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, REPAIRS_STEEL, STEEL_ARMOR_MATERIAL_KEY
    );

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Items for " + Lawnchair.MODID);
        Registry.register(Registries.ITEM_GROUP, GENERIC_ITEM_GROUP_KEY, GENERIC_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, POTIONS_ITEM_GROUP_KEY, POTIONS_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, COPPER_ITEM_GROUP_KEY, COPPER_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, COMBAT_ITEM_GROUP_KEY, COMBAT_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GENERIC_ITEM_GROUP_KEY).register(entries -> {
            entries.add(ModBlocks.CHARCOAL_BLOCK.asItem());
            entries.add(ModItems.COOKED_BACON);
            entries.add(ModItems.COOKED_SAUSAGE);
            entries.add(ModBlocks.COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.IRON_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.STEEL_BLOCK.asItem());
            entries.add(ModItems.ICE_PICK);
            entries.add(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModItems.PUMPKIN_SLICE);
            entries.add(ModItems.RAW_BACON);
            entries.add(ModItems.RAW_SAUSAGE);
            entries.add(ModItems.STEEL_AXE);
            entries.add(ModBlocks.STEEL_BARS.asItem());
            entries.add(ModItems.STEEL_BOOTS);
            entries.add(ModItems.STEEL_CHESTPLATE);
            entries.add(ModItems.STEEL_HELMET);
            entries.add(ModItems.STEEL_HOE);
            entries.add(ModItems.STEEL_INGOT);
            entries.add(ModItems.STEEL_LEGGINGS);
            entries.add(ModItems.STEEL_PICKAXE);
            entries.add(ModItems.STEEL_SHOVEL);
            entries.add(ModItems.STEEL_SWORD);
            entries.add(ModBlocks.WAXED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(POTIONS_ITEM_GROUP_KEY).register(ModItems::addStickyPotions);

        ItemGroupEvents.modifyEntriesEvent(COPPER_ITEM_GROUP_KEY).register(entries -> {
            entries.add(ModBlocks.COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK.asItem());
            entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(COMBAT_ITEM_GROUP_KEY).register(entries -> {
            entries.add(ModItems.STEEL_HELMET);
            entries.add(ModItems.STEEL_CHESTPLATE);
            entries.add(ModItems.STEEL_LEGGINGS);
            entries.add(ModItems.STEEL_BOOTS);
            entries.add(ModItems.STEEL_SWORD);
            entries.add(ModItems.STEEL_PICKAXE);
            entries.add(ModItems.STEEL_SHOVEL);
            entries.add(ModItems.STEEL_AXE);
            entries.add(ModItems.STEEL_HOE);
        });
    }

    public static final Item ICE_PICK = register("ice_pick", IcePickItem::new, new Item.Settings().maxCount(1).maxDamage(128).component(DataComponentTypes.TOOL, IcePickItem.createToolComponent()));
    public static final Item STEEL_INGOT = register("steel_ingot", Item::new, new Item.Settings());
    public static final Item PUMPKIN_SLICE = register("pumpkin_slice", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.05F).build()));
    public static final Item RAW_BACON = register("raw_bacon", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(20).saturationModifier(1.0F).build())); //placeholder stats
    public static final Item COOKED_BACON = register("cooked_bacon", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(20).saturationModifier(1.0F).build())); //placeholder stats
    public static final Item RAW_SAUSAGE = register("raw_sausage", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(20).saturationModifier(1.0F).build())); //placeholder stats
    public static final Item COOKED_SAUSAGE = register("cooked_sausage", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(20).saturationModifier(1.0F).build())); //placeholder stats

    //Steel tools
    public static final Item STEEL_SWORD = register(
            "steel_sword",
            Item::new,
            new Item.Settings().sword(STEEL_TOOL_MATERIAL, 1f, 1f)
    );

    public static final Item STEEL_PICKAXE = register(
            "steel_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(STEEL_TOOL_MATERIAL, 1f, 1f)
    );

    public static final Item STEEL_SHOVEL = register(
            "steel_shovel",
            Item::new,
            new Item.Settings().shovel(STEEL_TOOL_MATERIAL, 1f, 1f)
    );

    public static final Item STEEL_AXE = register(
            "steel_axe",
            Item::new,
            new Item.Settings().axe(STEEL_TOOL_MATERIAL, 1f, 1f)
    );

    public static final Item STEEL_HOE = register(
            "steel_hoe",
            Item::new,
            new Item.Settings().hoe(STEEL_TOOL_MATERIAL, 1f, 1f)
    );

    public static final Item STEEL_HELMET = register(
            "steel_helmet",
            Item::new,
            new Item.Settings().armor(STEEL_ARMOR_MATERIAL, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(33))
    );

    public static final Item STEEL_CHESTPLATE = register(
            "steel_chestplate",
            Item::new,
            new Item.Settings().armor(STEEL_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(33))
    );

    public static final Item STEEL_LEGGINGS = register(
            "steel_leggings",
            Item::new,
            new Item.Settings().armor(STEEL_ARMOR_MATERIAL, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(33))
    );

    public static final Item STEEL_BOOTS = register(
            "steel_boots",
            Item::new,
            new Item.Settings().armor(STEEL_ARMOR_MATERIAL, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(33))
    );

    private static void addStickyPotions(ItemGroup.Entries entries) {
        Potion[] stickyPotions = new Potion[] {
                ModPotions.STICKY_POTION,
                ModPotions.STICKY_POTION_LONG,
                ModPotions.STICKY_POTION_STRONG,
                ModPotions.STICKY_POTION_STRONG1,
                ModPotions.STICKY_POTION_STRONG2,
                ModPotions.STICKY_POTION_STRONG3
        };

        for (Potion potion : stickyPotions) {
            RegistryEntry<Potion> entry = Registries.POTION.getEntry(potion);
            ItemStack stack = new ItemStack(Items.POTION);
            stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(entry));
            entries.add(stack);
        }

        for (Potion potion : stickyPotions) {
            RegistryEntry<Potion> entry = Registries.POTION.getEntry(potion);
            ItemStack stack = new ItemStack(Items.SPLASH_POTION);
            stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(entry));
            entries.add(stack);
        }

        for (Potion potion : stickyPotions) {
            RegistryEntry<Potion> entry = Registries.POTION.getEntry(potion);
            ItemStack stack = new ItemStack(Items.LINGERING_POTION);
            stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(entry));
            entries.add(stack);
        }

    }
}
