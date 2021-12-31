package uk.debb.vanilla_disable.mixin.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(CommandManager.class)
public abstract class MixinCommandManager {
    /**
     * @author DragonEggBedrockBreaking
     * @author LittleLily
     * @reason map of all standard commands to their gamerules
     */
    private static final Map<String, GameRules.Key<GameRules.BooleanRule>> commandNameGameruleMap = new HashMap<String, GameRules.Key<GameRules.BooleanRule>>() {{
        put("advancement", RegisterGamerules.ADVANCEMENT_COMMAND);
        put("attribute", RegisterGamerules.ATTRIBUTE_COMMAND);
        put("bossbar", RegisterGamerules.BOSS_BAR_COMMAND);
        put("chase", RegisterGamerules.CHASE_COMMAND);
        put("clear", RegisterGamerules.CLEAR_COMMAND);
        put("clone", RegisterGamerules.CLONE_COMMAND);
        put("datapack", RegisterGamerules.DATAPACK_COMMAND);
        put("data", RegisterGamerules.DATA_COMMAND);
        put("difficulty", RegisterGamerules.DIFFICULTY_COMMAND);
        put("effect", RegisterGamerules.EFFECT_COMMAND);
        put("enchant", RegisterGamerules.ENCHANT_COMMAND);
        put("execute", RegisterGamerules.EXECUTE_COMMAND);
        put("experience", RegisterGamerules.EXPERIENCE_COMMAND);
        put("fill", RegisterGamerules.FILL_COMMAND);
        put("forceload", RegisterGamerules.FORCE_LOAD_COMMAND);
        put("function", RegisterGamerules.FUNCTION_COMMAND);
        put("gamemode", RegisterGamerules.GAME_MODE_COMMAND);
        put("give", RegisterGamerules.GIVE_COMMAND);
        put("help", RegisterGamerules.HELP_COMMAND);
        put("item", RegisterGamerules.ITEM_COMMAND);
        put("jfr", RegisterGamerules.JFR_COMMAND);
        put("kick", RegisterGamerules.KICK_COMMAND);
        put("kill", RegisterGamerules.KILL_COMMAND);
        put("list", RegisterGamerules.LIST_COMMAND);
        put("locatebiome", RegisterGamerules.LOCATE_BIOME_COMMAND);
        put("locate", RegisterGamerules.LOCATE_COMMAND);
        put("loot", RegisterGamerules.LOOT_COMMAND);
        put("me", RegisterGamerules.ME_COMMAND);
        put("msg", RegisterGamerules.MESSAGE_COMMAND);
        put("particle", RegisterGamerules.PARTICLE_COMMAND);
        put("playsound", RegisterGamerules.PLAY_SOUND_COMMAND);
        put("publish", RegisterGamerules.PUBLISH_COMMAND);
        put("raid", RegisterGamerules.RAID_COMMAND);
        put("recipe", RegisterGamerules.RECIPE_COMMAND);
        put("reload", RegisterGamerules.RELOAD_COMMAND);
        put("resetchunks", RegisterGamerules.RESET_CHUNKS_COMMAND);
        put("say", RegisterGamerules.SAY_COMMAND);
        put("schedule", RegisterGamerules.SCHEDULE_COMMAND);
        put("scoreboard", RegisterGamerules.SCOREBOARD_COMMAND);
        put("seed", RegisterGamerules.SEED_COMMAND);
        put("setblock", RegisterGamerules.SET_BLOCK_COMMAND);
        put("setworldspawn", RegisterGamerules.SET_WORLD_SPAWN_COMMAND);
        put("spawnpoint", RegisterGamerules.SPAWN_POINT_COMMAND);
        put("spectate", RegisterGamerules.SPECTATE_COMMAND);
        put("spreadplayers", RegisterGamerules.SPREAD_PLAYERS_COMMAND);
        put("stopsound", RegisterGamerules.STOP_SOUND_COMMAND);
        put("summon", RegisterGamerules.SUMMON_COMMAND);
        put("tag", RegisterGamerules.TAG_COMMAND);
        put("team", RegisterGamerules.TEAM_COMMAND);
        put("teammsg", RegisterGamerules.TEAM_MSG_COMMAND);
        put("teleport", RegisterGamerules.TELEPORT_COMMAND);
        put("tellraw", RegisterGamerules.TELL_RAW_COMMAND);
        put("time", RegisterGamerules.TIME_COMMAND);
        put("title", RegisterGamerules.TITLE_COMMAND);
        put("trigger", RegisterGamerules.TRIGGER_COMMAND);
        put("weather", RegisterGamerules.WEATHER_COMMAND);
        put("worldborder", RegisterGamerules.WORLD_BORDER_COMMAND);
    }};

    /**
     * @author DragonEggBedrockBreaking
     * @author LittleLily
     * @reason map of all standard commands to their gamerules
     */
    private static final Map<String, GameRules.Key<GameRules.BooleanRule>> dedicatedCommandNameGameruleMap = new HashMap<String, GameRules.Key<GameRules.BooleanRule>>() {{
        put("ban", RegisterGamerules.BAN_DEDICATED_COMMAND);
        put("ban-ip", RegisterGamerules.BAN_IP_DEDICATED_COMMAND);
        put("ban-list", RegisterGamerules.BAN_LIST_DEDICATED_COMMAND);
        put("deop", RegisterGamerules.DE_OP_DEDICATED_COMMAND);
        put("op", RegisterGamerules.OP_DEDICATED_COMMAND);
        put("pardon", RegisterGamerules.PARDON_DEDICATED_COMMAND);
        put("pardon-ip", RegisterGamerules.PARDON_IP_DEDICATED_COMMAND);
        put("perf", RegisterGamerules.PERF_DEDICATED_COMMAND);
        put("save-all", RegisterGamerules.SAVE_ALL_DEDICATED_COMMAND);
        put("save-off", RegisterGamerules.SAVE_OFF_DEDICATED_COMMAND);
        put("save-on", RegisterGamerules.SAVE_ON_DEDICATED_COMMAND);
        put("setidletimeout", RegisterGamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND);
        put("stop", RegisterGamerules.STOP_DEDICATED_COMMAND);
        put("whitelist", RegisterGamerules.WHITELIST_DEDICATED_COMMAND);
    }};

    /**
     * @author LittleLily
     * @author DragonEggBedrockBreaking
     * @param source the source of the command
     * @param command the command to be executed
     * @param cir the returnable callback info
     */
    @Inject(method = "execute", at = @At(value = "HEAD"), cancellable = true)
    private void execute(ServerCommandSource source, String command, CallbackInfoReturnable<Integer> cir) {
        String commandName = command.split(" ")[0].substring(1);
        GameRules.Key<GameRules.BooleanRule> commandGamerule = commandNameGameruleMap.get(commandName);
        GameRules.Key<GameRules.BooleanRule> dedicatedCommandGamerule = dedicatedCommandNameGameruleMap.get(commandName);
        if (commandName != "gamerule" && !source.getWorld().getGameRules().getBoolean(RegisterGamerules.COMMANDS_ENABLED)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        } else if (commandGamerule != null && !source.getWorld().getGameRules().getBoolean(commandGamerule)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        } else if (source.getServer().isDedicated() && dedicatedCommandGamerule != null && !source.getWorld().getGameRules().getBoolean(dedicatedCommandGamerule)) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
    }
}