package net.epiccool.lawnchair.util;

import net.epiccool.lawnchair.item.custom.GravityGunItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static void Initialize() {
        KeyBinding changeGravityGun = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "keybind.lawnchair.gravity_gun.change",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KeyBinding.Category.GAMEPLAY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (changeGravityGun.wasPressed()) {
                if (client.player != null) {
                    ItemStack stack = client.player.getStackInHand(Hand.MAIN_HAND);
                    if (stack.getItem() instanceof GravityGunItem gravityGun) {
                        gravityGun.changeMode(stack, client.player);
                        break;
                    }
                }
            }
        });
    }
}
