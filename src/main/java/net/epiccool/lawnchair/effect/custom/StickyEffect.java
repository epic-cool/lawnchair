package net.epiccool.lawnchair.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class StickyEffect extends StatusEffect {

    public StickyEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x7dba7e);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!(entity instanceof PlayerEntity player)) return false;

        player.fallDistance = 0f;

        if (!StickyEffectListener.isTouchingWall(player)) return true;

        player.setNoGravity(true);

        if (player.isSneaking()) {
            player.setVelocity(Vec3d.ZERO);
        } else {
            handleWallMotion(player, amplifier);
        }

        player.velocityModified = true;
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    private void handleWallMotion(PlayerEntity player, int amplifier) {
        Vec3d v = player.getVelocity();
        double baseSpeed = 0.06D;
        double speed = baseSpeed * (1 + amplifier + 0.5);

        double verticalMotion = v.y;

        if (player.forwardSpeed >= 0) {
            verticalMotion = speed;
        } else if (player.getPitch() > 50) {
            verticalMotion = -speed;
        } else {
            verticalMotion = Math.max(Math.min(v.y, speed), -speed);
        }

        player.setVelocity(v.x, verticalMotion, v.z);
        player.velocityModified = true;
    }
}
