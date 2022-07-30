package uk.debb.vanilla_disable.util.gamerules;

import net.minecraft.world.level.GameRules;
import static uk.debb.vanilla_disable.util.gamerules.GameruleCategories.*;

public enum BooleanGamerules {
    DAMAGE_ENABLED(VD_DAMAGE, true),
    PROJECTILE_DAMAGE(VD_DAMAGE, true),
    EXPLOSION_DAMAGE(VD_DAMAGE, true),
    FALLING_BLOCK_DAMAGE(VD_DAMAGE, true),
    VOID_DAMAGE(VD_DAMAGE, true),
    MAGIC_DAMAGE(VD_DAMAGE, true),
    CREATIVE_PLAYER_DAMAGE(VD_DAMAGE, true),
    LIGHTNING_DAMAGE(VD_DAMAGE, true),
    WALL_DAMAGE(VD_DAMAGE, true),
    CRAMMING_DAMAGE(VD_DAMAGE, true),
    STARVATION_DAMAGE(VD_DAMAGE, true),
    CACTUS_DAMAGE(VD_DAMAGE, true),
    FLY_INTO_WALL_DAMAGE(VD_DAMAGE, true),
    WITHER_DAMAGE(VD_DAMAGE, true),
    ANVIL_DAMAGE(VD_DAMAGE, true),
    DRAGON_DAMAGE(VD_DAMAGE, true),
    SWEET_BERRY_BUSH_DAMAGE(VD_DAMAGE, true),
    FALLING_STALACTITE_DAMAGE(VD_DAMAGE, true),

    KNOCKBACK_ENABLED(VD_KNOCKBACK, true),
    FIREBALL_KNOCKBACK(VD_KNOCKBACK, true),
    WITHER_SKULL_KNOCKBACK(VD_KNOCKBACK, true),
    DRAGON_KNOCKBACK(VD_KNOCKBACK, true),
    ARROW_KNOCKBACK(VD_KNOCKBACK, true),
    TRIDENT_KNOCKBACK(VD_KNOCKBACK, true),
    LLAMA_SPIT_KNOCKBACK(VD_KNOCKBACK, true),
    SHULKER_BULLET_KNOCKBACK(VD_KNOCKBACK, true),
    MOB_ATTACK_KNOCKBACK(VD_KNOCKBACK, true),
    PLAYER_ATTACK_KNOCKBACK(VD_KNOCKBACK, true),
    EXPLOSION_KNOCKBACK(VD_KNOCKBACK, true),

    MONSTER_SPAWNING(VD_SPAWNING, true),
    CREATURE_SPAWNING(VD_SPAWNING, true),
    AMBIENT_SPAWNING(VD_SPAWNING, true),
    AXOLOTL_SPAWNING(VD_SPAWNING, true),
    GLOWSQUID_SPAWNING(VD_SPAWNING, true),
    WATER_CREATURE_SPAWNING(VD_SPAWNING, true),
    WATER_AMBIENT_SPAWNING(VD_SPAWNING, true),
    SPAWNERS_ENABLED(VD_SPAWNING, true),
    PIG_SPAWNERS(VD_SPAWNING, true),
    CAVE_SPIDER_SPAWNERS(VD_SPAWNING, true),
    SILVERFISH_SPAWNERS(VD_SPAWNING, true),
    ZOMBIE_SPAWNERS(VD_SPAWNING, true),
    SKELETON_SPAWNERS(VD_SPAWNING, true),
    BLAZE_SPAWNERS(VD_SPAWNING, true),
    SPIDER_SPAWNERS(VD_SPAWNING, true),
    MAGMA_CUBE_SPAWNERS(VD_SPAWNING, true),
    ANIMAL_BREEDING(VD_SPAWNING, true),

    MONSTERS_DESPAWN(VD_DESPAWNING, true),
    CREATURES_DESPAWN(VD_DESPAWNING, true),
    AMBIENT_DESPAWN(VD_DESPAWNING, true),
    AXOLOTLS_DESPAWN(VD_DESPAWNING, true),
    GLOWSQUIDS_DESPAWN(VD_DESPAWNING, true),
    WATER_CREATURES_DESPAWN(VD_DESPAWNING, true),
    WATER_AMBIENT_DESPAWN(VD_DESPAWNING, true),
    ENDER_PEARLS_DESPAWN_ON_DEATH(VD_DESPAWNING, true),

