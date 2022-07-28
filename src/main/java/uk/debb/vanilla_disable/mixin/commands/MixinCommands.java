package uk.debb.vanilla_disable.mixin.commands;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Commands.class)
public abstract class MixinCommands implements Maps {
    @ModifyReturnValue(method = "performCommand", at = @At(value = "RETURN"))
    private int performCommand(int original, CommandSourceStack source, String command) {
        String commandName = command.split(" ")[0];
        GameRules.Key<GameRules.BooleanValue> commandGamerule = commandsStringMap.get(commandName);
        GameRules.Key<GameRules.BooleanValue> dedicatedCommandGamerule = commandsStringMapDedicated.get(commandName);
        if ((!command.startsWith("/gamerule") && !GameruleHelper.getBool(Gamerules.COMMANDS_ENABLED)) ||
                (commandGamerule != null && !GameruleHelper.getBool(commandGamerule)) ||
                (source.getServer().isDedicatedServer() && dedicatedCommandGamerule != null && !GameruleHelper.getBool(dedicatedCommandGamerule))) {
            source.getServer().getPlayerList().broadcastSystemMessage(Component.translatable("commands.disabled.by.vd").withStyle(ChatFormatting.RED), ChatType.CHAT);
            return 0;
        }
        return original;
    }
}