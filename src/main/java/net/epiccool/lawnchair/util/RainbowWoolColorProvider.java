package net.epiccool.lawnchair.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class RainbowWoolColorProvider implements BlockColorProvider {
    private static final float SPEED = 0.05f;

    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        if (world == null || pos == null) {
            return Color.BLUE.getRGB();
        }

        long time = 0;
        if (world instanceof ClientWorld world1) {
            time = world1.getTime();
        }

        double animatedValue = (time + pos.asLong() * 0.01) * SPEED;
        float hue = (float) ((animatedValue % 360) / 360.0);

        return Color.HSBtoRGB(hue, 1.0f, 1.0f);
    }
}
