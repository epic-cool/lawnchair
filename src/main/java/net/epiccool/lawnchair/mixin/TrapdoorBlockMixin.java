package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrapdoorBlock.class)
public class TrapdoorBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    protected void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        Boolean open = state.get(TrapdoorBlock.OPEN);
        BlockHalf half = state.get(TrapdoorBlock.HALF);
        Boolean powered = state.get(TrapdoorBlock.POWERED);
        Boolean waterlogged = state.get(TrapdoorBlock.WATERLOGGED);
        Direction facing = state.get(HorizontalFacingBlock.FACING);
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        if (stack.isOf(Items.SHEARS) && player.getAbilities().allowModifyWorld) {
            if (state.isOf(Blocks.BIRCH_TRAPDOOR)) {
                world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                stack.damage(1, player);
                world.setBlockState(pos, ModBlocks.UNCOVERED_BIRCH_TRAPDOOR.getDefaultState().with(TrapdoorBlock.WATERLOGGED, waterlogged).with(TrapdoorBlock.HALF, half).with(TrapdoorBlock.OPEN, open).with(TrapdoorBlock.POWERED, powered).with(HorizontalFacingBlock.FACING, facing), 11);
                world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.WHITE_WOOL)));

                cir.setReturnValue(ActionResult.SUCCESS);
                cir.cancel();
            }
        }
    }
}
