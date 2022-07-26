package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.storage.WorldData;
import uk.debb.vanilla_disable.util.VDServer;

public class GameruleHelper {
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