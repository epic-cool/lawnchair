package net.epiccool.lawnchair.block;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Lawnchair.MODID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Blocks for " + Lawnchair.MODID);
    }

    public static final Block CHARCOAL_BLOCK = register("charcoal_block", Block::new, AbstractBlock.Settings.copy(Blocks.COAL_BLOCK), true);
    public static final Block STEEL_BLOCK = register("steel_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), true);
    public static final Block IRON_CHAIN_BLOCK = register("iron_chain_block", Block::new, AbstractBlock.Settings.copy(Blocks.IRON_CHAIN), true);
}
