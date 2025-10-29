package net.epiccool.lawnchair.entity.client.duck;

import net.epiccool.lawnchair.entity.custom.DuckEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class DuckEntityRenderer extends MobEntityRenderer<DuckEntity, DuckEntityRenderState, DuckEntityModel> {
    private static final Identifier TEXTURE = Identifier.ofVanilla( "textures/entity/chicken/temperate_chicken.png");
    private static final Identifier FEMALE_TEXTURE = Identifier.ofVanilla( "textures/entity/chicken/cold_chicken.png");
    private final DuckEntityModel adultModel;
    private final DuckEntityModel babyModel;

    public DuckEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
        this.adultModel = new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN));
        this.babyModel = new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN_BABY));
    }

    @Override
    public void updateRenderState(DuckEntity duck, DuckEntityRenderState livingEntityRenderState, float tickDelta) {
        super.updateRenderState(duck, livingEntityRenderState, tickDelta);

        livingEntityRenderState.flapProgress = MathHelper.lerp(tickDelta, duck.lastFlapProgress, duck.flapProgress);
        livingEntityRenderState.maxWingDeviation = MathHelper.lerp(tickDelta, duck.lastMaxWingDeviation, duck.maxWingDeviation);
        livingEntityRenderState.isMale = duck.isMale();

        livingEntityRenderState.baseScale = duck.isBaby() ? 0.5f : 1.0f;
        this.shadowRadius = duck.isBaby() ? 0.15f : 0.3f;
    }

    @Override
    public void render(DuckEntityRenderState livingEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
        DuckEntityModel modelToRender = livingEntityRenderState.baby ? this.babyModel : this.adultModel;
        modelToRender.setAngles(livingEntityRenderState);
        super.render(livingEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    @Override
    public DuckEntityRenderState createRenderState() {
        return new DuckEntityRenderState();
    }

    @Override
    public Identifier getTexture(DuckEntityRenderState state) {
        return state.isMale ? TEXTURE : FEMALE_TEXTURE;
    }
}
