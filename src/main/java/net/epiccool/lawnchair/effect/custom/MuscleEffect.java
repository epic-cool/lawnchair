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

public class MuscleEffect extends StatusEffect {
    private final Map<AttributeContainer, LivingEntity> map = new WeakHashMap<>();
    private final Map<LivingEntity, Backup> og = new WeakHashMap<>();

    public MuscleEffect() {
        super(StatusEffectCategory.BENEFICIAL, 16762624);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);

        var knockback = entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK);
        var damage = entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);

        map.put(entity.getAttributes(), entity);
        Backup backup = og.computeIfAbsent(entity, e -> new Backup());

        if (knockback != null) {
            backup.knockback = knockback.getBaseValue();
            knockback.setBaseValue(backup.knockback * 3.0);
        }

        if (damage != null) {
            backup.damage = damage.getBaseValue();
            damage.setBaseValue(backup.damage * 3.0);
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
        if (entity == null) return;

        Backup backup = og.remove(entity);
        if (backup == null) return;

        var knockback = entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK);
        var damage = entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);

        if (knockback != null) knockback.setBaseValue(backup.knockback);
        if (damage != null) damage.setBaseValue(backup.damage);
    }

    private static class Backup {
        double knockback;
        double damage;
    }
}
