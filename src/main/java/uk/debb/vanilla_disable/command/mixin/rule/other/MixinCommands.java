package uk.debb.vanilla_disable.command.mixin.rule.other;

import com.mojang.brigadier.ParseResults;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Commands.class)
public abstract class MixinCommands {
    @Inject(method = "performCommand", at = @At(value = "HEAD"), cancellable = true)
    private void performCommand(ParseResults<CommandSourceStack> parseResults, String command, CallbackInfoReturnable<Integer> cir) {
        if (!DataHandler.getCachedBoolean("others", "/" + command.split(" ")[0], "enabled")) {
            DataHandler.server.getPlayerList().broadcastSystemMessage(Component.translatable("commands.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            cir.setReturnValue(0);
        }
    }
}
