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

public class StrengthEffect extends StatusEffect {
    private final Map<AttributeContainer, LivingEntity> map = new WeakHashMap<>();

    public StrengthEffect() {
        super(StatusEffectCategory.BENEFICIAL, 16762624);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);

        var knockback = entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK);
        var damage = entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);

        if (knockback != null) {
            knockback.setBaseValue(3.0);
        }

        if (damage != null) {
            damage.setBaseValue(3.0);
        }

        map.put(entity.getAttributes(), entity);
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);

        LivingEntity entity = map.remove(attributeContainer);
        resetKnockbackAndDamage(entity);
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onEntityRemoval(world, entity, amplifier, reason);

        resetKnockbackAndDamage(entity);
    }

    private void resetKnockbackAndDamage(LivingEntity entity) {
        var knockback = entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK);
        var damage = entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);

        if (knockback != null) {
            knockback.setBaseValue(1.0);
        }

        if (damage != null) {
            damage.setBaseValue(1.0);
        }
    }
}
