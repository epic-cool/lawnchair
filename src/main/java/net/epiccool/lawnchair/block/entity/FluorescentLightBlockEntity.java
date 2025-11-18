package net.epiccool.lawnchair.block.entity;

import net.epiccool.lawnchair.block.ModBlockEntities;
import net.epiccool.lawnchair.block.custom.FluorescentLightBlock;
import net.epiccool.lawnchair.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FluorescentLightBlockEntity extends BlockEntity {
    private boolean wasPlaying = false;
    private double idleTickCounter = 0.0;

    public FluorescentLightBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUORESCENT_LIGHT, pos, state);
    }

    private static final double IDLE_INTERVAL_TICKS = 3.618688 * 20.0;

    private static void tick(World world, BlockPos pos, FluorescentLightBlockEntity blockEntity) {
        boolean isOn = blockEntity.getCachedState().get(FluorescentLightBlock.ON);

        if (!world.isClient()) {
            if (isOn && !blockEntity.wasPlaying) {
                //playSound(world, pos, ModSoundEvents.BLOCK_FLUORESCENTLIGHT_TURNON);
            } else if (!isOn && blockEntity.wasPlaying) {
                playSound(world, pos, ModSoundEvents.BLOCK_FLUORESCENTLIGHT_TURNOFF);
            }
        }


        if (isOn) {
            blockEntity.idleTickCounter += 1.0;

            if (blockEntity.idleTickCounter >= IDLE_INTERVAL_TICKS) {
                world.playSoundClient(pos.getX(), pos.getY(), pos.getZ(), ModSoundEvents.BLOCK_FLUORESCENTLIGHT_IDLE,SoundCategory.BLOCKS, 1.0f, 1.0f, true);
                blockEntity.idleTickCounter = 0.0;
            }
        } else {
            blockEntity.idleTickCounter = 0.0;
        }

        blockEntity.wasPlaying = isOn;
    }

    public static void playSound(World world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    public static void clientTick(World world, BlockPos pos, FluorescentLightBlockEntity blockEntity) {
        tick(world, pos, blockEntity);
    }

    @Override
    public void markRemoved() {
        super.markRemoved();
    }
}