package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.debb.vanilla_disable.mixin.VanillaDisableMixinConfigPlugin;

public class RegisterGamerules implements ModInitializer {
    // Creates a logger for this mod to use
    private static Logger logger = LoggerFactory.getLogger("Vanilla Disable");
    public static Logger getLogger() {
        return logger;
    }

    // Defining the minecraft server that I can use to get gamerules anywhere
    private static MinecraftServer server;
    public static MinecraftServer getServer() {
        return server;
    }

    // Defining the gamerules themselves
    public static GameRules.Key<GameRules.BooleanValue> DAMAGE_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> PROJECTILE_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> EXPLOSION_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> FALLING_BLOCK_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> VOID_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> MAGIC_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> CREATIVE_PLAYER_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> LIGHTNING_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> WALL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> CRAMMING_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> STARVATION_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> CACTUS_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> FLY_INTO_WALL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> WITHER_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> ANVIL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> DRAGON_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> SWEET_BERRY_BUSH_DAMAGE;
    public static GameRules.Key<GameRules.BooleanValue> FALLING_STALACTITE_DAMAGE;

    public static GameRules.Key<GameRules.BooleanValue> KNOCKBACK_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> FIREBALL_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> WITHER_SKULL_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> DRAGON_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> ARROW_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> TRIDENT_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> LLAMA_SPIT_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> SHULKER_BULLET_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> MOB_ATTACK_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> PLAYER_ATTACK_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanValue> EXPLOSION_KNOCKBACK;

    public static GameRules.Key<GameRules.BooleanValue> MONSTER_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> CREATURE_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> AMBIENT_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> AXOLOTL_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> GLOWSQUID_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> WATER_CREATURE_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> WATER_AMBIENT_SPAWNING;
    public static GameRules.Key<GameRules.BooleanValue> SPAWNERS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> PIG_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> CAVE_SPIDER_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> SILVERFISH_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> ZOMBIE_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> SKELETON_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> BLAZE_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> SPIDER_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> MAGMA_CUBE_SPAWNERS;
    public static GameRules.Key<GameRules.BooleanValue> SPAWN_EGGS;
    public static GameRules.Key<GameRules.BooleanValue> ANIMAL_BREEDING;

    public static GameRules.Key<GameRules.IntegerValue> MIN_SPAWN_DISTANCE;
    public static GameRules.Key<GameRules.BooleanValue> MONSTERS_DESPAWN;
    public static GameRules.Key<GameRules.BooleanValue> CREATURES_DESPAWN;
    public static GameRules.Key<GameRules.BooleanValue> AMBIENT_DESPAWN;
    public static GameRules.Key<GameRules.BooleanValue> AXOLOTLS_DESPAWN;
    public static GameRules.Key<GameRules.BooleanValue> GLOWSQUIDS_DESPAWN;
    public static GameRules.Key<GameRules.BooleanValue> WATER_CREATURES_DESPAWN;
    public static GameRules.Key<GameRules.BooleanValue> WATER_AMBIENT_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> MONSTER_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> CREATURE_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> AMBIENT_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> AXOLOTL_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> GLOWSQUID_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> WATER_CREATURE_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> WATER_AMBIENT_MAX_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> MONSTER_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> CREATURE_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> AMBIENT_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> AXOLOTL_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> GLOWSQUID_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> WATER_CREATURE_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> WATER_AMBIENT_MIN_DESPAWN;
    public static GameRules.Key<GameRules.IntegerValue> ITEM_DESPAWN_TIME;
    public static GameRules.Key<GameRules.BooleanValue> ENDER_PEARLS_DESPAWN_ON_DEATH;

    public static GameRules.Key<GameRules.IntegerValue> MONSTER_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> CREATURE_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> AMBIENT_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> AXOLOTL_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> GLOWSQUID_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> WATER_CREATURE_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> WATER_AMBIENT_MOBCAP;
    public static GameRules.Key<GameRules.IntegerValue> MONSTER_MAX_LIGHT_LEVEL;

