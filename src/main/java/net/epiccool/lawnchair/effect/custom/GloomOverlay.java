package net.epiccool.lawnchair.effect.custom;

import net.epiccool.lawnchair.effect.ModEffects;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class GloomOverlay implements HudElement {
    private static boolean registered = false;

    public static void Initialize() {
        if (registered) return;
        registered = true;

        HudElementRegistry.attachElementAfter(VanillaHudElements.MISC_OVERLAYS, GloomEffect.GLOOM_OVERLAY_ID, new GloomOverlay());
    }

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;
        if (!client.player.hasStatusEffect(ModEffects.GLOOM)) return;

        int w = client.getWindow().getScaledWidth();
        int h = client.getWindow().getScaledHeight();

        context.fill( 0, 0, w, h, 0xFF000000);
    }
}
