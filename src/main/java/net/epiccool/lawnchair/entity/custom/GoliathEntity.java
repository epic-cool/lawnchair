package net.epiccool.lawnchair.entity.custom;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class GoliathEntity extends SpiderEntity {
    public GoliathEntity(EntityType<? extends GoliathEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createGoliathAttributes() {
        return SpiderEntity.createSpiderAttributes()
                .add(EntityAttributes.MAX_HEALTH, 64.0f)
                .add(EntityAttributes.FALL_DAMAGE_MULTIPLIER, 0.0f)
                .add(EntityAttributes.ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25f);
    }

    @Override
    protected int getExperienceToDrop(ServerWorld world) {
        return super.getExperienceToDrop(world) * 4;
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        injectEffects(world, difficulty, spawnReason, entityData);

        if (world.getDifficulty() == Difficulty.HARD) {
            this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(12.0);
        } else {
            this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(8.0);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    private void injectEffects(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        GoliathEntity self = (GoliathEntity)(Object)this;
        //Ignore warnings
        Random random = world.getRandom();

        if (world.getDifficulty() == Difficulty.HARD
                && random.nextFloat() < 0.1F * difficulty.getClampedLocalDifficulty()) {

            RegistryEntry<StatusEffect> effect = null;
            int i = random.nextInt(100);

            if (i < 40) {
                effect = StatusEffects.SPEED;
            } else if (i < 60) {
                effect = StatusEffects.STRENGTH;
            } else if (i < 80) {
                effect = StatusEffects.REGENERATION;
            } else {
                effect = StatusEffects.INVISIBILITY;
            }

            if (effect != null) {
                self.addStatusEffect(new StatusEffectInstance(effect, -1));
            }
        }
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getPhototaxisFavor(pos);
    }
}
