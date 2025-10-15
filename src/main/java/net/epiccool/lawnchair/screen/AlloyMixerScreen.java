//package net.epiccool.lawnchair.screen;
//
//import net.epiccool.lawnchair.Lawnchair;
//import net.minecraft.client.gl.RenderPipelines;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.screen.ingame.HandledScreen;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//
//public class AlloyMixerScreen extends HandledScreen<AlloyMixerScreenHandler> {
//    private static final Identifier TEXTURE = Identifier.of(Lawnchair.MODID, "textures/gui/alloy_mixer.png");
//
//    public AlloyMixerScreen(AlloyMixerScreenHandler handler, PlayerInventory inventory, Text title) {
//        super(handler, inventory, title);
//        this.backgroundWidth = 176;
//        this.backgroundHeight = 133;
//    }
//
//    @Override
//    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
//        context.drawGuiTexture(
//                RenderPipelines.GUI_TEXTURED,
//                TEXTURE,
//                this.x,
//                this.y,
//                this.backgroundWidth,
//                this.backgroundHeight
//        );
//    }
//
//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        this.renderBackground(context, mouseX, mouseY, delta);
//        super.render(context, mouseX, mouseY, delta);
//        this.drawMouseoverTooltip(context, mouseX, mouseY);
//    }
//}
