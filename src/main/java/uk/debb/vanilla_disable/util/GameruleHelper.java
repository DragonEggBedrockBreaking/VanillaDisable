package uk.debb.vanilla_disable.util;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.storage.WorldData;

import org.spongepowered.asm.mixin.Unique;

public class GameruleHelper {
    /**
     * @author DragonEggBedrockBreaking
     * @reason register boolean gamerules
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a boolean)
     * @return the gamerule key (boolean)
     */
    @Unique
    public static GameRules.Key<GameRules.BooleanValue> register(String name, CustomGameRuleCategory category, boolean defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createBooleanRule(defaultValue));
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason register integer gamerules with only a default
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a ineger)
     * @return the gamerule key (int)
     */
    @Unique
    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue));
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason register integer gamerules with default and minimum values
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a ineger)
     * @return the gamerule key (int)
     */
    @Unique
    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue));
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason register integer gamerules with default, minimum, and maximum values
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a ineger)
     * @return the gamerule key (int)
     */
    @Unique
    public static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue, int maxValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue, maxValue));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason get boolean values of boolean gamerules
     * @param key the gamerule key
     * @return the gamerule value (boolean)
     */
    @Unique
    public static boolean getBool(GameRules.Key<GameRules.BooleanValue> key) {
        return VDServer.getServer().getGameRules().getBoolean(key);
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason get boolean values of boolean gamerules using specified worldData
     * @param key the gamerule key
     * @param worldData the world data
     * @return the gamerule value (boolean)
     */
    @Unique
    public static boolean getBool(GameRules.Key<GameRules.BooleanValue> key, WorldData worldData) {
        return worldData.getGameRules().getBoolean(key);
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason get integer values of integer gamerules
     * @param key the gamerule key
     * @return the gamerule value (int)
     */
    @Unique
    public static int getInt(GameRules.Key<GameRules.IntegerValue> key) {
        return VDServer.getServer().getGameRules().getInt(key);
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason get integer values of integer gamerules using specified worldData
     * @param key the gamerule key
     * @param worldData the world data
     * @return the gamerule value (int)
     */
    @Unique
    public static int getInt(GameRules.Key<GameRules.IntegerValue> key, WorldData worldData) {
        return worldData.getGameRules().getInt(key);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason set new boolean values to boolean gamerules
     * @param key the gamerule key
     * @param newValue the gamerule's new value (boolean)
     */
    @Unique
    public static void setBool(GameRules.Key<GameRules.BooleanValue> key, boolean newValue) {
        VDServer.getServer().getGameRules().getRule(key).set(newValue, VDServer.getServer());
    }
}
