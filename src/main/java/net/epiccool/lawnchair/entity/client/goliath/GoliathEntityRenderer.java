package net.epiccool.lawnchair.entity.client.goliath;

import net.epiccool.lawnchair.entity.custom.GoliathEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GoliathEntityRenderer extends MobEntityRenderer<GoliathEntity, LivingEntityRenderState, GoliathEntityModel> {
    private static final Identifier TEXTURE = Identifier.ofVanilla( "textures/entity/spider/spider.png");

    public GoliathEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GoliathEntityModel(context.getPart(EntityModelLayers.SPIDER)), 1.5f);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    protected void scale(LivingEntityRenderState state, MatrixStack matrices) {
        matrices.scale(4.0f, 4.0f, 4.0f);
    }
}
