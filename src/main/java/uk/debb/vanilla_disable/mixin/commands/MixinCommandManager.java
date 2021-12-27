package uk.debb.vanilla_disable.mixin.commands;

import java.util.UUID;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(CommandManager.class)
public abstract class MixinCommandManager {
    /**
     * @author DragonEggBedrockBreaking
     * @param source the source of the command
     * @param command the command to be executed
     * @param ci the callback info
     */
    @Inject(method = "execute", at = @At(value = "HEAD"), cancellable = true)
    private void execute(ServerCommandSource source, String command, CallbackInfoReturnable<Integer> cir) {
        final Logger LOGGER = LogManager.getLogger();
        if (command.startsWith("/advancement") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ADVANCEMENT_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/attribute") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ATTRIBUTE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/bossbar") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.BOSS_BAR_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/chase") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.CHASE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/clear") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.CLEAR_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/clone") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.CLONE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/datapack") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DATAPACK_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/data") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DATA_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/difficulty") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DIFFICULTY_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/effect") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.EFFECT_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/enchant") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ENCHANT_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/execute") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.EXECUTE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/experience") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.EXPERIENCE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/fill") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.FILL_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/forceload") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.FORCE_LOAD_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/function") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.FUNCTION_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/gamemode") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.GAME_MODE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/give") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.GIVE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/help") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.HELP_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/item") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ITEM_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/jfr") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.JFR_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/kick") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.KICK_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/kill") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.KILL_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/list") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LIST_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/locatebiome") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LOCATE_BIOME_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/locate") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LOCATE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/loot") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LOOT_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/me") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ME_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/msg") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.MESSAGE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/particle") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PARTICLE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/playsound") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PLAY_SOUND_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/publish") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PUBLISH_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/raid") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RAID_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/recipe") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RECIPE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/reload") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RELOAD_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/resetchunks") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RESET_CHUNKS_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/say") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SAY_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/schedule") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SCHEDULE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/scoreboard") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SCOREBOARD_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/seed") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SEED_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/setblock") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SET_BLOCK_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/setworldspawn") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SET_WORLD_SPAWN_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/spawnpoint") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SPAWN_POINT_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/spectate") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SPECTATE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/spreadplayers") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SPREAD_PLAYERS_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/stopsound") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.STOP_SOUND_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/summon") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SUMMON_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/tag") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TAG_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/team") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TEAM_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/teammsg") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TEAM_MSG_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/teleport") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TELEPORT_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/tellraw") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TELL_RAW_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/time") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TIME_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/title") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TITLE_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/trigger") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TRIGGER_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/weather") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.WEATHER_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/worldborder") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.WORLD_BORDER_COMMAND)) {
            LOGGER.info(String.format("The command \"%s\" has been disabled by an operator", command));
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
    }
}
