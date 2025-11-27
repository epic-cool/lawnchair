package net.epiccool.lawnchair.entity.goal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.EnumSet;

public class SwimAroundGoal1 extends Goal {
    private final PathAwareEntity pathAwareEntity;
    private final double speed;
    private int timer;

    public SwimAroundGoal1(PathAwareEntity pathAwareEntity, double speed) {
        this.pathAwareEntity = pathAwareEntity;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        return pathAwareEntity.isPartlyTouchingWater();
    }

    @Override
    public boolean shouldContinue() {
        return pathAwareEntity.isPartlyTouchingWater();
    }

    @Override
    public void tick() {
        if (--timer <= 0) {
            timer = 80;

            Vec3d target = getRandomWaterPosition();

            if (target != null) {
                pathAwareEntity.getNavigation().startMovingTo(target.x, target.y, target.z, speed);
            }
        }
    }

    private Vec3d getRandomWaterPosition() {
        Random random = pathAwareEntity.getRandom();
        BlockPos origin = pathAwareEntity.getBlockPos();

        for (int i = 0; i < 25; i++) {
            int x = origin.getX() + random.nextInt(25);
//            int y = origin.getY() + random.nextInt(4) - 2;
            int z = origin.getZ() + random.nextInt(25);
            BlockPos randomPos = new BlockPos(x, origin.getY(), z);

            if (pathAwareEntity.getEntityWorld().getBlockState(randomPos).getBlock() == Blocks.WATER) {
                return Vec3d.ofCenter(randomPos);
            }
        }
        return null;
    }
}