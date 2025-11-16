//package net.epiccool.lawnchair.mixin;
//
//import net.epiccool.lawnchair.entity.goal.GrassStompGoal;
//import net.minecraft.entity.passive.PigEntity;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(PigEntity.class)
//public abstract class PigEntityMixin {
//    @Inject(method = "initGoals", at = @At("TAIL"))
//    private void addGrassStompGoal(CallbackInfo ci) {
//        PigEntity self = (PigEntity)(Object)this;
//        ((MobEntityAccessor) self).getGoalSelector().add(9, new GrassStompGoal(self));
//    }
//}
