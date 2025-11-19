package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.command.EmojiCommand;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "sendMessage(Lnet/minecraft/text/Text;Z)V", at = @At("HEAD"), cancellable = true)
    private void fixEmojiSendMessage(Text message, boolean overlay, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        if (EmojiCommand.EMOJI_MESSAGES.contains(message)) {
            String fullMessage = "<" + player.getName().getString() + "> " + message.getString();
            Text packetText = Text.literal(fullMessage);

            player.networkHandler.send(
                    new GameMessageS2CPacket(packetText, false),
                    PacketCallbacks.always(() -> {})
            );

            EmojiCommand.EMOJI_MESSAGES.remove(message);
            ci.cancel();
        }
    }
}
