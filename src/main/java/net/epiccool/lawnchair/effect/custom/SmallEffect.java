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

public class SmallEffect extends StatusEffect {
    private final Map<AttributeContainer, LivingEntity> map = new WeakHashMap<>();

    public SmallEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xD031C9);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);

        var scale = entity.getAttributeInstance(EntityAttributes.SCALE);

        if (scale != null) {
            scale.setBaseValue(0.1);
        }

        map.put(entity.getAttributes(), entity);
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);

        LivingEntity entity = map.remove(attributeContainer);
        resetScale(entity);
    }

    @Override
    public void onEntityRemoval(ServerWorld world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onEntityRemoval(world, entity, amplifier, reason);

        resetScale(entity);
    }

    private void resetScale(LivingEntity entity) {
        var scale = entity.getAttributeInstance(EntityAttributes.SCALE);

        if (scale != null) {
            scale.setBaseValue(1.0);
        }
    }
}
