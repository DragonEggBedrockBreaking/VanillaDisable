package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;

public class GameruleHelper implements GameruleDefaults {
    private static MinecraftServer server;
    public static void setServer(MinecraftServer serverArg) {
        server = serverArg;
    }
    public static MinecraftServer getServer() {
        return server;
    }

    /**
     * @param name         the name of the gamerule
     * @param category     the gamerule category
     * @param defaultValue the default value (a boolean)
     * @return the gamerule key (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.BooleanValue> register(String name, CustomGameRuleCategory category, boolean defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createBooleanRule(defaultValue));
    }

    /**
     * @param name         the name of the gamerule
     * @param category     the gamerule category
     * @param defaultValue the default value (an integer)
     * @return the gamerule key (int)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue));
    }

    /**
     * @param name         the name of the gamerule
     * @param category     the gamerule category
     * @param defaultValue the default value (an integer)
     * @param minValue the minimum possible value of the gamerule
     * @return the gamerule key (int)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue));
    }

    /**
     * @param name         the name of the gamerule
     * @param category     the gamerule category
     * @param defaultValue the default value (an integer)
     * @param minValue the minimum possible value of the gamerule
     * @param maxValue the maximum possible value of the gamerule
     * @return the gamerule key (int)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue, int maxValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue, maxValue));
    }

    /**
     * @param key the gamerule key
     * @return the gamerule value (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static boolean getBool(GameRules.Key<GameRules.BooleanValue> key) {
        if (server == null) return defaultBooleans.getBoolean(key);
        return server.getGameRules().getBoolean(key);
    }

    /**
     * @param key the gamerule key
     * @return the gamerule value (int)
     * @author DragonEggBedrockBreaking
     */
    public static int getInt(GameRules.Key<GameRules.IntegerValue> key) {
        if (server == null) return defaultInts.getInt(key);
        return server.getGameRules().getInt(key);
    }

    /**
     * @param key      the gamerule key
     * @param newValue the gamerule's new value (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static void setBool(GameRules.Key<GameRules.BooleanValue> key, boolean newValue) {
        server.getGameRules().getRule(key).set(newValue, server);
    }
}