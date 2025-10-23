package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {
    private static final Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE =
            state -> state != null && (state.isOf(Blocks.CARVED_PUMPKIN) || state.isOf(Blocks.JACK_O_LANTERN) || state.isOf(ModBlocks.SOUL_JACK_O_LANTERN));

    @Inject(method = "getIronGolemPattern", at = @At("RETURN"), cancellable = true)
    private void replaceIronBlockPattern(CallbackInfoReturnable<BlockPattern> cir) {
        BlockPattern newPattern = BlockPatternBuilder.start()
                .aisle("~^~", "I#I", "~I~")
                .where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE))
                .where('#', pos -> pos.getBlockState().isOf(ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE))
                .where('I', pos -> pos.getBlockState().isOf(Blocks.IRON_BLOCK))
                .where('~', pos -> pos.getBlockState().isAir())
                .build();

        cir.setReturnValue(newPattern);
    }
}
