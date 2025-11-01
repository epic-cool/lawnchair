package net.epiccool.lawnchair.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void rideChicken(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!(entity instanceof ChickenEntity chicken)) return;

        PlayerEntity player = (PlayerEntity) (Object) this;
        var scale = player.getAttributeInstance(EntityAttributes.SCALE);

        if (scale != null) {
            if (scale.getBaseValue() < 1.0f) {
                player.startRiding(chicken);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