    public static GameRules.Key<GameRules.BooleanValue> COMMANDS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> ADVANCEMENT_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> ATTRIBUTE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> BOSS_BAR_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> CHASE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> CLEAR_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> CLONE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> DATAPACK_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> DATA_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> DIFFICULTY_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> EFFECT_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> ENCHANT_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> EXECUTE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> EXPERIENCE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> FILL_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> FORCE_LOAD_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> FUNCTION_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> GAME_MODE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> GIVE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> HELP_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> ITEM_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> JFR_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> KICK_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> KILL_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> LIST_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> LOCATE_BIOME_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> LOCATE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> LOOT_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> ME_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> MESSAGE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> PARTICLE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> PLAY_SOUND_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> PUBLISH_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> RAID_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> RECIPE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> RELOAD_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> RESET_CHUNKS_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SAY_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SCHEDULE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SCOREBOARD_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SEED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SET_BLOCK_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SET_WORLD_SPAWN_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SPAWN_POINT_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SPECTATE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SPREAD_PLAYERS_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> STOP_SOUND_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SUMMON_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TAG_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TEAM_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TEAM_MSG_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TELEPORT_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TELL_RAW_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TIME_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TITLE_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> TRIGGER_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> WEATHER_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> WORLD_BORDER_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> BAN_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> BAN_IP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> BAN_LIST_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> DE_OP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> OP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> PARDON_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> PARDON_IP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> PERF_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SAVE_ALL_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SAVE_OFF_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SAVE_ON_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> SET_IDLE_TIMEOUT_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> STOP_DEDICATED_COMMAND;
    public static GameRules.Key<GameRules.BooleanValue> WHITELIST_DEDICATED_COMMAND;

    public static GameRules.Key<GameRules.BooleanValue> INFINITE_WATER;
    public static GameRules.Key<GameRules.BooleanValue> INFINITE_LAVA;
    public static GameRules.Key<GameRules.BooleanValue> WATER_REACHES_FAR;
    public static GameRules.Key<GameRules.BooleanValue> LAVA_REACHES_FAR;
    public static GameRules.Key<GameRules.BooleanValue> LAVA_REACHES_FAR_IN_NETHER;
    public static GameRules.Key<GameRules.IntegerValue> WATER_FLOW_SPEED;
    public static GameRules.Key<GameRules.IntegerValue> LAVA_FLOW_SPEED;
    public static GameRules.Key<GameRules.IntegerValue> LAVA_FLOW_SPEED_NETHER;
    public static GameRules.Key<GameRules.BooleanValue> WATER_PLACEABLE_IN_NETHER;
    public static GameRules.Key<GameRules.BooleanValue> BUBBLE_COLUMNS_ENABLED;

    public static GameRules.Key<GameRules.BooleanValue> CURABLE_ZILLAGERS;
    public static GameRules.Key<GameRules.BooleanValue> VILLAGERS_CONVERT_TO_ZILLAGERS;
    public static GameRules.Key<GameRules.BooleanValue> VILLAGERS_CONVERT_TO_WITCHES;
    public static GameRules.Key<GameRules.BooleanValue> PIGLINS_CONVERT_TO_ZIGLINS;
    public static GameRules.Key<GameRules.BooleanValue> HOGLINS_CONVERT_TO_ZOGLINS;
    public static GameRules.Key<GameRules.BooleanValue> HUSKS_CONVERT_TO_ZOMBIES;
    public static GameRules.Key<GameRules.BooleanValue> ZOMBIES_CONVERT_TO_DROWNED;
    public static GameRules.Key<GameRules.BooleanValue> SKELETONS_CONVERT_TO_STRAYS;
    public static GameRules.Key<GameRules.BooleanValue> INFINITE_TRADING;
    public static GameRules.Key<GameRules.BooleanValue> VILLAGER_TRADING_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> PIGLIN_BARTERING_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> WITHER_SPAWNS;
    public static GameRules.Key<GameRules.BooleanValue> PIGS_BREED_WITH_WHEAT;
    public static GameRules.Key<GameRules.BooleanValue> MOBS_BURN_IN_SUNLIGHT;
    public static GameRules.Key<GameRules.BooleanValue> DRAGON_FIREBALLS;
    public static GameRules.Key<GameRules.BooleanValue> FIRE_ASPECT_IGNITES_CREEPERS;

