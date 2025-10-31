package net.epiccool.lawnchair.block.custom.gravity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ColorCode;

public class SandSlabBlock extends ColoredFallingSlabBlock {
    public static final MapCodec<SandSlabBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(ColorCode.CODEC.fieldOf("falling_dust_color").forGetter(block -> block.color), createSettingsCodec()).apply(instance, SandSlabBlock::new));
    public MapCodec<SandSlabBlock> getCodec() {
        return CODEC;
    }

    public SandSlabBlock(ColorCode color, Settings settings) {
        super(color, settings);
    }
}
