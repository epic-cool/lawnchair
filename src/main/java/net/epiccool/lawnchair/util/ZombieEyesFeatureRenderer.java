package net.epiccool.lawnchair.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ZombieEyesFeatureRenderer
        extends EyesFeatureRenderer<ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> {

    private static final RenderLayer SKIN =
            RenderLayer.getEyes(Identifier.ofVanilla("textures/entity/zombie_eyes.png"));

    public ZombieEyesFeatureRenderer(
            FeatureRendererContext<ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}