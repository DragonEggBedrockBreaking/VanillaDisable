package uk.debb.vanilla_disable.mixin.commands;

import java.util.UUID;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
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
        if (!command.startsWith("/gamerule") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.COMMANDS_ENABLED)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/advancement") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ADVANCEMENT_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/attribute") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ATTRIBUTE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/bossbar") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.BOSS_BAR_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/chase") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.CHASE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/clear") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.CLEAR_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/clone") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.CLONE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/datapack") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DATAPACK_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/data") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DATA_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/difficulty") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DIFFICULTY_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/effect") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.EFFECT_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/enchant") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ENCHANT_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/execute") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.EXECUTE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/experience") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.EXPERIENCE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/fill") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.FILL_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/forceload") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.FORCE_LOAD_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/function") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.FUNCTION_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/gamemode") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.GAME_MODE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/give") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.GIVE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/help") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.HELP_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/item") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ITEM_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/jfr") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.JFR_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/kick") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.KICK_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/kill") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.KILL_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/list") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LIST_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/locatebiome") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LOCATE_BIOME_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/locate") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LOCATE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/loot") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.LOOT_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/me") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.ME_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/msg") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.MESSAGE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/particle") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PARTICLE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/playsound") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PLAY_SOUND_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/publish") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PUBLISH_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/raid") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RAID_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/recipe") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RECIPE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/reload") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RELOAD_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/resetchunks") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.RESET_CHUNKS_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/say") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SAY_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/schedule") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SCHEDULE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/scoreboard") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SCOREBOARD_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/seed") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SEED_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/setblock") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SET_BLOCK_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/setworldspawn") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SET_WORLD_SPAWN_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/spawnpoint") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SPAWN_POINT_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/spectate") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SPECTATE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/spreadplayers") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SPREAD_PLAYERS_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/stopsound") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.STOP_SOUND_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/summon") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SUMMON_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/tag") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TAG_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/team") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TEAM_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/teammsg") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TEAM_MSG_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/teleport") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TELEPORT_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/tellraw") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TELL_RAW_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/time") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TIME_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/title") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TITLE_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/trigger") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.TRIGGER_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/weather") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.WEATHER_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (command.startsWith("/worldborder") &&
            !source.getWorld().getGameRules().getBoolean(RegisterGamerules.WORLD_BORDER_COMMAND)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
        if (source.getServer().isDedicated()) {
            if (command.startsWith("/ban ") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.BAN_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/ban-ip") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.BAN_IP_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/banlist") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.BAN_LIST_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/deop") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.DE_OP_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/op") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.OP_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/pardon") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PARDON_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/pardon-ip") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PARDON_IP_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/perf") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.PERF_DEDICATED_COMMAND)) { 
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/save-all") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SAVE_ALL_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/save-off") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SAVE_OFF_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/save-on") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SAVE_ON_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/setidletimeout") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
            if (command.startsWith("/stop") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.STOP_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
                }
            if (command.startsWith("/whitelist") &&
                !source.getWorld().getGameRules().getBoolean(RegisterGamerules.WHITELIST_DEDICATED_COMMAND)) {
                source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
                cir.setReturnValue(0);
            }
        }
    }
}
