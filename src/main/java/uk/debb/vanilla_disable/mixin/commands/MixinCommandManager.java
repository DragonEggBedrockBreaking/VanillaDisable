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
import org.spongepowered.asm.mixin.Unique;
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
    @Unique
    private static final Map<String, GameRules.Key<GameRules.BooleanRule>> commandNameGameruleMap = new HashMap<String, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        commandNameGameruleMap.put("advancement", RegisterGamerules.ADVANCEMENT_COMMAND);
        commandNameGameruleMap.put("attribute", RegisterGamerules.ATTRIBUTE_COMMAND);
        commandNameGameruleMap.put("bossbar", RegisterGamerules.BOSS_BAR_COMMAND);
        commandNameGameruleMap.put("chase", RegisterGamerules.CHASE_COMMAND);
        commandNameGameruleMap.put("clear", RegisterGamerules.CLEAR_COMMAND);
        commandNameGameruleMap.put("clone", RegisterGamerules.CLONE_COMMAND);
        commandNameGameruleMap.put("datapack", RegisterGamerules.DATAPACK_COMMAND);
        commandNameGameruleMap.put("data", RegisterGamerules.DATA_COMMAND);
        commandNameGameruleMap.put("difficulty", RegisterGamerules.DIFFICULTY_COMMAND);
        commandNameGameruleMap.put("effect", RegisterGamerules.EFFECT_COMMAND);
        commandNameGameruleMap.put("enchant", RegisterGamerules.ENCHANT_COMMAND);
        commandNameGameruleMap.put("execute", RegisterGamerules.EXECUTE_COMMAND);
        commandNameGameruleMap.put("experience", RegisterGamerules.EXPERIENCE_COMMAND);
        commandNameGameruleMap.put("fill", RegisterGamerules.FILL_COMMAND);
        commandNameGameruleMap.put("forceload", RegisterGamerules.FORCE_LOAD_COMMAND);
        commandNameGameruleMap.put("function", RegisterGamerules.FUNCTION_COMMAND);
        commandNameGameruleMap.put("gamemode", RegisterGamerules.GAME_MODE_COMMAND);
        commandNameGameruleMap.put("give", RegisterGamerules.GIVE_COMMAND);
        commandNameGameruleMap.put("help", RegisterGamerules.HELP_COMMAND);
        commandNameGameruleMap.put("item", RegisterGamerules.ITEM_COMMAND);
        commandNameGameruleMap.put("jfr", RegisterGamerules.JFR_COMMAND);
        commandNameGameruleMap.put("kick", RegisterGamerules.KICK_COMMAND);
        commandNameGameruleMap.put("kill", RegisterGamerules.KILL_COMMAND);
        commandNameGameruleMap.put("list", RegisterGamerules.LIST_COMMAND);
        commandNameGameruleMap.put("locatebiome", RegisterGamerules.LOCATE_BIOME_COMMAND);
        commandNameGameruleMap.put("locate", RegisterGamerules.LOCATE_COMMAND);
        commandNameGameruleMap.put("loot", RegisterGamerules.LOOT_COMMAND);
        commandNameGameruleMap.put("me", RegisterGamerules.ME_COMMAND);
        commandNameGameruleMap.put("msg", RegisterGamerules.MESSAGE_COMMAND);
        commandNameGameruleMap.put("particle", RegisterGamerules.PARTICLE_COMMAND);
        commandNameGameruleMap.put("playsound", RegisterGamerules.PLAY_SOUND_COMMAND);
        commandNameGameruleMap.put("publish", RegisterGamerules.PUBLISH_COMMAND);
        commandNameGameruleMap.put("raid", RegisterGamerules.RAID_COMMAND);
        commandNameGameruleMap.put("recipe", RegisterGamerules.RECIPE_COMMAND);
        commandNameGameruleMap.put("reload", RegisterGamerules.RELOAD_COMMAND);
        commandNameGameruleMap.put("resetchunks", RegisterGamerules.RESET_CHUNKS_COMMAND);
        commandNameGameruleMap.put("say", RegisterGamerules.SAY_COMMAND);
        commandNameGameruleMap.put("schedule", RegisterGamerules.SCHEDULE_COMMAND);
        commandNameGameruleMap.put("scoreboard", RegisterGamerules.SCOREBOARD_COMMAND);
        commandNameGameruleMap.put("seed", RegisterGamerules.SEED_COMMAND);
        commandNameGameruleMap.put("setblock", RegisterGamerules.SET_BLOCK_COMMAND);
        commandNameGameruleMap.put("setworldspawn", RegisterGamerules.SET_WORLD_SPAWN_COMMAND);
        commandNameGameruleMap.put("spawnpoint", RegisterGamerules.SPAWN_POINT_COMMAND);
        commandNameGameruleMap.put("spectate", RegisterGamerules.SPECTATE_COMMAND);
        commandNameGameruleMap.put("spreadplayers", RegisterGamerules.SPREAD_PLAYERS_COMMAND);
        commandNameGameruleMap.put("stopsound", RegisterGamerules.STOP_SOUND_COMMAND);
        commandNameGameruleMap.put("summon", RegisterGamerules.SUMMON_COMMAND);
        commandNameGameruleMap.put("tag", RegisterGamerules.TAG_COMMAND);
        commandNameGameruleMap.put("team", RegisterGamerules.TEAM_COMMAND);
        commandNameGameruleMap.put("teammsg", RegisterGamerules.TEAM_MSG_COMMAND);
        commandNameGameruleMap.put("teleport", RegisterGamerules.TELEPORT_COMMAND);
        commandNameGameruleMap.put("tellraw", RegisterGamerules.TELL_RAW_COMMAND);
        commandNameGameruleMap.put("time", RegisterGamerules.TIME_COMMAND);
        commandNameGameruleMap.put("title", RegisterGamerules.TITLE_COMMAND);
        commandNameGameruleMap.put("trigger", RegisterGamerules.TRIGGER_COMMAND);
        commandNameGameruleMap.put("weather", RegisterGamerules.WEATHER_COMMAND);
        commandNameGameruleMap.put("worldborder", RegisterGamerules.WORLD_BORDER_COMMAND);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @author LittleLily
     * @reason map of all standard commands to their gamerules
     */
    @Unique
    private static final Map<String, GameRules.Key<GameRules.BooleanRule>> dedicatedCommandNameGameruleMap = new HashMap<String, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addDedicatedOptionsToMap() {
        dedicatedCommandNameGameruleMap.put("ban", RegisterGamerules.BAN_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("ban-ip", RegisterGamerules.BAN_IP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("ban-list", RegisterGamerules.BAN_LIST_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("deop", RegisterGamerules.DE_OP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("op", RegisterGamerules.OP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("pardon", RegisterGamerules.PARDON_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("pardon-ip", RegisterGamerules.PARDON_IP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("perf", RegisterGamerules.PERF_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("save-all", RegisterGamerules.SAVE_ALL_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("save-off", RegisterGamerules.SAVE_OFF_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("save-on", RegisterGamerules.SAVE_ON_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("setidletimeout", RegisterGamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("stop", RegisterGamerules.STOP_DEDICATED_COMMAND);
        dedicatedCommandNameGameruleMap.put("whitelist", RegisterGamerules.WHITELIST_DEDICATED_COMMAND);
    }

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
        if (commandNameGameruleMap.isEmpty()) {
            this.addOptionsToMap();
        }
        if (dedicatedCommandNameGameruleMap.isEmpty()) {
            this.addDedicatedOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanRule> commandGamerule = commandNameGameruleMap.get(commandName);
        GameRules.Key<GameRules.BooleanRule> dedicatedCommandGamerule = dedicatedCommandNameGameruleMap.get(commandName);
        if ((!command.startsWith("/gamerule") && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.COMMANDS_ENABLED)) ||
            (commandGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(commandGamerule)) ||
            (source.getServer().isDedicated() && dedicatedCommandGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(dedicatedCommandGamerule))) {
            source.getServer().getPlayerManager().broadcast(new TranslatableText("commands.disabled.by.vd").formatted(Formatting.RED), MessageType.CHAT, UUID.randomUUID());
            cir.setReturnValue(0);
        }
    }
}