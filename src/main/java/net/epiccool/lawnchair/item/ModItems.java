package net.epiccool.lawnchair.item;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.item.custom.IcePickItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final RegistryKey<ItemGroup> GENERIC_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lawnchair.MODID, "item_group"));
    public static final ItemGroup GENERIC_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ICE_PICK))
            .displayName(Text.translatable("itemGroup.lawnchair.generic"))
            .build();

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);
        //+group
        Registry.register(Registries.ITEM_GROUP, GENERIC_ITEM_GROUP_KEY, GENERIC_ITEM_GROUP);

        return item;
    }

    public static void initialize() {
        Lawnchair.LOGGER.info("Registering Mod Items for " + Lawnchair.MODID);

        //Alphabetised list of all mod items
        ItemGroupEvents.modifyEntriesEvent(GENERIC_ITEM_GROUP_KEY).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModItems.ICE_PICK);
        });
    }

    public static final Item ICE_PICK = register("ice_pick", IcePickItem::new, new Item.Settings().maxCount(1).maxDamage(64));
}