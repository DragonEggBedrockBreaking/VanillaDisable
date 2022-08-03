package uk.debb.vanilla_disable.mixin.commands;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.brigadier.ParseResults;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Commands.class)
public abstract class MixinCommands implements Maps {
    @ModifyReturnValue(method = "performCommand", at = @At(value = "RETURN"))
    private int performCommand(int original, ParseResults<CommandSourceStack> parseResults, String command) {
        String commandName = command.split(" ")[0];
        Gamerules commandGamerule = commandsStringMap.get(commandName);
        Gamerules dedicatedCommandGamerule = commandsStringMapDedicated.get(commandName);
        MinecraftServer server = parseResults.getContext().getSource().getServer();
        if ((!command.startsWith("gamerule") && !Gamerules.COMMANDS_ENABLED.getValue(Boolean::parseBoolean)) ||
                (commandGamerule != null && !commandGamerule.getValue(Boolean::parseBoolean)) ||
                (server.isDedicatedServer() && dedicatedCommandGamerule != null && !dedicatedCommandGamerule.getValue(Boolean::parseBoolean))) {
            server.getPlayerList().broadcastSystemMessage(Component.translatable("commands.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            return 0;
        }
        return original;
    }
}
