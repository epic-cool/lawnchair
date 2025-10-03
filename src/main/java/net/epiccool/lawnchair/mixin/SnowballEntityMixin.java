package net.epiccool.lawnchair.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public class SnowballEntityMixin {
    @Inject(method = "onEntityHit", at = @At("TAIL"))
    protected void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        Entity entity = entityHitResult.getEntity();
        SnowballEntity snowball = (SnowballEntity) (Object) this;

        double knockbackStrength = 0.5;

        Vec3d direction = entity.getEntityPos().subtract(snowball.getEntityPos()).normalize();
        entity.addVelocity(direction.x * knockbackStrength, 0.1, direction.z * knockbackStrength);


        entity.setFrozenTicks(250);
    }
}