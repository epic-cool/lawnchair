package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.util.UnnamedHelper;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Inject(method = "initialize", at = @At("RETURN"))
    private void injectEffects(
            ServerWorldAccess world,
            LocalDifficulty difficulty,
            SpawnReason spawnReason,
            @Nullable EntityData entityData,
            CallbackInfoReturnable<EntityData> cir
    ) {
        UnnamedHelper.injectEffects(world, difficulty, (ZombieEntity)(Object)this);
    }
}
