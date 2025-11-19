package net.epiccool.lawnchair.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ScorchedDirtBlock extends Block {
    public ScorchedDirtBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (!world.isClient()) {
            world.scheduleBlockTick(pos, this, 20);
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isRaining() && world.hasRain(pos.up()) && random.nextFloat() < 0.2f) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } else {
            world.scheduleBlockTick(pos, this, 20 + random.nextInt(20));
        }
    }

    @Override
    public boolean shouldDropItemsOnExplosion(Explosion explosion) {
        return false;
    }
}
