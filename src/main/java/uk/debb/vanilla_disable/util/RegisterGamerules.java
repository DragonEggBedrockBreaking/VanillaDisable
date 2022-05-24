package uk.debb.vanilla_disable.util;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Unique;
import uk.debb.vanilla_disable.mixin.VanillaDisableMixinConfigPlugin;

public class RegisterGamerules {
    /**
     * @author DragonEggBedrockBreaking
     * @reason register boolean gamerules
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a boolean)
     * @return the gamerule key (boolean)
     */
    @Unique
    private static GameRules.Key<GameRules.BooleanValue> register(String name, CustomGameRuleCategory category, boolean defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createBooleanRule(defaultValue));
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason register integer gamerules with only a default
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a ineger)
     * @return the gamerule key (int)
     */
    @Unique
    private static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue));
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason register integer gamerules with default and minimum values
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a ineger)
     * @return the gamerule key (int)
     */
    @Unique
    private static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue));
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason register integer gamerules with default, minimum, and maximum values
     * @param name the name of the gamerule
     * @param category the gamerule category
     * @param defaultValue the default value (a ineger)
     * @return the gamerule key (int)
     */
    @Unique
    private static GameRules.Key<GameRules.IntegerValue> register(String name, CustomGameRuleCategory category, int defaultValue, int minValue, int maxValue) {
        return GameRuleRegistry.register(name, category, GameRuleFactory.createIntRule(defaultValue, minValue, maxValue));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason register all of our gamerules
     */
    @Unique
    public static void registerGamerules() {
        if (VanillaDisableMixinConfigPlugin.damage) {
            Gamerules.DAMAGE_ENABLED = register("damageEnabled", GameruleCategories.VD_DAMAGE, true);
            Gamerules.PROJECTILE_DAMAGE = register("projectileDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.EXPLOSION_DAMAGE = register("explosionDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.FALLING_BLOCK_DAMAGE = register("fallingBlockDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.VOID_DAMAGE = register("voidDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.MAGIC_DAMAGE = register("magicDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.CREATIVE_PLAYER_DAMAGE = register("creativePlayerDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.LIGHTNING_DAMAGE = register("lightningDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.WALL_DAMAGE = register("wallDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.CRAMMING_DAMAGE = register("crammingDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.STARVATION_DAMAGE = register("starvationDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.CACTUS_DAMAGE = register("cactusDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.FLY_INTO_WALL_DAMAGE = register("flyIntoWallDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.WITHER_DAMAGE = register("witherDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.ANVIL_DAMAGE = register("anvilDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.DRAGON_DAMAGE = register("dragonDamage",GameruleCategories.VD_DAMAGE, true);
            Gamerules.SWEET_BERRY_BUSH_DAMAGE = register("sweetBerryBushDamage", GameruleCategories.VD_DAMAGE, true);
            Gamerules.FALLING_STALACTITE_DAMAGE = register("fallingStalactiteDamage", GameruleCategories.VD_DAMAGE, true);
        }
        if (VanillaDisableMixinConfigPlugin.knockback) {
            Gamerules.KNOCKBACK_ENABLED = register("knockbackEnabled",GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.FIREBALL_KNOCKBACK = register("fireballKnockback",GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.WITHER_SKULL_KNOCKBACK = register("witherSkullKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.DRAGON_KNOCKBACK = register("dragonKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.ARROW_KNOCKBACK = register("arrowKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.TRIDENT_KNOCKBACK = register("tridentKnockback",GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.LLAMA_SPIT_KNOCKBACK = register("llamaSpitKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.SHULKER_BULLET_KNOCKBACK = register("shulkerBulletKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.MOB_ATTACK_KNOCKBACK = register("mobAttackKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.PLAYER_ATTACK_KNOCKBACK = register("playerAttackKnockback", GameruleCategories.VD_KNOCKBACK, true);
            Gamerules.EXPLOSION_KNOCKBACK = register("explosionKnockback", GameruleCategories.VD_KNOCKBACK, true);
        }
        if (VanillaDisableMixinConfigPlugin.spawning) {
            Gamerules.MONSTER_SPAWNING = register("monsterSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.CREATURE_SPAWNING = register("creatureSpawning",GameruleCategories.VD_SPAWNING, true);
            Gamerules.AMBIENT_SPAWNING = register("ambientSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.AXOLOTL_SPAWNING = register("axolotlSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.GLOWSQUID_SPAWNING = register("glowsquidSpawning",GameruleCategories.VD_SPAWNING, true);
            Gamerules.WATER_CREATURE_SPAWNING = register("waterCreatureSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.WATER_AMBIENT_SPAWNING = register("waterAmbientSpawning", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SPAWNERS_ENABLED = register("spawnersEnabled", GameruleCategories.VD_SPAWNING, true);
            Gamerules.PIG_SPAWNERS = register("pigSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.CAVE_SPIDER_SPAWNERS = register("caveSpiderSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SILVERFISH_SPAWNERS = register("silverfishSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.ZOMBIE_SPAWNERS = register("zombieSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SKELETON_SPAWNERS = register("skeletonSpawners",GameruleCategories.VD_SPAWNING, true);
            Gamerules.BLAZE_SPAWNERS = register("blazeSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.SPIDER_SPAWNERS = register("spiderSpawners", GameruleCategories.VD_SPAWNING, true);
            Gamerules.MAGMA_CUBE_SPAWNERS = register("magmaCubeSpawners",GameruleCategories.VD_SPAWNING, true);
            Gamerules.SPAWN_EGGS = register("spawnEggs", GameruleCategories.VD_SPAWNING, true);
            Gamerules.ANIMAL_BREEDING = register("animalBreeding", GameruleCategories.VD_SPAWNING, true);
        }
        if (VanillaDisableMixinConfigPlugin.despawning) {
            Gamerules.MIN_SPAWN_DISTANCE = register("minSpawnDistance",GameruleCategories.VD_SPAWNING, 24, 0, 512);
            Gamerules.MONSTERS_DESPAWN = register("monstersDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.CREATURES_DESPAWN = register("creaturesDespawn",GameruleCategories.VD_DESPAWNING, false);
            Gamerules.AMBIENT_DESPAWN = register("ambientDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.AXOLOTLS_DESPAWN = register("axolotlsDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.GLOWSQUIDS_DESPAWN = register("glowsquidsDespawn",GameruleCategories.VD_DESPAWNING, true);
            Gamerules.WATER_CREATURES_DESPAWN = register("waterCreaturesDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.WATER_AMBIENT_DESPAWN = register("waterAmbientDespawn", GameruleCategories.VD_DESPAWNING, true);
            Gamerules.MONSTER_MAX_DESPAWN = register("monsterMaxDespawn",GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.CREATURE_MAX_DESPAWN = register("creatureMaxDespawn", GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.AMBIENT_MAX_DESPAWN = register("ambientMaxDespawn",GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.AXOLOTL_MAX_DESPAWN = register("axolotlMaxDespawn",GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.GLOWSQUID_MAX_DESPAWN = register("glowsquidMaxDespawn", GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.WATER_CREATURE_MAX_DESPAWN= register("waterCreatureMaxDespawn", GameruleCategories.VD_DESPAWNING, 128, 0, 512);
            Gamerules.WATER_AMBIENT_MAX_DESPAWN = register("waterAmbientMaxDespawn", GameruleCategories.VD_DESPAWNING, 64, 0, 512);
            Gamerules.MONSTER_MIN_DESPAWN = register("monsterMinDespawn",GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.CREATURE_MIN_DESPAWN = register("creatureMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.AMBIENT_MIN_DESPAWN = register("ambientMinDespawn",GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.AXOLOTL_MIN_DESPAWN = register("axolotlMinDespawn",GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.GLOWSQUID_MIN_DESPAWN = register("glowsquidMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.WATER_CREATURE_MIN_DESPAWN= register("waterCreatureMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.WATER_AMBIENT_MIN_DESPAWN = register("waterAmbientMinDespawn", GameruleCategories.VD_DESPAWNING, 32, 0, 512);
            Gamerules.ITEM_DESPAWN_TIME = register("itemDespawnTime", GameruleCategories.VD_DESPAWNING, 300, 0, Integer.MAX_VALUE);
            Gamerules.ENDER_PEARLS_DESPAWN_ON_DEATH = register("enderPearlsDespawnOnDeath", GameruleCategories.VD_DESPAWNING, true);
        }
        if (VanillaDisableMixinConfigPlugin.spawn_limits) {
            Gamerules.MONSTER_MOBCAP = register("monsterMobCap", GameruleCategories.VD_SPAWN_LIMITS, 70, 0, Integer.MAX_VALUE);
            Gamerules.CREATURE_MOBCAP = register("creatureMobCap", GameruleCategories.VD_SPAWN_LIMITS, 10, 0, Integer.MAX_VALUE);
            Gamerules.AMBIENT_MOBCAP = register("ambientMobCap", GameruleCategories.VD_SPAWN_LIMITS, 15, 0, Integer.MAX_VALUE);
            Gamerules.AXOLOTL_MOBCAP = register("axolotlMobCap", GameruleCategories.VD_SPAWN_LIMITS, 5, 0, Integer.MAX_VALUE);
            Gamerules.GLOWSQUID_MOBCAP = register("glowsquidMobCap", GameruleCategories.VD_SPAWN_LIMITS, 5, 0, Integer.MAX_VALUE);
            Gamerules.WATER_CREATURE_MOBCAP = register("waterCreatureMobCap", GameruleCategories.VD_SPAWN_LIMITS, 5, 0, Integer.MAX_VALUE);
            Gamerules.WATER_AMBIENT_MOBCAP = register("waterAmbientMobCap", GameruleCategories.VD_SPAWN_LIMITS, 20, 0, Integer.MAX_VALUE);
            Gamerules.MONSTER_MAX_LIGHT_LEVEL = register("monsterMaxLightLevel", GameruleCategories.VD_SPAWN_LIMITS, 0, 0, 15);
        }
        if (VanillaDisableMixinConfigPlugin.commands) {
            Gamerules.COMMANDS_ENABLED = register("commandsEnabled", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ADVANCEMENT_COMMAND = register("advancementCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ATTRIBUTE_COMMAND = register("attributeCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.BOSS_BAR_COMMAND = register("bossBarCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.CHASE_COMMAND = register("chaseCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.CLEAR_COMMAND = register("clearCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.CLONE_COMMAND = register("cloneCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.DATAPACK_COMMAND = register("datapackCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.DATA_COMMAND = register("dataCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.DIFFICULTY_COMMAND = register("difficultyCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.EFFECT_COMMAND = register("effectCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ENCHANT_COMMAND = register("enchantCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.EXECUTE_COMMAND = register("executeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.EXPERIENCE_COMMAND = register("experienceCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.FILL_COMMAND = register("fillCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.FORCE_LOAD_COMMAND = register("forceLoadCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.FUNCTION_COMMAND = register("functionCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.GAME_MODE_COMMAND = register("gameModeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.GIVE_COMMAND = register("giveCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.HELP_COMMAND = register("helpCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ITEM_COMMAND = register("itemCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.JFR_COMMAND = register("jfrCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.KICK_COMMAND = register("kickCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.KILL_COMMAND = register("killCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LIST_COMMAND = register("listCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LOCATE_BIOME_COMMAND = register("locateBiomeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LOCATE_COMMAND = register("locateCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.LOOT_COMMAND = register("lootCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.ME_COMMAND = register("meCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.MESSAGE_COMMAND = register("messageCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PARTICLE_COMMAND = register("particleCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PLAY_SOUND_COMMAND = register("playSoundCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.PUBLISH_COMMAND = register("publishCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RAID_COMMAND = register("raidCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RECIPE_COMMAND = register("recipeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RELOAD_COMMAND = register("reloadCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.RESET_CHUNKS_COMMAND = register("resetChunksCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAY_COMMAND = register("sayCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SCHEDULE_COMMAND = register("scheduleCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SCOREBOARD_COMMAND = register("scoreboardCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.SEED_COMMAND = register("seedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SET_BLOCK_COMMAND = register("setBlockCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SET_WORLD_SPAWN_COMMAND = register("setWorldSpawnCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SPAWN_POINT_COMMAND = register("spawnPointCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.STOP_SOUND_COMMAND = register("stopSoundCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.SUMMON_COMMAND = register("summonCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TEAM_COMMAND = register("teamCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TEAM_MSG_COMMAND = register("teamMsgCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TELEPORT_COMMAND = register("teleportCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TELL_RAW_COMMAND = register("tellRawCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TIME_COMMAND = register("timeCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.TITLE_COMMAND = register("titleCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.TRIGGER_COMMAND = register("triggerCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.WEATHER_COMMAND = register("weatherCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.WORLD_BORDER_COMMAND = register("worldBorderCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.BAN_DEDICATED_COMMAND = register("banDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.BAN_IP_DEDICATED_COMMAND = register("banIpDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.BAN_LIST_DEDICATED_COMMAND = register("banListDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.DE_OP_DEDICATED_COMMAND = register("deOpDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.OP_DEDICATED_COMMAND = register("opDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PARDON_DEDICATED_COMMAND = register("pardonDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.PARDON_IP_DEDICATED_COMMAND = register("pardonIpDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.PERF_DEDICATED_COMMAND = register("perfDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAVE_ALL_DEDICATED_COMMAND = register("saveAllDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAVE_OFF_DEDICATED_COMMAND = register("saveOffDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SAVE_ON_DEDICATED_COMMAND = register("saveOnDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND = register("setIdleTimeoutDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
            Gamerules.STOP_DEDICATED_COMMAND = register("stopDedicatedCommand",GameruleCategories.VD_COMMANDS, true);
            Gamerules.WHITELIST_DEDICATED_COMMAND = register("whitelistDedicatedCommand", GameruleCategories.VD_COMMANDS, true);
        }
        if (VanillaDisableMixinConfigPlugin.fluids) {
            Gamerules.INFINITE_WATER = register("infiniteWater", GameruleCategories.VD_FLUIDS, true);
            Gamerules.INFINITE_LAVA = register("infiniteLava", GameruleCategories.VD_FLUIDS, false);
            Gamerules.WATER_REACHES_FAR = register("waterReachesFar",GameruleCategories.VD_FLUIDS, true);
            Gamerules.LAVA_REACHES_FAR = register("lavaReachesFar", GameruleCategories.VD_FLUIDS, false);
            Gamerules.LAVA_REACHES_FAR_IN_NETHER = register("lavaReachesFarInNether", GameruleCategories.VD_FLUIDS, true);
            Gamerules.WATER_FLOW_SPEED = register("waterFlowSpeed", GameruleCategories.VD_FLUIDS, 5, 1, 128);
            Gamerules.LAVA_FLOW_SPEED = register("lavaFlowSpeed", GameruleCategories.VD_FLUIDS, 30, 1, 128);
            Gamerules.LAVA_FLOW_SPEED_NETHER = register("lavaFlowSpeedNether",GameruleCategories.VD_FLUIDS, 10, 1, 128);
            Gamerules.WATER_PLACEABLE_IN_NETHER = register("waterPlaceableInNether", GameruleCategories.VD_FLUIDS, false);
            Gamerules.BUBBLE_COLUMNS_ENABLED = register("bubbleColumnsEnabled",GameruleCategories.VD_FLUIDS, true);
        }
        if (VanillaDisableMixinConfigPlugin.mob) {
            Gamerules.CURABLE_ZILLAGERS = register("curableZillagers", GameruleCategories.VD_MOBS, true);
            Gamerules.VILLAGERS_CONVERT_TO_ZILLAGERS = register("villagersConvertToZillagers", GameruleCategories.VD_MOBS, true);
            Gamerules.VILLAGERS_CONVERT_TO_WITCHES = register("villagersConvertToWitches", GameruleCategories.VD_MOBS, true);
            Gamerules.PIGLINS_CONVERT_TO_ZIGLINS = register("piglinsConvertToZiglins",GameruleCategories.VD_MOBS, true);
            Gamerules.HOGLINS_CONVERT_TO_ZOGLINS = register("hoglinsConvertToZoglins",GameruleCategories.VD_MOBS, true);
            Gamerules.HUSKS_CONVERT_TO_ZOMBIES = register("husksConvertToZombies", GameruleCategories.VD_MOBS, true);
            Gamerules.ZOMBIES_CONVERT_TO_DROWNED = register("zombiesConvertToDrowned",GameruleCategories.VD_MOBS, true);
            Gamerules.SKELETONS_CONVERT_TO_STRAYS = register("skeletonsConvertToStrays",GameruleCategories.VD_MOBS, true);
            Gamerules.INFINITE_TRADING = register("infiniteTrading", GameruleCategories.VD_MOBS, false);
            Gamerules.VILLAGER_TRADING_ENABLED = register("villagerTradingEnabled", GameruleCategories.VD_MOBS, true);
            Gamerules.PIGLIN_BARTERING_ENABLED = register("piglinBarteringEnabled", GameruleCategories.VD_MOBS, true);
            Gamerules.WITHER_SPAWNS = register("witherSpawns", GameruleCategories.VD_MOBS, true);
            Gamerules.PIGS_BREED_WITH_WHEAT = register("pigsBreedWithWheat", GameruleCategories.VD_MOBS, false);
            Gamerules.MOBS_BURN_IN_SUNLIGHT = register("mobsBurnInSunlight", GameruleCategories.VD_MOBS, true);
            Gamerules.DRAGON_FIREBALLS = register("dragonFireballs", GameruleCategories.VD_MOBS, true);
            Gamerules.FIRE_ASPECT_IGNITES_CREEPERS = register("fireAspectIgnitesCreepers", GameruleCategories.VD_MOBS, false);
        }
        if (VanillaDisableMixinConfigPlugin.effects) {
            Gamerules.EFFECTS_ENABLED = register("effectsEnabled", GameruleCategories.VD_EFFECTS, true);
            Gamerules.ABSORPTION_EFFECT = register("absorptionEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.BAD_OMEN_EFFECT = register("badOmenEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.BLINDNESS_EFFECT = register("blindnessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.CONDUIT_POWER_EFFECT = register("conduitPowerEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.DOLPHINS_GRACE_EFFECT = register("dolphinsGraceEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.FIRE_RESISTANCE_EFFECT = register("fireResistanceEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.GLOWING_EFFECT = register("glowingEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.HASTE_EFFECT = register("hasteEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.HEALTH_BOOST_EFFECT = register("healthBoostEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.HUNGER_EFFECT = register("hungerEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.INSTANT_DAMAGE_EFFECT = register("instantDamageEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.INSTANT_HEALTH_EFFECT = register("instantHealthEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.INVISIBILITY_EFFECT = register("invisibilityEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.JUMP_BOOST_EFFECT = register("jumpBoostEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.LEVITATION_EFFECT = register("levitationEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.LUCK_EFFECT = register("luckEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.MINING_FATIGUE_EFFECT = register("miningFatigueEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.NAUSEA_EFFECT = register("nauseaEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.NIGHT_VISION_EFFECT = register("nightVisionEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.POISON_EFFECT = register("poisonEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.REGENERATION_EFFECT = register("regenerationEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.RESISTANCE_EFFECT = register("resistanceEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.SATURATION_EFFECT = register("saturationEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.SLOWNESS_EFFECT = register("slownessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.SLOW_FALLING_EFFECT = register("slowFallingEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.SPEED_EFFECT = register("speedEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.STRENGTH_EFFECT = register("strengthEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.UNLUCK_EFFECT = register("unluckEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.WATER_BREATHING_EFFECT = register("waterBreathingEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.WEAKNESS_EFFECT = register("weaknessEffect", GameruleCategories.VD_EFFECTS, true);
            Gamerules.WITHER_EFFECT = register("witherEffect",GameruleCategories.VD_EFFECTS, true);
            Gamerules.MILK_CLEARS_EFFECTS = register("milkClearsEffects",GameruleCategories.VD_EFFECTS, true);
        }
        if (VanillaDisableMixinConfigPlugin.enchantments) {
            Gamerules.ENCHANTMENTS_ENABLED = register("enchantmentsEnabled", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.AQUA_AFFINITY_ENCHANTMENT = register("aquaAffinityEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.BANE_OF_ARTHROPODS_ENCHANTMENT = register("baneOfArthropodsEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.BLAST_PROTECTION_ENCHANTMENT = register("blastProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.CHANNELING_ENCHANTMENT = register("channelingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.DEPTH_STRIDER_ENCHANTMENT = register("depthStriderEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.EFFICIENCY_ENCHANTMENT = register("efficiencyEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FEATHER_FALLING_ENCHANTMENT = register("featherFallingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FIRE_ASPECT_ENCHANTMENT = register("fireAspectEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FIRE_PROTECTION_ENCHANTMENT = register("fireProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FLAME_ENCHANTMENT = register("flameEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FORTUNE_ENCHANTMENT = register("fortuneEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.FROST_WALKER_ENCHANTMENT = register("frostWalkerEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.IMPALING_ENCHANTMENT = register("impalingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.INFINITY_ENCHANTMENT = register("infinityEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.KNOCKBACK_ENCHANTMENT = register("knockbackEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LOOTING_ENCHANTMENT = register("lootingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LOYALTY_ENCHANTMENT = register("loyaltyEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LUCK_OF_THE_SEA_ENCHANTMENT = register("luckOfTheSeaEnchantment",GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.LURE_ENCHANTMENT = register("lureEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.MENDING_ENCHANTMENT = register("mendingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.MULTISHOT_ENCHANTMENT = register("multishotEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PIERCING_ENCHANTMENT = register("piercingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.POWER_ENCHANTMENT = register("powerEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PROJECTILE_PROTECTION_ENCHANTMENT = register("projectileProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PROTECTION_ENCHANTMENT = register("protectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.PUNCH_ENCHANTMENT = register("punchEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.QUICK_CHARGE_ENCHANTMENT = register("quickChargeEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.RESPIRATION_ENCHANTMENT = register("respirationEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.RIPTIDE_ENCHANTMENT = register("riptideEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SHARPNESS_ENCHANTMENT = register("sharpnessEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SILK_TOUCH_ENCHANTMENT = register("silkTouchEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SMITE_ENCHANTMENT = register("smiteEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SOUL_SPEED_ENCHANTMENT = register("soulSpeedEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.SWEEPING_ENCHANTMENT = register("sweepingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.THORNS_ENCHANTMENT = register("thornsEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.UNBREAKING_ENCHANTMENT = register("unbreakingEnchantment", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.BINDING_CURSE = register("bindingCurse", GameruleCategories.VD_ENCHANTMENTS, true);
            Gamerules.VANISHING_CURSE = register("vanishingCurse", GameruleCategories.VD_ENCHANTMENTS, true);
            if (VanillaDisableMixinConfigPlugin.enchantment_conflicts) {
                Gamerules.BOOT_ENCHANTMENT_CONFLICTS = register("bootEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.BOW_ENCHANTMENT_CONFLICTS = register("bowEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.CROSSBOW_ENCHANTMENT_CONFLICTS = register("crossbowEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS = register("damageEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.MINING_ENCHANTMENT_CONFLICTS = register("miningEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS = register("protectionEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, true);
                Gamerules.TRIDENT_ENCHANTMENT_CONFLICTS = register("tridentEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, true);
            }
        }
        if (VanillaDisableMixinConfigPlugin.worldgen) {
            Gamerules.BASTION_REMNANT_GENERATION = register("bastionRemnantGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.BURIED_TREASURE_GENERATION = register("buriedTreasureGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DESERT_PYRAMID_GENERATION = register("desertPyramidGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_CITY_GENERATION = register("endCityGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.FORTRESS_GENERATION = register("fortressGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.IGLOO_GENERATION = register("iglooGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.JUNGLE_PYRAMID_GENERATION = register("junglePyramidGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MANSION_GENERATION = register("mansionGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MINESHAFT_GENERATION = register("mineshaftGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MONUMENT_GENERATION = register("monumentGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_FOSSIL_GENERATION = register("netherFossilGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OCEAN_RUIN_GENERATION = register("oceanRuinGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.PILLAGER_OUTPOST_GENERATION = register("pillagerOutpostGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.RUINED_PORTAL_GENERATION = register("ruinedPortalGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SHIPWRECK_GENERATION = register("shipwreckGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.STRONGHOLD_GENERATION = register("strongholdGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SWAMP_HUT_GENERATION = register("swampHutGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.VILLAGE_GENERATION = register("villageGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_VEGETATION = register("endVegetation", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_VEGETATION = register("netherVegetation",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OCEAN_VEGETATION = register("oceanVegetation", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OVERWORLD_VEGETATION = register("overworldVegetation", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.UNDERGROUND_VEGETATION = register("undergroundVegetation",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.AMETHYST_GEODE_GENERATION = register("amethystGeodeGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.BASALT_BLACKSTONE_GENERATION = register("basaltBlackstoneGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DESERT_WELL_GENERATION = register("desertWellGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DRIPSTONE_GENERATION = register("dripstoneGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.DUNGEON_GENERATION = register("dungeonGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_FEATURES_GENERATION = register("endFeaturesGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.FOSSIL_GENERATION = register("fossilGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.GLOWSTONE_GENERATION = register("glowstoneGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.ICE_GENERATION = register("iceGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.LAVA_LAKE_GENERATION = register("lavaLakeGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.MAGMA_GENERATION = register("magmaGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_FIRE_GENERATION = register("netherFireGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.NETHER_ORE_GENERATION = register("netherOreGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.OCEAN_FLOOR_GENERATION = register("oceanFloorGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.ORE_GENERATION = register("oreGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.SPRING_GENERATION = register("springGeneration",GameruleCategories.VD_WORLDGEN, true);
            Gamerules.TREE_GENERATION = register("treeGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.WELL_GENERATION = register("wellGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.END_PILLAR_CAGE_GENERATION = register("endPillarCageGeneration", GameruleCategories.VD_WORLDGEN, true);
            Gamerules.REMOVE_OVERWORLD_BIOMES = register("removeOverworldBiomes",GameruleCategories.VD_WORLDGEN, false);
            Gamerules.REMOVE_NETHER_BIOMES = register("removeNetherBiomes", GameruleCategories.VD_WORLDGEN, false);
            Gamerules.REMOVE_END_BIOMES = register("removeEndBiomes", GameruleCategories.VD_WORLDGEN, false);
        }
        if (VanillaDisableMixinConfigPlugin.player) {
            Gamerules.PLAYER_CAN_BE_ON_FIRE = register("playerCanBeOnFire", GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_SPRINT = register("playerCanSprint",GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_CROUCH = register("playerCanCrouch",GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_SWIM = register("playerCanSwim", GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_JUMP = register("playerCanJump", GameruleCategories.VD_PLAYER, true);
            Gamerules.PLAYER_CAN_BE_INVISIBLE = register("playerCanBeInvisible",GameruleCategories.VD_PLAYER, true);
        }
        if (VanillaDisableMixinConfigPlugin.redstone) {
            Gamerules.REPEATER_BASE_DELAY = register("repeaterBaseDelay", GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.REPEATER_SIGNAL = register("repeaterSignal", GameruleCategories.VD_REDSTONE, 15, 0, 15);
            Gamerules.COMPARATOR_BASE_DELAY = register("comparatorBaseDelay",GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.COMPARATOR_ENABLED = register("comparatorEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.TORCH_REDSTONE_SIGNAL = register("torchRedstoneSignal",GameruleCategories.VD_REDSTONE, 15, 0, 15);
            Gamerules.REDSTONE_WIRE_ENABLED = register("redstoneWireEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.DROPPER_ENABLED = register("dropperEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.DISPENSER_ENABLED = register("dispenserEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.DAYLIGHT_SENSOR_ENABLED = register("daylightSensorEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.WOOD_BUTTON_PRESS_DURATION = register("woodButtonPressDuration", GameruleCategories.VD_REDSTONE, 30, 0);
            Gamerules.STONE_BUTTON_PRESS_DURATION = register("stoneButtonPressDuration",GameruleCategories.VD_REDSTONE, 20, 0);
            Gamerules.BUTTON_ENABLED = register("buttonEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.LEVER_ENABLED = register("leverEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.LIGHTNING_ROD_ENABLED = register("lightningRodEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.OBSERVER_DELAY = register("observerDelay", GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.OBSERVER_DURATION = register("observerDuration", GameruleCategories.VD_REDSTONE, 2, 0);
            Gamerules.OBSERVER_ENABLED = register("observerEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.PRESSURE_PLATE_ENABLED = register("pressurePlateEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.TARGET_BLOCK_ENABLED = register("targetBlockEnabled", GameruleCategories.VD_REDSTONE, true);
            Gamerules.TRAPPED_CHEST_ENABLED = register("trappedChestEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.TRIPWIRE_HOOK_ENABLED = register("tripwireHookEnabled",GameruleCategories.VD_REDSTONE, true);
            Gamerules.PISTON_ENABLED = register("pistonEnabled", GameruleCategories.VD_REDSTONE, true);
        }
        if (VanillaDisableMixinConfigPlugin.misc) {
            Gamerules.NETHER_PORTALS_ENABLED = register("netherPortalsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.END_PORTALS_ENABLED = register("endPortalsEnabled",GameruleCategories.VD_MISC, true);
            Gamerules.END_GATEWAYS_ENABLED = register("endGatewaysEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.NETHER_PORTAL_COOLDOWN = register("netherPortalCooldown", GameruleCategories.VD_MISC, 300);
            Gamerules.CROP_TRAMPLING = register("cropTrampling", GameruleCategories.VD_MISC, true);
            if (VanillaDisableMixinConfigPlugin.misc_hunger) {
                Gamerules.OLD_HUNGER = register("oldHunger", GameruleCategories.VD_MISC, false);
            }
            Gamerules.OLD_BOATS = register("oldBoats", GameruleCategories.VD_MISC, false);
            Gamerules.BEACONS_ENABLED = register("beaconsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.CONDUITS_ENABLED = register("conduitsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.ICE_SLIDING = register("iceSliding", GameruleCategories.VD_MISC, true);
            Gamerules.TOTEMS_ENABLED = register("totemsEnabled", GameruleCategories.VD_MISC, true);
            Gamerules.BOW_SPAMMING = register("bowSpamming", GameruleCategories.VD_MISC, false);
            Gamerules.CROSSBOW_SPAMMING = register("crossbowSpamming",GameruleCategories.VD_MISC, false);
            Gamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS = register("creativeSwordCanBreakBlocks", GameruleCategories.VD_MISC, false);
            Gamerules.PUSHABLE_BUDDING_AMETHYST = register("pushableBuddingAmethyst",GameruleCategories.VD_MISC, false);
            if (VanillaDisableMixinConfigPlugin.misc_container) {
                Gamerules.CONTAINER_OPENING_BLOCKED = register("containerOpeningBlocked",GameruleCategories.VD_MISC, true);
            }
        }
    }
}
