package net.epiccool.lawnchair.entity.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;

public class GrassStompGoal extends Goal {
    private final PigEntity pig;
    private int cooldown = 0;
    private int jump = 0;
    private boolean shouldBeJumping = false;

    public GrassStompGoal(PigEntity pig) {
        this.pig = pig;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    @Override
    public boolean canStart() {
        return cooldown-- <= 0 && pig.isOnGround();
    }

    @Override
    public boolean shouldContinue() {
        return shouldBeJumping && jump > 0;
    }

    @Override
    public void start() {
        shouldBeJumping = true;
        jump = 40 + pig.getRandom().nextInt(20);
        pig.getNavigation().stop();
        pig.setVelocity(0, 0, 0);
    }

    @Override
    public void tick() {
        if (shouldBeJumping) {
            if (jump % 10 == 0 && pig.isOnGround()) {
                pig.getJumpControl().setActive();
                pig.playSound(SoundEvents.ENTITY_PIG_AMBIENT, 1.0f, 1.0f);
            }

            jump--;

            if (jump <= 0) {
                shouldBeJumping = false;
                convertBlock();
                cooldown = 100 + pig.getRandom().nextInt(80);
            }
        }
    }

    private void convertBlock() {
        World world = pig.getEntityWorld();
        BlockPos pos = pig.getBlockPos().down();
        BlockState state = world.getBlockState(pos);

        if (!world.isClient()) {
            if (world instanceof ServerWorld) {
                if (state.isOf(Blocks.GRASS_BLOCK)) {
                    world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 3);
                    pig.playSound(SoundEvents.BLOCK_GRASS_BREAK, 1.0f, 1.0f);
                } else if (state.isOf(Blocks.DIRT)) {
                    world.setBlockState(pos, Blocks.COARSE_DIRT.getDefaultState(), 3);
                    pig.playSound(SoundEvents.BLOCK_GRASS_BREAK, 1.0f, 1.0f);
                } else if (state.isOf(Blocks.COARSE_DIRT)) {
                    pig.playSound(SoundEvents.BLOCK_GRASS_BREAK, 1.0f, 1.0f);
                }
            }
        }
    }

    @Override
    public void stop() {
        shouldBeJumping = false;
        jump = 0;
    }
}
