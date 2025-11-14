package net.epiccool.lawnchair.effect;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.effect.custom.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
//    public static final RegistryEntry<StatusEffect> STICKY =
//            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "sticky"), new StickyEffect());

    public static final RegistryEntry<StatusEffect> SMALL =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "small"), new SmallEffect());

    public static final RegistryEntry<StatusEffect> MUSCLE =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "muscle"), new MuscleEffect());

    public static final RegistryEntry<StatusEffect> LIGHT =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "light"), new LightEffect());

    public static final RegistryEntry<StatusEffect> BIG =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "big"), new BigEffect());

    public static final RegistryEntry<StatusEffect> GLOOM =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "gloom"), new GloomEffect());


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, name), statusEffect);
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Effects for " + Lawnchair.MODID);
    }
}
