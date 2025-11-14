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

public class LightEffect extends StatusEffect {
    private final Map<AttributeContainer, LivingEntity> map = new WeakHashMap<>();
    private final Map<LivingEntity, Double> map1 = new WeakHashMap<>();

    public LightEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xDCD9C0);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);

        var fallDamageMultiplier = entity.getAttributeInstance(EntityAttributes.FALL_DAMAGE_MULTIPLIER);

        map.put(entity.getAttributes(), entity);
        if (fallDamageMultiplier != null) {
            double fallDamageMultiplier1 = fallDamageMultiplier.getBaseValue();
            double fallDamageMultiplier2 = fallDamageMultiplier1 * 0;
            map1.putIfAbsent(entity, fallDamageMultiplier.getBaseValue());
            fallDamageMultiplier.setBaseValue(fallDamageMultiplier2);
        }
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);

        LivingEntity entity = map.remove(attributeContainer);
        resetFallDamageMultiplier(entity);
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onEntityRemoval(world, entity, amplifier, reason);

        resetFallDamageMultiplier(entity);
    }

    private void resetFallDamageMultiplier(LivingEntity entity) {
        var fallDamageMultiplier = entity.getAttributeInstance(EntityAttributes.FALL_DAMAGE_MULTIPLIER);

        if (fallDamageMultiplier != null) {
            Double fallDamageMultiplier1 = map1.remove(entity);
            if (fallDamageMultiplier1 != null) {
                fallDamageMultiplier.setBaseValue(fallDamageMultiplier1);
            }
        }
    }
}
