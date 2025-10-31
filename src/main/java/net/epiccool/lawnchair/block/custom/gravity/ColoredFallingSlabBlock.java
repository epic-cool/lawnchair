package net.epiccool.lawnchair.block.custom.gravity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.ColorCode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ColoredFallingSlabBlock extends FallingSlabBlock {
    public static final MapCodec<ColoredFallingSlabBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(ColorCode.CODEC.fieldOf("falling_dust_color").forGetter(block -> block.color), createSettingsCodec()).apply(instance, ColoredFallingSlabBlock::new));
    protected final ColorCode color;

    public ColoredFallingSlabBlock(ColorCode color, AbstractBlock.Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public MapCodec<? extends FallingSlabBlock> getCodec() {
        return CODEC;
    }

    @Override
    public int getColor(BlockState var1, BlockView var2, BlockPos var3) {
        return this.color.rgba();
    }
}
