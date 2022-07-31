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
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Commands.class)
public abstract class MixinCommands implements Maps {
    @ModifyReturnValue(method = "performCommand", at = @At(value = "RETURN"))
    private int performCommand(int original, ParseResults<CommandSourceStack> parseResults, String command) {
        String commandName = command.split(" ")[0];
        BooleanGamerules commandGamerule = commandsStringMap.get(commandName);
        BooleanGamerules dedicatedCommandGamerule = commandsStringMapDedicated.get(commandName);
        MinecraftServer server = parseResults.getContext().getSource().getServer();
        if ((!command.startsWith("/gamerule") && !GameruleHelper.getBool(BooleanGamerules.COMMANDS_ENABLED)) ||
                (commandGamerule != null && !GameruleHelper.getBool(commandGamerule)) ||
                (server.isDedicatedServer() && dedicatedCommandGamerule != null && !GameruleHelper.getBool(dedicatedCommandGamerule))) {
            server.getPlayerList().broadcastSystemMessage(Component.translatable("commands.disabled.by.vd").withStyle(ChatFormatting.RED), false);
            return 0;
        }
        return original;
    }
}
