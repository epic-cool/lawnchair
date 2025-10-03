//package net.epiccool.lawnchair.effect.custom;
//
//import net.epiccool.lawnchair.effect.ModEffects;
//import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.world.World;
//
//import java.util.Map;
//import java.util.WeakHashMap;
//
//public class StickyEffectListener {
//
//    private static final Map<PlayerEntity, Boolean> wasTouchingWall = new WeakHashMap<>();
//
//    public static void Initialize() {
//        ServerTickEvents.END_SERVER_TICK.register(server -> {
//            for (ServerWorld world : server.getWorlds()) {
//                for (PlayerEntity player : world.getPlayers()) {
//                    tickPlayer(player);
//                }
//            }
//        });
//    }
//
//    private static void tickPlayer(PlayerEntity player) {
//        boolean touchingWall = isTouchingWall(player);
//        boolean previouslyTouching = wasTouchingWall.getOrDefault(player, false);
//        boolean hasEffect = player.hasStatusEffect(ModEffects.STICKY);
//
//        if (!hasEffect || (!touchingWall && previouslyTouching)) {
//            resetPlayer(player);
//        }
//
//        wasTouchingWall.put(player, touchingWall);
//
//        if (hasEffect) {
//            player.setNoGravity(true);
//        }
//    }
//
//    public static void resetPlayer(LivingEntity entity) {
//        entity.setNoGravity(false);
//
//        Vec3d vel = entity.getVelocity();
//        entity.setVelocity(vel.x, vel.y, vel.z);
//        entity.velocityModified = true;
//    }
//
//
//    public static boolean isTouchingWall(PlayerEntity player) {
//        World world = player.getEntityWorld();
//        double checkDistance = 0.01;
//        var box = player.getBoundingBox();
//
//        return world.getBlockCollisions(player, box.offset(checkDistance, 0, 0)).iterator().hasNext()
//                || world.getBlockCollisions(player, box.offset(-checkDistance, 0, 0)).iterator().hasNext()
//                || world.getBlockCollisions(player, box.offset(0, 0, checkDistance)).iterator().hasNext()
//                || world.getBlockCollisions(player, box.offset(0, 0, -checkDistance)).iterator().hasNext();
//    }
//}
