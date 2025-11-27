package net.epiccool.lawnchair.block.custom.debug;

import net.epiccool.lawnchair.util.ModBlockSetTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableButtonBlock2 extends ButtonBlock2 implements Oxidizable {
    private final OxidationLevel oxidationLevel;

    public OxidizableButtonBlock2(OxidationLevel oxidationLevel, ModBlockSetTypes blockSetType, int pressTicks, Settings settings) {
        super(blockSetType, pressTicks, settings.ticksRandomly());
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }
}
