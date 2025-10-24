package net.epiccool.lawnchair.entity.client.goliath;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GoliathEntityModel extends EntityModel<LivingEntityRenderState> {
    public static final EntityModelLayer GOLIATH = new EntityModelLayer(Identifier.of(Lawnchair.MODID, "goliath"), "main");

    private static final String BODY0 = "body0";
    private static final String BODY1 = "body1";
    private static final String RIGHT_MIDDLE_FRONT_LEG = "right_middle_front_leg";
    private static final String LEFT_MIDDLE_FRONT_LEG = "left_middle_front_leg";
    private static final String RIGHT_MIDDLE_HIND_LEG = "right_middle_hind_leg";
    private static final String LEFT_MIDDLE_HIND_LEG = "left_middle_hind_leg";
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightMiddleLeg;
    private final ModelPart leftMiddleLeg;
    private final ModelPart rightMiddleFrontLeg;
    private final ModelPart leftMiddleFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public GoliathEntityModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild(EntityModelPartNames.HEAD);
        this.rightHindLeg = modelPart.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
        this.leftHindLeg = modelPart.getChild(EntityModelPartNames.LEFT_HIND_LEG);
        this.rightMiddleLeg = modelPart.getChild(RIGHT_MIDDLE_HIND_LEG);
        this.leftMiddleLeg = modelPart.getChild(LEFT_MIDDLE_HIND_LEG);
        this.rightMiddleFrontLeg = modelPart.getChild(RIGHT_MIDDLE_FRONT_LEG);
        this.leftMiddleFrontLeg = modelPart.getChild(LEFT_MIDDLE_FRONT_LEG);
        this.rightFrontLeg = modelPart.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
        this.leftFrontLeg = modelPart.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(32, 4).cuboid(-4.0f, -4.0f, -8.0f, 8.0f, 8.0f, 8.0f), ModelTransform.origin(0.0f, 15.0f, -3.0f));
        modelPartData.addChild(BODY0, ModelPartBuilder.create().uv(0, 0).cuboid(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f), ModelTransform.origin(0.0f, 15.0f, 0.0f));
        modelPartData.addChild(BODY1, ModelPartBuilder.create().uv(0, 12).cuboid(-5.0f, -4.0f, -6.0f, 10.0f, 8.0f, 12.0f), ModelTransform.origin(0.0f, 15.0f, 9.0f));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(18, 0).cuboid(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f);
        ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(18, 0).mirrored().cuboid(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f);
        modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, modelPartBuilder, ModelTransform.of(-4.0f, 15.0f, 2.0f, 0.0f, 0.7853982f, -0.7853982f));
        modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, modelPartBuilder2, ModelTransform.of(4.0f, 15.0f, 2.0f, 0.0f, -0.7853982f, 0.7853982f));
        modelPartData.addChild(RIGHT_MIDDLE_HIND_LEG, modelPartBuilder, ModelTransform.of(-4.0f, 15.0f, 1.0f, 0.0f, 0.3926991f, -0.58119464f));
        modelPartData.addChild(LEFT_MIDDLE_HIND_LEG, modelPartBuilder2, ModelTransform.of(4.0f, 15.0f, 1.0f, 0.0f, -0.3926991f, 0.58119464f));
        modelPartData.addChild(RIGHT_MIDDLE_FRONT_LEG, modelPartBuilder, ModelTransform.of(-4.0f, 15.0f, 0.0f, 0.0f, -0.3926991f, -0.58119464f));
        modelPartData.addChild(LEFT_MIDDLE_FRONT_LEG, modelPartBuilder2, ModelTransform.of(4.0f, 15.0f, 0.0f, 0.0f, 0.3926991f, 0.58119464f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, modelPartBuilder, ModelTransform.of(-4.0f, 15.0f, -1.0f, 0.0f, -0.7853982f, -0.7853982f));
        modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, modelPartBuilder2, ModelTransform.of(4.0f, 15.0f, -1.0f, 0.0f, 0.7853982f, 0.7853982f));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setAngles(LivingEntityRenderState livingEntityRenderState) {
        super.setAngles(livingEntityRenderState);
        this.head.yaw = livingEntityRenderState.relativeHeadYaw * ((float)Math.PI / 180);
        this.head.pitch = livingEntityRenderState.pitch * ((float)Math.PI / 180);
        float f = livingEntityRenderState.limbSwingAnimationProgress * 0.6662f;
        float g = livingEntityRenderState.limbSwingAmplitude;
        float h = -(MathHelper.cos(f * 2.0f + 0.0f) * 0.4f) * g;
        float i = -(MathHelper.cos(f * 2.0f + (float)Math.PI) * 0.4f) * g;
        float j = -(MathHelper.cos(f * 2.0f + 1.5707964f) * 0.4f) * g;
        float k = -(MathHelper.cos(f * 2.0f + 4.712389f) * 0.4f) * g;
        float l = Math.abs(MathHelper.sin(f + 0.0f) * 0.4f) * g;
        float m = Math.abs(MathHelper.sin(f + (float)Math.PI) * 0.4f) * g;
        float n = Math.abs(MathHelper.sin(f + 1.5707964f) * 0.4f) * g;
        float o = Math.abs(MathHelper.sin(f + 4.712389f) * 0.4f) * g;
        this.rightHindLeg.yaw += h;
        this.leftHindLeg.yaw -= h;
        this.rightMiddleLeg.yaw += i;
        this.leftMiddleLeg.yaw -= i;
        this.rightMiddleFrontLeg.yaw += j;
        this.leftMiddleFrontLeg.yaw -= j;
        this.rightFrontLeg.yaw += k;
        this.leftFrontLeg.yaw -= k;
        this.rightHindLeg.roll += l;
        this.leftHindLeg.roll -= l;
        this.rightMiddleLeg.roll += m;
        this.leftMiddleLeg.roll -= m;
        this.rightMiddleFrontLeg.roll += n;
        this.leftMiddleFrontLeg.roll -= n;
        this.rightFrontLeg.roll += o;
        this.leftFrontLeg.roll -= o;
    }
}