    public static GameRules.Key<GameRules.BooleanValue> EFFECTS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> ABSORPTION_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> BAD_OMEN_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> BLINDNESS_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> CONDUIT_POWER_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> DOLPHINS_GRACE_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> FIRE_RESISTANCE_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> GLOWING_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> HASTE_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> HEALTH_BOOST_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> HUNGER_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> INSTANT_DAMAGE_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> INSTANT_HEALTH_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> INVISIBILITY_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> JUMP_BOOST_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> LEVITATION_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> LUCK_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> MINING_FATIGUE_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> NAUSEA_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> NIGHT_VISION_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> POISON_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> REGENERATION_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> RESISTANCE_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> SATURATION_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> SLOWNESS_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> SLOW_FALLING_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> SPEED_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> STRENGTH_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> UNLUCK_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> WATER_BREATHING_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> WEAKNESS_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> WITHER_EFFECT;
    public static GameRules.Key<GameRules.BooleanValue> MILK_CLEARS_EFFECTS;

    public static GameRules.Key<GameRules.BooleanValue> ENCHANTMENTS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> AQUA_AFFINITY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> BANE_OF_ARTHROPODS_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> BLAST_PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> CHANNELING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> DEPTH_STRIDER_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> EFFICIENCY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> FEATHER_FALLING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> FIRE_ASPECT_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> FIRE_PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> FLAME_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> FORTUNE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> FROST_WALKER_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> IMPALING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> INFINITY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> KNOCKBACK_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> LOOTING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> LOYALTY_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> LUCK_OF_THE_SEA_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> LURE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> MENDING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> MULTISHOT_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> PIERCING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> POWER_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> PROJECTILE_PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> PROTECTION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> PUNCH_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> QUICK_CHARGE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> RESPIRATION_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> RIPTIDE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> SHARPNESS_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> SILK_TOUCH_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> SMITE_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> SOUL_SPEED_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> SWEEPING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> THORNS_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> UNBREAKING_ENCHANTMENT;
    public static GameRules.Key<GameRules.BooleanValue> BINDING_CURSE;
    public static GameRules.Key<GameRules.BooleanValue> VANISHING_CURSE;
    public static GameRules.Key<GameRules.BooleanValue> BOOT_ENCHANTMENT_CONFLICTS;
    public static GameRules.Key<GameRules.BooleanValue> BOW_ENCHANTMENT_CONFLICTS;
    public static GameRules.Key<GameRules.BooleanValue> CROSSBOW_ENCHANTMENT_CONFLICTS;
    public static GameRules.Key<GameRules.BooleanValue> DAMAGE_ENCHANTMENT_CONFLICTS;
    public static GameRules.Key<GameRules.BooleanValue> MINING_ENCHANTMENT_CONFLICTS;
    public static GameRules.Key<GameRules.BooleanValue> PROTECTION_ENCHANTMENT_CONFLICTS;
    public static GameRules.Key<GameRules.BooleanValue> TRIDENT_ENCHANTMENT_CONFLICTS;

