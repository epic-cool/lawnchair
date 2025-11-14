package net.epiccool.lawnchair.effect.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

import java.util.Map;
import java.util.WeakHashMap;

public class BigEffect extends StatusEffect {
    private final Map<AttributeContainer, LivingEntity> map = new WeakHashMap<>();
    private final Map<LivingEntity, Backup> og = new WeakHashMap<>();

    public BigEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x91D031);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);

        var scale = entity.getAttributeInstance(EntityAttributes.SCALE);
        var blockRange = entity.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE);
        var entityRange = entity.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE);
        var walkSpeed = entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        var jumpHeight = entity.getAttributeInstance(EntityAttributes.JUMP_STRENGTH);

        map.put(entity.getAttributes(), entity);
        Backup backup = og.computeIfAbsent(entity, e -> new Backup());

        if (scale != null) {
            backup.scale = scale.getBaseValue();
            scale.setBaseValue(backup.scale * 10);
        }

        if (entityRange != null) {
            backup.entityRange = entityRange.getBaseValue();
            entityRange.setBaseValue(backup.entityRange * 10);
        }

        if (blockRange != null) {
            backup.blockRange = blockRange.getBaseValue();
            blockRange.setBaseValue(backup.blockRange * 10);
        }

        if (walkSpeed != null) {
            backup.walkSpeed = walkSpeed.getBaseValue();
            walkSpeed.setBaseValue(backup.walkSpeed * 0.75);
        }

        if (jumpHeight != null) {
            backup.jumpHeight = jumpHeight.getBaseValue();
            jumpHeight.setBaseValue(backup.jumpHeight * 10);
        }
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);

        LivingEntity entity = map.remove(attributeContainer);
        resetAttr(entity);
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onEntityRemoval(world, entity, amplifier, reason);

        resetAttr(entity);
    }

    private void resetAttr(LivingEntity entity) {
        Backup backup = og.remove(entity);

        var scale = entity.getAttributeInstance(EntityAttributes.SCALE);
        var blockRange = entity.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE);
        var entityRange = entity.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE);
        var walkSpeed = entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        var jumpHeight = entity.getAttributeInstance(EntityAttributes.JUMP_STRENGTH);

        if (scale != null) scale.setBaseValue(backup.scale);
        if (blockRange != null) blockRange.setBaseValue(backup.blockRange);
        if (entityRange != null) entityRange.setBaseValue(backup.entityRange);
        if (walkSpeed != null) walkSpeed.setBaseValue(backup.walkSpeed);
        if (jumpHeight != null) jumpHeight.setBaseValue(backup.jumpHeight);
    }

    private static class Backup {
        double scale;
        double blockRange;
        double entityRange;
        double walkSpeed;
        double jumpHeight;
    }
}
