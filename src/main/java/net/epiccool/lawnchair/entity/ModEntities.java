package net.epiccool.lawnchair.entity;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.entity.custom.GoliathEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static final RegistryKey<EntityType<?>> GOLIATH_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Lawnchair.MODID, "goliath"));


    public static final EntityType<GoliathEntity> GOLIATH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Lawnchair.MODID, "goliath"),
            EntityType.Builder.create(GoliathEntity::new, SpawnGroup.MONSTER)
                    .dimensions(5.6f, 3.6f)
                    .notAllowedInPeaceful()
                    .maxTrackingRange(512)
                    .build(GOLIATH_KEY));

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Entities for " + Lawnchair.MODID);
    }
}
