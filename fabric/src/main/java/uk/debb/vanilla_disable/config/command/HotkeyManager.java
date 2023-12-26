package uk.debb.vanilla_disable.config.command;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class HotkeyManager implements ClientModInitializer {
    public static KeyMapping keyMapping;

    @Override
    public void onInitializeClient() {
        HotkeyManager.keyMapping = new KeyMapping("vd.key.open_command_config", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "vd.key.category");
        KeyBindingHelper.registerKeyBinding(HotkeyManager.keyMapping);
    }
}
