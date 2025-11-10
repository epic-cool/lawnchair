package net.epiccool.lawnchair.item.custom;

import net.epiccool.lawnchair.Lawnchair;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class ShieldFactory {
    public static Item registerShield(String name, int durability, float reduction, float blockDelay, float disableCooldown) {
        BlocksAttacksComponent component = new BlocksAttacksComponent(
                blockDelay,
                disableCooldown,
                List.of(new BlocksAttacksComponent.DamageReduction((float)Math.PI, Optional.empty(), reduction, 0.0f)),
                new BlocksAttacksComponent.ItemDamage(3.0f, 1.0f, 0.0f),
                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
                Optional.of(SoundEvents.ITEM_SHIELD_BREAK)
        );

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));

        Item shield = new ShieldItem(new Item.Settings()
                        .maxCount(1)
                        .maxDamage(durability)
                        .component(DataComponentTypes.BLOCKS_ATTACKS, component)
                .registryKey(itemKey));

        Registry.register(Registries.ITEM, Identifier.of(Lawnchair.MODID, name), shield);
        return shield;
    }

    public static Item registerShieldFireproof(String name, int durability, float reduction, float blockDelay, float disableCooldown) {
        BlocksAttacksComponent component = new BlocksAttacksComponent(
                blockDelay,
                disableCooldown,
                List.of(new BlocksAttacksComponent.DamageReduction((float)Math.PI, Optional.empty(), reduction, 0.0f)),
                new BlocksAttacksComponent.ItemDamage(3.0f, 1.0f, 0.0f),
                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
                Optional.of(SoundEvents.ITEM_SHIELD_BREAK)
        );

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lawnchair.MODID, name));

        Item shield = new ShieldItem(new Item.Settings()
                        .maxCount(1)
                        .maxDamage(durability)
                        .component(DataComponentTypes.BLOCKS_ATTACKS, component)
                        .fireproof()
                .registryKey(itemKey));

        Registry.register(Registries.ITEM, Identifier.of(Lawnchair.MODID, name), shield);
        return shield;
    }

    public static void registerTooltips() {
        ItemTooltipCallback.EVENT.register((ItemStack stack, Item.TooltipContext context, TooltipType tooltipType, List<Text> lines) -> {
            BlocksAttacksComponent comp = stack.get(DataComponentTypes.BLOCKS_ATTACKS);
            if (comp == null || comp.damageReductions().isEmpty()) return;

            BlocksAttacksComponent.DamageReduction reduction = comp.damageReductions().getFirst();
            float percent = reduction.base() * 100f;

            lines.add(Text.translatable("shield.lawnchair.tooltip.block_power", String.format("%.0f%%", percent)).formatted(Formatting.GRAY));

            if (comp.blockDelaySeconds() > 0) {
                lines.add(Text.translatable("shield.lawnchair.tooltip.block_delay", String.format("%.2fs", comp.blockDelaySeconds())).formatted(Formatting.DARK_GRAY));
            }
            if (comp.disableCooldownScale() != 1.0f) {
                lines.add(Text.translatable("shield.lawnchair.tooltip.cooldown_scale", String.format("%.2f×", comp.disableCooldownScale())).formatted(Formatting.DARK_GRAY));
            }
        });
    }
}
