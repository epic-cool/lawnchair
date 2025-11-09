package net.epiccool.lawnchair.util;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> RAN_OVER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Lawnchair.MODID, "ran_over"));
}
