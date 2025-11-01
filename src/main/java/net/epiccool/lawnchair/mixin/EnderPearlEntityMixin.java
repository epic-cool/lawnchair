package net.epiccool.lawnchair.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void ride(CallbackInfo ci) {
        EnderPearlEntity pearl = (EnderPearlEntity) (Object) this;
        World world = pearl.getEntityWorld();

        if (!world.isClient()) {
            if (pearl.getOwner() instanceof PlayerEntity player) {
                if (!player.hasVehicle()) {
                    player.startRiding(pearl);
                }
            }
        }
    }
}