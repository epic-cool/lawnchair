package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.util.ModGameRules;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @Shadow
    private int foodLevel;
    @Shadow
    private float saturationLevel;
    @Shadow
    private float exhaustion;
    @Shadow
    private int foodTickTimer;

    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void stopNaturalRegenButKeepHunger(ServerPlayerEntity player, CallbackInfo ci) {
        if (player.getEntityWorld().getGameRules().getBoolean(ModGameRules.NATURAL_EXHAUSTION)) {
            HungerManager self = (HungerManager)(Object)this;

            Difficulty difficulty = player.getEntityWorld().getDifficulty();

            if (this.exhaustion > 4.0F) {
                this.exhaustion -= 4.0F;

                if (this.saturationLevel > 0.0F) {
                    this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
                } else if (difficulty != Difficulty.PEACEFUL) {
                    this.foodLevel = Math.max(this.foodLevel - 1, 0);
                }
            }

            if (this.foodLevel <= 0) {
                this.foodTickTimer++;

                if (this.foodTickTimer >= 80) {
                    if (player.getHealth() > 10.0F
                            || difficulty == Difficulty.HARD
                            || (player.getHealth() > 1.0F && difficulty == Difficulty.NORMAL)) {
                        player.damage(player.getEntityWorld(), player.getDamageSources().starve(), 1.0F);
                    }
                    this.foodTickTimer = 0;
                }
            } else {
                this.foodTickTimer = 0;
            }

            ci.cancel();
        }
    }
}