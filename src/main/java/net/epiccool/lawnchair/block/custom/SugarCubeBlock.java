package net.epiccool.lawnchair.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

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
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        int topY = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos).getY();
        boolean exposed = topY <= pos.getY();

        if (world.isRaining() && exposed) {
            world.breakBlock(pos, false);
        }

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

        world.scheduleBlockTick(pos, this, 20);
    }
}
