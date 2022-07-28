package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;

public class GameruleHelper implements GameruleDefaults {
    private static MinecraftServer server;

    public static MinecraftServer getServer() {
        return server;
    }

    public static void setServer(MinecraftServer serverArg) {
        server = serverArg;
    }

    public static GameRules.Key<GameRules.BooleanValue> register(String name, CustomGameRuleCategory category, boolean defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createBooleanRule(defaultValue));
    }

    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue));
    }

    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue));
    }

    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue, int maxValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue, maxValue));
    }

    public static boolean getBool(GameRules.Key<GameRules.BooleanValue> key) {
        if (server == null) return defaultBooleans.getBoolean(key);
        return server.getGameRules().getBoolean(key);
    }

    public static int getInt(GameRules.Key<GameRules.IntegerValue> key) {
        if (server == null) return defaultInts.getInt(key);
        return server.getGameRules().getInt(key);
    }

    public static void setBool(GameRules.Key<GameRules.BooleanValue> key, boolean newValue) {
        server.getGameRules().getRule(key).set(newValue, server);
    }
}