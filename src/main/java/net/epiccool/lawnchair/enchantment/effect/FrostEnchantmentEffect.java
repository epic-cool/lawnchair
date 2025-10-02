package net.epiccool.lawnchair.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashSet;
import java.util.Set;

public record FrostEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    //I'm just gonna not let this work for the time-being
    public static final MapCodec<FrostEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(FrostEnchantmentEffect::amount)
            ).apply(instance, FrostEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof PlayerEntity player) {
                int freezeTicks = (int) this.amount.getValue(level) * 80;
                victim.setFrozenTicks(freezeTicks);
                addFrozenEntity(victim);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }

    private static final Set<LivingEntity> frozenEntities = new HashSet<>();

    public static void addFrozenEntity(LivingEntity entity) {
        frozenEntities.add(entity);
    }

    public static void registerTick() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (!(world instanceof ServerWorld serverWorld)) return;

            frozenEntities.removeIf(entity -> !entity.isAlive() || entity.getFrozenTicks() <= 0);

            for (LivingEntity entity : frozenEntities) {
                serverWorld.spawnParticles(
                        ParticleTypes.ITEM_SNOWBALL,
                        entity.getX(),
                        entity.getY() + 1,
                        entity.getZ(),
                        10,
                        0.5, 0.5, 0.5,
                        0.0
                );
            }
        });
    }

}
