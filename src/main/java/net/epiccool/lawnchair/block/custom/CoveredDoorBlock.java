package net.epiccool.lawnchair.block.custom;

import net.epiccool.lawnchair.block.ModBlocks;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
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

public class CoveredDoorBlock extends DoorBlock {
    public CoveredDoorBlock(BlockSetType type, Settings settings) {
        super(type, settings);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Direction facing = state.get(DoorBlock.FACING);
        DoubleBlockHalf half = state.get(DoorBlock.HALF);
        DoorHinge hinge = state.get(DoorBlock.HINGE);
        Boolean open = state.get(DoorBlock.OPEN);
        Boolean powered = state.get(DoorBlock.POWERED);

        if (stack.isOf(Items.WHITE_WOOL) && player.getAbilities().allowModifyWorld) {
            if (state.isOf(ModBlocks.UNCOVERED_BIRCH_DOOR)) {
                world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                stack.decrementUnlessCreative(1, player);
                world.setBlockState(pos, Blocks.BIRCH_DOOR.getDefaultState().with(DoorBlock.FACING, facing).with(DoorBlock.HALF, half).with(DoorBlock.HINGE, hinge).with(DoorBlock.OPEN, open).with(DoorBlock.POWERED, powered), 11);
//                world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.WHITE_WOOL)));
                return ActionResult.SUCCESS;
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!this.getBlockSetType().canOpenByHand()) {
            return ActionResult.PASS;
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
