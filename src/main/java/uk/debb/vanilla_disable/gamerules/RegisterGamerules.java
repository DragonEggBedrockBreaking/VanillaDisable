package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import uk.debb.vanilla_disable.gamerules.CreateGameruleCategories;

public class RegisterGamerules implements ModInitializer {
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

    public static GameRules.Key<GameRules.BooleanRule> KNOCKBACK_ENABLED;
    public static GameRules.Key<GameRules.BooleanRule> FIREBALL_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> WITHER_SKULL_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> DRAGON_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> ARROW_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> LLAMA_SPIT_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> SHULKER_BULLET_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> MOB_ATTACK_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> PLAYER_ATTACK_KNOCKBACK;

    @Override
    public void onInitialize() {
        DAMAGE_ENABLED            = GameRuleRegistry.register(
            "damageEnabled",        CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        PROJECTILE_DAMAGE         = GameRuleRegistry.register(
            "projectileDamage",     CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        EXPLOSION_DAMAGE          = GameRuleRegistry.register(
            "explosionDamage",      CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        FALLING_BLOCK_DAMAGE      = GameRuleRegistry.register(
            "fallingBlockDamage",   CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        VOID_DAMAGE               = GameRuleRegistry.register(
            "voidDamage",           CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        MAGIC_DAMAGE              = GameRuleRegistry.register(
            "magicDamage",          CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        CREATIVE_PLAYER_DAMAGE    = GameRuleRegistry.register(
            "creativePlayerDamage", CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        LIGHTNING_DAMAGE          = GameRuleRegistry.register(
            "lightningDamage",      CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        WALL_DAMAGE               = GameRuleRegistry.register(
            "wallDamage",           CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        CRAMMING_DAMAGE           = GameRuleRegistry.register(
            "crammingDamage",       CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        STARVATION_DAMAGE         = GameRuleRegistry.register(
            "starvationDamage",     CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        CACTUS_DAMAGE             = GameRuleRegistry.register(
            "cactusDamage",         CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        FLY_INTO_WALL_DAMAGE      = GameRuleRegistry.register(
            "flyIntoWallDamage",    CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        WITHER_DAMAGE             = GameRuleRegistry.register(
            "witherDamage",         CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        ANVIL_DAMAGE              = GameRuleRegistry.register(
            "anvilDamage",          CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        DRAGON_DAMAGE             = GameRuleRegistry.register(
            "dragonDamage",         CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        DRYOUT_DAMAGE             = GameRuleRegistry.register(
            "dryoutDamage",         CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        SWEET_BERRY_BUSH_DAMAGE   = GameRuleRegistry.register(
            "sweetBerryBushDamage", CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        FALLING_STALACTITE_DAMAGE = GameRuleRegistry.register(
            "fallingStalactiteDamage", CreateGameruleCategories.DAMAGE, GameRuleFactory.createBooleanRule(true));
        
        KNOCKBACK_ENABLED         = GameRuleRegistry.register(
            "knockbackEnabled",     CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        FIREBALL_KNOCKBACK        = GameRuleRegistry.register(
            "fireballKnockback",    CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        WITHER_SKULL_KNOCKBACK    = GameRuleRegistry.register(
            "witherSkullKnockback", CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        DRAGON_KNOCKBACK          = GameRuleRegistry.register(
            "dragonKnockback",      CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        ARROW_KNOCKBACK           = GameRuleRegistry.register(
            "arrowKnockback",       CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        LLAMA_SPIT_KNOCKBACK      = GameRuleRegistry.register(
            "llamaSpitKnockback",   CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        SHULKER_BULLET_KNOCKBACK  = GameRuleRegistry.register(
            "shulkerBulletKnockback", CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        MOB_ATTACK_KNOCKBACK      = GameRuleRegistry.register(
            "mobAttackKnockback",   CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        PLAYER_ATTACK_KNOCKBACK   = GameRuleRegistry.register(
            "playerAttackKnockback", CreateGameruleCategories.KNOCKBACK, GameRuleFactory.createBooleanRule(true));
    }
}
