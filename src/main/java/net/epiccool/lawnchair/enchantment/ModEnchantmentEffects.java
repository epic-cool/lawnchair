package net.epiccool.lawnchair.enchantment;

import com.mojang.serialization.MapCodec;
import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.enchantment.effect.FrostEnchantmentEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final RegistryKey<Enchantment> FROST = of("frost");
    public static MapCodec<FrostEnchantmentEffect> FROST_EFFECT = register("frost_effect", FrostEnchantmentEffect.CODEC);

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Identifier.of(Lawnchair.MODID, path);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Lawnchair.MODID, id), codec);
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering EnchantmentEffects for" + Lawnchair.MODID);
        FrostEnchantmentEffect.Initialize();
    }
}
