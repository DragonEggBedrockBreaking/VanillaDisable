package uk.debb.vanilla_disable.mixin.util.command;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.config.command.CommandConfigScreen;
import uk.debb.vanilla_disable.config.command.HotkeyManager;

import java.util.Objects;

@Mixin(KeyboardHandler.class)
public abstract class MixinKeyboardHandler {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("RETURN"))
    private void vanillaDisable$keyPress(CallbackInfo ci) {
        if (HotkeyManager.keyMapping.isDown()) {
            if (!this.minecraft.hasSingleplayerServer()) {
                Objects.requireNonNull(this.minecraft.player).displayClientMessage(Component.translatable("vd.key.cannot_press").withStyle(ChatFormatting.RED), true);
            } else {
                this.minecraft.setScreen(new CommandConfigScreen(this.minecraft.screen));
            }
        }
    }
}
