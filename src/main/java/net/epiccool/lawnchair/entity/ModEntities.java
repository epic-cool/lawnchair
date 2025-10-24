package net.epiccool.lawnchair.entity;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.entity.custom.DuckEntity;
import net.epiccool.lawnchair.entity.custom.GoliathEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntities {
    private static final RegistryKey<EntityType<?>> GOLIATH_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Lawnchair.MODID, "goliath"));
    private static final RegistryKey<EntityType<?>> DUCK_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Lawnchair.MODID, "duck"));

    public static final EntityType<GoliathEntity> GOLIATH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Lawnchair.MODID, "goliath"),
            EntityType.Builder.create(GoliathEntity::new, SpawnGroup.MONSTER)
                    .dimensions(5.6f, 3.6f)
                    .notAllowedInPeaceful()
                    .maxTrackingRange(512)
                    .build(GOLIATH_KEY));

    public static final EntityType<DuckEntity> DUCK = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Lawnchair.MODID, "duck"),
            EntityType.Builder.create(DuckEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.4f, 0.7f)
                    .eyeHeight(0.644f)
                    .maxTrackingRange(10)
                    .build(DUCK_KEY));

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Entities for " + Lawnchair.MODID);

        //Attributes
        FabricDefaultAttributeRegistry.register(ModEntities.GOLIATH, GoliathEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DUCK, DuckEntity.createAttributes());

        //Spawns
        //Duck
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.RIVER),
                SpawnGroup.CREATURE,
                ModEntities.DUCK,
                30,
                3,
                7
        );
    }
}
