package uk.debb.vanilla_disable.gamerules.mixin.commands;

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
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(Commands.class)
public abstract class MixinCommands implements Maps {
    @Inject(method = "performCommand", at = @At(value = "HEAD"), cancellable = true)
    private void performCommand(ParseResults<CommandSourceStack> parseResults, String command, CallbackInfoReturnable<Integer> cir) {
        String commandName = command.split(" ")[0];
        Gamerules commandGamerule = commandsStringMap.get(commandName);
        Gamerules dedicatedCommandGamerule = commandsStringMapDedicated.get(commandName);
        MinecraftServer server = parseResults.getContext().getSource().getServer();
        if ((!command.startsWith("gamerule") && !Gamerules.COMMANDS_ENABLED.getBool()) ||
                (commandGamerule != null && !commandGamerule.getBool()) ||
                (server.isDedicatedServer() && dedicatedCommandGamerule != null && !dedicatedCommandGamerule.getBool())) {
            server.getPlayerList().broadcastSystemMessage(Component.translatable("commands.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            cir.setReturnValue(0);
        }
    }
}
