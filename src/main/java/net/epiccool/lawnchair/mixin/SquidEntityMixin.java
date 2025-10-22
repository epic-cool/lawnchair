package net.epiccool.lawnchair.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SquidEntity.class)
public abstract class SquidEntityMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onSpawn(EntityType<? extends SquidEntity> type, World world, CallbackInfo ci) {
        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            if (serverWorld.random.nextInt(1) == 1) { //2010 | 69
                ((SquidEntity)(Object)this).addCommandTag("milkable");
            }
        }
    }
}
