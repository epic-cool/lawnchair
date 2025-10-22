package net.epiccool.lawnchair.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Inject(method = "initialize", at = @At("RETURN"))
    private void injectEffects(
            ServerWorldAccess world,
            LocalDifficulty difficulty,
            SpawnReason spawnReason,
            @Nullable EntityData entityData,
            CallbackInfoReturnable<EntityData> cir
    ) {
        ZombieEntity self = (ZombieEntity)(Object)this;
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
}
