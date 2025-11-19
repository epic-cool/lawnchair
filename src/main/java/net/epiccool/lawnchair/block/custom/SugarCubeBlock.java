package net.epiccool.lawnchair.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class SugarCubeBlock extends FallingBlock {
    public static final MapCodec<SugarCubeBlock> CODEC = createCodec(SugarCubeBlock::new);
//todo: make fall
    public SugarCubeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return 0xEAEAEA;
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
        Box attractionArea = new Box(pos).expand(7);
        Box searchArea = new Box(pos).expand(30);
        List<AnimalEntity> animals = world.getEntitiesByClass(AnimalEntity.class, searchArea, a -> true);

        for (AnimalEntity animal : animals) {
            BlockPos animalPos = animal.getBlockPos();

            if (!attractionArea.contains(animalPos)) {
                animal.getNavigation().startMovingTo(pos.getX(), pos.getY(), pos.getZ(), 1.2);
            } else if (animal.getNavigation().isIdle()) {
                double randomX = pos.getX() + Random.create().nextDouble() * 14 - 7;
                double randomY = pos.getY();
                double randomZ = pos.getZ() + Random.create().nextDouble() * 14 - 7;
                animal.getNavigation().startMovingTo(randomX, randomY, randomZ, 1.2);
            }
        }

        if (world.isRaining() && world.hasRain(pos.up()) && random.nextFloat() < 0.5f) {
            world.breakBlock(pos, false);
        } else {
            world.scheduleBlockTick(pos, this, 20 + random.nextInt(20));
        }
    }
}