    COMMANDS_ENABLED(VD_COMMANDS, true),
    ADVANCEMENT_COMMAND(VD_COMMANDS, true),
    ATTRIBUTE_COMMAND(VD_COMMANDS, true),
    BOSS_BAR_COMMAND(VD_COMMANDS, true),
    CHASE_COMMAND(VD_COMMANDS, true),
    CLEAR_COMMAND(VD_COMMANDS, true),
    CLONE_COMMAND(VD_COMMANDS, true),
    DATAPACK_COMMAND(VD_COMMANDS, true),
    DATA_COMMAND(VD_COMMANDS, true),
    DIFFICULTY_COMMAND(VD_COMMANDS, true),
    EFFECT_COMMAND(VD_COMMANDS, true),
    ENCHANT_COMMAND(VD_COMMANDS, true),
    EXECUTE_COMMAND(VD_COMMANDS, true),
    EXPERIENCE_COMMAND(VD_COMMANDS, true),
    FILL_COMMAND(VD_COMMANDS, true),
    FORCE_LOAD_COMMAND(VD_COMMANDS, true),
    FUNCTION_COMMAND(VD_COMMANDS, true),
    GAME_MODE_COMMAND(VD_COMMANDS, true),
    GIVE_COMMAND(VD_COMMANDS, true),
    HELP_COMMAND(VD_COMMANDS, true),
    ITEM_COMMAND(VD_COMMANDS, true),
    JFR_COMMAND(VD_COMMANDS, true),
    KICK_COMMAND(VD_COMMANDS, true),
    KILL_COMMAND(VD_COMMANDS, true),
    LIST_COMMAND(VD_COMMANDS, true),
    LOCATE_COMMAND(VD_COMMANDS, true),
    LOOT_COMMAND(VD_COMMANDS, true),
    ME_COMMAND(VD_COMMANDS, true),
    MESSAGE_COMMAND(VD_COMMANDS, true),
    PARTICLE_COMMAND(VD_COMMANDS, true),
    PLACE_COMMAND(VD_COMMANDS, true),
    PLAY_SOUND_COMMAND(VD_COMMANDS, true),
    PUBLISH_COMMAND(VD_COMMANDS, true),
    RAID_COMMAND(VD_COMMANDS, true),
    RECIPE_COMMAND(VD_COMMANDS, true),
    RELOAD_COMMAND(VD_COMMANDS, true),
    RESET_CHUNKS_COMMAND(VD_COMMANDS, true),
    SAY_COMMAND(VD_COMMANDS, true),
    SCHEDULE_COMMAND(VD_COMMANDS, true),
    SCOREBOARD_COMMAND(VD_COMMANDS, true),
    SEED_COMMAND(VD_COMMANDS, true),
    SET_BLOCK_COMMAND(VD_COMMANDS, true),
    SET_WORLD_SPAWN_COMMAND(VD_COMMANDS, true),
    SPAWN_POINT_COMMAND(VD_COMMANDS, true),
    SPECTATE_COMMAND(VD_COMMANDS, true),
    SPREAD_PLAYERS_COMMAND(VD_COMMANDS, true),
    STOP_SOUND_COMMAND(VD_COMMANDS, true),
    SUMMON_COMMAND(VD_COMMANDS, true),
    TAG_COMMAND(VD_COMMANDS, true),
    TEAM_COMMAND(VD_COMMANDS, true),
    TEAM_MSG_COMMAND(VD_COMMANDS, true),
    TELEPORT_COMMAND(VD_COMMANDS, true),
    TELL_RAW_COMMAND(VD_COMMANDS, true),
    TIME_COMMAND(VD_COMMANDS, true),
    TITLE_COMMAND(VD_COMMANDS, true),
    TRIGGER_COMMAND(VD_COMMANDS, true),
    WEATHER_COMMAND(VD_COMMANDS, true),
    WORLD_BORDER_COMMAND(VD_COMMANDS, true),
    BAN_DEDICATED_COMMAND(VD_COMMANDS, true),
    BAN_IP_DEDICATED_COMMAND(VD_COMMANDS, true),
    BAN_LIST_DEDICATED_COMMAND(VD_COMMANDS, true),
    DE_OP_DEDICATED_COMMAND(VD_COMMANDS, true),
    OP_DEDICATED_COMMAND(VD_COMMANDS, true),
    PARDON_DEDICATED_COMMAND(VD_COMMANDS, true),
    PARDON_IP_DEDICATED_COMMAND(VD_COMMANDS, true),
    PERF_DEDICATED_COMMAND(VD_COMMANDS, true),
    SAVE_ALL_DEDICATED_COMMAND(VD_COMMANDS, true),
    SAVE_OFF_DEDICATED_COMMAND(VD_COMMANDS, true),
    SAVE_ON_DEDICATED_COMMAND(VD_COMMANDS, true),
    SET_IDLE_TIMEOUT_DEDICATED_COMMAND(VD_COMMANDS, true),
    STOP_DEDICATED_COMMAND(VD_COMMANDS, true),
    WHITELIST_DEDICATED_COMMAND(VD_COMMANDS, true),

    INFINITE_WATER(VD_FLUIDS, true),
    INFINITE_LAVA(VD_FLUIDS, false),
    WATER_REACHES_FAR(VD_FLUIDS, true),
    LAVA_REACHES_FAR(VD_FLUIDS, false),
    LAVA_REACHES_FAR_IN_NETHER(VD_FLUIDS, true),
    WATER_PLACEABLE_IN_NETHER(VD_FLUIDS, false),
    BUBBLE_COLUMNS_ENABLED(VD_FLUIDS, true),

    CURABLE_ZILLAGERS(VD_MOBS, true),
    VILLAGERS_CONVERT_TO_ZILLAGERS(VD_MOBS, true),
    VILLAGERS_CONVERT_TO_WITCHES(VD_MOBS, true),
    PIGLINS_CONVERT_TO_ZIGLINS(VD_MOBS, true),
    HOGLINS_CONVERT_TO_ZOGLINS(VD_MOBS, true),
    HUSKS_CONVERT_TO_ZOMBIES(VD_MOBS, true),
    ZOMBIES_CONVERT_TO_DROWNED(VD_MOBS, true),
    SKELETONS_CONVERT_TO_STRAYS(VD_MOBS, true),
    INFINITE_TRADING(VD_MOBS, false),
    VILLAGER_TRADING_ENABLED(VD_MOBS, true),
    PIGLIN_BARTERING_ENABLED(VD_MOBS, true),
    PIGS_BREED_WITH_WHEAT(VD_MOBS, true),
    MOBS_BURN_IN_SUNLIGHT(VD_MOBS, true),
    DRAGON_FIREBALLS(VD_MOBS, true),
    FIRE_ASPECT_IGNITES_CREEPERS(VD_MOBS, false),

