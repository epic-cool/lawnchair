package net.epiccool.lawnchair.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public record FrostEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<FrostEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(FrostEnchantmentEffect::amount)
            ).apply(instance, FrostEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (target instanceof LivingEntity victim && context.owner() instanceof PlayerEntity) {
            int freezeTicks = (int) this.amount.getValue(level) * 80;
            int slownessDuration = freezeTicks + 15;
            int slownessLevel = Math.min(3, level);
            victim.setFrozenTicks(freezeTicks);
            victim.setFireTicks(0);
            victim.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, slownessDuration, slownessLevel, false, false, true));
            addFrozenEntity(victim);
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

    public static void Initialize() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (!(world instanceof ServerWorld serverWorld)) return;

            Iterator<LivingEntity> iterator = frozenEntities.iterator();
            while (iterator.hasNext()) {
                LivingEntity entity = iterator.next();

                if (!entity.isAlive() || entity.getFrozenTicks() <= 0) {
                    iterator.remove();
                    continue;
                }

                double radius = 0.5;
                for (int i = 0; i < 8; i++) {
                    double offsetX = (serverWorld.random.nextDouble() - 0.5) * radius;
                    double offsetY = serverWorld.random.nextDouble() * 1.5;
                    double offsetZ = (serverWorld.random.nextDouble() - 0.5) * radius;
                    serverWorld.spawnParticles(
                            ParticleTypes.SNOWFLAKE,
                            entity.getX() + offsetX,
                            entity.getY() + offsetY,
                            entity.getZ() + offsetZ,
                            1,
                            0, 0, 0,
                            0.01
                    );
                }
            }
        });
    }
}
