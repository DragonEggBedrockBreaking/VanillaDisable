package uk.debb.vanilla_disable.config.command;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import uk.debb.vanilla_disable.Constants;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HotkeyManager {
    public static KeyMapping keyMapping;

    @SubscribeEvent
    public static void registerKeyMappingsEvent(RegisterKeyMappingsEvent event) {
        keyMapping = new KeyMapping("vd.key.open_command_config", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "vd.key.category");
        event.register(keyMapping);
    }
}
