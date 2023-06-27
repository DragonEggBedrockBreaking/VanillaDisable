package uk.debb.vanilla_disable.config.command;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class CommandConfigHotkeyManager implements ClientModInitializer {
    private static KeyMapping keyMapping;

    @Override
    public void onInitializeClient() {
        keyMapping = new KeyMapping("vd.key.open_command_config", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "vd.key.category");
        KeyBindingHelper.registerKeyBinding(keyMapping);
    }

    public static boolean isPressed() {
        return keyMapping.isDown();
    }
}
