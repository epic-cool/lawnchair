package net.epiccool.lawnchair;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.entity.ModEntities;
import net.epiccool.lawnchair.entity.client.duck.DuckEntityModel;
import net.epiccool.lawnchair.entity.client.duck.DuckEntityRenderer;
import net.epiccool.lawnchair.entity.client.goliath.GoliathEntityModel;
import net.epiccool.lawnchair.entity.client.goliath.GoliathEntityRenderer;
import net.epiccool.lawnchair.util.RainbowWoolColorProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactories;

public class LawnchairClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.STEEL_BARS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.UNLIT_LANTERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.OAK_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.OAK_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SPRUCE_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SPRUCE_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BIRCH_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BIRCH_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.JUNGLE_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.JUNGLE_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ACACIA_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ACACIA_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DARK_OAK_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DARK_OAK_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MANGROVE_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MANGROVE_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.CHERRY_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.CHERRY_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PALE_OAK_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PALE_OAK_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.AZALEA_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.AZALEA_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.FLOWERING_AZALEA_LEAVES_SLAB, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.FLOWERING_AZALEA_LEAVES_STAIRS, BlockRenderLayer.CUTOUT);

        EntityModelLayerRegistry.registerModelLayer(GoliathEntityModel.GOLIATH, GoliathEntityModel::getTexturedModelData);
        EntityRendererFactories.register(ModEntities.GOLIATH, GoliathEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(DuckEntityModel.DUCK, DuckEntityModel::getTexturedModelData);
        EntityRendererFactories.register(ModEntities.DUCK, DuckEntityRenderer::new);

        ColorProviderRegistry.BLOCK.register(new RainbowWoolColorProvider(), ModBlocks.RAINBOW_WOOL);


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
