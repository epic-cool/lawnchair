package net.epiccool.lawnchair.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$Settings;create()Lnet/minecraft/block/AbstractBlock$Settings;", ordinal = 0), slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=torchflower")))
    private static AbstractBlock.Settings modifyTorchflower(AbstractBlock.Settings settings) {
        return settings.luminance(state -> 10);
    }
}