    ALLAYS_ENABLED(VD_MOB_TOGGLES, true),
    BATS_ENABLED(VD_MOB_TOGGLES, true),
    CATS_ENABLED(VD_MOB_TOGGLES, true),
    CHICKENS_ENABLED(VD_MOB_TOGGLES, true),
    CODS_ENABLED(VD_MOB_TOGGLES, true),
    COWS_ENABLED(VD_MOB_TOGGLES, true),
    DONKEYS_ENABLED(VD_MOB_TOGGLES, true),
    FOXES_ENABLED(VD_MOB_TOGGLES, true),
    FROGS_ENABLED(VD_MOB_TOGGLES, true),
    HORSES_ENABLED(VD_MOB_TOGGLES, true),
    MOOSHROOMS_ENABLED(VD_MOB_TOGGLES, true),
    MULES_ENABLED(VD_MOB_TOGGLES, true),
    OCELOTS_ENABLED(VD_MOB_TOGGLES, true),
    PARROTS_ENABLED(VD_MOB_TOGGLES, true),
    PIGS_ENABLED(VD_MOB_TOGGLES, true),
    PUFFERFISH_ENABLED(VD_MOB_TOGGLES, true),
    RABBITS_ENABLED(VD_MOB_TOGGLES, true),
    SALMONS_ENABLED(VD_MOB_TOGGLES, true),
    SHEEP_ENABLED(VD_MOB_TOGGLES, true),
    SKELETON_HORSES_ENABLED(VD_MOB_TOGGLES, true),
    SNOW_GOLEMS_ENABLED(VD_MOB_TOGGLES, true),
    SQUIDS_ENABLED(VD_MOB_TOGGLES, true),
    STRIDERS_ENABLED(VD_MOB_TOGGLES, true),
    TADPOLES_ENABLED(VD_MOB_TOGGLES, true),
    TROPICAL_FISH_ENABLED(VD_MOB_TOGGLES, true),
    TURTLES_ENABLED(VD_MOB_TOGGLES, true),
    VILLAGERS_ENABLED(VD_MOB_TOGGLES, true),
    WANDERING_TRADERS_ENABLED(VD_MOB_TOGGLES, true),
    BEES_ENABLED(VD_MOB_TOGGLES, true),
    CAVE_SPIDERS_ENABLED(VD_MOB_TOGGLES, true),
    DOLPHINS_ENABLED(VD_MOB_TOGGLES, true),
    ENDERMEN_ENABLED(VD_MOB_TOGGLES, true),
    GOATS_ENABLED(VD_MOB_TOGGLES, true),
    IRON_GOLEMS_ENABLED(VD_MOB_TOGGLES, true),
    LLAMAS_ENABLED(VD_MOB_TOGGLES, true),
    PANDAS_ENABLED(VD_MOB_TOGGLES, true),
    PIGLINS_ENABLED(VD_MOB_TOGGLES, true),
    POLAR_BEARS_ENABLED(VD_MOB_TOGGLES, true),
    SPIDERS_ENABLED(VD_MOB_TOGGLES, true),
    TRADER_LLAMAS_ENABLED(VD_MOB_TOGGLES, true),
    WOLVES_ENABLED(VD_MOB_TOGGLES, true),
    ZOMBIFIED_PIGLINS_ENABLED(VD_MOB_TOGGLES, true),
    BLAZES_ENABLED(VD_MOB_TOGGLES, true),
    CREEPERS_ENABLED(VD_MOB_TOGGLES, true),
    DROWNED_ENABLED(VD_MOB_TOGGLES, true),
    ELDER_GUARDIANS_ENABLED(VD_MOB_TOGGLES, true),
    ENDERMITES_ENABLED(VD_MOB_TOGGLES, true),
    EVOKERS_ENABLED(VD_MOB_TOGGLES, true),
    GHASTS_ENABLED(VD_MOB_TOGGLES, true),
    GUARDIANS_ENABLED(VD_MOB_TOGGLES, true),
    HOGLINS_ENABLED(VD_MOB_TOGGLES, true),
    HUSKS_ENABLED(VD_MOB_TOGGLES, true),
    MAGMA_CUBES_ENABLED(VD_MOB_TOGGLES, true),
    PHANTOMS_ENABLED(VD_MOB_TOGGLES, true),
    PIGLIN_BRUTES_ENABLED(VD_MOB_TOGGLES, true),
    PILLAGERS_ENABLED(VD_MOB_TOGGLES, true),
    RAVAGERS_ENABLED(VD_MOB_TOGGLES, true),
    SHULKERS_ENABLED(VD_MOB_TOGGLES, true),
    SILVERFISH_ENABLED(VD_MOB_TOGGLES, true),
    SKELETONS_ENABLED(VD_MOB_TOGGLES, true),
    SLIMES_ENABLED(VD_MOB_TOGGLES, true),
    STRAYS_ENABLED(VD_MOB_TOGGLES, true),
    VEXES_ENABLED(VD_MOB_TOGGLES, true),
    VINDICATORS_ENABLED(VD_MOB_TOGGLES, true),
    WARDENS_ENABLED(VD_MOB_TOGGLES, true),
    WITCHES_ENABLED(VD_MOB_TOGGLES, true),
    WITHER_SKELETONS_ENABLED(VD_MOB_TOGGLES, true),
    ZOGLINS_ENABLED(VD_MOB_TOGGLES, true),
    ZOMBIES_ENABLED(VD_MOB_TOGGLES, true),
    ZOMBIE_VILLAGERS_ENABLED(VD_MOB_TOGGLES, true),
    DRAGONS_ENABLED(VD_MOB_TOGGLES, true),
    WITHERS_ENABLED(VD_MOB_TOGGLES, true),

