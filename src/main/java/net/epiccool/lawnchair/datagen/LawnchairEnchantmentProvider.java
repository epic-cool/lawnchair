package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.enchantment.ModEnchantmentEffects;
import net.epiccool.lawnchair.enchantment.effect.BowFrostEnchantmentEffect;
import net.epiccool.lawnchair.enchantment.effect.FrostEnchantmentEffect;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class LawnchairEnchantmentProvider extends FabricDynamicRegistryProvider {
    public LawnchairEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
        System.out.println("REGISTERING ENCHANTS");
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        var enchantmentRegistry = registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        RegistryEntry<Enchantment> fireAspect = enchantmentRegistry.getOrThrow(Enchantments.FIRE_ASPECT);
        RegistryEntry<Enchantment> flame = enchantmentRegistry.getOrThrow(Enchantments.FLAME);
        RegistryEntryList<Enchantment> frostExclusive = RegistryEntryList.of(fireAspect);
        RegistryEntryList<Enchantment> bowFrostExclusive = RegistryEntryList.of(flame);

        register(entries, ModEnchantmentEffects.FROST, Enchantment.builder(
                                Enchantment.definition(
                                        registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                        // this is the "weight" or probability of our enchantment showing up in the table
                                        3,
                                        // the maximum level of the enchantment
                                        2,
                                        // base cost for level 1 of the enchantment, and min levels required for something higher
                                        Enchantment.leveledCost(1, 10),
                                        // same fields as above but for max cost
                                        Enchantment.leveledCost(1, 30),
                                        // anvil cost
                                        4,
                                        // valid slots
                                        AttributeModifierSlot.HAND
                                )
                        )
                        .addEffect(
                                // enchantment occurs POST_ATTACK
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new FrostEnchantmentEffect(
                                        EnchantmentLevelBasedValue.linear(4.0f, 4.0f) // duration scales linearly per level
                                )
                        )
                .exclusiveSet(frostExclusive)
        );

        register(entries, ModEnchantmentEffects.BOW_FROST, Enchantment.builder(
                                Enchantment.definition(
                                        registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                        // this is the "weight" or probability of our enchantment showing up in the table
                                        2,
                                        // the maximum level of the enchantment
                                        1,
                                        // base cost for level 1 of the enchantment, and min levels required for something higher
                                        Enchantment.leveledCost(1, 20),
                                        // same fields as above but for max cost
                                        Enchantment.leveledCost(1, 50),
                                        // anvil cost
                                        4,
                                        // valid slots
                                        AttributeModifierSlot.MAINHAND
                                )
                        )
                .addEffect(
                        EnchantmentEffectComponentTypes.PROJECTILE_SPAWNED,
                        new BowFrostEnchantmentEffect(
                                EnchantmentLevelBasedValue.constant(100.0f)))
                .exclusiveSet(bowFrostExclusive)
        );
    }

    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "LawnchairEnchantmentGenerator";
    }
}
