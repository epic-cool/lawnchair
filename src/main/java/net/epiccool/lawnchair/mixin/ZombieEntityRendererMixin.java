package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.helper.ZombieEyesFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntityRenderer.class)
public abstract class ZombieEntityRendererMixin extends ZombieBaseEntityRenderer<ZombieEntity, ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> {

    protected ZombieEntityRendererMixin(EntityRendererFactory.Context context, ZombieEntityModel<ZombieEntityRenderState> mainModel,
                                        ZombieEntityModel<ZombieEntityRenderState> babyMainModel,
                                        EquipmentModelData<ZombieEntityModel<ZombieEntityRenderState>> equipmentModelData,
                                        EquipmentModelData<ZombieEntityModel<ZombieEntityRenderState>> equipmentModelData2) {
        super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void addEyesFeature(CallbackInfo ci) {
        this.addFeature(new ZombieEyesFeatureRenderer((ZombieEntityRenderer) (Object) this));
    }
}
