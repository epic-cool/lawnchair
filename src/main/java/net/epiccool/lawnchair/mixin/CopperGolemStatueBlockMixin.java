package net.epiccool.lawnchair.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CopperGolemStatueBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CopperGolemStatueBlock.class)
public class CopperGolemStatueBlockMixin {
    @Shadow
    public static final EnumProperty<CopperGolemStatueBlock.Pose> POSE = Properties.COPPER_GOLEM_POSE;

    /**
     * @author epiccool
     * @reason fixes copper golem's poses being changeable in adventure mode
     */
    @Overwrite
    public void changePose(World world, BlockState state, BlockPos pos, PlayerEntity player) {
        if (player.getAbilities().allowModifyWorld) {
            world.playSound(null, pos, SoundEvents.ENTITY_COPPER_GOLEM_BECOME_STATUE, SoundCategory.BLOCKS);
            world.setBlockState(pos, (BlockState) state.with(POSE, state.get(POSE).getNext()), Block.NOTIFY_ALL);
            world.emitGameEvent((Entity) player, GameEvent.BLOCK_CHANGE, pos);
        }
    }
}
