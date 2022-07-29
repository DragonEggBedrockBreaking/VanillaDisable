package uk.debb.vanilla_disable.util.gamerules;

import com.google.common.base.CaseFormat;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;

public class GameruleHelper {
    public static MinecraftServer server;

    public static void register(CustomGameRuleCategory category, BooleanGamerules rule) {
        String ruleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, rule.name());
        rule.setGameRule(GameRuleRegistry.register(ruleName, category, GameRuleFactory.createBooleanRule(rule.getDefaultBool())));
    }

    public static void register(CustomGameRuleCategory category, IntegerGamerules rule) {
        String ruleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, rule.name());
        rule.setGameRule(GameRuleRegistry.register(ruleName, category, GameRuleFactory.createIntRule(rule.getDefaultInt(), rule.getMinInt(), rule.getMaxInt())));
    }

    public static boolean getBool(BooleanGamerules key) {
        if (server == null) return key.getDefaultBool();
        return server.getGameRules().getBoolean(key.getGameRule());
    }

    public static int getInt(IntegerGamerules key) {
        if (server == null) return key.getDefaultInt();
        return server.getGameRules().getInt(key.getGameRule());
    }

    public static void setBool(GameRules.Key<GameRules.BooleanValue> key, boolean newValue) {
        server.getGameRules().getRule(key).set(newValue, server);
    }
}