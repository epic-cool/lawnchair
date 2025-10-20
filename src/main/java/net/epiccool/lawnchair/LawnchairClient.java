package net.epiccool.lawnchair;

import net.epiccool.lawnchair.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;

public class LawnchairClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.STEEL_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.UNLIT_LANTERN, BlockRenderLayer.CUTOUT);

//        HandledScreens.register(ModScreenHandlers.ALLOY_MIXER_SCREEN_HANDLER, AlloyMixerScreen::new);

        //Fluid
//        FluidRenderHandlerRegistry.INSTANCE.register(Lawnchair.EVIL_FLUID_STILL, Lawnchair.EVIL_FLUID_FLOWING, new SimpleFluidRenderHandler(
//                Identifier.of("minecraft:block/water_still"),
//                Identifier.of("minecraft:block/water_flow"),
//                0x4CC248
//        ));
//
//        BlockRenderLayerMap.putFluids(BlockRenderLayer.TRANSLUCENT, Lawnchair.EVIL_FLUID_STILL, Lawnchair.EVIL_FLUID_FLOWING);

        //if you want to use custom textures they needs to be registered.
        //In this example this is unnecessary because the vanilla water textures are already registered.
        //To register your custom textures use this method.
        //ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
        //    registry.register(new Identifier("tutorial:block/custom_fluid_still"));
        //    registry.register(new Identifier("tutorial:block/custom_fluid_flowing"));
        //});

        // ...
    }
}
