package net.epiccool.lawnchair.block.custom;

import com.mojang.serialization.MapCodec;
import net.epiccool.lawnchair.block.ModBlockEntities;
import net.epiccool.lawnchair.block.entity.FluorescentLightBlockEntity;
import net.epiccool.lawnchair.sound.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FluorescentLightBlock extends BlockWithEntity {
    public static final MapCodec<FluorescentLightBlock> CODEC = FluorescentLightBlock.createCodec(FluorescentLightBlock::new);
    public static final BooleanProperty ON = BooleanProperty.of("on");

    public FluorescentLightBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(ON, true));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            if (!world.isClient() && player.getAbilities().allowModifyWorld) {
                boolean stateNow = state.get(ON);
                BlockState newState = state.with(ON, !stateNow);
                world.setBlockState(hit.getBlockPos(), newState);

                if (stateNow && ModSoundEvents.BLOCK_FLUORESCENTLIGHT_TURNOFF != null) {
                    world.playSound(null, hit.getBlockPos(), ModSoundEvents.BLOCK_FLUORESCENTLIGHT_TURNOFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
            }
            player.swingHand(Hand.MAIN_HAND);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FluorescentLightBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.FLUORESCENT_LIGHT,
                world.isClient() ? (w, pos, s, be) -> FluorescentLightBlockEntity.clientTick(w, pos, be) : null
        );
    }
}
