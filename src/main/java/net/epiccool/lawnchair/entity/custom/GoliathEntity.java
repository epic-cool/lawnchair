package net.epiccool.lawnchair.entity.custom;

import net.epiccool.lawnchair.util.UnnamedHelper;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GoliathEntity extends SpiderEntity {
    public GoliathEntity(EntityType<? extends GoliathEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
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
        injectEffects(world, difficulty);

        if (world.getDifficulty() == Difficulty.HARD) {
            Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE)).setBaseValue(12.0);
        } else {
            Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE)).setBaseValue(8.0);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    private void injectEffects(ServerWorldAccess world, LocalDifficulty difficulty) {
        UnnamedHelper.injectEffects(world, difficulty, this);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getPhototaxisFavor(pos);
    }
}
