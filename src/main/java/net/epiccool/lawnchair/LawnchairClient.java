package net.epiccool.lawnchair;

import net.epiccool.lawnchair.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;

public class LawnchairClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.STEEL_BARS, BlockRenderLayer.CUTOUT);
    }
}
