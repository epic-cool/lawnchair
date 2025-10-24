package net.epiccool.lawnchair.util;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface ModDamageTypes {
    RegistryKey<DamageType> RAN_OVER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Lawnchair.MODID, "ran_over"));

     static void bootstrap(Registerable<DamageType> damageTypeRegisterable) {
        damageTypeRegisterable.register(RAN_OVER, new DamageType("ran_over", 0.1f));
    }
}
