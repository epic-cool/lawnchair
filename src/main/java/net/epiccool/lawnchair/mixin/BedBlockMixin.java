package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void lawnchair$preventExplosion(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (!(world instanceof ServerWorld serverWorld)) {
            return;
        }

        if (world.getRegistryKey() == World.NETHER || world.getRegistryKey() == World.END) {
            boolean allowBedExplosions = serverWorld.getGameRules()
                    .getBoolean(Lawnchair.BED_EXPLOSIONS);

            if (!allowBedExplosions) {
                player.swingHand(Hand.MAIN_HAND);
                player.sendMessage(Text.translatable("block.minecraft.bed.lawnchair.dontexplode"), true);

                cir.setReturnValue(ActionResult.SUCCESS_SERVER);
                cir.cancel();
            }
        }
    }
}