    public static GameRules.Key<GameRules.BooleanValue> BASTION_REMNANT_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> BURIED_TREASURE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> DESERT_PYRAMID_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> END_CITY_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> FORTRESS_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> IGLOO_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> JUNGLE_PYRAMID_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> MANSION_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> MINESHAFT_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> MONUMENT_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> NETHER_FOSSIL_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> OCEAN_RUIN_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> PILLAGER_OUTPOST_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> RUINED_PORTAL_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> SHIPWRECK_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> STRONGHOLD_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> SWAMP_HUT_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> VILLAGE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> END_VEGETATION;
    public static GameRules.Key<GameRules.BooleanValue> NETHER_VEGETATION;
    public static GameRules.Key<GameRules.BooleanValue> OCEAN_VEGETATION;
    public static GameRules.Key<GameRules.BooleanValue> OVERWORLD_VEGETATION;
    public static GameRules.Key<GameRules.BooleanValue> UNDERGROUND_VEGETATION;
    public static GameRules.Key<GameRules.BooleanValue> AMETHYST_GEODE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> BASALT_BLACKSTONE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> DESERT_WELL_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> DRIPSTONE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> DUNGEON_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> END_FEATURES_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> FOSSIL_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> GLOWSTONE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> ICE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> LAVA_LAKE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> MAGMA_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> NETHER_FIRE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> NETHER_ORE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> OCEAN_FLOOR_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> ORE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> SPRING_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> TREE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> WELL_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> END_PILLAR_CAGE_GENERATION;
    public static GameRules.Key<GameRules.BooleanValue> REMOVE_OVERWORLD_BIOMES;
    public static GameRules.Key<GameRules.BooleanValue> REMOVE_NETHER_BIOMES;
    public static GameRules.Key<GameRules.BooleanValue> REMOVE_END_BIOMES;

    public static GameRules.Key<GameRules.BooleanValue> PLAYER_CAN_BE_ON_FIRE;
    public static GameRules.Key<GameRules.BooleanValue> PLAYER_CAN_SPRINT;
    public static GameRules.Key<GameRules.BooleanValue> PLAYER_CAN_CROUCH;
    public static GameRules.Key<GameRules.BooleanValue> PLAYER_CAN_SWIM;
    public static GameRules.Key<GameRules.BooleanValue> PLAYER_CAN_JUMP;
    public static GameRules.Key<GameRules.BooleanValue> PLAYER_CAN_BE_INVISIBLE;

    public static GameRules.Key<GameRules.IntegerValue> REPEATER_BASE_DELAY;
    public static GameRules.Key<GameRules.IntegerValue> REPEATER_SIGNAL;
    public static GameRules.Key<GameRules.IntegerValue> COMPARATOR_BASE_DELAY;
    public static GameRules.Key<GameRules.BooleanValue> COMPARATOR_ENABLED;
    public static GameRules.Key<GameRules.IntegerValue> TORCH_REDSTONE_SIGNAL;
    public static GameRules.Key<GameRules.BooleanValue> REDSTONE_WIRE_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> DROPPER_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> DISPENSER_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> DAYLIGHT_SENSOR_ENABLED;
    public static GameRules.Key<GameRules.IntegerValue> WOOD_BUTTON_PRESS_DURATION;
    public static GameRules.Key<GameRules.IntegerValue> STONE_BUTTON_PRESS_DURATION;
    public static GameRules.Key<GameRules.BooleanValue> BUTTON_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> LEVER_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> LIGHTNING_ROD_ENABLED;
    public static GameRules.Key<GameRules.IntegerValue> OBSERVER_DELAY;
    public static GameRules.Key<GameRules.IntegerValue> OBSERVER_DURATION;
    public static GameRules.Key<GameRules.BooleanValue> OBSERVER_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> PRESSURE_PLATE_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> TARGET_BLOCK_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> TRAPPED_CHEST_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> TRIPWIRE_HOOK_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> PISTON_ENABLED;

    public static GameRules.Key<GameRules.BooleanValue> NETHER_PORTALS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> END_PORTALS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> END_GATEWAYS_ENABLED;
    public static GameRules.Key<GameRules.IntegerValue> NETHER_PORTAL_COOLDOWN;
    public static GameRules.Key<GameRules.BooleanValue> CROP_TRAMPLING;
    public static GameRules.Key<GameRules.BooleanValue> OLD_HUNGER;
    public static GameRules.Key<GameRules.BooleanValue> OLD_BOATS;
    public static GameRules.Key<GameRules.BooleanValue> BEACONS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> CONDUITS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> ICE_SLIDING;
    public static GameRules.Key<GameRules.BooleanValue> TOTEMS_ENABLED;
    public static GameRules.Key<GameRules.BooleanValue> BOW_SPAMMING;
    public static GameRules.Key<GameRules.BooleanValue> CROSSBOW_SPAMMING;
    public static GameRules.Key<GameRules.BooleanValue> CREATIVE_SWORD_CAN_BREAK_BLOCKS;
    public static GameRules.Key<GameRules.BooleanValue> PUSHABLE_BUDDING_AMETHYST;

