package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

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

    public static GameRules.Key<GameRules.BooleanRule> MONSTERS_DESPAWN;
    public static GameRules.Key<GameRules.BooleanRule> CREATURES_DESPAWN;
    public static GameRules.Key<GameRules.BooleanRule> AMBIENT_DESPAWN;
    public static GameRules.Key<GameRules.BooleanRule> AXOLOTLS_DESPAWN;
    public static GameRules.Key<GameRules.BooleanRule> GLOWSQUIDS_DESPAWN;
    public static GameRules.Key<GameRules.BooleanRule> WATER_CREATURES_DESPAWN;
    public static GameRules.Key<GameRules.BooleanRule> WATER_AMBIENT_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> MONSTER_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> CREATURE_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> AMBIENT_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> AXOLOTL_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> GLOWSQUID_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> WATER_CREATURE_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> WATER_AMBIENT_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> MONSTER_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> CREATURE_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> AMBIENT_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> AXOLOTL_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> GLOWSQUID_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> WATER_CREATURE_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> WATER_AMBIENT_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntRule> ITEM_DESPAWN_TIME;
    public static GameRules.Key<GameRules.BooleanRule> ENDER_PEARLS_DESPAWN_ON_DEATH;

    public static GameRules.Key<GameRules.IntRule> MONSTER_MOBCAP;
    public static GameRules.Key<GameRules.IntRule> CREATURE_MOBCAP;
    public static GameRules.Key<GameRules.IntRule> AMBIENT_MOBCAP;
    public static GameRules.Key<GameRules.IntRule> AXOLOTL_MOBCAP;
    public static GameRules.Key<GameRules.IntRule> GLOWSQUID_MOBCAP;
    public static GameRules.Key<GameRules.IntRule> WATER_CREATURE_MOBCAP;
    public static GameRules.Key<GameRules.IntRule> WATER_AMBIENT_MOBCAP;

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
        
        MONSTERS_DESPAWN          = GameRuleRegistry.register(
            "monstersDespawn",      CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        CREATURES_DESPAWN         = GameRuleRegistry.register(
            "creaturesDespawn",     CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(false));
        AMBIENT_DESPAWN           = GameRuleRegistry.register(
            "ambientDespawn",       CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        AXOLOTLS_DESPAWN          = GameRuleRegistry.register(
            "axolotlsDespawn",      CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        GLOWSQUIDS_DESPAWN        = GameRuleRegistry.register(
            "glowsquidsDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        WATER_CREATURES_DESPAWN   = GameRuleRegistry.register(
            "waterCreaturesDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        WATER_AMBIENT_DESPAWN    = GameRuleRegistry.register(
            "waterAmbientsDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        MONSTER_MAX_DESPAWN       = GameRuleRegistry.register(
            "monsterMaxDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
        CREATURE_MAX_DESPAWN      = GameRuleRegistry.register(
            "creatureMaxDespawn",   CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
        AMBIENT_MAX_DESPAWN       = GameRuleRegistry.register(
            "ambientMaxDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
        AXOLOTL_MAX_DESPAWN       = GameRuleRegistry.register(
            "axolotlMaxDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
        GLOWSQUID_MAX_DESPAWN     = GameRuleRegistry.register(
            "glowsquidMaxDespawn",  CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
        WATER_CREATURE_MAX_DESPAWN= GameRuleRegistry.register(
            "waterCreatureMaxDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
        WATER_AMBIENT_MAX_DESPAWN = GameRuleRegistry.register(
            "waterAmbientMaxDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(64, 0, 512));
        MONSTER_MIN_DESPAWN       = GameRuleRegistry.register(
            "monsterMinDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        CREATURE_MIN_DESPAWN      = GameRuleRegistry.register(
            "creatureMinDespawn",   CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        AMBIENT_MIN_DESPAWN       = GameRuleRegistry.register(
            "ambientMinDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        AXOLOTL_MIN_DESPAWN       = GameRuleRegistry.register(
            "axolotlMinDespawn",    CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        GLOWSQUID_MIN_DESPAWN     = GameRuleRegistry.register(
            "glowsquidMinDespawn",  CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        WATER_CREATURE_MIN_DESPAWN= GameRuleRegistry.register(
            "waterCreatureMinDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        WATER_AMBIENT_MIN_DESPAWN = GameRuleRegistry.register(
            "waterAmbientMinDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
        ITEM_DESPAWN_TIME         = GameRuleRegistry.register(
            "itemDespawnTime",      CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(300, 0, Integer.MAX_VALUE));
        ENDER_PEARLS_DESPAWN_ON_DEATH = GameRuleRegistry.register(
            "enderPearlsDespawnOnDeath", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));

        MONSTER_MOBCAP           = GameRuleRegistry.register(
            "monsterMobCap",        CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(70, 0, Integer.MAX_VALUE));
        CREATURE_MOBCAP          = GameRuleRegistry.register(
            "creatureMobCap",       CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(10, 0, Integer.MAX_VALUE));
        AMBIENT_MOBCAP           = GameRuleRegistry.register(
            "ambientMobCap",        CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(15, 0, Integer.MAX_VALUE));
        AXOLOTL_MOBCAP           = GameRuleRegistry.register(
            "axolotlMobCap",        CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(5, 0, Integer.MAX_VALUE));
        GLOWSQUID_MOBCAP         = GameRuleRegistry.register(
            "glowsquidMobCap",      CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(5, 0, Integer.MAX_VALUE));
        WATER_CREATURE_MOBCAP    = GameRuleRegistry.register(
            "waterCreatureMobCap",  CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(5, 0, Integer.MAX_VALUE));
        WATER_AMBIENT_MOBCAP     = GameRuleRegistry.register(
            "waterAmbientMobCap",   CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(20, 0, Integer.MAX_VALUE));
    }
}
