package uk.debb.vanilla_disable.mixin.commands;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Commands.class)
public abstract class MixinCommands {
    @Unique
    private static final Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> commandNameGameruleMap = new Object2ObjectOpenHashMap<>();
    @Unique
    private static final Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> dedicatedCommandNameGameruleMap = new Object2ObjectOpenHashMap<>();

    @Unique
    private void addOptionsToMap() {
        commandNameGameruleMap.put("advancement", Gamerules.ADVANCEMENT_COMMAND);
        commandNameGameruleMap.put("attribute", Gamerules.ATTRIBUTE_COMMAND);
        commandNameGameruleMap.put("bossbar", Gamerules.BOSS_BAR_COMMAND);
        commandNameGameruleMap.put("chase", Gamerules.CHASE_COMMAND);
        commandNameGameruleMap.put("clear", Gamerules.CLEAR_COMMAND);
        commandNameGameruleMap.put("clone", Gamerules.CLONE_COMMAND);
        commandNameGameruleMap.put("datapack", Gamerules.DATAPACK_COMMAND);
        commandNameGameruleMap.put("data", Gamerules.DATA_COMMAND);
        commandNameGameruleMap.put("difficulty", Gamerules.DIFFICULTY_COMMAND);
        commandNameGameruleMap.put("effect", Gamerules.EFFECT_COMMAND);
        commandNameGameruleMap.put("enchant", Gamerules.ENCHANT_COMMAND);
        commandNameGameruleMap.put("execute", Gamerules.EXECUTE_COMMAND);
        commandNameGameruleMap.put("experience", Gamerules.EXPERIENCE_COMMAND);
        commandNameGameruleMap.put("fill", Gamerules.FILL_COMMAND);
        commandNameGameruleMap.put("forceload", Gamerules.FORCE_LOAD_COMMAND);
        commandNameGameruleMap.put("function", Gamerules.FUNCTION_COMMAND);
        commandNameGameruleMap.put("gamemode", Gamerules.GAME_MODE_COMMAND);
        commandNameGameruleMap.put("give", Gamerules.GIVE_COMMAND);
        commandNameGameruleMap.put("help", Gamerules.HELP_COMMAND);
        commandNameGameruleMap.put("item", Gamerules.ITEM_COMMAND);
        commandNameGameruleMap.put("jfr", Gamerules.JFR_COMMAND);
        commandNameGameruleMap.put("kick", Gamerules.KICK_COMMAND);
        commandNameGameruleMap.put("kill", Gamerules.KILL_COMMAND);
        commandNameGameruleMap.put("list", Gamerules.LIST_COMMAND);
        commandNameGameruleMap.put("locate", Gamerules.LOCATE_COMMAND);
        commandNameGameruleMap.put("loot", Gamerules.LOOT_COMMAND);
        commandNameGameruleMap.put("me", Gamerules.ME_COMMAND);
        commandNameGameruleMap.put("msg", Gamerules.MESSAGE_COMMAND);
        commandNameGameruleMap.put("particle", Gamerules.PARTICLE_COMMAND);
        commandNameGameruleMap.put("place", Gamerules.PLACE_COMMAND);
        commandNameGameruleMap.put("playsound", Gamerules.PLAY_SOUND_COMMAND);
        commandNameGameruleMap.put("publish", Gamerules.PUBLISH_COMMAND);
        commandNameGameruleMap.put("raid", Gamerules.RAID_COMMAND);
        commandNameGameruleMap.put("recipe", Gamerules.RECIPE_COMMAND);
        commandNameGameruleMap.put("reload", Gamerules.RELOAD_COMMAND);
        commandNameGameruleMap.put("resetchunks", Gamerules.RESET_CHUNKS_COMMAND);
        commandNameGameruleMap.put("say", Gamerules.SAY_COMMAND);
        commandNameGameruleMap.put("schedule", Gamerules.SCHEDULE_COMMAND);
        commandNameGameruleMap.put("scoreboard", Gamerules.SCOREBOARD_COMMAND);
        commandNameGameruleMap.put("seed", Gamerules.SEED_COMMAND);
        commandNameGameruleMap.put("setblock", Gamerules.SET_BLOCK_COMMAND);
        commandNameGameruleMap.put("setworldspawn", Gamerules.SET_WORLD_SPAWN_COMMAND);
        commandNameGameruleMap.put("spawnpoint", Gamerules.SPAWN_POINT_COMMAND);
        commandNameGameruleMap.put("spectate", Gamerules.SPECTATE_COMMAND);
        commandNameGameruleMap.put("spreadplayers", Gamerules.SPREAD_PLAYERS_COMMAND);
        commandNameGameruleMap.put("stopsound", Gamerules.STOP_SOUND_COMMAND);
        commandNameGameruleMap.put("summon", Gamerules.SUMMON_COMMAND);
        commandNameGameruleMap.put("tag", Gamerules.TAG_COMMAND);
        commandNameGameruleMap.put("team", Gamerules.TEAM_COMMAND);
        commandNameGameruleMap.put("teammsg", Gamerules.TEAM_MSG_COMMAND);
        commandNameGameruleMap.put("teleport", Gamerules.TELEPORT_COMMAND);
        commandNameGameruleMap.put("tellraw", Gamerules.TELL_RAW_COMMAND);
        commandNameGameruleMap.put("time", Gamerules.TIME_COMMAND);
        commandNameGameruleMap.put("title", Gamerules.TITLE_COMMAND);
        commandNameGameruleMap.put("trigger", Gamerules.TRIGGER_COMMAND);
        commandNameGameruleMap.put("weather", Gamerules.WEATHER_COMMAND);
        commandNameGameruleMap.put("worldborder", Gamerules.WORLD_BORDER_COMMAND);
    }

    @Unique
    private void addDedicatedOptionsToMap() {
        dedicatedCommandNameGameruleMap.put("ban", Gamerules.BAN_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("ban-ip", Gamerules.BAN_IP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("ban-list", Gamerules.BAN_LIST_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("deop", Gamerules.DE_OP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("op", Gamerules.OP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("pardon", Gamerules.PARDON_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("pardon-ip", Gamerules.PARDON_IP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("perf", Gamerules.PERF_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("save-all", Gamerules.SAVE_ALL_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("save-off", Gamerules.SAVE_OFF_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("save-on", Gamerules.SAVE_ON_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("setidletimeout", Gamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("stop", Gamerules.STOP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("whitelist", Gamerules.WHITELIST_DEDICATED_COMMAND);
    }

    @ModifyReturnValue(method = "performCommand", at = @At(value = "RETURN"))
    private int performCommand(int original, CommandSourceStack source, String command) {
        String commandName = command.split(" ")[0].substring(1);
        if (commandNameGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        if (dedicatedCommandNameGameruleMap.isEmpty()) {
            addDedicatedOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> commandGamerule = commandNameGameruleMap.get(commandName);
        GameRules.Key<GameRules.BooleanValue> dedicatedCommandGamerule = dedicatedCommandNameGameruleMap.get(commandName);
        if ((!command.startsWith("/gamerule") && !GameruleHelper.getBool(Gamerules.COMMANDS_ENABLED)) ||
                (commandGamerule != null && !GameruleHelper.getBool(commandGamerule)) ||
                (source.getServer().isDedicatedServer() && dedicatedCommandGamerule != null && !GameruleHelper.getBool(dedicatedCommandGamerule))) {
            source.getServer().getPlayerList().broadcastSystemMessage(Component.translatable("commands.disabled.by.vd").withStyle(ChatFormatting.RED), ChatType.CHAT);
            return 0;
        }
        return original;
    }
}