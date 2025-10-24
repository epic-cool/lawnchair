package net.epiccool.lawnchair.entity.client.duck;

import net.epiccool.lawnchair.entity.custom.DuckEntity;
import net.minecraft.client.model.BabyModelPair;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.ChickenEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Map;

public class DuckEntityRenderer extends MobEntityRenderer<DuckEntity, ChickenEntityRenderState, DuckEntityModel> {
    private static final Identifier TEXTURE = Identifier.ofVanilla( "textures/entity/chicken/temperate_chicken.png");
    private static final Identifier FEMALE_TEXTURE = Identifier.ofVanilla( "textures/entity/chicken/cold_chicken.png");
    private final Map<DuckEntityModel, BabyModelPair<DuckEntityModel>> babyModelPairMap;
    private DuckEntity duck;

    public DuckEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
        this.babyModelPairMap = DuckEntityRenderer.createBabyModelPairMap(context);
    }

    private static Map<DuckEntityModel, BabyModelPair<DuckEntityModel>> createBabyModelPairMap(EntityRendererFactory.Context context) {
        return Map.of(
                new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN)),
                new BabyModelPair<>(
                        new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN)),
                        new DuckEntityModel(context.getPart(EntityModelLayers.CHICKEN_BABY))
                )
        );
    }

    @Override
    public void updateRenderState(DuckEntity duck, ChickenEntityRenderState chickenEntityRenderState, float tickDelta) {
        super.updateRenderState(duck, chickenEntityRenderState, tickDelta);
        this.duck = duck;

        chickenEntityRenderState.flapProgress = MathHelper.lerp(tickDelta, duck.lastFlapProgress, duck.flapProgress);
        chickenEntityRenderState.maxWingDeviation = MathHelper.lerp(tickDelta, duck.lastMaxWingDeviation, duck.maxWingDeviation);

        BabyModelPair<DuckEntityModel> modelPair = babyModelPairMap.values().iterator().next();
        if (duck.isBaby()) {
            this.model = modelPair.babyModel();
        } else {
            this.model = modelPair.adultModel();
        }

        float scale = duck.isBaby() ? 0.5f : 1.0f;
        chickenEntityRenderState.baseScale = scale;
        this.shadowRadius = 0.3f * scale;
    }

    @Override
    public ChickenEntityRenderState createRenderState() {
        return new ChickenEntityRenderState();
    }

    @Override
    public Identifier getTexture(ChickenEntityRenderState state) {
        return duck.isMale() ? TEXTURE : FEMALE_TEXTURE;
    }
}
