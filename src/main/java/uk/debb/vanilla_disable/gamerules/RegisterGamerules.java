package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

public class RegisterGamerules implements ModInitializer {
    // Defining the minecraft server that I can use to get gamerules anywhere
    private static MinecraftServer server;
    public static MinecraftServer getServer() {
        return server;
    }

    // Defining the gamerules themselves
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

    public static GameRules.Key<GameRules.BooleanRule> COMMANDS_ENABLED;
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

    public static GameRules.Key<GameRules.BooleanRule> INFINITE_WATER;
    public static GameRules.Key<GameRules.BooleanRule> INFINITE_LAVA;
    public static GameRules.Key<GameRules.BooleanRule> WATER_REACHES_FAR;
    public static GameRules.Key<GameRules.BooleanRule> LAVA_REACHES_FAR;
    public static GameRules.Key<GameRules.BooleanRule> LAVA_REACHES_FAR_IN_NETHER;
    public static GameRules.Key<GameRules.IntRule> WATER_FLOW_SPEED;
    public static GameRules.Key<GameRules.IntRule> LAVA_FLOW_SPEED;
    public static GameRules.Key<GameRules.IntRule> LAVA_FLOW_SPEED_NETHER;
    public static GameRules.Key<GameRules.BooleanRule> WATER_PLACEABLE_IN_NETHER;

    public static GameRules.Key<GameRules.BooleanRule> CURABLE_ZILLAGERS;
    public static GameRules.Key<GameRules.BooleanRule> VILLAGERS_CONVERT_TO_ZILLAGERS;
    public static GameRules.Key<GameRules.BooleanRule> VILLAGERS_CONVERT_TO_WITCHES;
    public static GameRules.Key<GameRules.BooleanRule> PIGLINS_CONVERT_TO_ZIGLINS;
    public static GameRules.Key<GameRules.BooleanRule> HOGLINS_CONVERT_TO_ZOGLINS;
    public static GameRules.Key<GameRules.BooleanRule> HUSKS_CONVERT_TO_ZOMBIES;
    public static GameRules.Key<GameRules.BooleanRule> ZOMBIES_CONVERT_TO_DROWNED;
    public static GameRules.Key<GameRules.BooleanRule> SKELETONS_CONVERT_TO_STRAYS;
    public static GameRules.Key<GameRules.BooleanRule> INFINITE_TRADING;

    public static GameRules.Key<GameRules.BooleanRule> EFFECTS_ENABLED;
    public static GameRules.Key<GameRules.BooleanRule> ABSORPTION_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> BAD_OMEN_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> BLINDNESS_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> CONDUIT_POWER_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> DOLPHINS_GRACE_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> FIRE_RESISTANCE_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> GLOWING_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> HASTE_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> HEALTH_BOOST_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> HUNGER_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> INSTANT_DAMAGE_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> INSTANT_HEALTH_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> INVISIBILITY_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> JUMP_BOOST_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> LEVITATION_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> LUCK_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> MINING_FATIGUE_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> NAUSEA_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> NIGHT_VISION_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> POISON_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> REGENERATION_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> RESISTANCE_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> SATURATION_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> SLOWNESS_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> SLOW_FALLING_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> SPEED_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> STRENGTH_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> UNLUCK_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> WATER_BREATHING_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> WEAKNESS_EFFECT;
    public static GameRules.Key<GameRules.BooleanRule> WITHER_EFFECT;

    public static GameRules.Key<GameRules.BooleanRule> AQUA_AFFINITY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> BANE_OF_ARTHROPODS_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> BLAST_PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> CHANNELING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> DEPTH_STRIDER_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> EFFICIENCY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> FEATHER_FALLING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> FIRE_ASPECT_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> FIRE_PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> FLAME_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> FORTUNE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> FROST_WALKER_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> IMPALING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> INFINITY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> KNOCKBACK_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> LOOTING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> LOYALTY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> LUCK_OF_THE_SEA_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> LURE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> MENDING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> MULTISHOT_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> PIERCING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> POWER_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> PROJECTILE_PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> PUNCH_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> QUICK_CHARGE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> RESPIRATION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> RIPTIDE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> SHARPNESS_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> SILK_TOUCH_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> SMITE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> SOUL_SPEED_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> SWEEPING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> THORNS_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> UNBREAKING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanRule> BINDING_CURSE;
    public static GameRules.Key<GameRules.BooleanRule> VANISHING_CURSE;

    public static GameRules.Key<GameRules.BooleanRule> BASTION_REMNANT_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> BURIED_TREASURE_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> DESERT_PYRAMID_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> END_CITY_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> FORTRESS_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> IGLOO_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> JUNGLE_PYRAMID_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> MANSION_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> MINESHAFT_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> MONUMENT_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> NETHER_FOSSIL_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> OCEAN_RUIN_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> PILLAGER_OUTPOST_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> RUINED_PORTAL_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> SHIPWRECK_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> STRONGHOLD_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> SWAMP_HUT_GENERATION;
    public static GameRules.Key<GameRules.BooleanRule> VILLAGE_GENERATION;

