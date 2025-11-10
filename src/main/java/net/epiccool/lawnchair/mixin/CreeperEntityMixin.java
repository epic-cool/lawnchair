package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.util.ModGameRules;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
    @Shadow private int explosionRadius;
    @Shadow private void spawnEffectsCloud() {}

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void explode1(CallbackInfo ci) {
        CreeperEntity creeper = (CreeperEntity) (Object) this;

        if (!(creeper.getEntityWorld() instanceof ServerWorld world)) {
            return;
        }

        boolean allowExplosions = world.getGameRules()
                .getBoolean(ModGameRules.CREEPER_EXPLOSIONS);

        boolean silkTouch = world.getGameRules()
                .getBoolean(ModGameRules.SILKY_CREEPERS);

        if (!allowExplosions) {
            world.createExplosion(
                    creeper,
                    creeper.getX(), creeper.getY(), creeper.getZ(),
                    0.0f,
                    World.ExplosionSourceType.MOB
            );

            creeper.discard();
            ci.cancel();
        }

        //todo: fix
        if (silkTouch) {
            float radius = explosionRadius * (creeper.isCharged() ? 2.0f : 1.0f);
            double ex = creeper.getX();
            double ey = creeper.getY();
            double ez = creeper.getZ();

            world.createExplosion(creeper, ex, ey, ez, radius, false, World.ExplosionSourceType.MOB);

            BlockPos.Mutable pos = new BlockPos.Mutable();
            int r = (int) Math.ceil(radius);

            for (int dx = -r; dx <= r; dx++) {
                for (int dy = -r; dy <= r; dy++) {
                    for (int dz = -r; dz <= r; dz++) {
                        pos.set(creeper.getBlockPos().getX() + dx, creeper.getBlockPos().getY() + dy, creeper.getBlockPos().getZ() + dz);

                        double distanceSq = (dx + 0.5) * (dx + 0.5) +
                                (dy + 0.5) * (dy + 0.5) +
                                (dz + 0.5) * (dz + 0.5);

                        if (distanceSq > radius * radius) continue;

                        BlockState state = world.getBlockState(pos);
                        if (state.isAir()) continue;

                        float resistance = state.getHardness(world, pos);
                        if (resistance > 500.0f) {
                            Block.dropStack(world, pos, new ItemStack(state.getBlock()));
                            world.removeBlock(pos, false);
                        }
                    }
                }
            }

            spawnEffectsCloud();
            creeper.remove(Entity.RemovalReason.KILLED);
            creeper.discard();
            ci.cancel();
        }
    }
}
