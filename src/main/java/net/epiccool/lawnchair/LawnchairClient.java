package net.epiccool.lawnchair;

import net.epiccool.lawnchair.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.render.BlockRenderLayer;

public class LawnchairClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.STEEL_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.UNLIT_LANTERN, BlockRenderLayer.CUTOUT);

//        HandledScreens.register(ModScreenHandlers.ALLOY_MIXER_SCREEN_HANDLER, AlloyMixerScreen::new);

        //Rainbow wool
        BlockColorProvider rainbowProvider = (state, world, pos, tintIndex) -> {
            if (world == null || pos == null) return 0xFFFFFF;

            var client = MinecraftClient.getInstance();
            if (client.world == null) return 0xFFFFFF;

            float tickDelta = client.getRenderTickCounter().getTickProgress(false);
            double time = (client.world.getTime() + tickDelta);

            double speed = 100.0;
            double baseHue = (time / speed) % 1.0;

            long seed = pos.asLong();
            double offset = ((seed * 0.0001) % 1.0 + 1.0) % 1.0;
            double hue = (baseHue + offset) % 1.0;

            int rgb = java.awt.Color.HSBtoRGB((float) hue, 1.0f, 1.0f);

            return rgb & 0xFFFFFF;
        };


        ColorProviderRegistry.BLOCK.register(rainbowProvider, ModBlocks.RAINBOW_WOOL);
    }
}
