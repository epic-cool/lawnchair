package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.block.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "neighborUpdate", at = @At("HEAD"))
    private void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, WireOrientation wireOrientation, boolean notify, CallbackInfo ci) {
        if (!world.isClient()) {
            if (state.isOf(Blocks.GRASS_BLOCK) || state.isOf(Blocks.DIRT)) {
                if (sourceBlock == Blocks.FIRE) {
                    world.setBlockState(pos, ModBlocks.SCORCHED_DIRT.getDefaultState());
                }
            }
        }
    }
}