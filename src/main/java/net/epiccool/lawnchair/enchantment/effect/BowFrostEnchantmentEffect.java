package net.epiccool.lawnchair.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashSet;
import java.util.Set;

public record BowFrostEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<BowFrostEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(BowFrostEnchantmentEffect::amount)
            ).apply(instance, BowFrostEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (!(context.owner() instanceof PlayerEntity player)) return;
        if (!(target instanceof LivingEntity victim)) return;

        DamageSource source = context.owner().getRecentDamageSource();
        if (source != null) {
            Lawnchair.LOGGER.info("not null");
            if (source.getSource() instanceof PersistentProjectileEntity arrow && arrow.getOwner() == player) {
                int freezeTicks = (int) this.amount.getValue(level) * 60;
                int slownessDuration = freezeTicks + 14;
                int slownessLevel = Math.min(3, level);

                victim.setFrozenTicks(freezeTicks);
                victim.setFireTicks(0);
                victim.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, slownessDuration, slownessLevel, false, false, true));
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

    public static void Initialize() {
        FrostEnchantmentEffect.freeze();
    }
}
