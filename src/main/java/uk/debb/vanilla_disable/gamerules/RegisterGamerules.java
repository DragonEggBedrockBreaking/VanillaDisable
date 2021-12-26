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
    public static GameRules.Key<GameRules.BooleanRule> TRIDENT_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> LLAMA_SPIT_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> SHULKER_BULLET_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> MOB_ATTACK_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> PLAYER_ATTACK_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> KNOCKBACK_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> EXPLOSION_KNOCKBACK;

    public static GameRules.Key<GameRules.BooleanRule> MONSTER_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> CREATURE_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> AMBIENT_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> AXOLOTL_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> GLOWSQUID_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> WATER_CREATURE_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> WATER_AMBIENT_SPAWNING;
    public static GameRules.Key<GameRules.BooleanRule> SPAWNERS_ENABLED;
    public static GameRules.Key<GameRules.BooleanRule> PIG_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> CAVE_SPIDER_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> SILVERFISH_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> ZOMBIE_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> SKELETON_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> BLAZE_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> SPIDER_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> MAGMA_CUBE_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanRule> SPAWN_EGGS;
    public static GameRules.Key<GameRules.BooleanRule> SUMMON_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> ANIMAL_BREEDING;

    @Override
    public void onInitialize() {
        DAMAGE_ENABLED            = GameRuleRegistry.register(
            "damageEnabled",        CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        PROJECTILE_DAMAGE         = GameRuleRegistry.register(
            "projectileDamage",     CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        EXPLOSION_DAMAGE          = GameRuleRegistry.register(
            "explosionDamage",      CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        FALLING_BLOCK_DAMAGE      = GameRuleRegistry.register(
            "fallingBlockDamage",   CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        VOID_DAMAGE               = GameRuleRegistry.register(
            "voidDamage",           CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        MAGIC_DAMAGE              = GameRuleRegistry.register(
            "magicDamage",          CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        CREATIVE_PLAYER_DAMAGE    = GameRuleRegistry.register(
            "creativePlayerDamage", CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        LIGHTNING_DAMAGE          = GameRuleRegistry.register(
            "lightningDamage",      CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        WALL_DAMAGE               = GameRuleRegistry.register(
            "wallDamage",           CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        CRAMMING_DAMAGE           = GameRuleRegistry.register(
            "crammingDamage",       CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        STARVATION_DAMAGE         = GameRuleRegistry.register(
            "starvationDamage",     CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        CACTUS_DAMAGE             = GameRuleRegistry.register(
            "cactusDamage",         CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        FLY_INTO_WALL_DAMAGE      = GameRuleRegistry.register(
            "flyIntoWallDamage",    CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        WITHER_DAMAGE             = GameRuleRegistry.register(
            "witherDamage",         CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        ANVIL_DAMAGE              = GameRuleRegistry.register(
            "anvilDamage",          CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        DRAGON_DAMAGE             = GameRuleRegistry.register(
            "dragonDamage",         CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        DRYOUT_DAMAGE             = GameRuleRegistry.register(
            "dryoutDamage",         CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        SWEET_BERRY_BUSH_DAMAGE   = GameRuleRegistry.register(
            "sweetBerryBushDamage", CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        FALLING_STALACTITE_DAMAGE = GameRuleRegistry.register(
            "fallingStalactiteDamage", CreateGameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        
        KNOCKBACK_ENABLED         = GameRuleRegistry.register(
            "knockbackEnabled",     CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        FIREBALL_KNOCKBACK        = GameRuleRegistry.register(
            "fireballKnockback",    CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        WITHER_SKULL_KNOCKBACK    = GameRuleRegistry.register(
            "witherSkullKnockback", CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        DRAGON_KNOCKBACK          = GameRuleRegistry.register(
            "dragonKnockback",      CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        ARROW_KNOCKBACK           = GameRuleRegistry.register(
            "arrowKnockback",       CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        TRIDENT_KNOCKBACK         = GameRuleRegistry.register(
            "tridentKnockback",     CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        LLAMA_SPIT_KNOCKBACK      = GameRuleRegistry.register(
            "llamaSpitKnockback",   CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        SHULKER_BULLET_KNOCKBACK  = GameRuleRegistry.register(
            "shulkerBulletKnockback", CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        MOB_ATTACK_KNOCKBACK      = GameRuleRegistry.register(
            "mobAttackKnockback",   CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        PLAYER_ATTACK_KNOCKBACK   = GameRuleRegistry.register(
            "playerAttackKnockback", CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        KNOCKBACK_ENCHANTMENT     = GameRuleRegistry.register(
            "knockbackEnchantment", CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        EXPLOSION_KNOCKBACK       = GameRuleRegistry.register(
            "explosionKnockback",   CreateGameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        
        MONSTER_SPAWNING          = GameRuleRegistry.register(
            "monsterSpawning",      CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        CREATURE_SPAWNING         = GameRuleRegistry.register(
            "creatureSpawning",     CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        AMBIENT_SPAWNING          = GameRuleRegistry.register(
            "ambientSpawning",      CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        AXOLOTL_SPAWNING          = GameRuleRegistry.register(
            "axolotlSpawning",      CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        GLOWSQUID_SPAWNING        = GameRuleRegistry.register(
            "glowsquidSpawning",    CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        WATER_CREATURE_SPAWNING   = GameRuleRegistry.register(
            "waterCreatureSpawning", CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        WATER_AMBIENT_SPAWNING    = GameRuleRegistry.register(
            "waterAmbientSpawning", CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        SPAWNERS_ENABLED          = GameRuleRegistry.register(
            "spawnersEnabled",      CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        PIG_SPAWNERS              = GameRuleRegistry.register(
            "pigSpawners",          CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        CAVE_SPIDER_SPAWNERS      = GameRuleRegistry.register(
            "caveSpiderSpawners",   CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        SILVERFISH_SPAWNERS       = GameRuleRegistry.register(
            "silverfishSpawners",   CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        ZOMBIE_SPAWNERS           = GameRuleRegistry.register(
            "zombieSpawners",       CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        SKELETON_SPAWNERS         = GameRuleRegistry.register(
            "skeletonSpawners",     CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        BLAZE_SPAWNERS            = GameRuleRegistry.register(
            "blazeSpawners",        CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        SPIDER_SPAWNERS           = GameRuleRegistry.register(
            "spiderSpawners",       CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        MAGMA_CUBE_SPAWNERS       = GameRuleRegistry.register(
            "magmaCubeSpawners",    CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        SPAWN_EGGS                = GameRuleRegistry.register(
            "spawnEggs",            CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        SUMMON_COMMAND            = GameRuleRegistry.register(
            "summonCommand",        CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        ANIMAL_BREEDING           = GameRuleRegistry.register(
            "animalBreeding",       CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
    }
}