    EFFECTS_ENABLED(VD_EFFECTS, true),
    ABSORPTION_EFFECT(VD_EFFECTS, true),
    BAD_OMEN_EFFECT(VD_EFFECTS, true),
    BLINDNESS_EFFECT(VD_EFFECTS, true),
    CONDUIT_POWER_EFFECT(VD_EFFECTS, true),
    DARKNESS_EFFECT(VD_EFFECTS, true),
    DOLPHINS_GRACE_EFFECT(VD_EFFECTS, true),
    FIRE_RESISTANCE_EFFECT(VD_EFFECTS, true),
    GLOWING_EFFECT(VD_EFFECTS, true),
    HASTE_EFFECT(VD_EFFECTS, true),
    HEALTH_BOOST_EFFECT(VD_EFFECTS, true),
    HUNGER_EFFECT(VD_EFFECTS, true),
    INSTANT_DAMAGE_EFFECT(VD_EFFECTS, true),
    INSTANT_HEALTH_EFFECT(VD_EFFECTS, true),
    INVISIBILITY_EFFECT(VD_EFFECTS, true),
    JUMP_BOOST_EFFECT(VD_EFFECTS, true),
    LEVITATION_EFFECT(VD_EFFECTS, true),
    LUCK_EFFECT(VD_EFFECTS, true),
    MINING_FATIGUE_EFFECT(VD_EFFECTS, true),
    NAUSEA_EFFECT(VD_EFFECTS, true),
    NIGHT_VISION_EFFECT(VD_EFFECTS, true),
    POISON_EFFECT(VD_EFFECTS, true),
    REGENERATION_EFFECT(VD_EFFECTS, true),
    RESISTANCE_EFFECT(VD_EFFECTS, true),
    SATURATION_EFFECT(VD_EFFECTS, true),
    SLOWNESS_EFFECT(VD_EFFECTS, true),
    SLOW_FALLING_EFFECT(VD_EFFECTS, true),
    SPEED_EFFECT(VD_EFFECTS, true),
    STRENGTH_EFFECT(VD_EFFECTS, true),
    UNLUCK_EFFECT(VD_EFFECTS, true),
    WATER_BREATHING_EFFECT(VD_EFFECTS, true),
    WEAKNESS_EFFECT(VD_EFFECTS, true),
    WITHER_EFFECT(VD_EFFECTS, true),

