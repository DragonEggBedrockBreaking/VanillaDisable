package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class VanillaDisableGamerules implements ModInitializer {
    public static GameRules.Key<GameRules.BooleanRule> DAMAGE_ENABLED;
    public static GameRules.Key<GameRules.BooleanRule> PROJECTILE_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> EXPLOSION_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> FALLING_BLOCK_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> VOID_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> MAGIC_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> CREATIVE_PLAYER_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> LIGHTNING_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> WALL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> CRAMMING_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> STARVATION_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> CACTUS_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> FLY_INTO_WALL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> WITHER_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> ANVIL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> DRAGON_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> DRYOUT_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> SWEET_BERRY_BUSH_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> FALLING_STALACTITE_DAMAGE;

    @Override
    public void onInitialize() {
        DAMAGE_ENABLED            = GameRuleRegistry.register(
            "damageEnabled",        GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        PROJECTILE_DAMAGE         = GameRuleRegistry.register(
            "projectileDamage",     GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        EXPLOSION_DAMAGE          = GameRuleRegistry.register(
            "explosionDamage",      GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        FALLING_BLOCK_DAMAGE      = GameRuleRegistry.register(
            "fallingBlockDamage",   GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        VOID_DAMAGE               = GameRuleRegistry.register(
            "voidDamage",           GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        MAGIC_DAMAGE              = GameRuleRegistry.register(
            "magicDamage",          GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        CREATIVE_PLAYER_DAMAGE    = GameRuleRegistry.register(
            "creativePlayerDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        LIGHTNING_DAMAGE          = GameRuleRegistry.register(
            "lightningDamage",      GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        WALL_DAMAGE               = GameRuleRegistry.register(
            "wallDamage",           GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        CRAMMING_DAMAGE           = GameRuleRegistry.register(
            "crammingDamage",       GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        STARVATION_DAMAGE         = GameRuleRegistry.register(
            "starvationDamage",     GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        CACTUS_DAMAGE             = GameRuleRegistry.register(
            "cactusDamage",         GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        FLY_INTO_WALL_DAMAGE      = GameRuleRegistry.register(
            "flyIntoWallDamage",    GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        WITHER_DAMAGE             = GameRuleRegistry.register(
            "witherDamage",         GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        ANVIL_DAMAGE              = GameRuleRegistry.register(
            "anvilDamage",          GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        DRAGON_DAMAGE             = GameRuleRegistry.register(
            "dragonDamage",         GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        DRYOUT_DAMAGE             = GameRuleRegistry.register(
            "dryoutDamage",         GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        SWEET_BERRY_BUSH_DAMAGE   = GameRuleRegistry.register(
            "sweetBerryBushDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        FALLING_STALACTITE_DAMAGE = GameRuleRegistry.register(
            "fallingStalactiteDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
    }
}
