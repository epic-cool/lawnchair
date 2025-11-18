package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
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

@Mixin(DoorBlock.class)
public class DoorBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    protected void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        Direction facing = state.get(DoorBlock.FACING);
        DoubleBlockHalf half = state.get(DoorBlock.HALF);
        DoorHinge hinge = state.get(DoorBlock.HINGE);
        Boolean open = state.get(DoorBlock.OPEN);
        Boolean powered = state.get(DoorBlock.POWERED);
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        if (stack.isOf(Items.SHEARS) && player.getAbilities().allowModifyWorld) {
            if (state.isOf(Blocks.BIRCH_DOOR)) {
                world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                stack.damage(1, player);
                world.setBlockState(pos, ModBlocks.UNCOVERED_BIRCH_DOOR.getDefaultState().with(DoorBlock.FACING, facing).with(DoorBlock.HALF, half).with(DoorBlock.HINGE, hinge).with(DoorBlock.OPEN, open).with(DoorBlock.POWERED, powered), 11);
                world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.WHITE_WOOL)));

                cir.setReturnValue(ActionResult.SUCCESS);
                cir.cancel();
            }
        }
    }
}
