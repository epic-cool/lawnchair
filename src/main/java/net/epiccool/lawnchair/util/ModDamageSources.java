package net.epiccool.lawnchair.util;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.registry.DynamicRegistryManager;

public class ModDamageSources extends DamageSources {
    private final DamageSource ranOver;

    public ModDamageSources(DynamicRegistryManager registryManager) {
        super(registryManager);
        this.ranOver = create(ModDamageTypes.RAN_OVER);
    }

    public DamageSource ranOver() {
        return this.ranOver;
    }
}
