package uk.debb.vanilla_disable.mixin.command.command;

import com.mojang.brigadier.ParseResults;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Commands.class)
public abstract class MixinCommands {
    @Inject(method = "performCommand", at = @At(value = "HEAD"), cancellable = true)
    private void vanillaDisable$performCommand(ParseResults<CommandSourceStack> parseResults, String command, CallbackInfoReturnable<Integer> cir) {
        if (!CommandDataHandler.commands.containsKey("/" + command.split(" ")[0])) return;
        if (!CommandDataHandler.getCachedBoolean("commands", "/" + command.split(" ")[0], "enabled")) {
            CommandDataHandler.server.getPlayerList().broadcastSystemMessage(Component.translatable("vd.commands.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            cir.setReturnValue(0);
        }
    }
}
