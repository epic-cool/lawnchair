package net.epiccool.lawnchair.entity.client.duck;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.state.ChickenEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class DuckEntityModel extends EntityModel<ChickenEntityRenderState> {
    public static final EntityModelLayer DUCK = new EntityModelLayer(Identifier.of(Lawnchair.MODID, "duck"), "main");

    public static final String RED_THING = "red_thing";
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public DuckEntityModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild(EntityModelPartNames.HEAD);
        this.rightLeg = modelPart.getChild(EntityModelPartNames.RIGHT_LEG);
        this.leftLeg = modelPart.getChild(EntityModelPartNames.LEFT_LEG);
        this.rightWing = modelPart.getChild(EntityModelPartNames.RIGHT_WING);
        this.leftWing = modelPart.getChild(EntityModelPartNames.LEFT_WING);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = DuckEntityModel.getModelData();
        return TexturedModelData.of(modelData, 64, 32);
    }

    protected static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-2.0f, -6.0f, -2.0f, 4.0f, 6.0f, 3.0f), ModelTransform.origin(0.0f, 15.0f, -4.0f));
        modelPartData2.addChild(EntityModelPartNames.BEAK, ModelPartBuilder.create().uv(14, 0).cuboid(-2.0f, -4.0f, -4.0f, 4.0f, 2.0f, 2.0f), ModelTransform.NONE);
        modelPartData2.addChild(RED_THING, ModelPartBuilder.create().uv(14, 4).cuboid(-1.0f, -2.0f, -3.0f, 2.0f, 2.0f, 2.0f), ModelTransform.NONE);
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 9).cuboid(-3.0f, -4.0f, -3.0f, 6.0f, 8.0f, 6.0f), ModelTransform.of(0.0f, 16.0f, 0.0f, 1.5707964f, 0.0f, 0.0f));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(26, 0).cuboid(-1.0f, 0.0f, -3.0f, 3.0f, 5.0f, 3.0f);
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, modelPartBuilder, ModelTransform.origin(-2.0f, 19.0f, 1.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, modelPartBuilder, ModelTransform.origin(1.0f, 19.0f, 1.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_WING, ModelPartBuilder.create().uv(24, 13).cuboid(0.0f, 0.0f, -3.0f, 1.0f, 4.0f, 6.0f), ModelTransform.origin(-4.0f, 13.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_WING, ModelPartBuilder.create().uv(24, 13).cuboid(-1.0f, 0.0f, -3.0f, 1.0f, 4.0f, 6.0f), ModelTransform.origin(4.0f, 13.0f, 0.0f));
        return modelData;
    }

    @Override
    public void setAngles(ChickenEntityRenderState chickenEntityRenderState) {
        super.setAngles(chickenEntityRenderState);
        float f = (MathHelper.sin(chickenEntityRenderState.flapProgress) + 1.0f) * chickenEntityRenderState.maxWingDeviation;
        this.head.pitch = chickenEntityRenderState.pitch * ((float)Math.PI / 180);
        this.head.yaw = chickenEntityRenderState.relativeHeadYaw * ((float)Math.PI / 180);
        float g = chickenEntityRenderState.limbSwingAmplitude;
        float h = chickenEntityRenderState.limbSwingAnimationProgress;
        this.rightLeg.pitch = MathHelper.cos(h * 0.6662f) * 1.4f * g;
        this.leftLeg.pitch = MathHelper.cos(h * 0.6662f + (float)Math.PI) * 1.4f * g;
        this.rightWing.roll = f;
        this.leftWing.roll = -f;
    }
}
