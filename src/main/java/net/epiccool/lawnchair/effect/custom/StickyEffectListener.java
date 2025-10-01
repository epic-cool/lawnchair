package net.epiccool.lawnchair.effect.custom;

import net.epiccool.lawnchair.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Map;
import java.util.WeakHashMap;

public class StickyEffectListener {

    private static final Map<PlayerEntity, Boolean> wasTouchingWall = new WeakHashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                for (PlayerEntity player : world.getPlayers()) {
                    tickPlayer(player);
                }
            }
        });
    }

    private static void tickPlayer(PlayerEntity player) {
        boolean touchingWall = isTouchingWall(player);
        boolean previouslyTouching = wasTouchingWall.getOrDefault(player, false);

        if (previouslyTouching && !touchingWall) {
            resetPlayer(player);
            //System.out.println("[StickyEffect] Player " + player.getName().getString() + " left wall, resetting");
        }

        boolean hasEffect = player.hasStatusEffect(ModEffects.STICKY);
        if (!hasEffect && previouslyTouching) {
            resetPlayer(player);
            //System.out.println("[StickyEffect] Effect expired for " + player.getName().getString() + ", reset gravity/velocity");
        }

        wasTouchingWall.put(player, touchingWall);
    }

    public static void resetPlayer(LivingEntity entity) {
        entity.setNoGravity(false);
        Vec3d v = entity.getVelocity();
        entity.setVelocity(v.x, -0.2, v.z);
        entity.velocityModified = true;

        //System.out.println("[StickyEffect] Player " + entity.getName().getString() + " gravity reset, velocity applied: " + entity.getVelocity());
    }

    public static boolean isTouchingWall(PlayerEntity player) {
        World world = player.getEntityWorld();
        Box box = player.getBoundingBox();
        double checkDistance = 0.01;

        Box checkBoxXPlus = box.offset(checkDistance, 0, 0);
        Box checkBoxXMinus = box.offset(-checkDistance, 0, 0);
        Box checkBoxZPlus = box.offset(0, 0, checkDistance);
        Box checkBoxZMinus = box.offset(0, 0, -checkDistance);

        return world.getBlockCollisions(player, checkBoxXPlus).iterator().hasNext()
                || world.getBlockCollisions(player, checkBoxXMinus).iterator().hasNext()
                || world.getBlockCollisions(player, checkBoxZPlus).iterator().hasNext()
                || world.getBlockCollisions(player, checkBoxZMinus).iterator().hasNext();
    }
}
