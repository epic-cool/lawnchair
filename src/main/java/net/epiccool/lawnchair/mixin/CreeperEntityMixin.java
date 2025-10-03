package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void lawnchair$modifyExplosion(CallbackInfo ci) {
        CreeperEntity creeper = (CreeperEntity) (Object) this;

        if (!(creeper.getEntityWorld() instanceof ServerWorld world)) {
            return;
        }

        boolean allowExplosions = world.getGameRules()
                .getBoolean(Lawnchair.CREEPER_EXPLOSIONS);

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
    }
}