    @Override
    public void onInitialize() {
        // Registering the gamerules that I previously defined
        if (VanillaDisableMixinConfigPlugin.damage) {
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
        }
        if (VanillaDisableMixinConfigPlugin.knockback) {
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
        }
        if (VanillaDisableMixinConfigPlugin.spawning) {
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
        }
        if (VanillaDisableMixinConfigPlugin.despawning) {
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
        }
        if (VanillaDisableMixinConfigPlugin.spawn_limits) {
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
        }
        if (VanillaDisableMixinConfigPlugin.commands) {
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
        }
        if (VanillaDisableMixinConfigPlugin.fluids) {
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
            BUBBLE_COLUMNS_ENABLED      = GameRuleRegistry.register(
                "bubbleColumnsEnabled",    CreateGameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.mob) {
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
            VILLAGER_TRADING_ENABLED        = GameRuleRegistry.register(
                "villagerTradingEnabled",      CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            PIGLIN_BARTERING_ENABLED        = GameRuleRegistry.register(
                "piglinBarteringEnabled",      CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            WITHER_SPAWNS                   = GameRuleRegistry.register(
                "witherSpawns",                CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            PIGS_BREED_WITH_WHEAT           = GameRuleRegistry.register(
                "pigsBreedWithWheat",          CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(false));
            MOBS_BURN_IN_SUNLIGHT           = GameRuleRegistry.register(
                "mobsBurnInSunlight",          CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            DRAGON_FIREBALLS                = GameRuleRegistry.register(
                "dragonFireballs",             CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            FIRE_ASPECT_IGNITES_CREEPERS    = GameRuleRegistry.register(
                "fireAspectIgnitesCreepers",   CreateGameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(false));
        }
        if (VanillaDisableMixinConfigPlugin.effects) {
            EFFECTS_ENABLED          = GameRuleRegistry.register(
                "effectsEnabled",       CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            ABSORPTION_EFFECT        = GameRuleRegistry.register(
                "absorptionEffect",     CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
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
            MILK_CLEARS_EFFECTS      = GameRuleRegistry.register(
                "milkClearsEffects",    CreateGameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.enchantments) {
            ENCHANTMENTS_ENABLED                 = GameRuleRegistry.register(
                "enchantmentsEnabled",             CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            AQUA_AFFINITY_ENCHANTMENT            = GameRuleRegistry.register(
                "aquaAffinityEnchantment",         CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
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
            BOOT_ENCHANTMENT_CONFLICTS           = GameRuleRegistry.register(
                "bootEnchantmentConflicts",        CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            BOW_ENCHANTMENT_CONFLICTS            = GameRuleRegistry.register(
                "bowEnchantmentConflicts",         CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            CROSSBOW_ENCHANTMENT_CONFLICTS       = GameRuleRegistry.register(
                "crossbowEnchantmentConflicts",    CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            DAMAGE_ENCHANTMENT_CONFLICTS         = GameRuleRegistry.register(
                "damageEnchantmentConflicts",      CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            MINING_ENCHANTMENT_CONFLICTS         = GameRuleRegistry.register(
                "miningEnchantmentConflicts",      CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            PROTECTION_ENCHANTMENT_CONFLICTS     = GameRuleRegistry.register(
                "protectionEnchantmentConflicts",  CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            TRIDENT_ENCHANTMENT_CONFLICTS        = GameRuleRegistry.register(
                "tridentEnchantmentConflicts",     CreateGameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.worldgen) {
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
            END_VEGETATION                 = GameRuleRegistry.register(
                "endVegetation",            CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            NETHER_VEGETATION              = GameRuleRegistry.register(
                "netherVegetation",         CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            OCEAN_VEGETATION               = GameRuleRegistry.register(
                "oceanVegetation",          CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            OVERWORLD_VEGETATION           = GameRuleRegistry.register(
                "overworldVegetation",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            UNDERGROUND_VEGETATION         = GameRuleRegistry.register(
                "undergroundVegetation",    CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            AMETHYST_GEODE_GENERATION      = GameRuleRegistry.register(
                "amethystGeodeGeneration",  CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            BASALT_BLACKSTONE_GENERATION   = GameRuleRegistry.register(
                "basaltBlackstoneGeneration",CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            DESERT_WELL_GENERATION         = GameRuleRegistry.register(
                "desertWellGeneration",     CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            DRIPSTONE_GENERATION           = GameRuleRegistry.register(
                "dripstoneGeneration",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            DUNGEON_GENERATION             = GameRuleRegistry.register(
                "dungeonGeneration",        CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            END_FEATURES_GENERATION        = GameRuleRegistry.register(
                "endFeaturesGeneration",    CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            FOSSIL_GENERATION              = GameRuleRegistry.register(
                "fossilGeneration",         CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            GLOWSTONE_GENERATION           = GameRuleRegistry.register(
                "glowstoneGeneration",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            ICE_GENERATION                 = GameRuleRegistry.register(
                "iceGeneration",            CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            LAVA_LAKE_GENERATION           = GameRuleRegistry.register(
                "lavaLakeGeneration",       CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            MAGMA_GENERATION               = GameRuleRegistry.register(
                "magmaGeneration",          CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            NETHER_FIRE_GENERATION         = GameRuleRegistry.register(
                "netherFireGeneration",     CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            NETHER_ORE_GENERATION          = GameRuleRegistry.register(
                "netherOreGeneration",      CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            OCEAN_FLOOR_GENERATION         = GameRuleRegistry.register(
                "oceanFloorGeneration",     CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            ORE_GENERATION                 = GameRuleRegistry.register(
                "oreGeneration",            CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            SPRING_GENERATION              = GameRuleRegistry.register(
                "springGeneration",         CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            TREE_GENERATION                = GameRuleRegistry.register(
                "treeGeneration",           CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            WELL_GENERATION                = GameRuleRegistry.register(
                "wellGeneration",           CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            END_PILLAR_CAGE_GENERATION     = GameRuleRegistry.register(
                "endPillarCageGeneration",  CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            REMOVE_OVERWORLD_BIOMES        = GameRuleRegistry.register(
                "removeOverworldBiomes",    CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(false));
            REMOVE_NETHER_BIOMES           = GameRuleRegistry.register(
                "removeNetherBiomes",       CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(false));
            REMOVE_END_BIOMES              = GameRuleRegistry.register(
                "removeEndBiomes",          CreateGameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(false));
        }
        if (VanillaDisableMixinConfigPlugin.player) {
            PLAYER_CAN_BE_ON_FIRE   = GameRuleRegistry.register(
                "playerCanBeOnFire",   CreateGameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            PLAYER_CAN_SPRINT       = GameRuleRegistry.register(
                "playerCanSprint",     CreateGameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            PLAYER_CAN_CROUCH       = GameRuleRegistry.register(
                "playerCanCrouch",     CreateGameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            PLAYER_CAN_SWIM         = GameRuleRegistry.register(
                "playerCanSwim",       CreateGameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            PLAYER_CAN_JUMP         = GameRuleRegistry.register(
                "playerCanJump",       CreateGameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            PLAYER_CAN_BE_INVISIBLE = GameRuleRegistry.register(
                "playerCanBeInvisible",CreateGameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.redstone) {
            REPEATER_BASE_DELAY         = GameRuleRegistry.register(
                "repeaterBaseDelay",       CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            REPEATER_SIGNAL            = GameRuleRegistry.register(
                "repeaterSignal",          CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(15, 0, 15));
            COMPARATOR_BASE_DELAY       = GameRuleRegistry.register(
                "comparatorBaseDelay",     CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            COMPARATOR_ENABLED          = GameRuleRegistry.register(
                "comparatorEnabled",       CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            TORCH_REDSTONE_SIGNAL       = GameRuleRegistry.register(
                "torchRedstoneSignal",     CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(15, 0, 15));
            REDSTONE_WIRE_ENABLED       = GameRuleRegistry.register(
                "redstoneWireEnabled",     CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            DROPPER_ENABLED             = GameRuleRegistry.register(
                "dropperEnabled",          CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            DISPENSER_ENABLED           = GameRuleRegistry.register(
                "dispenserEnabled",        CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            DAYLIGHT_SENSOR_ENABLED     = GameRuleRegistry.register(
                "daylightSensorEnabled",   CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            WOOD_BUTTON_PRESS_DURATION  = GameRuleRegistry.register(
                "woodButtonPressDuration", CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(30, 0));
            STONE_BUTTON_PRESS_DURATION = GameRuleRegistry.register(
                "stoneButtonPressDuration",CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(20, 0));
            BUTTON_ENABLED              = GameRuleRegistry.register(
                "buttonEnabled",           CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            LEVER_ENABLED               = GameRuleRegistry.register(
                "leverEnabled",            CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            LIGHTNING_ROD_ENABLED       = GameRuleRegistry.register(
                "lightningRodEnabled",     CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            OBSERVER_DELAY              = GameRuleRegistry.register(
                "observerDelay",           CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            OBSERVER_DURATION           = GameRuleRegistry.register(
                "observerDuration",        CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            OBSERVER_ENABLED            = GameRuleRegistry.register(
                "observerEnabled",         CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            PRESSURE_PLATE_ENABLED      = GameRuleRegistry.register(
                "pressurePlateEnabled",    CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            TARGET_BLOCK_ENABLED        = GameRuleRegistry.register(
                "targetBlockEnabled",      CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            TRAPPED_CHEST_ENABLED       = GameRuleRegistry.register(
                "trappedChestEnabled",     CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            TRIPWIRE_HOOK_ENABLED       = GameRuleRegistry.register(
                "tripwireHookEnabled",     CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            PISTON_ENABLED              = GameRuleRegistry.register(
                "pistonEnabled",           CreateGameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.misc) {
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
            if (VanillaDisableMixinConfigPlugin.misc_hunger) {
                OLD_HUNGER               = GameRuleRegistry.register(
                    "oldHunger",            CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            }
            OLD_BOATS                = GameRuleRegistry.register(
                "oldBoats",             CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            BEACONS_ENABLED          = GameRuleRegistry.register(
                "beaconsEnabled",       CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            CONDUITS_ENABLED         = GameRuleRegistry.register(
                "conduitsEnabled",      CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            ICE_SLIDING              = GameRuleRegistry.register(
                "iceSliding",           CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            TOTEMS_ENABLED           = GameRuleRegistry.register(
                "totemsEnabled",        CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            BOW_SPAMMING             = GameRuleRegistry.register(
                "bowSpamming",          CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            CROSSBOW_SPAMMING        = GameRuleRegistry.register(
                "crossbowSpamming",     CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            CREATIVE_SWORD_CAN_BREAK_BLOCKS = GameRuleRegistry.register(
                "creativeSwordCanBreakBlocks", CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            PUSHABLE_BUDDING_AMETHYST       = GameRuleRegistry.register(
                "pushableBuddingAmethyst",     CreateGameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
        }

        // Registering the Minecraft server to when it actually starts
        ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
            RegisterGamerules.server = minecraftServer;
        });

        // Fabric is deprecated
        if (!FabricLoader.getInstance().isModLoaded("quilt_loader")) {
            for (int i = 0; i < 30; ++i) {
                logger.warn("Using this mod on Fabric is deprecated. This mod will be Quilt only from 1.19.");
            }
        }
    }
}