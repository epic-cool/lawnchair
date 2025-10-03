//package net.epiccool.lawnchair.effect.custom;
//
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.effect.StatusEffect;
//import net.minecraft.entity.effect.StatusEffectCategory;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.math.Vec3d;
//
//public class StickyEffect extends StatusEffect {
//
//    public StickyEffect() {
//        super(StatusEffectCategory.NEUTRAL, 0x7dba7e);
//    }
//
//    @Override
//    public boolean canApplyUpdateEffect(int duration, int amplifier) {
//        return true;
//    }
//
//    @Override
//    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
//        if (!(entity instanceof PlayerEntity player)) return false;
//
//        player.fallDistance = 0f;
//
//        player.setNoGravity(true);
//
//        if (StickyEffectListener.isTouchingWall(player)) {
//            if (player.isSneaking()) {
//                player.setVelocity(Vec3d.ZERO);
//            } else {
//                handleWallClimbing(player, amplifier);
//            }
//        } else {
//            Vec3d vel = player.getVelocity();
//            player.setVelocity(0, vel.y * 0.9, 0);
//        }
//
//        player.velocityModified = true;
//        return super.applyUpdateEffect(world, entity, amplifier);
//    }
//
//    private void handleWallClimbing(PlayerEntity player, int amplifier) {
//        Vec3d vel = player.getVelocity();
//
//        double climbSpeed = 0.12D + (0.03D * amplifier);
//        double verticalMotion = vel.y;
//
//        if (player.forwardSpeed >= 0) {
//            verticalMotion = climbSpeed;
//        } else if (player.getPitch() > 50) {
//            verticalMotion = -climbSpeed;
//        } else {
//            verticalMotion = 0;
//        }
//
//        player.setVelocity(0, verticalMotion, 0);
//        player.velocityModified = true;
//    }
//}
