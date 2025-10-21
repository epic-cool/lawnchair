package net.epiccool.lawnchair.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {
    @ModifyReturnValue(method = "createEndermanAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder modifyEndermanSpeed(DefaultAttributeContainer.Builder builder) {
        builder.add(EntityAttributes.MOVEMENT_SPEED, 0.3f * 1.25f);
        return builder;
    }
}
