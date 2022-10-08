package uk.debb.vanilla_disable.api;

import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.world.level.GameRules;
import uk.debb.vanilla_disable.util.lists.Lists;
import uk.debb.vanilla_disable.util.maps.Maps;

@Api
public class VanillaDisableApi implements Lists, Maps {
    @Api
    public static void setupRule(GameRules.Key<GameRules.BooleanValue> gamerule, boolean defaultVal) {
        stringToDefaultBooleanMap.put(gamerule.getId(), defaultVal);
        vanillaGamerules.add(gamerule);
    }
    @Api
    public static void setupRule(GameRules.Key<GameRules.IntegerValue> gamerule, int defaultVal, int minVal, int maxVal) {
        stringToDefaultIntMap.put(gamerule.getId(), defaultVal);
        stringToMinIntMap.put(gamerule.getId(), minVal);
        stringToMaxIntMap.put(gamerule.getId(), maxVal);
        vanillaGamerules.add(gamerule);
    }
    @Api
    public static void setupRule(GameRules.Key<DoubleRule> gamerule, double defaultVal, double minVal, double maxVal) {
        stringToDefaultDoubleMap.put(gamerule.getId(), defaultVal);
        stringToMinDoubleMap.put(gamerule.getId(), minVal);
        stringToMaxDoubleMap.put(gamerule.getId(), maxVal);
        vanillaGamerules.add(gamerule);
    }
}
