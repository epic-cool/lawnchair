package net.epiccool.lawnchair.block.entity;

import net.epiccool.lawnchair.block.ModBlockEntities;
import net.epiccool.lawnchair.block.custom.FluorescentLightBlock;
import net.epiccool.lawnchair.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class FluorescentLightBlockEntity extends BlockEntity {
    private boolean playing = false;
    private PositionedSoundInstance soundInstance;

    public FluorescentLightBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUORESCENT_LIGHT, pos, state);
    }

    private static void tick(FluorescentLightBlockEntity blockEntity) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (!blockEntity.getCachedState().get(FluorescentLightBlock.ON)) {
            if (blockEntity.playing && blockEntity.soundInstance != null) {
                client.getSoundManager().stop(blockEntity.soundInstance);
                blockEntity.playing = false;
                blockEntity.soundInstance = null;
            }
            return;
        }

        if (!blockEntity.playing) {
            PositionedSoundInstance sound = new PositionedSoundInstance(
                    ModSoundEvents.BLOCK_FLUORESCENTLIGHT_IDLE.id(), SoundCategory.BLOCKS, 1.0f, 1.0f,
                    SoundInstance.createRandom(), true, 0, SoundInstance.AttenuationType.LINEAR,
                    blockEntity.getPos().getX(), blockEntity.getPos().getY(), blockEntity.getPos().getZ(), false);

            MinecraftClient.getInstance().getSoundManager().play(sound);
            blockEntity.soundInstance = sound;
            blockEntity.playing = true;
        }
    }

    public static void clientTick(FluorescentLightBlockEntity blockEntity) {
        FluorescentLightBlockEntity.tick(blockEntity);
    }

    @Override
    public void markRemoved() {
        super.markRemoved();
        if (playing && soundInstance != null) {
            MinecraftClient.getInstance().getSoundManager().stop(soundInstance);
            playing = false;
            soundInstance = null;
        }
    }
}