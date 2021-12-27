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
    public static GameRules.Key<GameRules.BooleanRule> ANIMAL_BREEDING;

    public static GameRules.Key<GameRules.IntRule> MIN_SPAWN_DISTANCE;
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
    public static GameRules.Key<GameRules.IntRule> MONSTER_MAX_LIGHT_LEVEL;

    public static GameRules.Key<GameRules.BooleanRule> ADVANCEMENT_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> ATTRIBUTE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> BOSS_BAR_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> CHASE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> CLEAR_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> CLONE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> DATAPACK_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> DATA_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> DIFFICULTY_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> EFFECT_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> ENCHANT_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> EXECUTE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> EXPERIENCE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> FILL_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> FORCE_LOAD_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> FUNCTION_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> GAME_MODE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> GIVE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> HELP_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> ITEM_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> JFR_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> KICK_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> KILL_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> LIST_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> LOCATE_BIOME_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> LOCATE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> LOOT_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> ME_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> MESSAGE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> PARTICLE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> PLAY_SOUND_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> PUBLISH_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> RAID_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> RECIPE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> RELOAD_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> RESET_CHUNKS_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SAY_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SCHEDULE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SCOREBOARD_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SEED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SET_BLOCK_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SET_WORLD_SPAWN_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SPAWN_POINT_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SPECTATE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SPREAD_PLAYERS_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> STOP_SOUND_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SUMMON_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TAG_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TEAM_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TEAM_MSG_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TELEPORT_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TELL_RAW_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TIME_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TITLE_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> TRIGGER_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> WEATHER_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> WORLD_BORDER_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> BAN_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> BAN_IP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> BAN_LIST_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> DE_OP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> OP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> PARDON_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> PARDON_IP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> PERF_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SAVE_ALL_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SAVE_OFF_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SAVE_ON_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> SET_IDLE_TIMEOUT_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> STOP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanRule> WHITELIST_DEDICATED_COMMAND;

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
        ANIMAL_BREEDING           = GameRuleRegistry.register(
            "animalBreeding",       CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        
        MIN_SPAWN_DISTANCE        = GameRuleRegistry.register(
            "minSpawnDistance",     CreateGameruleCategories.VD_SPAWNING, GameRuleFactory.createIntRule(24, 0, 512));
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
        MONSTER_MAX_LIGHT_LEVEL  = GameRuleRegistry.register(
            "monsterMaxLightLevel", CreateGameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(0, 0, 15));
        
        ADVANCEMENT_COMMAND      = GameRuleRegistry.register(
            "advancementCommand",   CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        ATTRIBUTE_COMMAND        = GameRuleRegistry.register(
            "attributeCommand",     CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        BOSS_BAR_COMMAND         = GameRuleRegistry.register(
            "bossBarCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        CHASE_COMMAND            = GameRuleRegistry.register(
            "chaseCommand",         CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        CLEAR_COMMAND            = GameRuleRegistry.register(
            "clearCommand",         CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        CLONE_COMMAND            = GameRuleRegistry.register(
            "cloneCommand",         CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        DATAPACK_COMMAND         = GameRuleRegistry.register(
            "datapackCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        DATA_COMMAND             = GameRuleRegistry.register(
            "dataCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        DIFFICULTY_COMMAND       = GameRuleRegistry.register(
            "difficultyCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        EFFECT_COMMAND           = GameRuleRegistry.register(
            "effectCommand",        CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        ENCHANT_COMMAND          = GameRuleRegistry.register(
            "enchantCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        EXECUTE_COMMAND          = GameRuleRegistry.register(
            "executeCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        EXPERIENCE_COMMAND       = GameRuleRegistry.register(
            "experienceCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        FILL_COMMAND             = GameRuleRegistry.register(
            "fillCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        FORCE_LOAD_COMMAND       = GameRuleRegistry.register(
            "forceLoadCommand",     CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        FUNCTION_COMMAND         = GameRuleRegistry.register(
            "functionCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        GAME_MODE_COMMAND        = GameRuleRegistry.register(
            "gameModeCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        GIVE_COMMAND             = GameRuleRegistry.register(
            "giveCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        HELP_COMMAND             = GameRuleRegistry.register(
            "helpCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        ITEM_COMMAND             = GameRuleRegistry.register(
            "itemCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        JFR_COMMAND              = GameRuleRegistry.register(
            "jfrCommand",           CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        KICK_COMMAND             = GameRuleRegistry.register(
            "kickCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        KILL_COMMAND             = GameRuleRegistry.register(
            "killCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        LIST_COMMAND             = GameRuleRegistry.register(
            "listCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        LOCATE_BIOME_COMMAND     = GameRuleRegistry.register(
            "locateBiomeCommand",   CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        LOCATE_COMMAND           = GameRuleRegistry.register(
            "locateCommand",        CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        LOOT_COMMAND             = GameRuleRegistry.register(
            "lootCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        ME_COMMAND               = GameRuleRegistry.register(
            "meCommand",            CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        MESSAGE_COMMAND          = GameRuleRegistry.register(
            "messageCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        PARTICLE_COMMAND         = GameRuleRegistry.register(
            "particleCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        PLAY_SOUND_COMMAND       = GameRuleRegistry.register(
            "playSoundCommand",     CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        PUBLISH_COMMAND          = GameRuleRegistry.register(
            "publishCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        RAID_COMMAND             = GameRuleRegistry.register(
            "raidCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        RECIPE_COMMAND           = GameRuleRegistry.register(
            "recipeCommand",        CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        RELOAD_COMMAND           = GameRuleRegistry.register(
            "reloadCommand",        CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        RESET_CHUNKS_COMMAND     = GameRuleRegistry.register(
            "resetChunksCommand",   CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SAY_COMMAND              = GameRuleRegistry.register(
            "sayCommand",           CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SCHEDULE_COMMAND         = GameRuleRegistry.register(
            "scheduleCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SCOREBOARD_COMMAND       = GameRuleRegistry.register(
            "scoreboardCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SEED_COMMAND             = GameRuleRegistry.register(
            "seedCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SET_BLOCK_COMMAND        = GameRuleRegistry.register(
            "setBlockCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SET_WORLD_SPAWN_COMMAND  = GameRuleRegistry.register(
            "setWorldSpawnCommand", CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SPAWN_POINT_COMMAND      = GameRuleRegistry.register(
            "spawnPointCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        STOP_SOUND_COMMAND       = GameRuleRegistry.register(
            "stopSoundCommand",     CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SUMMON_COMMAND           = GameRuleRegistry.register(
            "summonCommand",        CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TEAM_COMMAND             = GameRuleRegistry.register(
            "teamCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TEAM_MSG_COMMAND         = GameRuleRegistry.register(
            "teamMsgCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TELEPORT_COMMAND         = GameRuleRegistry.register(
            "teleportCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TELL_RAW_COMMAND         = GameRuleRegistry.register(
            "tellRawCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TIME_COMMAND             = GameRuleRegistry.register(
            "timeCommand",          CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TITLE_COMMAND            = GameRuleRegistry.register(
            "titleCommand",         CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        TRIGGER_COMMAND          = GameRuleRegistry.register(
            "triggerCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        WEATHER_COMMAND          = GameRuleRegistry.register(
            "weatherCommand",       CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        WORLD_BORDER_COMMAND     = GameRuleRegistry.register(
            "worldBorderCommand",   CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        BAN_DEDICATED_COMMAND       = GameRuleRegistry.register(
            "banDedicatedCommand",   CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        BAN_IP_DEDICATED_COMMAND    = GameRuleRegistry.register(
            "banIpDedicatedCommand",  CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        BAN_LIST_DEDICATED_COMMAND  = GameRuleRegistry.register(
            "banListDedicatedCommand", CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        DE_OP_DEDICATED_COMMAND     = GameRuleRegistry.register(
            "deOpDedicatedCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        OP_DEDICATED_COMMAND        = GameRuleRegistry.register(
            "opDedicatedCommand",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        PARDON_DEDICATED_COMMAND    = GameRuleRegistry.register(
            "pardonDedicatedCommand",  CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        PARDON_IP_DEDICATED_COMMAND = GameRuleRegistry.register(
            "pardonIpDedicatedCommand",CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        PERF_DEDICATED_COMMAND      = GameRuleRegistry.register(
            "perfDedicatedCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SAVE_ALL_DEDICATED_COMMAND  = GameRuleRegistry.register(
            "saveAllDedicatedCommand", CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SAVE_OFF_DEDICATED_COMMAND  = GameRuleRegistry.register(
            "saveOffDedicatedCommand", CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SAVE_ON_DEDICATED_COMMAND   = GameRuleRegistry.register(
            "saveOnDedicatedCommand",  CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        SET_IDLE_TIMEOUT_DEDICATED_COMMAND = GameRuleRegistry.register(
            "setIdleTimeoutDedicatedCommand", CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        STOP_DEDICATED_COMMAND      = GameRuleRegistry.register(
            "stopDedicatedCommand",    CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        WHITELIST_DEDICATED_COMMAND = GameRuleRegistry.register(
            "whitelistDedicatedCommand", CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
    }
}