    public static GameRules.Key<GameRules.BooleanRule> NETHER_PORTALS_ENABLED;
    public static GameRules.Key<GameRules.BooleanRule> END_PORTALS_ENABLED;
    public static GameRules.Key<GameRules.BooleanRule> END_GATEWAYS_ENABLED;
    public static GameRules.Key<GameRules.IntRule> NETHER_PORTAL_COOLDOWN;
    public static GameRules.Key<GameRules.BooleanRule> CROP_TRAMPLING;

    @Override
    public void onInitialize() {
        // Registering the gamerules that I previously defined
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
            "waterAmbientDespawn", CreateGameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
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

        COMMANDS_ENABLED         = GameRuleRegistry.register(
            "commandsEnabled",      CreateGameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
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
        
        INFINITE_WATER              = GameRuleRegistry.register(
            "infiniteWater",           CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
        INFINITE_LAVA               = GameRuleRegistry.register(
            "infiniteLava",            CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(false));
        WATER_REACHES_FAR           = GameRuleRegistry.register(
            "waterReachesFar",         CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
        LAVA_REACHES_FAR            = GameRuleRegistry.register(
            "lavaReachesFar",          CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(false));
        LAVA_REACHES_FAR_IN_NETHER  = GameRuleRegistry.register(
            "lavaReachesFarInNether",  CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
        WATER_FLOW_SPEED            = GameRuleRegistry.register(
            "waterFlowSpeed",          CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createIntRule(5, 1, 128));
        LAVA_FLOW_SPEED             = GameRuleRegistry.register(
            "lavaFlowSpeed",           CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createIntRule(30, 1, 128));
        LAVA_FLOW_SPEED_NETHER      = GameRuleRegistry.register(
            "lavaFlowSpeedNether",     CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createIntRule(10, 1, 128));
        WATER_PLACEABLE_IN_NETHER   = GameRuleRegistry.register(
            "waterPlaceableInNether",  CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(false));
    
        CURABLE_ZILLAGERS           = GameRuleRegistry.register(
            "curableZillagers",        CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        VILLAGERS_CONVERT_TO_ZILLAGERS  = GameRuleRegistry.register(
            "villagersConvertToZillagers", CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        VILLAGERS_CONVERT_TO_WITCHES    = GameRuleRegistry.register(
            "villagersConvertToWitches",   CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        PIGLINS_CONVERT_TO_ZIGLINS      = GameRuleRegistry.register(
            "piglinsConvertToZiglins",     CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        HOGLINS_CONVERT_TO_ZOGLINS      = GameRuleRegistry.register(
            "hoglinsConvertToZoglins",     CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        HUSKS_CONVERT_TO_ZOMBIES        = GameRuleRegistry.register(
            "husksConvertToZombies",       CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        ZOMBIES_CONVERT_TO_DROWNED      = GameRuleRegistry.register(
            "zombiesConvertToDrowned",     CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        SKELETONS_CONVERT_TO_STRAYS     = GameRuleRegistry.register(
            "skeletonsConvertToStrays",    CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
        INFINITE_TRADING                = GameRuleRegistry.register(
            "infiniteTrading",             CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(false));

        EFFECTS_ENABLED          = GameRuleRegistry.register(
            "effectsEnabled",       CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        ABSORPTION_EFFECT        = GameRuleRegistry.register(
            "absorbtionEffect",     CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        BAD_OMEN_EFFECT          = GameRuleRegistry.register(
            "badOmenEffect",        CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        BLINDNESS_EFFECT         = GameRuleRegistry.register(
            "blindnessEffect",      CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        CONDUIT_POWER_EFFECT     = GameRuleRegistry.register(
            "conduitPowerEffect",   CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        DOLPHINS_GRACE_EFFECT    = GameRuleRegistry.register(
            "dolphinsGraceEffect",  CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        FIRE_RESISTANCE_EFFECT   = GameRuleRegistry.register(
            "fireResistanceEffect", CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        GLOWING_EFFECT           = GameRuleRegistry.register(
            "glowingEffect",        CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        HASTE_EFFECT             = GameRuleRegistry.register(
            "hasteEffect",          CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        HEALTH_BOOST_EFFECT      = GameRuleRegistry.register(
            "healthBoostEffect",    CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        HUNGER_EFFECT            = GameRuleRegistry.register(
            "hungerEffect",         CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        INSTANT_DAMAGE_EFFECT    = GameRuleRegistry.register(
            "instantDamageEffect",  CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        INSTANT_HEALTH_EFFECT    = GameRuleRegistry.register(
            "instantHealthEffect",  CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        INVISIBILITY_EFFECT      = GameRuleRegistry.register(
            "invisibilityEffect",   CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        JUMP_BOOST_EFFECT        = GameRuleRegistry.register(
            "jumpBoostEffect",      CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        LEVITATION_EFFECT        = GameRuleRegistry.register(
            "levitationEffect",     CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        LUCK_EFFECT              = GameRuleRegistry.register(
            "luckEffect",           CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        MINING_FATIGUE_EFFECT    = GameRuleRegistry.register(
            "miningFatigueEffect",  CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        NAUSEA_EFFECT            = GameRuleRegistry.register(
            "nauseaEffect",         CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        NIGHT_VISION_EFFECT      = GameRuleRegistry.register(
            "nightVisionEffect",    CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        POISON_EFFECT            = GameRuleRegistry.register(
            "poisonEffect",         CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        REGENERATION_EFFECT      = GameRuleRegistry.register(
            "regenerationEffect",   CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        RESISTANCE_EFFECT        = GameRuleRegistry.register(
            "resistanceEffect",     CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        SATURATION_EFFECT        = GameRuleRegistry.register(
            "saturationEffect",     CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        SLOWNESS_EFFECT          = GameRuleRegistry.register(
            "slownessEffect",       CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        SLOW_FALLING_EFFECT      = GameRuleRegistry.register(
            "slowFallingEffect",    CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        SPEED_EFFECT             = GameRuleRegistry.register(
            "speedEffect",          CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        STRENGTH_EFFECT          = GameRuleRegistry.register(
            "strengthEffect",       CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        UNLUCK_EFFECT            = GameRuleRegistry.register(
            "unluckEffect",         CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        WATER_BREATHING_EFFECT   = GameRuleRegistry.register(
            "waterBreathingEffect", CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        WEAKNESS_EFFECT          = GameRuleRegistry.register(
            "weaknessEffect",       CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        WITHER_EFFECT            = GameRuleRegistry.register(
            "witherEffect",         CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));

        AQUA_AFFINITY_ENCHANTMENT            = GameRuleRegistry.register(
            "aquaAffinityEnchantment",          CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        BANE_OF_ARTHROPODS_ENCHANTMENT       = GameRuleRegistry.register(
            "baneOfArthropodsEnchantment",     CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        BLAST_PROTECTION_ENCHANTMENT         = GameRuleRegistry.register(
            "blastProtectionEnchantment",      CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        CHANNELING_ENCHANTMENT               = GameRuleRegistry.register(
            "channelingEnchantment",           CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        DEPTH_STRIDER_ENCHANTMENT            = GameRuleRegistry.register(
            "depthStriderEnchantment",         CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        EFFICIENCY_ENCHANTMENT               = GameRuleRegistry.register(
            "efficiencyEnchantment",           CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        FEATHER_FALLING_ENCHANTMENT          = GameRuleRegistry.register(
            "featherFallingEnchantment",       CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        FIRE_ASPECT_ENCHANTMENT              = GameRuleRegistry.register(
            "fireAspectEnchantment",           CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        FIRE_PROTECTION_ENCHANTMENT          = GameRuleRegistry.register(
            "fireProtectionEnchantment",       CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        FLAME_ENCHANTMENT                    = GameRuleRegistry.register(
            "flameEnchantment",                CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        FORTUNE_ENCHANTMENT                  = GameRuleRegistry.register(
            "fortuneEnchantment",              CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        FROST_WALKER_ENCHANTMENT             = GameRuleRegistry.register(
            "frostWalkerEnchantment",          CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        IMPALING_ENCHANTMENT                 = GameRuleRegistry.register(
            "impalingEnchantment",             CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        INFINITY_ENCHANTMENT                 = GameRuleRegistry.register(
            "infinityEnchantment",             CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        KNOCKBACK_ENCHANTMENT                = GameRuleRegistry.register(
            "knockbackEnchantment",            CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        LOOTING_ENCHANTMENT                  = GameRuleRegistry.register(
            "lootingEnchantment",              CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        LOYALTY_ENCHANTMENT                  = GameRuleRegistry.register(
            "loyaltyEnchantment",              CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        LUCK_OF_THE_SEA_ENCHANTMENT          = GameRuleRegistry.register(
            "luckOfTheSeaEnchantment",         CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        LURE_ENCHANTMENT                     = GameRuleRegistry.register(
            "lureEnchantment",                 CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        MENDING_ENCHANTMENT                  = GameRuleRegistry.register(
            "mendingEnchantment",              CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        MULTISHOT_ENCHANTMENT                = GameRuleRegistry.register(
            "multishotEnchantment",            CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        PIERCING_ENCHANTMENT                 = GameRuleRegistry.register(
            "piercingEnchantment",             CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        POWER_ENCHANTMENT                    = GameRuleRegistry.register(
            "powerEnchantment",                CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        PROJECTILE_PROTECTION_ENCHANTMENT    = GameRuleRegistry.register(
            "projectileProtectionEnchantment", CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        PROTECTION_ENCHANTMENT               = GameRuleRegistry.register(
            "protectionEnchantment",           CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        PUNCH_ENCHANTMENT                    = GameRuleRegistry.register(
            "punchEnchantment",                CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        QUICK_CHARGE_ENCHANTMENT             = GameRuleRegistry.register(
            "quickChargeEnchantment",          CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        RESPIRATION_ENCHANTMENT              = GameRuleRegistry.register(
            "respirationEnchantment",          CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        RIPTIDE_ENCHANTMENT                  = GameRuleRegistry.register(
            "riptideEnchantment",              CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        SHARPNESS_ENCHANTMENT                = GameRuleRegistry.register(
            "sharpnessEnchantment",            CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        SILK_TOUCH_ENCHANTMENT               = GameRuleRegistry.register(
            "silkTouchEnchantment",            CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        SMITE_ENCHANTMENT                    = GameRuleRegistry.register(
            "smiteEnchantment",                CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        SOUL_SPEED_ENCHANTMENT               = GameRuleRegistry.register(
            "soulSpeedEnchantment",            CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        SWEEPING_ENCHANTMENT                 = GameRuleRegistry.register(
            "sweepingEnchantment",             CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        THORNS_ENCHANTMENT                   = GameRuleRegistry.register(
            "thornsEnchantment",               CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        UNBREAKING_ENCHANTMENT               = GameRuleRegistry.register(
            "unbreakingEnchantment",           CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        BINDING_CURSE                        = GameRuleRegistry.register(
            "bindingCurse",                    CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        VANISHING_CURSE                      = GameRuleRegistry.register(
            "vanishingCurse",                  CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));

        BASTION_REMNANT_GENERATION     = GameRuleRegistry.register(
            "bastionRemnantGeneration", CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        BURIED_TREASURE_GENERATION     = GameRuleRegistry.register(
            "buriedTreasureGeneration", CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        DESERT_PYRAMID_GENERATION      = GameRuleRegistry.register(
            "desertPyramidGeneration",  CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        END_CITY_GENERATION            = GameRuleRegistry.register(
            "endCityGeneration",        CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        FORTRESS_GENERATION            = GameRuleRegistry.register(
            "fortressGeneration",       CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        IGLOO_GENERATION               = GameRuleRegistry.register(
            "iglooGeneration",          CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        JUNGLE_PYRAMID_GENERATION      = GameRuleRegistry.register(
            "junglePyramidGeneration",  CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        MANSION_GENERATION             = GameRuleRegistry.register(
            "mansionGeneration",        CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        MINESHAFT_GENERATION           = GameRuleRegistry.register(
            "mineshaftGeneration",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        MONUMENT_GENERATION            = GameRuleRegistry.register(
            "monumentGeneration",       CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        NETHER_FOSSIL_GENERATION       = GameRuleRegistry.register(
            "netherFossilGeneration",   CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        OCEAN_RUIN_GENERATION          = GameRuleRegistry.register(
            "oceanRuinGeneration",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        PILLAGER_OUTPOST_GENERATION    = GameRuleRegistry.register(
            "pillagerOutpostGeneration",CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        RUINED_PORTAL_GENERATION        = GameRuleRegistry.register(
            "ruinedPortalGeneration",    CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        SHIPWRECK_GENERATION           = GameRuleRegistry.register(
            "shipwreckGeneration",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        STRONGHOLD_GENERATION          = GameRuleRegistry.register(
            "strongholdGeneration",     CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        SWAMP_HUT_GENERATION           = GameRuleRegistry.register(
            "swampHutGeneration",       CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
        VILLAGE_GENERATION             = GameRuleRegistry.register(
            "villageGeneration",        CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));

        NETHER_PORTALS_ENABLED   = GameRuleRegistry.register(
            "netherPortalsEnabled", CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
        END_PORTALS_ENABLED      = GameRuleRegistry.register(
            "endPortalsEnabled",    CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
        END_GATEWAYS_ENABLED     = GameRuleRegistry.register(
            "endGatewaysEnabled",   CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
        NETHER_PORTAL_COOLDOWN   = GameRuleRegistry.register(
            "netherPortalCooldown", CreateGameruleCategories.VD_MISC, GameRuleFactory.createIntRule(300));
        CROP_TRAMPLING           = GameRuleRegistry.register(
            "cropTrampling",        CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));

        // Registering the Minecraft server to when it actually starts
        ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
            RegisterGamerules.server = minecraftServer;
        });
    }
}