package net.epiccool.lawnchair.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.MapRenderState;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MapRenderer.class)
public abstract class MapRendererMixin {
    @Inject(method = "draw", at = @At("TAIL"))
    private void drawCoords(MapRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, boolean bl, int light, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int x = client.player.getBlockX();
        int z = client.player.getBlockZ();
        int y = client.player.getBlockY();

        matrices.push();
        matrices.translate(0, 0, -0.03f);
        matrices.scale(0.8f, 0.8f, 0.8f);

        queue.getBatchingQueue(1).submitText(
                matrices,
                0f, 0f,
                Text.literal(String.format("X:%d,Y:%d,Z:%d", x, y, z)).asOrderedText(),
                true,
                TextRenderer.TextLayerType.SEE_THROUGH,
                light,
                0xFF000000,
                0x00000000,
                0xFFFFFFFF
        );
    }
}
