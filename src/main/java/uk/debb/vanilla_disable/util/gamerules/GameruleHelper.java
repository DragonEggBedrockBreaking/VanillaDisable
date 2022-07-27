package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.storage.WorldData;
import uk.debb.vanilla_disable.util.VDServer;

public class GameruleHelper implements GameruleDefaults {
    /**
     * @param name     the name of the gamerule
     * @param category the gamerule category
     * @param gameRule the gamerule (boolean)
     * @return the gamerule key (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.BooleanValue> registerBoolean(String name, CustomGameRuleCategory category, GameRules.Key<GameRules.BooleanValue> gameRule) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createBooleanRule(defaultBooleans.getBoolean(gameRule)));
    }

    /**
     * @param name     the name of the gamerule
     * @param category the gamerule category
     * @param gameRule the gamerule (int)
     * @return the gamerule key (int)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.IntegerValue> registerInt(String name, CustomGameRuleCategory category, GameRules.Key<GameRules.IntegerValue> gameRule) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultInts.getInt(gameRule)));
    }

    /**
     * @param name     the name of the gamerule
     * @param category the gamerule category
     * @param gameRule the gamerule (int)
     * @param minValue the minimum possible value of the gamerule
     * @return the gamerule key (int)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.IntegerValue> registerInt(String name, CustomGameRuleCategory category, GameRules.Key<GameRules.IntegerValue> gameRule, int minValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultInts.getInt(gameRule), minValue));
    }

    /**
     * @param name     the name of the gamerule
     * @param category the gamerule category
     * @param gameRule the gamerule (int)
     * @param minValue the minimum possible value of the gamerule
     * @param maxValue the maximum possible value of the gamerule
     * @return the gamerule key (int)
     * @author DragonEggBedrockBreaking
     */
    public static GameRules.Key<GameRules.IntegerValue> registerInt(String name, CustomGameRuleCategory category, GameRules.Key<GameRules.IntegerValue> gameRule, int minValue, int maxValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultInts.getInt(gameRule), minValue, maxValue));
    }

    /**
     * @param key the gamerule key
     * @return the gamerule value (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static boolean getBool(GameRules.Key<GameRules.BooleanValue> key) {
        if (VDServer.getServer() == null) return defaultBooleans.getBoolean(key);
        return VDServer.getServer().getGameRules().getBoolean(key);
    }

    /**
     * @param key       the gamerule key
     * @param worldData the world data
     * @return the gamerule value (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static boolean getBool(GameRules.Key<GameRules.BooleanValue> key, WorldData worldData) {
        return worldData.getGameRules().getBoolean(key);
    }

    /**
     * @param key the gamerule key
     * @return the gamerule value (int)
     * @author DragonEggBedrockBreaking
     */
    public static int getInt(GameRules.Key<GameRules.IntegerValue> key) {
        if (VDServer.getServer() == null) return defaultInts.getInt(key);
        return VDServer.getServer().getGameRules().getInt(key);
    }

    /**
     * @param key      the gamerule key
     * @param newValue the gamerule's new value (boolean)
     * @author DragonEggBedrockBreaking
     */
    public static void setBool(GameRules.Key<GameRules.BooleanValue> key, boolean newValue) {
        VDServer.getServer().getGameRules().getRule(key).set(newValue, VDServer.getServer());
    }
}