    ENCHANTMENTS_ENABLED(VD_ENCHANTMENTS, true),
    AQUA_AFFINITY_ENCHANTMENT(VD_ENCHANTMENTS, true),
    BANE_OF_ARTHROPODS_ENCHANTMENT(VD_ENCHANTMENTS, true),
    BLAST_PROTECTION_ENCHANTMENT(VD_ENCHANTMENTS, true),
    CHANNELING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    DEPTH_STRIDER_ENCHANTMENT(VD_ENCHANTMENTS, true),
    EFFICIENCY_ENCHANTMENT(VD_ENCHANTMENTS, true),
    FEATHER_FALLING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    FIRE_ASPECT_ENCHANTMENT(VD_ENCHANTMENTS, true),
    FIRE_PROTECTION_ENCHANTMENT(VD_ENCHANTMENTS, true),
    FLAME_ENCHANTMENT(VD_ENCHANTMENTS, true),
    FORTUNE_ENCHANTMENT(VD_ENCHANTMENTS, true),
    FROST_WALKER_ENCHANTMENT(VD_ENCHANTMENTS, true),
    IMPALING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    INFINITY_ENCHANTMENT(VD_ENCHANTMENTS, true),
    KNOCKBACK_ENCHANTMENT(VD_ENCHANTMENTS, true),
    LOOTING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    LOYALTY_ENCHANTMENT(VD_ENCHANTMENTS, true),
    LUCK_OF_THE_SEA_ENCHANTMENT(VD_ENCHANTMENTS, true),
    LURE_ENCHANTMENT(VD_ENCHANTMENTS, true),
    MENDING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    MULTISHOT_ENCHANTMENT(VD_ENCHANTMENTS, true),
    PIERCING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    POWER_ENCHANTMENT(VD_ENCHANTMENTS, true),
    PROJECTILE_PROTECTION_ENCHANTMENT(VD_ENCHANTMENTS, true),
    PROTECTION_ENCHANTMENT(VD_ENCHANTMENTS, true),
    PUNCH_ENCHANTMENT(VD_ENCHANTMENTS, true),
    QUICK_CHARGE_ENCHANTMENT(VD_ENCHANTMENTS, true),
    RESPIRATION_ENCHANTMENT(VD_ENCHANTMENTS, true),
    RIPTIDE_ENCHANTMENT(VD_ENCHANTMENTS, true),
    SHARPNESS_ENCHANTMENT(VD_ENCHANTMENTS, true),
    SILK_TOUCH_ENCHANTMENT(VD_ENCHANTMENTS, true),
    SMITE_ENCHANTMENT(VD_ENCHANTMENTS, true),
    SOUL_SPEED_ENCHANTMENT(VD_ENCHANTMENTS, true),
    SWEEPING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    SWIFT_SNEAK_ENCHANTMENT(VD_ENCHANTMENTS, true),
    THORNS_ENCHANTMENT(VD_ENCHANTMENTS, true),
    UNBREAKING_ENCHANTMENT(VD_ENCHANTMENTS, true),
    BINDING_CURSE(VD_ENCHANTMENTS, true),
    VANISHING_CURSE(VD_ENCHANTMENTS, true),
    BOOT_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),
    BOW_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),
    CROSSBOW_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),
    DAMAGE_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),
    MINING_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),
    PROTECTION_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),
    TRIDENT_ENCHANTMENT_CONFLICTS(VD_ENCHANTMENTS, true),

    ANCIENT_CITY_GENERATION(VD_WORLDGEN, true),
    BASTION_REMNANT_GENERATION(VD_WORLDGEN, true),
    BURIED_TREASURE_GENERATION(VD_WORLDGEN, true),
    DESERT_PYRAMID_GENERATION(VD_WORLDGEN, true),
    END_CITY_GENERATION(VD_WORLDGEN, true),
    FORTRESS_GENERATION(VD_WORLDGEN, true),
    IGLOO_GENERATION(VD_WORLDGEN, true),
    JUNGLE_PYRAMID_GENERATION(VD_WORLDGEN, true),
    MANSION_GENERATION(VD_WORLDGEN, true),
    MINESHAFT_GENERATION(VD_WORLDGEN, true),
    MONUMENT_GENERATION(VD_WORLDGEN, true),
    NETHER_FOSSIL_GENERATION(VD_WORLDGEN, true),
    OCEAN_RUIN_GENERATION(VD_WORLDGEN, true),
    PILLAGER_OUTPOST_GENERATION(VD_WORLDGEN, true),
    RUINED_PORTAL_GENERATION(VD_WORLDGEN, true),
    SHIPWRECK_GENERATION(VD_WORLDGEN, true),
    STRONGHOLD_GENERATION(VD_WORLDGEN, true),
    SWAMP_HUT_GENERATION(VD_WORLDGEN, true),
    VILLAGE_GENERATION(VD_WORLDGEN, true),
    END_VEGETATION(VD_WORLDGEN, true),
    NETHER_VEGETATION(VD_WORLDGEN, true),
    OCEAN_VEGETATION(VD_WORLDGEN, true),
    OVERWORLD_VEGETATION(VD_WORLDGEN, true),
    UNDERGROUND_VEGETATION(VD_WORLDGEN, true),
    AMETHYST_GEODE_GENERATION(VD_WORLDGEN, true),
    BASALT_BLACKSTONE_GENERATION(VD_WORLDGEN, true),
    DESERT_WELL_GENERATION(VD_WORLDGEN, true),
    DRIPSTONE_GENERATION(VD_WORLDGEN, true),
    DUNGEON_GENERATION(VD_WORLDGEN, true),
    END_FEATURES_GENERATION(VD_WORLDGEN, true),
    FOSSIL_GENERATION(VD_WORLDGEN, true),
    GLOWSTONE_GENERATION(VD_WORLDGEN, true),
    ICE_GENERATION(VD_WORLDGEN, true),
    LAVA_LAKE_GENERATION(VD_WORLDGEN, true),
    MAGMA_GENERATION(VD_WORLDGEN, true),
    NETHER_FIRE_GENERATION(VD_WORLDGEN, true),
    NETHER_ORE_GENERATION(VD_WORLDGEN, true),
    OCEAN_FLOOR_GENERATION(VD_WORLDGEN, true),
    ORE_GENERATION(VD_WORLDGEN, true),
    SCULK_GENERATION(VD_WORLDGEN, true),
    SPRING_GENERATION(VD_WORLDGEN, true),
    TREE_GENERATION(VD_WORLDGEN, true),
    END_PILLAR_CAGE_GENERATION(VD_WORLDGEN, true),
    REMOVE_OVERWORLD_BIOMES(VD_WORLDGEN, false),
    REMOVE_NETHER_BIOMES(VD_WORLDGEN, false),
    REMOVE_END_BIOMES(VD_WORLDGEN, false),

    PLAYER_CAN_BE_ON_FIRE(VD_PLAYER, true),
    PLAYER_CAN_SPRINT(VD_PLAYER, true),
    PLAYER_CAN_CROUCH(VD_PLAYER, true),
    PLAYER_CAN_SWIM(VD_PLAYER, true),
    PLAYER_CAN_JUMP(VD_PLAYER, true),
    PLAYER_CAN_BE_INVISIBLE(VD_PLAYER, true),

    COMPARATOR_ENABLED(VD_REDSTONE, true),
    REDSTONE_TORCH_ENABLED(VD_REDSTONE, true),
    REDSTONE_WIRE_ENABLED(VD_REDSTONE, true),
    DROPPER_ENABLED(VD_REDSTONE, true),
    DISPENSER_ENABLED(VD_REDSTONE, true),
    DAYLIGHT_SENSOR_ENABLED(VD_REDSTONE, true),
    BUTTON_ENABLED(VD_REDSTONE, true),
    LEVER_ENABLED(VD_REDSTONE, true),
    LIGHTNING_ROD_ENABLED(VD_REDSTONE, true),
    OBSERVER_ENABLED(VD_REDSTONE, true),
    PRESSURE_PLATE_ENABLED(VD_REDSTONE, true),
    TARGET_BLOCK_ENABLED(VD_REDSTONE, true),
    TRAPPED_CHEST_ENABLED(VD_REDSTONE, true),
    TRIPWIRE_HOOK_ENABLED(VD_REDSTONE, true),
    PISTON_ENABLED(VD_REDSTONE, true),
    SCULK_SENSOR_ENABLED(VD_REDSTONE, true),

    BEE_AI(VD_AI, true),
    BLAZE_AI(VD_AI, true),
    CAT_AI(VD_AI, true),
    DOLPHIN_AI(VD_AI, true),
    DROWNED_AI(VD_AI, true),
    ENDERMAN_AI(VD_AI, true),
    FISH_AI(VD_AI, true),
    FOX_AI(VD_AI, true),
    GHAST_AI(VD_AI, true),
    GOLEM_AI(VD_AI, true),
    GUARDIAN_AI(VD_AI, true),
    LLAMA_AI(VD_AI, true),
    OCELOT_AI(VD_AI, true),
    PANDA_AI(VD_AI, true),
    PARROT_AI(VD_AI, true),
    PHANTOM_AI(VD_AI, true),
    POLAR_BEAR_AI(VD_AI, true),
    PUFFERFISH_AI(VD_AI, true),
    RABBIT_AI(VD_AI, true),
    RAIDER_AI(VD_AI, true),
    SHULKER_AI(VD_AI, true),
    SILVERFISH_AI(VD_AI, true),
    SLIME_AI(VD_AI, true),
    SPIDER_AI(VD_AI, true),
    SQUID_AI(VD_AI, true),
    STRIDER_AI(VD_AI, true),
    TURTLE_AI(VD_AI, true),
    WOLF_AI(VD_AI, true),
    ZOMBIE_AI(VD_AI, true),

    NETHER_PORTALS_ENABLED(VD_BLOCKS, true),
    END_PORTALS_ENABLED(VD_BLOCKS, true),
    END_GATEWAYS_ENABLED(VD_BLOCKS, true),
    CROP_TRAMPLING(VD_BLOCKS, true),
    BEACONS_ENABLED(VD_BLOCKS, true),
    CONDUITS_ENABLED(VD_BLOCKS, true),
    CREATIVE_SWORD_CAN_BREAK_BLOCKS(VD_BLOCKS, false),
    PUSHABLE_BUDDING_AMETHYST(VD_BLOCKS, true),
    CONTAINER_OPENING_BLOCKED(VD_BLOCKS, true),
    OLD_TNT(VD_BLOCKS, false),
    FILLING_CAULDRONS(VD_BLOCKS, true),
    EMPTYING_CAULDRONS(VD_BLOCKS, true),
    CAULDRONS_CLEAN_LEATHER_ARMOUR(VD_BLOCKS, true),
    CAULDRONS_CLEAN_BANNERS(VD_BLOCKS, true),
    CAULDRONS_CLEAN_SHULKER_BOXES(VD_BLOCKS, true),
    DRIPSTONE_FILLS_CAULDRONS(VD_BLOCKS, true),
    PRECIPITATION_FILLS_CAULDRONS(VD_BLOCKS, true),

    OLD_HUNGER(VD_FOOD, false),

    POTIONS_ENABLED(VD_POTIONS, true),
    NORMAL_POTIONS_ENABLED(VD_POTIONS, true),
    SPLASH_POTIONS_ENABLED(VD_POTIONS, true),
    LINGERING_POTIONS_ENABLED(VD_POTIONS, true),
    FIRE_RESISTANCE_POTION(VD_POTIONS, true),
    HARMING_POTION(VD_POTIONS, true),
    HEALING_POTION(VD_POTIONS, true),
    INVISIBILITY_POTION(VD_POTIONS, true),
    LEAPING_POTION(VD_POTIONS, true),
    LUCK_POTION(VD_POTIONS, true),
    NIGHT_VISION_POTION(VD_POTIONS, true),
    POISON_POTION(VD_POTIONS, true),
    REGENERATION_POTION(VD_POTIONS, true),
    SLOWNESS_POTION(VD_POTIONS, true),
    SLOW_FALLING_POTION(VD_POTIONS, true),
    STRENGTH_POTION(VD_POTIONS, true),
    SWIFTNESS_POTION(VD_POTIONS, true),
    TURTLE_MASTER_POTION(VD_POTIONS, true),
    WATER_BREATHING_POTION(VD_POTIONS, true),
    WEAKNESS_POTION(VD_POTIONS, true),

    DEATH_ENABLED(VD_DEATH, true),
    ANVIL_DEATH(VD_DEATH, true),
    CACTUS_DEATH(VD_DEATH, true),
    CRAMMING_DEATH(VD_DEATH, true),
    DRAGON_BREATH_DEATH(VD_DEATH, true),
    DROWNING_DEATH(VD_DEATH, true),
    EXPLOSION_DEATH(VD_DEATH, true),
    FALLING_BLOCK_DEATH(VD_DEATH, true),
    FALLING_DEATH(VD_DEATH, true),
    FALLING_STALACTITE_DEATH(VD_DEATH, true),
    FLY_INTO_WALL_DEATH(VD_DEATH, true),
    FREEZING_DEATH(VD_DEATH, true),
    HOT_FLOOR_DEATH(VD_DEATH, true),
    IN_FIRE_DEATH(VD_DEATH, true),
    IN_WALL_DEATH(VD_DEATH, true),
    LAVA_DEATH(VD_DEATH, true),
    LIGHTNING_BOLT_DEATH(VD_DEATH, true),
    MAGIC_DEATH(VD_DEATH, true),
    MOB_DEATH(VD_DEATH, true),
    ON_FIRE_DEATH(VD_DEATH, true),
    OUT_OF_WORLD_DEATH(VD_DEATH, true),
    PLAYER_DEATH(VD_DEATH, true),
    SONIC_BOOM_DEATH(VD_DEATH, true),
    STALAGMITE_DEATH(VD_DEATH, true),
    STARVATION_DEATH(VD_DEATH, true),
    STINGING_DEATH(VD_DEATH, true),
    SWEET_BERRY_BUSH_DEATH(VD_DEATH, true),
    THORNS_DEATH(VD_DEATH, true),
    WITHER_DEATH(VD_DEATH, true),

    BOATS_ENABLED(VD_ITEMS, true),
    BOOKS_ENABLED(VD_ITEMS, true),
    BOTTLES_ENABLED(VD_ITEMS, true),
    BOWS_ENABLED(VD_ITEMS, true),
    BUCKETS_ENABLED(VD_ITEMS, true),
    BUNDLES_ENABLED(VD_ITEMS, true),
    CROSSBOWS_ENABLED(VD_ITEMS, true),
    EGGS_ENABLED(VD_ITEMS, true),
    ENDER_EYES_ENABLED(VD_ITEMS, true),
    ENDER_PEARLS_ENABLED(VD_ITEMS, true),
    EXPERIENCE_BOTTLES_ENABLED(VD_ITEMS, true),
    FIREWORKS_ENABLED(VD_ITEMS, true),
    FISHING_ENABLED(VD_ITEMS, true),
    FOOD_ON_STICKS_ENABLED(VD_ITEMS, true),
    GOAT_HORNS_ENABLED(VD_ITEMS, true),
    MAPS_ENABLED(VD_ITEMS, true),
    SHIELDS_ENABLED(VD_ITEMS, true),
    SNOWBALLS_ENABLED(VD_ITEMS, true),
    SPYGLASSES_ENABLED(VD_ITEMS, true),
    TRIDENTS_ENABLED(VD_ITEMS, true),
    AXES_ENABLED(VD_ITEMS, true),
    BONE_MEAL_ENABLED(VD_ITEMS, true),
    COMPASSES_ENABLED(VD_ITEMS, true),
    END_CRYSTALS_ENABLED(VD_ITEMS, true),
    FIRE_CHARGES_ENABLED(VD_ITEMS, true),
    FLINT_AND_STEEL_ENABLED(VD_ITEMS, true),
    HANGING_ENTITIES_ENABLED(VD_ITEMS, true),
    HOES_ENABLED(VD_ITEMS, true),
    HONEYCOMBS_ENABLED(VD_ITEMS, true),
    LEADS_ENABLED(VD_ITEMS, true),
    MINECARTS_ENABLED(VD_ITEMS, true),
    RECORDS_ENABLED(VD_ITEMS, true),
    SHEARS_ENABLED(VD_ITEMS, true),
    SHOVELS_ENABLED(VD_ITEMS, true),
    DYES_ENABLED(VD_ITEMS, true),
    NAMETAGS_ENABLED(VD_ITEMS, true),
    SADDLES_ENABLED(VD_ITEMS, true),
    CHORUS_FRUIT_EFFECTS_ENABLED(VD_ITEMS, true),
    HONEY_BOTTLE_EFFECTS_ENABLED(VD_ITEMS, true),
    MILK_BUCKET_EFFECTS_ENABLED(VD_ITEMS, true),
    SUSPICIOUS_STEW_EFFECTS_ENABLED(VD_ITEMS, true),
    TOTEMS_ENABLED(VD_ITEMS, true),
    BOW_SPAMMING(VD_ITEMS, true),
    CROSSBOW_SPAMMING(VD_ITEMS, true),

    DISPENSER_BONEMEALS_PLANTS(VD_DISPENSER, true),
    DISPENSER_BUCKETS_ITEMS(VD_DISPENSER, true),
    DISPENSER_EQUIPS_ARMOUR(VD_DISPENSER, true),
    DISPENSER_FILLS_BOTTLES(VD_DISPENSER, true),
    DISPENSER_FILLS_RESPAWN_ANCHOR(VD_DISPENSER, true),
    DISPENSER_FIRES_PROJECTILES(VD_DISPENSER, true),
    DISPENSER_FIRES_TRIDENTS(VD_DISPENSER, true),
    DISPENSER_FLINTS_AND_STEELS(VD_DISPENSER, true),
    DISPENSER_LAUNCHES_FIREWORKS(VD_DISPENSER, true),
    DISPENSER_LAUNCHES_FIRE_CHARGES(VD_DISPENSER, true),
    DISPENSER_LIGHTS_TNT(VD_DISPENSER, true),
    DISPENSER_PLACES_ARMOUR_STANDS(VD_DISPENSER, true),
    DISPENSER_PLACES_BOATS(VD_DISPENSER, true),
    DISPENSER_PLACES_HEADS(VD_DISPENSER, true),
    DISPENSER_PLACES_MINECARTS(VD_DISPENSER, true),
    DISPENSER_PLACES_SHULKER_BOXES(VD_DISPENSER, true),
    DISPENSER_SHEARS_SHEEP(VD_DISPENSER, true),
    DISPENSER_SPAWNS_MOBS(VD_DISPENSER, true),
    DISPENSER_WATERS_MUD(VD_DISPENSER, true),
    DISPENSER_WAXES_COPPER(VD_DISPENSER, true),

    TIPPED_ARROWS_ENABLED(VD_ARROW, true),
    FIRE_RESISTANCE_TIPPED_ARROW(VD_ARROW, true),
    HARMING_TIPPED_ARROW(VD_ARROW, true),
    HEALING_TIPPED_ARROW(VD_ARROW, true),
    INVISIBILITY_TIPPED_ARROW(VD_ARROW, true),
    LEAPING_TIPPED_ARROW(VD_ARROW, true),
    LUCK_TIPPED_ARROW(VD_ARROW, true),
    NIGHT_VISION_TIPPED_ARROW(VD_ARROW, true),
    POISON_TIPPED_ARROW(VD_ARROW, true),
    REGENERATION_TIPPED_ARROW(VD_ARROW, true),
    SLOWNESS_TIPPED_ARROW(VD_ARROW, true),
    SLOW_FALLING_TIPPED_ARROW(VD_ARROW, true),
    STRENGTH_TIPPED_ARROW(VD_ARROW, true),
    SWIFTNESS_TIPPED_ARROW(VD_ARROW, true),
    TURTLE_MASTER_TIPPED_ARROW(VD_ARROW, true),
    WATER_BREATHING_TIPPED_ARROW(VD_ARROW, true),
    WEAKNESS_TIPPED_ARROW(VD_ARROW, true),
    SPECTRAL_ARROWS_ENABLED(VD_ARROW, true),

    ADVANCEMENTS_ENABLED(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_ADVENTURE(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_SWEET_DREAMS(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_ADVENTURING_TIME(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_WHAT_A_DEAL(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_STAR_TRADER(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_MONSTER_HUNTER(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_MONSTERS_HUNTED(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_TAKE_AIM(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_THROWAWAY_JOKE(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_VERY_FRIGHTENING(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_HIRED_HELP(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_SNIPER_DUEL(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_POSTMORTAL(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_OLD_BETSY(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_WHOS_THE_PILLAGER_NOW(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_TWO_BIRDS_ONE_ARROW(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_ARBALISTIC(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_VOLUNTARY_EXILE(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_HERO_OF_VILLAGE(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_STICKY_SITUATION(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_BULLSEYE(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_LIGHT_AS_RABBIT(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_SURGE_PROTECTOR(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_IS_IT_A_BIRD(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_IS_IT_A_BALLOON(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_SOUND_OF_MUSIC(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_IS_IT_A_PLANE(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_CAVES_AND_CLIFFS(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_IT_SPREADS(VD_ADVANCEMENT, true),
    ADVENTURE_ADVANCEMENT_SNEAK_100(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_HUSBANDRY(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_SEEDY_PLACE(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_PARROTS_AND_BATS(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_BALANCED_DIET(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_SERIOUS_DEDICATION(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_BEST_FRIENDS(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_TWO_BY_TWO(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_FISHY_BUSINESS(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_TACTICAL_FISHING(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_CUTEST_PREDATOR(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_HEALING_FRIENDSHIP(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_COMPLETE_CATALOGUE(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_BEE_OUR_GUEST(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_WAX_ON(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_WAX_OFF(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_BUKKIT(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_SQUAD_HOPS_INTO_TOWN(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_POWERS_COMBINED(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_BEELOCATION(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_FLOATS_YOUR_GOAT(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_GLOW_AND_BEHOLD(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_FRIEND_IN_ME(VD_ADVANCEMENT, true),
    HUSBANDRY_ADVANCEMENT_BIRTHDAY_SONG(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_NETHER(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_RETURN_TO_SENDER(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_TERRIBLE_FORTRESS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_SUBSPACE_BUBBLE(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_UNEASY_ALLIANCE(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_SPOOKY_SCARY_SKELETON(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_WITHERING_HEIGHTS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_INTO_FIRE(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_BRING_HOME_BEACON(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_BEACONATOR(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_LOCAL_BREWERY(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_FURIOUS_COCKTAIL(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_HOW_DID_WE_GET_HERE(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_HIDDEN_IN_DEPTHS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_COVER_IN_DEBRIS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_COUNTRY_LODE(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_CUTTING_ONIONS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_NOT_QUITE_NINE_LIVES(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_BOAT_HAS_LEGS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_FEELS_LIKE_HOME(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_HOT_TOURIST_DESTINATIONS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_THOSE_WERE_THE_DAYS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_WAR_PIGS(VD_ADVANCEMENT, true),
    NETHER_ADVANCEMENT_OH_SHINY(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_MINECRAFT(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_STONE_AGE(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_GETTING_UPGRADE(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_ACQUIRE_HARDWARE(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_IRON_PICK(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_DIAMONDS(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_HOT_STUFF(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_SUIT_UP(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_ENCHANTER(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_ICE_BUCKET_CHALLENGE(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_NOT_TODAY_THANKS(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_COVER_WITH_DIAMONDS(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_GO_DEEPER(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_ZOMBIE_DOCTOR(VD_ADVANCEMENT, true),
    STORY_ADVANCEMENT_EYE_SPY(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_THE_END(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_FREE_THE_END(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_REMOTE_GETAWAY(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_END_AGAIN(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_CITY_AT_END(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_YOU_NEED_MINT(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_GREAT_VIEW(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_SKY_IS_LIMIT(VD_ADVANCEMENT, true),
    THE_END_ADVANCEMENT_NEXT_GENERATION(VD_ADVANCEMENT, true),

    OLD_BOATS(VD_MISC, false);

    private final boolean defaultBool;
    private GameRules.Key<GameRules.BooleanValue> gameRule;
    private final GameruleCategories category;

    BooleanGamerules(GameruleCategories category, boolean defaultBool) {
        this.category = category;
        this.defaultBool = defaultBool;
    }

    public GameRules.Key<GameRules.BooleanValue> getGameRule() {
        return this.gameRule;
    }
    public void setGameRule(GameRules.Key<GameRules.BooleanValue> gameRule) {
        this.gameRule = gameRule;
    }
    public GameruleCategories getCategory() {
        return this.category;
    }
    public boolean getDefaultBool() {
        return this.defaultBool;
    }
}