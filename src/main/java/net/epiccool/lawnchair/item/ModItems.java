package net.epiccool.lawnchair.item;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.effect.potion.ModPotions;
import net.epiccool.lawnchair.item.custom.IcePickItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final RegistryKey<ItemGroup> GENERIC_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "item_group"));
    public static final ItemGroup GENERIC_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ICE_PICK))
            .displayName(Text.translatable("itemGroup.lawnchair.generic"))
            .build();

    public static final RegistryKey<ItemGroup> POTIONS_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "potions_group"));
    public static final ItemGroup POTIONS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.STICK))
            .displayName(Text.translatable("itemGroup.lawnchair.potions"))
            .build();

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void Initialize() {
        Registry.register(Registries.ITEM_GROUP, GENERIC_ITEM_GROUP_KEY, GENERIC_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, POTIONS_ITEM_GROUP_KEY, POTIONS_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GENERIC_ITEM_GROUP_KEY).register(entries -> {
            entries.add(ModItems.ICE_PICK);
        });

        ItemGroupEvents.modifyEntriesEvent(POTIONS_ITEM_GROUP_KEY).register(ModItems::addStickyPotions);
    }

    public static final Item ICE_PICK = register("ice_pick", IcePickItem::new, new Item.Settings().maxCount(1).maxDamage(128).component(DataComponentTypes.TOOL, IcePickItem.createToolComponent()));


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
