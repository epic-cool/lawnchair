package net.epiccool.lawnchair.util;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");
        public static final TagKey<Block> FLOWERS = createTag("flowers");
        public static final TagKey<Block> TURN_TO_LAVA = createTag("turn_to_lava");
        public static final TagKey<Block> LIGHT_EMITTING_BLOCKS_10 = createTag("flowers_that_emit_light_10");
        public static final TagKey<Block> COPPER_BUTTONS = createTag("copper_buttons");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Lawnchair.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> STEEL_REPAIR = createTag("steel_repair");
        public static final TagKey<Item> EMERALD_REPAIR = createTag("steel_repair");
        public static final TagKey<Item> GAS_MASK_REPAIR = createTag("gas_mask_repair");
        public static final TagKey<Item> PIG_STOMP_RESULT = createTag("pig_stomp_result");
        public static final TagKey<Item> CANDY = createTag("candy");

        public static final TagKey<Item> AZALEA_LOGS = createTag("azalea_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));
        }
    }
}