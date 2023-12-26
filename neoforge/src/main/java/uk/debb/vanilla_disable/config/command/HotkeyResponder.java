package uk.debb.vanilla_disable.config.command;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import uk.debb.vanilla_disable.Constants;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class HotkeyResponder {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (HotkeyManager.keyMapping.consumeClick()) {
            Minecraft minecraft = Minecraft.getInstance();
            if (!minecraft.hasSingleplayerServer()) {
                Objects.requireNonNull(minecraft.player).displayClientMessage(Component.translatable("vd.key.cannot_press").withStyle(ChatFormatting.RED), true);
            } else {
                minecraft.setScreen(new CommandConfigScreen(minecraft.screen));
            }
        }
    }
}
