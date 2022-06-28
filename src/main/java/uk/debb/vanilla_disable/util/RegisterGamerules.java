package uk.debb.vanilla_disable.util;

import uk.debb.vanilla_disable.mixin.VanillaDisableMixinConfigPlugin;

public class RegisterGamerules {
    /**
     * @author DragonEggBedrockBreaking
     * @reason register all of our gamerules
     */
    public static void registerGamerules() {
        if (VanillaDisableMixinConfigPlugin.damage) {
            Gamerules.DAMAGE_ENABLED = GameruleHelper.register("damageEnabled", GameruleCategories.VD_DAMAGE, true);
            Gamerules.PROJECTILE_DAMAGE = GameruleHelper.register("projectileDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.EXPLOSION_DAMAGE = GameruleHelper.register("explosionDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.FALLING_BLOCK_DAMAGE = GameruleHelper.register("fallingBlockDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.VOID_DAMAGE = GameruleHelper.register("voidDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.MAGIC_DAMAGE = GameruleHelper.register("magicDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.CREATIVE_PLAYER_DAMAGE = GameruleHelper.register("creativePlayerDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.LIGHTNING_DAMAGE = GameruleHelper.register("lightningDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.WALL_DAMAGE = GameruleHelper.register("wallDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.CRAMMING_DAMAGE = GameruleHelper.register("crammingDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.STARVATION_DAMAGE = GameruleHelper.register("starvationDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.CACTUS_DAMAGE = GameruleHelper.register("cactusDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.FLY_INTO_WALL_DAMAGE = GameruleHelper.register("flyIntoWallDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.WITHER_DAMAGE = GameruleHelper.register("witherDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.ANVIL_DAMAGE = GameruleHelper.register("anvilDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.DRAGON_DAMAGE = GameruleHelper.register("dragonDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.SWEET_BERRY_BUSH_DAMAGE = GameruleHelper.register("sweetBerryBushDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.FALLING_STALACTITE_DAMAGE = GameruleHelper.register("fallingStalactiteDamage", GameruleCategories.VD_DAMAGE, true);
        }
        if (VanillaDisableMixinConfigPlugin.knockback) {
            Gamerules.KNOCKBACK_ENABLED = GameruleHelper.register("knockbackEnabled",GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.FIREBALL_KNOCKBACK = GameruleHelper.register("fireballKnockback",GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.WITHER_SKULL_KNOCKBACK = GameruleHelper.register("witherSkullKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.DRAGON_KNOCKBACK = GameruleHelper.register("dragonKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.ARROW_KNOCKBACK = GameruleHelper.register("arrowKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.TRIDENT_KNOCKBACK = GameruleHelper.register("tridentKnockback",GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.LLAMA_SPIT_KNOCKBACK = GameruleHelper.register("llamaSpitKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.SHULKER_BULLET_KNOCKBACK = GameruleHelper.register("shulkerBulletKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.MOB_ATTACK_KNOCKBACK = GameruleHelper.register("mobAttackKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.PLAYER_ATTACK_KNOCKBACK = GameruleHelper.register("playerAttackKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.EXPLOSION_KNOCKBACK = GameruleHelper.register("explosionKnockback", GameruleCategories.VD_KNOCKBACK, true);
        }
        if (VanillaDisableMixinConfigPlugin.spawning) {
            Gamerules.MONSTER_SPAWNING = GameruleHelper.register("monsterSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.CREATURE_SPAWNING = GameruleHelper.register("creatureSpawning",GameruleCategories.VD_SPAWNING, true);
            Gamerules.AMBIENT_SPAWNING = GameruleHelper.register("ambientSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.AXOLOTL_SPAWNING = GameruleHelper.register("axolotlSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.GLOWSQUID_SPAWNING = GameruleHelper.register("glowsquidSpawning",GameruleCategories.VD_SPAWNING, true);
            Gamerules.WATER_CREATURE_SPAWNING = GameruleHelper.register("waterCreatureSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.WATER_AMBIENT_SPAWNING = GameruleHelper.register("waterAmbientSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SPAWNERS_ENABLED = GameruleHelper.register("spawnersEnabled", GameruleCategories.VD_SPAWNING, true);
            Gamerules.PIG_SPAWNERS = GameruleHelper.register("pigSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.CAVE_SPIDER_SPAWNERS = GameruleHelper.register("caveSpiderSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SILVERFISH_SPAWNERS = GameruleHelper.register("silverfishSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.ZOMBIE_SPAWNERS = GameruleHelper.register("zombieSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SKELETON_SPAWNERS = GameruleHelper.register("skeletonSpawners",GameruleCategories.VD_SPAWNING, true);
            Gamerules.BLAZE_SPAWNERS = GameruleHelper.register("blazeSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SPIDER_SPAWNERS = GameruleHelper.register("spiderSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.MAGMA_CUBE_SPAWNERS = GameruleHelper.register("magmaCubeSpawners",GameruleCategories.VD_SPAWNING, true);
            Gamerules.SPAWN_EGGS = GameruleHelper.register("spawnEggs", GameruleCategories.VD_SPAWNING, true);
            Gamerules.ANIMAL_BREEDING = GameruleHelper.register("animalBreeding", GameruleCategories.VD_SPAWNING, true);
        }
        if (VanillaDisableMixinConfigPlugin.despawning) {
            Gamerules.MIN_SPAWN_DISTANCE = GameruleHelper.register("minSpawnDistance",GameruleCategories.VD_SPAWNING, 24, 0, 512);
            Gamerules.MONSTERS_DESPAWN = GameruleHelper.register("monstersDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.CREATURES_DESPAWN = GameruleHelper.register("creaturesDespawn",GameruleCategories.VD_DESPAWNING, false);
            Gamerules.AMBIENT_DESPAWN = GameruleHelper.register("ambientDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.AXOLOTLS_DESPAWN = GameruleHelper.register("axolotlsDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.GLOWSQUIDS_DESPAWN = GameruleHelper.register("glowsquidsDespawn",GameruleCategories.VD_DESPAWNING, true);
            Gamerules.WATER_CREATURES_DESPAWN = GameruleHelper.register("waterCreaturesDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.WATER_AMBIENT_DESPAWN = GameruleHelper.register("waterAmbientDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.MONSTER_MAX_DESPAWN = GameruleHelper.register("monsterMaxDespawn",GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.CREATURE_MAX_DESPAWN = GameruleHelper.register("creatureMaxDespawn", GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.AMBIENT_MAX_DESPAWN = GameruleHelper.register("ambientMaxDespawn",GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.AXOLOTL_MAX_DESPAWN = GameruleHelper.register("axolotlMaxDespawn",GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.GLOWSQUID_MAX_DESPAWN = GameruleHelper.register("glowsquidMaxDespawn", GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.WATER_CREATURE_MAX_DESPAWN= GameruleHelper.register("waterCreatureMaxDespawn", GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.WATER_AMBIENT_MAX_DESPAWN = GameruleHelper.register("waterAmbientMaxDespawn", GameruleCategories.VD_DESPAWNING, 64, 0, 512);
            Gamerules.MONSTER_MIN_DESPAWN = GameruleHelper.register("monsterMinDespawn",GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.CREATURE_MIN_DESPAWN = GameruleHelper.register("creatureMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.AMBIENT_MIN_DESPAWN = GameruleHelper.register("ambientMinDespawn",GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.AXOLOTL_MIN_DESPAWN = GameruleHelper.register("axolotlMinDespawn",GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.GLOWSQUID_MIN_DESPAWN = GameruleHelper.register("glowsquidMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.WATER_CREATURE_MIN_DESPAWN= GameruleHelper.register("waterCreatureMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.WATER_AMBIENT_MIN_DESPAWN = GameruleHelper.register("waterAmbientMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.ITEM_DESPAWN_TIME = GameruleHelper.register("itemDespawnTime", GameruleCategories.VD_DESPAWNING, 300, 0, Integer.MAX_VALUE);
            Gamerules.ENDER_PEARLS_DESPAWN_ON_DEATH = GameruleHelper.register("enderPearlsDespawnOnDeath", GameruleCategories.VD_DESPAWNING, true);
        }
        if (VanillaDisableMixinConfigPlugin.spawn_limits) {
            Gamerules.MONSTER_MOBCAP = GameruleHelper.register("monsterMobCap", GameruleCategories.VD_SPAWN_LIMITS, 70, 0, Integer.MAX_VALUE);
            Gamerules.CREATURE_MOBCAP = GameruleHelper.register("creatureMobCap", GameruleCategories.VD_SPAWN_LIMITS, 10, 0, Integer.MAX_VALUE);
            Gamerules.AMBIENT_MOBCAP = GameruleHelper.register("ambientMobCap", GameruleCategories.VD_SPAWN_LIMITS, 15, 0, Integer.MAX_VALUE);
            Gamerules.AXOLOTL_MOBCAP = GameruleHelper.register("axolotlMobCap", GameruleCategories.VD_SPAWN_LIMITS, 5, 0, Integer.MAX_VALUE);
            Gamerules.GLOWSQUID_MOBCAP = GameruleHelper.register("glowsquidMobCap", GameruleCategories.VD_SPAWN_LIMITS, 5, 0, Integer.MAX_VALUE);
            Gamerules.WATER_CREATURE_MOBCAP = GameruleHelper.register("waterCreatureMobCap", GameruleCategories.VD_SPAWN_LIMITS, 5, 0, Integer.MAX_VALUE);
            Gamerules.WATER_AMBIENT_MOBCAP = GameruleHelper.register("waterAmbientMobCap", GameruleCategories.VD_SPAWN_LIMITS, 20, 0, Integer.MAX_VALUE);
            Gamerules.MONSTER_MAX_LIGHT_LEVEL = GameruleHelper.register("monsterMaxLightLevel", GameruleCategories.VD_SPAWN_LIMITS, 0, 0, 15);
        }
        if (VanillaDisableMixinConfigPlugin.commands) {
            Gamerules.COMMANDS_ENABLED = GameruleHelper.register("commandsEnabled", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ADVANCEMENT_COMMAND = GameruleHelper.register("advancementCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ATTRIBUTE_COMMAND = GameruleHelper.register("attributeCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.BOSS_BAR_COMMAND = GameruleHelper.register("bossBarCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.CHASE_COMMAND = GameruleHelper.register("chaseCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.CLEAR_COMMAND = GameruleHelper.register("clearCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.CLONE_COMMAND = GameruleHelper.register("cloneCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.DATAPACK_COMMAND = GameruleHelper.register("datapackCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.DATA_COMMAND = GameruleHelper.register("dataCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.DIFFICULTY_COMMAND = GameruleHelper.register("difficultyCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.EFFECT_COMMAND = GameruleHelper.register("effectCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ENCHANT_COMMAND = GameruleHelper.register("enchantCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.EXECUTE_COMMAND = GameruleHelper.register("executeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.EXPERIENCE_COMMAND = GameruleHelper.register("experienceCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.FILL_COMMAND = GameruleHelper.register("fillCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.FORCE_LOAD_COMMAND = GameruleHelper.register("forceLoadCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.FUNCTION_COMMAND = GameruleHelper.register("functionCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.GAME_MODE_COMMAND = GameruleHelper.register("gameModeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.GIVE_COMMAND = GameruleHelper.register("giveCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.HELP_COMMAND = GameruleHelper.register("helpCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ITEM_COMMAND = GameruleHelper.register("itemCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.JFR_COMMAND = GameruleHelper.register("jfrCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.KICK_COMMAND = GameruleHelper.register("kickCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.KILL_COMMAND = GameruleHelper.register("killCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LIST_COMMAND = GameruleHelper.register("listCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LOCATE_COMMAND = GameruleHelper.register("locateCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LOOT_COMMAND = GameruleHelper.register("lootCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ME_COMMAND = GameruleHelper.register("meCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.MESSAGE_COMMAND = GameruleHelper.register("messageCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PARTICLE_COMMAND = GameruleHelper.register("particleCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PLACE_COMMAND = GameruleHelper.register("placeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PLAY_SOUND_COMMAND = GameruleHelper.register("playSoundCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.PUBLISH_COMMAND = GameruleHelper.register("publishCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RAID_COMMAND = GameruleHelper.register("raidCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RECIPE_COMMAND = GameruleHelper.register("recipeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RELOAD_COMMAND = GameruleHelper.register("reloadCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RESET_CHUNKS_COMMAND = GameruleHelper.register("resetChunksCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAY_COMMAND = GameruleHelper.register("sayCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SCHEDULE_COMMAND = GameruleHelper.register("scheduleCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SCOREBOARD_COMMAND = GameruleHelper.register("scoreboardCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.SEED_COMMAND = GameruleHelper.register("seedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SET_BLOCK_COMMAND = GameruleHelper.register("setBlockCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SET_WORLD_SPAWN_COMMAND = GameruleHelper.register("setWorldSpawnCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SPAWN_POINT_COMMAND = GameruleHelper.register("spawnPointCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.STOP_SOUND_COMMAND = GameruleHelper.register("stopSoundCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.SUMMON_COMMAND = GameruleHelper.register("summonCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TEAM_COMMAND = GameruleHelper.register("teamCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TEAM_MSG_COMMAND = GameruleHelper.register("teamMsgCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TELEPORT_COMMAND = GameruleHelper.register("teleportCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TELL_RAW_COMMAND = GameruleHelper.register("tellRawCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TIME_COMMAND = GameruleHelper.register("timeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TITLE_COMMAND = GameruleHelper.register("titleCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.TRIGGER_COMMAND = GameruleHelper.register("triggerCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.WEATHER_COMMAND = GameruleHelper.register("weatherCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.WORLD_BORDER_COMMAND = GameruleHelper.register("worldBorderCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.BAN_DEDICATED_COMMAND = GameruleHelper.register("banDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.BAN_IP_DEDICATED_COMMAND = GameruleHelper.register("banIpDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.BAN_LIST_DEDICATED_COMMAND = GameruleHelper.register("banListDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.DE_OP_DEDICATED_COMMAND = GameruleHelper.register("deOpDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.OP_DEDICATED_COMMAND = GameruleHelper.register("opDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PARDON_DEDICATED_COMMAND = GameruleHelper.register("pardonDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PARDON_IP_DEDICATED_COMMAND = GameruleHelper.register("pardonIpDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.PERF_DEDICATED_COMMAND = GameruleHelper.register("perfDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAVE_ALL_DEDICATED_COMMAND = GameruleHelper.register("saveAllDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAVE_OFF_DEDICATED_COMMAND = GameruleHelper.register("saveOffDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAVE_ON_DEDICATED_COMMAND = GameruleHelper.register("saveOnDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND = GameruleHelper.register("setIdleTimeoutDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.STOP_DEDICATED_COMMAND = GameruleHelper.register("stopDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.WHITELIST_DEDICATED_COMMAND = GameruleHelper.register("whitelistDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
        }
        if (VanillaDisableMixinConfigPlugin.fluids) {
            Gamerules.INFINITE_WATER = GameruleHelper.register("infiniteWater", GameruleCategories.VD_FLUIDS, true);
            Gamerules.INFINITE_LAVA = GameruleHelper.register("infiniteLava", GameruleCategories.VD_FLUIDS, false);
            Gamerules.WATER_REACHES_FAR = GameruleHelper.register("waterReachesFar",GameruleCategories.VD_FLUIDS, true);
            Gamerules.LAVA_REACHES_FAR = GameruleHelper.register("lavaReachesFar", GameruleCategories.VD_FLUIDS, false);
            Gamerules.LAVA_REACHES_FAR_IN_NETHER = GameruleHelper.register("lavaReachesFarInNether", GameruleCategories.VD_FLUIDS, true);
            Gamerules.WATER_FLOW_SPEED = GameruleHelper.register("waterFlowSpeed", GameruleCategories.VD_FLUIDS, 5, 1, 128);
            Gamerules.LAVA_FLOW_SPEED = GameruleHelper.register("lavaFlowSpeed", GameruleCategories.VD_FLUIDS, 30, 1, 128);
            Gamerules.LAVA_FLOW_SPEED_NETHER = GameruleHelper.register("lavaFlowSpeedNether",GameruleCategories.VD_FLUIDS, 10, 1, 128);
            Gamerules.WATER_PLACEABLE_IN_NETHER = GameruleHelper.register("waterPlaceableInNether", GameruleCategories.VD_FLUIDS, false);
            Gamerules.BUBBLE_COLUMNS_ENABLED = GameruleHelper.register("bubbleColumnsEnabled",GameruleCategories.VD_FLUIDS, true);
        }
        if (VanillaDisableMixinConfigPlugin.mob) {
            Gamerules.CURABLE_ZILLAGERS = GameruleHelper.register("curableZillagers", GameruleCategories.VD_MOBS, true);
            Gamerules.VILLAGERS_CONVERT_TO_ZILLAGERS = GameruleHelper.register("villagersConvertToZillagers", GameruleCategories.VD_MOBS, true);
            Gamerules.VILLAGERS_CONVERT_TO_WITCHES = GameruleHelper.register("villagersConvertToWitches", GameruleCategories.VD_MOBS, true);
            Gamerules.PIGLINS_CONVERT_TO_ZIGLINS = GameruleHelper.register("piglinsConvertToZiglins",GameruleCategories.VD_MOBS, true);
            Gamerules.HOGLINS_CONVERT_TO_ZOGLINS = GameruleHelper.register("hoglinsConvertToZoglins",GameruleCategories.VD_MOBS, true);
            Gamerules.HUSKS_CONVERT_TO_ZOMBIES = GameruleHelper.register("husksConvertToZombies", GameruleCategories.VD_MOBS, true);
            Gamerules.ZOMBIES_CONVERT_TO_DROWNED = GameruleHelper.register("zombiesConvertToDrowned",GameruleCategories.VD_MOBS, true);
            Gamerules.SKELETONS_CONVERT_TO_STRAYS = GameruleHelper.register("skeletonsConvertToStrays",GameruleCategories.VD_MOBS, true);
            Gamerules.INFINITE_TRADING = GameruleHelper.register("infiniteTrading", GameruleCategories.VD_MOBS, false);
            Gamerules.VILLAGER_DAILY_RESTOCKS = GameruleHelper.register("villagerDailyRestocks", GameruleCategories.VD_MOBS, 2, 0);
            Gamerules.VILLAGER_TRADING_ENABLED = GameruleHelper.register("villagerTradingEnabled", GameruleCategories.VD_MOBS, true);
            Gamerules.PIGLIN_BARTERING_ENABLED = GameruleHelper.register("piglinBarteringEnabled", GameruleCategories.VD_MOBS, true);
            Gamerules.WITHER_SPAWNS = GameruleHelper.register("witherSpawns", GameruleCategories.VD_MOBS, true);
            Gamerules.PIGS_BREED_WITH_WHEAT = GameruleHelper.register("pigsBreedWithWheat", GameruleCategories.VD_MOBS, false);
            Gamerules.MOBS_BURN_IN_SUNLIGHT = GameruleHelper.register("mobsBurnInSunlight", GameruleCategories.VD_MOBS, true);
            Gamerules.DRAGON_FIREBALLS = GameruleHelper.register("dragonFireballs", GameruleCategories.VD_MOBS, true);
            Gamerules.FIRE_ASPECT_IGNITES_CREEPERS = GameruleHelper.register("fireAspectIgnitesCreepers", GameruleCategories.VD_MOBS, false);
        }
        if (VanillaDisableMixinConfigPlugin.mob_toggles) {
            Gamerules.ALLAYS_ENABLED = GameruleHelper.register("allaysEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.BATS_ENABLED = GameruleHelper.register("batsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.CATS_ENABLED = GameruleHelper.register("catsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.CHICKENS_ENABLED = GameruleHelper.register("chickensEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.CODS_ENABLED = GameruleHelper.register("codsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.COWS_ENABLED = GameruleHelper.register("cowsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.DONKEYS_ENABLED = GameruleHelper.register("donkeysEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.FOXES_ENABLED = GameruleHelper.register("foxesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.FROGS_ENABLED = GameruleHelper.register("frogsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.HORSES_ENABLED = GameruleHelper.register("horsesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.MOOSHROOMS_ENABLED = GameruleHelper.register("mooshroomsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.MULES_ENABLED = GameruleHelper.register("mulesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.OCELOTS_ENABLED = GameruleHelper.register("ocelotsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PARROTS_ENABLED = GameruleHelper.register("parrotsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PIGS_ENABLED = GameruleHelper.register("pigsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PUFFERFISH_ENABLED = GameruleHelper.register("pufferfishEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.RABBITS_ENABLED = GameruleHelper.register("rabbitsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SALMONS_ENABLED = GameruleHelper.register("salmonsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SHEEP_ENABLED = GameruleHelper.register("sheepEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SKELETON_HORSES_ENABLED = GameruleHelper.register("skeletonHorsesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SNOW_GOLEMS_ENABLED = GameruleHelper.register("snowGolemsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SQUIDS_ENABLED = GameruleHelper.register("squidsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.STRIDERS_ENABLED = GameruleHelper.register("stridersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.TADPOLES_ENABLED = GameruleHelper.register("tadpolesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.TROPICAL_FISH_ENABLED = GameruleHelper.register("tropicalFishEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.TURTLES_ENABLED = GameruleHelper.register("turtlesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.VILLAGERS_ENABLED = GameruleHelper.register("villagersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.WANDERING_TRADERS_ENABLED = GameruleHelper.register("wanderingTradersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.BEES_ENABLED = GameruleHelper.register("beesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.CAVE_SPIDERS_ENABLED = GameruleHelper.register("caveSpidersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.DOLPHINS_ENABLED = GameruleHelper.register("dolphinsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ENDERMEN_ENABLED = GameruleHelper.register("endermenEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.GOATS_ENABLED = GameruleHelper.register("goatsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.IRON_GOLEMS_ENABLED = GameruleHelper.register("ironGolemsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.LLAMAS_ENABLED = GameruleHelper.register("llamasEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PANDAS_ENABLED = GameruleHelper.register("pandasEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PIGLINS_ENABLED = GameruleHelper.register("piglinsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.POLAR_BEARS_ENABLED = GameruleHelper.register("polarBearsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SPIDERS_ENABLED = GameruleHelper.register("spidersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.TRADER_LLAMAS_ENABLED = GameruleHelper.register("traderLlamasEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.WOLVES_ENABLED = GameruleHelper.register("wolvesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ZOMBIFIED_PIGLINS_ENABLED = GameruleHelper.register("zombifiedPiglinsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.BLAZES_ENABLED = GameruleHelper.register("blazesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.CREEPERS_ENABLED = GameruleHelper.register("creepersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.DROWNED_ENABLED = GameruleHelper.register("drownedEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ELDER_GUARDIANS_ENABLED = GameruleHelper.register("elderGuardiansEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ENDERMITES_ENABLED = GameruleHelper.register("endermitesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.EVOKERS_ENABLED = GameruleHelper.register("evokersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.GHASTS_ENABLED = GameruleHelper.register("ghastsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.GUARDIANS_ENABLED = GameruleHelper.register("guardiansEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.HOGLINS_ENABLED = GameruleHelper.register("hoglinsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.HUSKS_ENABLED = GameruleHelper.register("husksEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.MAGMA_CUBES_ENABLED = GameruleHelper.register("magmaCubesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PHANTOMS_ENABLED = GameruleHelper.register("phantomsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PIGLIN_BRUTES_ENABLED = GameruleHelper.register("piglinBrutesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.PILLAGERS_ENABLED = GameruleHelper.register("pillagersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.RAVAGERS_ENABLED = GameruleHelper.register("ravagersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SHULKERS_ENABLED = GameruleHelper.register("shulkersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SILVERFISH_ENABLED = GameruleHelper.register("silverfishEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SKELETONS_ENABLED = GameruleHelper.register("skeletonsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.SLIMES_ENABLED = GameruleHelper.register("slimesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.STRAYS_ENABLED = GameruleHelper.register("straysEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.VEXES_ENABLED = GameruleHelper.register("vexesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.VINDICATORS_ENABLED = GameruleHelper.register("vindicatorsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.WARDENS_ENABLED = GameruleHelper.register("wardensEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.WITCHES_ENABLED = GameruleHelper.register("witchesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.WITHER_SKELETONS_ENABLED = GameruleHelper.register("witherSkeletonsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ZOGLINS_ENABLED = GameruleHelper.register("zoglinsEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ZOMBIES_ENABLED = GameruleHelper.register("zombiesEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
            Gamerules.ZOMBIE_VILLAGERS_ENABLED = GameruleHelper.register("zombieVillagersEnabled", GameruleCategories.VD_MOB_TOGGLES, true);
        }
        if (VanillaDisableMixinConfigPlugin.effects) {
            Gamerules.EFFECTS_ENABLED = GameruleHelper.register("effectsEnabled", GameruleCategories.VD_EFFECTS, true);
            Gamerules.ABSORPTION_EFFECT = GameruleHelper.register("absorptionEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.BAD_OMEN_EFFECT = GameruleHelper.register("badOmenEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.BLINDNESS_EFFECT = GameruleHelper.register("blindnessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.CONDUIT_POWER_EFFECT = GameruleHelper.register("conduitPowerEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.DARKNESS_EFFECT = GameruleHelper.register("darknessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.DOLPHINS_GRACE_EFFECT = GameruleHelper.register("dolphinsGraceEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.FIRE_RESISTANCE_EFFECT = GameruleHelper.register("fireResistanceEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.GLOWING_EFFECT = GameruleHelper.register("glowingEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.HASTE_EFFECT = GameruleHelper.register("hasteEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.HEALTH_BOOST_EFFECT = GameruleHelper.register("healthBoostEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.HUNGER_EFFECT = GameruleHelper.register("hungerEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.INSTANT_DAMAGE_EFFECT = GameruleHelper.register("instantDamageEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.INSTANT_HEALTH_EFFECT = GameruleHelper.register("instantHealthEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.INVISIBILITY_EFFECT = GameruleHelper.register("invisibilityEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.JUMP_BOOST_EFFECT = GameruleHelper.register("jumpBoostEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.LEVITATION_EFFECT = GameruleHelper.register("levitationEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.LUCK_EFFECT = GameruleHelper.register("luckEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.MINING_FATIGUE_EFFECT = GameruleHelper.register("miningFatigueEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.NAUSEA_EFFECT = GameruleHelper.register("nauseaEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.NIGHT_VISION_EFFECT = GameruleHelper.register("nightVisionEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.POISON_EFFECT = GameruleHelper.register("poisonEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.REGENERATION_EFFECT = GameruleHelper.register("regenerationEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.RESISTANCE_EFFECT = GameruleHelper.register("resistanceEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.SATURATION_EFFECT = GameruleHelper.register("saturationEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.SLOWNESS_EFFECT = GameruleHelper.register("slownessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.SLOW_FALLING_EFFECT = GameruleHelper.register("slowFallingEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.SPEED_EFFECT = GameruleHelper.register("speedEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.STRENGTH_EFFECT = GameruleHelper.register("strengthEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.UNLUCK_EFFECT = GameruleHelper.register("unluckEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.WATER_BREATHING_EFFECT = GameruleHelper.register("waterBreathingEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.WEAKNESS_EFFECT = GameruleHelper.register("weaknessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.WITHER_EFFECT = GameruleHelper.register("witherEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.MILK_CLEARS_EFFECTS = GameruleHelper.register("milkClearsEffects",GameruleCategories.VD_EFFECTS, true);
        }
        if (VanillaDisableMixinConfigPlugin.enchantments) {
            Gamerules.ENCHANTMENTS_ENABLED = GameruleHelper.register("enchantmentsEnabled", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.AQUA_AFFINITY_ENCHANTMENT = GameruleHelper.register("aquaAffinityEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.BANE_OF_ARTHROPODS_ENCHANTMENT = GameruleHelper.register("baneOfArthropodsEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.BLAST_PROTECTION_ENCHANTMENT = GameruleHelper.register("blastProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.CHANNELING_ENCHANTMENT = GameruleHelper.register("channelingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.DEPTH_STRIDER_ENCHANTMENT = GameruleHelper.register("depthStriderEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.EFFICIENCY_ENCHANTMENT = GameruleHelper.register("efficiencyEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FEATHER_FALLING_ENCHANTMENT = GameruleHelper.register("featherFallingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FIRE_ASPECT_ENCHANTMENT = GameruleHelper.register("fireAspectEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FIRE_PROTECTION_ENCHANTMENT = GameruleHelper.register("fireProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FLAME_ENCHANTMENT = GameruleHelper.register("flameEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FORTUNE_ENCHANTMENT = GameruleHelper.register("fortuneEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FROST_WALKER_ENCHANTMENT = GameruleHelper.register("frostWalkerEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.IMPALING_ENCHANTMENT = GameruleHelper.register("impalingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.INFINITY_ENCHANTMENT = GameruleHelper.register("infinityEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.KNOCKBACK_ENCHANTMENT = GameruleHelper.register("knockbackEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LOOTING_ENCHANTMENT = GameruleHelper.register("lootingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LOYALTY_ENCHANTMENT = GameruleHelper.register("loyaltyEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LUCK_OF_THE_SEA_ENCHANTMENT = GameruleHelper.register("luckOfTheSeaEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LURE_ENCHANTMENT = GameruleHelper.register("lureEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.MENDING_ENCHANTMENT = GameruleHelper.register("mendingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.MULTISHOT_ENCHANTMENT = GameruleHelper.register("multishotEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PIERCING_ENCHANTMENT = GameruleHelper.register("piercingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.POWER_ENCHANTMENT = GameruleHelper.register("powerEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PROJECTILE_PROTECTION_ENCHANTMENT = GameruleHelper.register("projectileProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PROTECTION_ENCHANTMENT = GameruleHelper.register("protectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PUNCH_ENCHANTMENT = GameruleHelper.register("punchEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.QUICK_CHARGE_ENCHANTMENT = GameruleHelper.register("quickChargeEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.RESPIRATION_ENCHANTMENT = GameruleHelper.register("respirationEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.RIPTIDE_ENCHANTMENT = GameruleHelper.register("riptideEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SHARPNESS_ENCHANTMENT = GameruleHelper.register("sharpnessEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SILK_TOUCH_ENCHANTMENT = GameruleHelper.register("silkTouchEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SMITE_ENCHANTMENT = GameruleHelper.register("smiteEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SOUL_SPEED_ENCHANTMENT = GameruleHelper.register("soulSpeedEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SWEEPING_ENCHANTMENT = GameruleHelper.register("sweepingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SWIFT_SNEAK_ENCHANTMENT = GameruleHelper.register("swiftSneakEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.THORNS_ENCHANTMENT = GameruleHelper.register("thornsEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.UNBREAKING_ENCHANTMENT = GameruleHelper.register("unbreakingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.BINDING_CURSE = GameruleHelper.register("bindingCurse", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.VANISHING_CURSE = GameruleHelper.register("vanishingCurse", GameruleCategories.VD_ENCHANTMENTS, true);
            if (VanillaDisableMixinConfigPlugin.enchantment_conflicts) {
                Gamerules.BOOT_ENCHANTMENT_CONFLICTS = GameruleHelper.register("bootEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.BOW_ENCHANTMENT_CONFLICTS = GameruleHelper.register("bowEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.CROSSBOW_ENCHANTMENT_CONFLICTS = GameruleHelper.register("crossbowEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS = GameruleHelper.register("damageEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.MINING_ENCHANTMENT_CONFLICTS = GameruleHelper.register("miningEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS = GameruleHelper.register("protectionEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.TRIDENT_ENCHANTMENT_CONFLICTS = GameruleHelper.register("tridentEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, true);
            }
        }
        if (VanillaDisableMixinConfigPlugin.worldgen) {
            Gamerules.ANCIENT_CITY_GENERATION = GameruleHelper.register("ancientCityGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.BASTION_REMNANT_GENERATION = GameruleHelper.register("bastionRemnantGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.BURIED_TREASURE_GENERATION = GameruleHelper.register("buriedTreasureGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DESERT_PYRAMID_GENERATION = GameruleHelper.register("desertPyramidGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_CITY_GENERATION = GameruleHelper.register("endCityGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.FORTRESS_GENERATION = GameruleHelper.register("fortressGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.IGLOO_GENERATION = GameruleHelper.register("iglooGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.JUNGLE_PYRAMID_GENERATION = GameruleHelper.register("junglePyramidGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MANSION_GENERATION = GameruleHelper.register("mansionGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MINESHAFT_GENERATION = GameruleHelper.register("mineshaftGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MONUMENT_GENERATION = GameruleHelper.register("monumentGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_FOSSIL_GENERATION = GameruleHelper.register("netherFossilGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OCEAN_RUIN_GENERATION = GameruleHelper.register("oceanRuinGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.PILLAGER_OUTPOST_GENERATION = GameruleHelper.register("pillagerOutpostGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.RUINED_PORTAL_GENERATION = GameruleHelper.register("ruinedPortalGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SHIPWRECK_GENERATION = GameruleHelper.register("shipwreckGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.STRONGHOLD_GENERATION = GameruleHelper.register("strongholdGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SWAMP_HUT_GENERATION = GameruleHelper.register("swampHutGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.VILLAGE_GENERATION = GameruleHelper.register("villageGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_VEGETATION = GameruleHelper.register("endVegetation", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_VEGETATION = GameruleHelper.register("netherVegetation",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OCEAN_VEGETATION = GameruleHelper.register("oceanVegetation", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OVERWORLD_VEGETATION = GameruleHelper.register("overworldVegetation", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.UNDERGROUND_VEGETATION = GameruleHelper.register("undergroundVegetation",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.AMETHYST_GEODE_GENERATION = GameruleHelper.register("amethystGeodeGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.BASALT_BLACKSTONE_GENERATION = GameruleHelper.register("basaltBlackstoneGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DESERT_WELL_GENERATION = GameruleHelper.register("desertWellGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DRIPSTONE_GENERATION = GameruleHelper.register("dripstoneGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DUNGEON_GENERATION = GameruleHelper.register("dungeonGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_FEATURES_GENERATION = GameruleHelper.register("endFeaturesGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.FOSSIL_GENERATION = GameruleHelper.register("fossilGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.GLOWSTONE_GENERATION = GameruleHelper.register("glowstoneGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.ICE_GENERATION = GameruleHelper.register("iceGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.LAVA_LAKE_GENERATION = GameruleHelper.register("lavaLakeGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MAGMA_GENERATION = GameruleHelper.register("magmaGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_FIRE_GENERATION = GameruleHelper.register("netherFireGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_ORE_GENERATION = GameruleHelper.register("netherOreGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OCEAN_FLOOR_GENERATION = GameruleHelper.register("oceanFloorGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.ORE_GENERATION = GameruleHelper.register("oreGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SCULK_GENERATION = GameruleHelper.register("sculkGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SPRING_GENERATION = GameruleHelper.register("springGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.TREE_GENERATION = GameruleHelper.register("treeGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.WELL_GENERATION = GameruleHelper.register("wellGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_PILLAR_CAGE_GENERATION = GameruleHelper.register("endPillarCageGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.REMOVE_OVERWORLD_BIOMES = GameruleHelper.register("removeOverworldBiomes",GameruleCategories.VD_WORLDGEN, false);
            Gamerules.REMOVE_NETHER_BIOMES = GameruleHelper.register("removeNetherBiomes", GameruleCategories.VD_WORLDGEN, false);
            Gamerules.REMOVE_END_BIOMES = GameruleHelper.register("removeEndBiomes", GameruleCategories.VD_WORLDGEN, false);
        }
        if (VanillaDisableMixinConfigPlugin.player) {
            Gamerules.PLAYER_CAN_BE_ON_FIRE = GameruleHelper.register("playerCanBeOnFire", GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_SPRINT = GameruleHelper.register("playerCanSprint",GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_CROUCH = GameruleHelper.register("playerCanCrouch",GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_SWIM = GameruleHelper.register("playerCanSwim", GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_JUMP = GameruleHelper.register("playerCanJump", GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_BE_INVISIBLE = GameruleHelper.register("playerCanBeInvisible",GameruleCategories.VD_PLAYER, true);
        }
        if (VanillaDisableMixinConfigPlugin.redstone) {
            Gamerules.REPEATER_BASE_DELAY = GameruleHelper.register("repeaterBaseDelay", GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.REPEATER_SIGNAL = GameruleHelper.register("repeaterSignal", GameruleCategories.VD_REDSTONE, 15, 0, 15);
            Gamerules.COMPARATOR_BASE_DELAY = GameruleHelper.register("comparatorBaseDelay",GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.COMPARATOR_ENABLED = GameruleHelper.register("comparatorEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.REDSTONE_TORCH_ENABLED = GameruleHelper.register("torchRedstoneSignal",GameruleCategories.VD_REDSTONE, true);
            Gamerules.REDSTONE_WIRE_ENABLED = GameruleHelper.register("redstoneWireEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.DROPPER_ENABLED = GameruleHelper.register("dropperEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.DISPENSER_ENABLED = GameruleHelper.register("dispenserEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.DAYLIGHT_SENSOR_ENABLED = GameruleHelper.register("daylightSensorEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.WOOD_BUTTON_PRESS_DURATION = GameruleHelper.register("woodButtonPressDuration", GameruleCategories.VD_REDSTONE, 30, 0);
            Gamerules.STONE_BUTTON_PRESS_DURATION = GameruleHelper.register("stoneButtonPressDuration",GameruleCategories.VD_REDSTONE, 20, 0);
            Gamerules.BUTTON_ENABLED = GameruleHelper.register("buttonEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.LEVER_ENABLED = GameruleHelper.register("leverEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.LIGHTNING_ROD_ENABLED = GameruleHelper.register("lightningRodEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.OBSERVER_DELAY = GameruleHelper.register("observerDelay", GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.OBSERVER_DURATION = GameruleHelper.register("observerDuration", GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.OBSERVER_ENABLED = GameruleHelper.register("observerEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.PRESSURE_PLATE_ENABLED = GameruleHelper.register("pressurePlateEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.TARGET_BLOCK_ENABLED = GameruleHelper.register("targetBlockEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.TRAPPED_CHEST_ENABLED = GameruleHelper.register("trappedChestEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.TRIPWIRE_HOOK_ENABLED = GameruleHelper.register("tripwireHookEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.PISTON_ENABLED = GameruleHelper.register("pistonEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.SCULK_SENSOR_ENABLED = GameruleHelper.register("sculkSensorEnabled",GameruleCategories.VD_REDSTONE, true);
        }
        if (VanillaDisableMixinConfigPlugin.misc) {
            Gamerules.NETHER_PORTALS_ENABLED = GameruleHelper.register("netherPortalsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.END_PORTALS_ENABLED = GameruleHelper.register("endPortalsEnabled",GameruleCategories.VD_MISC, true);
            Gamerules.END_GATEWAYS_ENABLED = GameruleHelper.register("endGatewaysEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.NETHER_PORTAL_COOLDOWN = GameruleHelper.register("netherPortalCooldown", GameruleCategories.VD_MISC, 300);
            Gamerules.CROP_TRAMPLING = GameruleHelper.register("cropTrampling", GameruleCategories.VD_MISC, true);
            if (VanillaDisableMixinConfigPlugin.misc_hunger) {
                Gamerules.OLD_HUNGER = GameruleHelper.register("oldHunger", GameruleCategories.VD_MISC, false);
            }
            Gamerules.OLD_BOATS = GameruleHelper.register("oldBoats", GameruleCategories.VD_MISC, false);
            Gamerules.BEACONS_ENABLED = GameruleHelper.register("beaconsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.CONDUITS_ENABLED = GameruleHelper.register("conduitsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.ICE_SLIDING = GameruleHelper.register("iceSliding", GameruleCategories.VD_MISC, true);
            Gamerules.TOTEMS_ENABLED = GameruleHelper.register("totemsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.BOW_SPAMMING = GameruleHelper.register("bowSpamming", GameruleCategories.VD_MISC, false);
            Gamerules.CROSSBOW_SPAMMING = GameruleHelper.register("crossbowSpamming",GameruleCategories.VD_MISC, false);
            Gamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS = GameruleHelper.register("creativeSwordCanBreakBlocks", GameruleCategories.VD_MISC, false);
            Gamerules.PUSHABLE_BUDDING_AMETHYST = GameruleHelper.register("pushableBuddingAmethyst",GameruleCategories.VD_MISC, false);
            if (VanillaDisableMixinConfigPlugin.misc_container) {
                Gamerules.CONTAINER_OPENING_BLOCKED = GameruleHelper.register("containerOpeningBlocked",GameruleCategories.VD_MISC, true);
            }
        }
    }
}
