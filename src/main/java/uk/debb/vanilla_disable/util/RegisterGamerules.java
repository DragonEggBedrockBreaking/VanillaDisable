package uk.debb.vanilla_disable.util;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import org.spongepowered.asm.mixin.Unique;
import uk.debb.vanilla_disable.mixin.VanillaDisableMixinConfigPlugin;

public class RegisterGamerules {
    /**
     * @author DragonEggBedrockBreaking
     * @reason register all of our gamerules
     */
    @Unique
    public static void registerGamerules() {
        if (VanillaDisableMixinConfigPlugin.damage) {
            Gamerules.DAMAGE_ENABLED            = GameRuleRegistry.register(
                "damageEnabled",   GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.PROJECTILE_DAMAGE         = GameRuleRegistry.register(
                "projectileDamage",GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.EXPLOSION_DAMAGE          = GameRuleRegistry.register(
                "explosionDamage", GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.FALLING_BLOCK_DAMAGE      = GameRuleRegistry.register(
                "fallingBlockDamage",   GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.VOID_DAMAGE               = GameRuleRegistry.register(
                "voidDamage",      GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.MAGIC_DAMAGE              = GameRuleRegistry.register(
                "magicDamage",     GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.CREATIVE_PLAYER_DAMAGE    = GameRuleRegistry.register(
                "creativePlayerDamage", GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.LIGHTNING_DAMAGE          = GameRuleRegistry.register(
                "lightningDamage", GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.WALL_DAMAGE               = GameRuleRegistry.register(
                "wallDamage",      GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.CRAMMING_DAMAGE           = GameRuleRegistry.register(
                "crammingDamage",  GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.STARVATION_DAMAGE         = GameRuleRegistry.register(
                "starvationDamage",GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.CACTUS_DAMAGE             = GameRuleRegistry.register(
                "cactusDamage",GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.FLY_INTO_WALL_DAMAGE      = GameRuleRegistry.register(
                "flyIntoWallDamage",GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.WITHER_DAMAGE             = GameRuleRegistry.register(
                "witherDamage",GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.ANVIL_DAMAGE              = GameRuleRegistry.register(
                "anvilDamage",     GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.DRAGON_DAMAGE             = GameRuleRegistry.register(
                "dragonDamage",GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.SWEET_BERRY_BUSH_DAMAGE   = GameRuleRegistry.register(
                "sweetBerryBushDamage", GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
            Gamerules.FALLING_STALACTITE_DAMAGE = GameRuleRegistry.register(
                "fallingStalactiteDamage", GameruleCategories.VD_DAMAGE, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.knockback) {
            Gamerules.KNOCKBACK_ENABLED         = GameRuleRegistry.register(
                "knockbackEnabled",GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.FIREBALL_KNOCKBACK        = GameRuleRegistry.register(
                "fireballKnockback",GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.WITHER_SKULL_KNOCKBACK    = GameRuleRegistry.register(
                "witherSkullKnockback", GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.DRAGON_KNOCKBACK          = GameRuleRegistry.register(
                "dragonKnockback", GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.ARROW_KNOCKBACK           = GameRuleRegistry.register(
                "arrowKnockback",  GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.TRIDENT_KNOCKBACK         = GameRuleRegistry.register(
                "tridentKnockback",GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.LLAMA_SPIT_KNOCKBACK      = GameRuleRegistry.register(
                "llamaSpitKnockback",   GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.SHULKER_BULLET_KNOCKBACK  = GameRuleRegistry.register(
                "shulkerBulletKnockback", GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.MOB_ATTACK_KNOCKBACK      = GameRuleRegistry.register(
                "mobAttackKnockback",   GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAYER_ATTACK_KNOCKBACK   = GameRuleRegistry.register(
                "playerAttackKnockback", GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
            Gamerules.EXPLOSION_KNOCKBACK       = GameRuleRegistry.register(
                "explosionKnockback",   GameruleCategories.VD_KNOCKBACK, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.spawning) {
            Gamerules.MONSTER_SPAWNING          = GameRuleRegistry.register(
                "monsterSpawning", GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.CREATURE_SPAWNING         = GameRuleRegistry.register(
                "creatureSpawning",GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.AMBIENT_SPAWNING          = GameRuleRegistry.register(
                "ambientSpawning", GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.AXOLOTL_SPAWNING          = GameRuleRegistry.register(
                "axolotlSpawning", GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.GLOWSQUID_SPAWNING        = GameRuleRegistry.register(
                "glowsquidSpawning",GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.WATER_CREATURE_SPAWNING   = GameRuleRegistry.register(
                "waterCreatureSpawning", GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.WATER_AMBIENT_SPAWNING    = GameRuleRegistry.register(
                "waterAmbientSpawning", GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.SPAWNERS_ENABLED          = GameRuleRegistry.register(
                "spawnersEnabled", GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.PIG_SPAWNERS              = GameRuleRegistry.register(
                "pigSpawners",     GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.CAVE_SPIDER_SPAWNERS      = GameRuleRegistry.register(
                "caveSpiderSpawners",   GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.SILVERFISH_SPAWNERS       = GameRuleRegistry.register(
                "silverfishSpawners",   GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.ZOMBIE_SPAWNERS           = GameRuleRegistry.register(
                "zombieSpawners",  GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.SKELETON_SPAWNERS         = GameRuleRegistry.register(
                "skeletonSpawners",GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.BLAZE_SPAWNERS            = GameRuleRegistry.register(
                "blazeSpawners",   GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.SPIDER_SPAWNERS           = GameRuleRegistry.register(
                "spiderSpawners",  GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.MAGMA_CUBE_SPAWNERS       = GameRuleRegistry.register(
                "magmaCubeSpawners",GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.SPAWN_EGGS                = GameRuleRegistry.register(
                "spawnEggs",       GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.ANIMAL_BREEDING           = GameRuleRegistry.register(
                "animalBreeding",  GameruleCategories.VD_SPAWNING, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.despawning) {
            Gamerules.MIN_SPAWN_DISTANCE        = GameRuleRegistry.register(
                "minSpawnDistance",GameruleCategories.VD_SPAWNING, GameRuleFactory.createIntRule(24, 0, 512));
            Gamerules.MONSTERS_DESPAWN          = GameRuleRegistry.register(
                "monstersDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.CREATURES_DESPAWN         = GameRuleRegistry.register(
                "creaturesDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(false));
            Gamerules.AMBIENT_DESPAWN           = GameRuleRegistry.register(
                "ambientDespawn",  GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.AXOLOTLS_DESPAWN          = GameRuleRegistry.register(
                "axolotlsDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.GLOWSQUIDS_DESPAWN        = GameRuleRegistry.register(
                "glowsquidsDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.WATER_CREATURES_DESPAWN   = GameRuleRegistry.register(
                "waterCreaturesDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.WATER_AMBIENT_DESPAWN    = GameRuleRegistry.register(
                "waterAmbientDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
            Gamerules.MONSTER_MAX_DESPAWN       = GameRuleRegistry.register(
                "monsterMaxDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
            Gamerules.CREATURE_MAX_DESPAWN      = GameRuleRegistry.register(
                "creatureMaxDespawn",   GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
            Gamerules.AMBIENT_MAX_DESPAWN       = GameRuleRegistry.register(
                "ambientMaxDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
            Gamerules.AXOLOTL_MAX_DESPAWN       = GameRuleRegistry.register(
                "axolotlMaxDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
            Gamerules.GLOWSQUID_MAX_DESPAWN     = GameRuleRegistry.register(
                "glowsquidMaxDespawn",  GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
            Gamerules.WATER_CREATURE_MAX_DESPAWN= GameRuleRegistry.register(
                "waterCreatureMaxDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(128, 0, 512));
            Gamerules.WATER_AMBIENT_MAX_DESPAWN = GameRuleRegistry.register(
                "waterAmbientMaxDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(64, 0, 512));
            Gamerules.MONSTER_MIN_DESPAWN       = GameRuleRegistry.register(
                "monsterMinDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.CREATURE_MIN_DESPAWN      = GameRuleRegistry.register(
                "creatureMinDespawn",   GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.AMBIENT_MIN_DESPAWN       = GameRuleRegistry.register(
                "ambientMinDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.AXOLOTL_MIN_DESPAWN       = GameRuleRegistry.register(
                "axolotlMinDespawn",GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.GLOWSQUID_MIN_DESPAWN     = GameRuleRegistry.register(
                "glowsquidMinDespawn",  GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.WATER_CREATURE_MIN_DESPAWN= GameRuleRegistry.register(
                "waterCreatureMinDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.WATER_AMBIENT_MIN_DESPAWN = GameRuleRegistry.register(
                "waterAmbientMinDespawn", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(32, 0, 512));
            Gamerules.ITEM_DESPAWN_TIME         = GameRuleRegistry.register(
                "itemDespawnTime", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createIntRule(300, 0, Integer.MAX_VALUE));
            Gamerules.ENDER_PEARLS_DESPAWN_ON_DEATH = GameRuleRegistry.register(
                "enderPearlsDespawnOnDeath", GameruleCategories.VD_DESPAWNING, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.spawn_limits) {
            Gamerules.MONSTER_MOBCAP           = GameRuleRegistry.register(
                "monsterMobCap",   GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(70, 0, Integer.MAX_VALUE));
            Gamerules.CREATURE_MOBCAP          = GameRuleRegistry.register(
                "creatureMobCap",  GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(10, 0, Integer.MAX_VALUE));
            Gamerules.AMBIENT_MOBCAP           = GameRuleRegistry.register(
                "ambientMobCap",   GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(15, 0, Integer.MAX_VALUE));
            Gamerules.AXOLOTL_MOBCAP           = GameRuleRegistry.register(
                "axolotlMobCap",   GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(5, 0, Integer.MAX_VALUE));
            Gamerules.GLOWSQUID_MOBCAP         = GameRuleRegistry.register(
                "glowsquidMobCap", GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(5, 0, Integer.MAX_VALUE));
            Gamerules.WATER_CREATURE_MOBCAP    = GameRuleRegistry.register(
                "waterCreatureMobCap",  GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(5, 0, Integer.MAX_VALUE));
            Gamerules.WATER_AMBIENT_MOBCAP     = GameRuleRegistry.register(
                "waterAmbientMobCap",   GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(20, 0, Integer.MAX_VALUE));
            Gamerules.MONSTER_MAX_LIGHT_LEVEL  = GameRuleRegistry.register(
                "monsterMaxLightLevel", GameruleCategories.VD_SPAWN_LIMITS, GameRuleFactory.createIntRule(0, 0, 15));
        }
        if (VanillaDisableMixinConfigPlugin.commands) {
            Gamerules.COMMANDS_ENABLED         = GameRuleRegistry.register(
                "commandsEnabled", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ADVANCEMENT_COMMAND      = GameRuleRegistry.register(
                "advancementCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ATTRIBUTE_COMMAND        = GameRuleRegistry.register(
                "attributeCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BOSS_BAR_COMMAND         = GameRuleRegistry.register(
                "bossBarCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.CHASE_COMMAND            = GameRuleRegistry.register(
                "chaseCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.CLEAR_COMMAND            = GameRuleRegistry.register(
                "clearCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.CLONE_COMMAND            = GameRuleRegistry.register(
                "cloneCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DATAPACK_COMMAND         = GameRuleRegistry.register(
                "datapackCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DATA_COMMAND             = GameRuleRegistry.register(
                "dataCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DIFFICULTY_COMMAND       = GameRuleRegistry.register(
                "difficultyCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.EFFECT_COMMAND           = GameRuleRegistry.register(
                "effectCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ENCHANT_COMMAND          = GameRuleRegistry.register(
                "enchantCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.EXECUTE_COMMAND          = GameRuleRegistry.register(
                "executeCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.EXPERIENCE_COMMAND       = GameRuleRegistry.register(
                "experienceCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FILL_COMMAND             = GameRuleRegistry.register(
                "fillCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FORCE_LOAD_COMMAND       = GameRuleRegistry.register(
                "forceLoadCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FUNCTION_COMMAND         = GameRuleRegistry.register(
                "functionCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.GAME_MODE_COMMAND        = GameRuleRegistry.register(
                "gameModeCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.GIVE_COMMAND             = GameRuleRegistry.register(
                "giveCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.HELP_COMMAND             = GameRuleRegistry.register(
                "helpCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ITEM_COMMAND             = GameRuleRegistry.register(
                "itemCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.JFR_COMMAND              = GameRuleRegistry.register(
                "jfrCommand",      GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.KICK_COMMAND             = GameRuleRegistry.register(
                "kickCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.KILL_COMMAND             = GameRuleRegistry.register(
                "killCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LIST_COMMAND             = GameRuleRegistry.register(
                "listCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LOCATE_BIOME_COMMAND     = GameRuleRegistry.register(
                "locateBiomeCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LOCATE_COMMAND           = GameRuleRegistry.register(
                "locateCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LOOT_COMMAND             = GameRuleRegistry.register(
                "lootCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ME_COMMAND               = GameRuleRegistry.register(
                "meCommand",       GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.MESSAGE_COMMAND          = GameRuleRegistry.register(
                "messageCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PARTICLE_COMMAND         = GameRuleRegistry.register(
                "particleCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAY_SOUND_COMMAND       = GameRuleRegistry.register(
                "playSoundCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PUBLISH_COMMAND          = GameRuleRegistry.register(
                "publishCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RAID_COMMAND             = GameRuleRegistry.register(
                "raidCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RECIPE_COMMAND           = GameRuleRegistry.register(
                "recipeCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RELOAD_COMMAND           = GameRuleRegistry.register(
                "reloadCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RESET_CHUNKS_COMMAND     = GameRuleRegistry.register(
                "resetChunksCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SAY_COMMAND              = GameRuleRegistry.register(
                "sayCommand",      GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SCHEDULE_COMMAND         = GameRuleRegistry.register(
                "scheduleCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SCOREBOARD_COMMAND       = GameRuleRegistry.register(
                "scoreboardCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SEED_COMMAND             = GameRuleRegistry.register(
                "seedCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SET_BLOCK_COMMAND        = GameRuleRegistry.register(
                "setBlockCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SET_WORLD_SPAWN_COMMAND  = GameRuleRegistry.register(
                "setWorldSpawnCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SPAWN_POINT_COMMAND      = GameRuleRegistry.register(
                "spawnPointCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.STOP_SOUND_COMMAND       = GameRuleRegistry.register(
                "stopSoundCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SUMMON_COMMAND           = GameRuleRegistry.register(
                "summonCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TEAM_COMMAND             = GameRuleRegistry.register(
                "teamCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TEAM_MSG_COMMAND         = GameRuleRegistry.register(
                "teamMsgCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TELEPORT_COMMAND         = GameRuleRegistry.register(
                "teleportCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TELL_RAW_COMMAND         = GameRuleRegistry.register(
                "tellRawCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TIME_COMMAND             = GameRuleRegistry.register(
                "timeCommand",     GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TITLE_COMMAND            = GameRuleRegistry.register(
                "titleCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.TRIGGER_COMMAND          = GameRuleRegistry.register(
                "triggerCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WEATHER_COMMAND          = GameRuleRegistry.register(
                "weatherCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WORLD_BORDER_COMMAND     = GameRuleRegistry.register(
                "worldBorderCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BAN_DEDICATED_COMMAND       = GameRuleRegistry.register(
                "banDedicatedCommand",   GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BAN_IP_DEDICATED_COMMAND    = GameRuleRegistry.register(
                "banIpDedicatedCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BAN_LIST_DEDICATED_COMMAND  = GameRuleRegistry.register(
                "banListDedicatedCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DE_OP_DEDICATED_COMMAND     = GameRuleRegistry.register(
                "deOpDedicatedCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.OP_DEDICATED_COMMAND        = GameRuleRegistry.register(
                "opDedicatedCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PARDON_DEDICATED_COMMAND    = GameRuleRegistry.register(
                "pardonDedicatedCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PARDON_IP_DEDICATED_COMMAND = GameRuleRegistry.register(
                "pardonIpDedicatedCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PERF_DEDICATED_COMMAND      = GameRuleRegistry.register(
                "perfDedicatedCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SAVE_ALL_DEDICATED_COMMAND  = GameRuleRegistry.register(
                "saveAllDedicatedCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SAVE_OFF_DEDICATED_COMMAND  = GameRuleRegistry.register(
                "saveOffDedicatedCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SAVE_ON_DEDICATED_COMMAND   = GameRuleRegistry.register(
                "saveOnDedicatedCommand",  GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SET_IDLE_TIMEOUT_DEDICATED_COMMAND = GameRuleRegistry.register(
                "setIdleTimeoutDedicatedCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.STOP_DEDICATED_COMMAND      = GameRuleRegistry.register(
                "stopDedicatedCommand",GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WHITELIST_DEDICATED_COMMAND = GameRuleRegistry.register(
                "whitelistDedicatedCommand", GameruleCategories.VD_COMMANDS, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.fluids) {
            Gamerules.INFINITE_WATER              = GameRuleRegistry.register(
                "infiniteWater",      GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.INFINITE_LAVA               = GameRuleRegistry.register(
                "infiniteLava",       GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(false));
            Gamerules.WATER_REACHES_FAR           = GameRuleRegistry.register(
                "waterReachesFar",GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LAVA_REACHES_FAR            = GameRuleRegistry.register(
                "lavaReachesFar",     GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(false));
            Gamerules.LAVA_REACHES_FAR_IN_NETHER  = GameRuleRegistry.register(
                "lavaReachesFarInNether",  GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WATER_FLOW_SPEED            = GameRuleRegistry.register(
                "waterFlowSpeed",     GameruleCategories.VD_FLUIDS, GameRuleFactory.createIntRule(5, 1, 128));
            Gamerules.LAVA_FLOW_SPEED             = GameRuleRegistry.register(
                "lavaFlowSpeed",      GameruleCategories.VD_FLUIDS, GameRuleFactory.createIntRule(30, 1, 128));
            Gamerules.LAVA_FLOW_SPEED_NETHER      = GameRuleRegistry.register(
                "lavaFlowSpeedNether",GameruleCategories.VD_FLUIDS, GameRuleFactory.createIntRule(10, 1, 128));
            Gamerules.WATER_PLACEABLE_IN_NETHER   = GameRuleRegistry.register(
                "waterPlaceableInNether",  GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(false));
            Gamerules.BUBBLE_COLUMNS_ENABLED      = GameRuleRegistry.register(
                "bubbleColumnsEnabled",GameruleCategories.VD_FLUIDS, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.mob) {
            Gamerules.CURABLE_ZILLAGERS           = GameRuleRegistry.register(
                "curableZillagers",   GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.VILLAGERS_CONVERT_TO_ZILLAGERS  = GameRuleRegistry.register(
                "villagersConvertToZillagers", GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.VILLAGERS_CONVERT_TO_WITCHES    = GameRuleRegistry.register(
                "villagersConvertToWitches",   GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PIGLINS_CONVERT_TO_ZIGLINS      = GameRuleRegistry.register(
                "piglinsConvertToZiglins",GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.HOGLINS_CONVERT_TO_ZOGLINS      = GameRuleRegistry.register(
                "hoglinsConvertToZoglins",GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.HUSKS_CONVERT_TO_ZOMBIES        = GameRuleRegistry.register(
                "husksConvertToZombies",  GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ZOMBIES_CONVERT_TO_DROWNED      = GameRuleRegistry.register(
                "zombiesConvertToDrowned",GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SKELETONS_CONVERT_TO_STRAYS     = GameRuleRegistry.register(
                "skeletonsConvertToStrays",GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.INFINITE_TRADING                = GameRuleRegistry.register(
                "infiniteTrading",        GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(false));
            Gamerules.VILLAGER_TRADING_ENABLED        = GameRuleRegistry.register(
                "villagerTradingEnabled", GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PIGLIN_BARTERING_ENABLED        = GameRuleRegistry.register(
                "piglinBarteringEnabled", GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WITHER_SPAWNS                   = GameRuleRegistry.register(
                "witherSpawns",           GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PIGS_BREED_WITH_WHEAT           = GameRuleRegistry.register(
                "pigsBreedWithWheat",     GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(false));
            Gamerules.MOBS_BURN_IN_SUNLIGHT           = GameRuleRegistry.register(
                "mobsBurnInSunlight",     GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DRAGON_FIREBALLS                = GameRuleRegistry.register(
                "dragonFireballs",        GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FIRE_ASPECT_IGNITES_CREEPERS    = GameRuleRegistry.register(
                "fireAspectIgnitesCreepers",   GameruleCategories.VD_MOBS, GameRuleFactory.createBooleanRule(false));
        }
        if (VanillaDisableMixinConfigPlugin.effects) {
            Gamerules.EFFECTS_ENABLED          = GameRuleRegistry.register(
                "effectsEnabled",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.ABSORPTION_EFFECT        = GameRuleRegistry.register(
                "absorptionEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BAD_OMEN_EFFECT          = GameRuleRegistry.register(
                "badOmenEffect",   GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BLINDNESS_EFFECT         = GameRuleRegistry.register(
                "blindnessEffect", GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.CONDUIT_POWER_EFFECT     = GameRuleRegistry.register(
                "conduitPowerEffect",   GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DOLPHINS_GRACE_EFFECT    = GameRuleRegistry.register(
                "dolphinsGraceEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FIRE_RESISTANCE_EFFECT   = GameRuleRegistry.register(
                "fireResistanceEffect", GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.GLOWING_EFFECT           = GameRuleRegistry.register(
                "glowingEffect",   GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.HASTE_EFFECT             = GameRuleRegistry.register(
                "hasteEffect",     GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.HEALTH_BOOST_EFFECT      = GameRuleRegistry.register(
                "healthBoostEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.HUNGER_EFFECT            = GameRuleRegistry.register(
                "hungerEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.INSTANT_DAMAGE_EFFECT    = GameRuleRegistry.register(
                "instantDamageEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.INSTANT_HEALTH_EFFECT    = GameRuleRegistry.register(
                "instantHealthEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.INVISIBILITY_EFFECT      = GameRuleRegistry.register(
                "invisibilityEffect",   GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.JUMP_BOOST_EFFECT        = GameRuleRegistry.register(
                "jumpBoostEffect", GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LEVITATION_EFFECT        = GameRuleRegistry.register(
                "levitationEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LUCK_EFFECT              = GameRuleRegistry.register(
                "luckEffect",      GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.MINING_FATIGUE_EFFECT    = GameRuleRegistry.register(
                "miningFatigueEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.NAUSEA_EFFECT            = GameRuleRegistry.register(
                "nauseaEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.NIGHT_VISION_EFFECT      = GameRuleRegistry.register(
                "nightVisionEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.POISON_EFFECT            = GameRuleRegistry.register(
                "poisonEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.REGENERATION_EFFECT      = GameRuleRegistry.register(
                "regenerationEffect",   GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RESISTANCE_EFFECT        = GameRuleRegistry.register(
                "resistanceEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SATURATION_EFFECT        = GameRuleRegistry.register(
                "saturationEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SLOWNESS_EFFECT          = GameRuleRegistry.register(
                "slownessEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SLOW_FALLING_EFFECT      = GameRuleRegistry.register(
                "slowFallingEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SPEED_EFFECT             = GameRuleRegistry.register(
                "speedEffect",     GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.STRENGTH_EFFECT          = GameRuleRegistry.register(
                "strengthEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.UNLUCK_EFFECT            = GameRuleRegistry.register(
                "unluckEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WATER_BREATHING_EFFECT   = GameRuleRegistry.register(
                "waterBreathingEffect", GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WEAKNESS_EFFECT          = GameRuleRegistry.register(
                "weaknessEffect",  GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.WITHER_EFFECT            = GameRuleRegistry.register(
                "witherEffect",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.MILK_CLEARS_EFFECTS      = GameRuleRegistry.register(
                "milkClearsEffects",GameruleCategories.VD_EFFECTS, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.enchantments) {
            Gamerules.ENCHANTMENTS_ENABLED                 = GameRuleRegistry.register(
                "enchantmentsEnabled",        GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.AQUA_AFFINITY_ENCHANTMENT            = GameRuleRegistry.register(
                "aquaAffinityEnchantment",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BANE_OF_ARTHROPODS_ENCHANTMENT       = GameRuleRegistry.register(
                "baneOfArthropodsEnchantment",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BLAST_PROTECTION_ENCHANTMENT         = GameRuleRegistry.register(
                "blastProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.CHANNELING_ENCHANTMENT               = GameRuleRegistry.register(
                "channelingEnchantment",      GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.DEPTH_STRIDER_ENCHANTMENT            = GameRuleRegistry.register(
                "depthStriderEnchantment",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.EFFICIENCY_ENCHANTMENT               = GameRuleRegistry.register(
                "efficiencyEnchantment",      GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FEATHER_FALLING_ENCHANTMENT          = GameRuleRegistry.register(
                "featherFallingEnchantment",  GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FIRE_ASPECT_ENCHANTMENT              = GameRuleRegistry.register(
                "fireAspectEnchantment",      GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FIRE_PROTECTION_ENCHANTMENT          = GameRuleRegistry.register(
                "fireProtectionEnchantment",  GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FLAME_ENCHANTMENT                    = GameRuleRegistry.register(
                "flameEnchantment",           GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FORTUNE_ENCHANTMENT                  = GameRuleRegistry.register(
                "fortuneEnchantment",         GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.FROST_WALKER_ENCHANTMENT             = GameRuleRegistry.register(
                "frostWalkerEnchantment",     GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.IMPALING_ENCHANTMENT                 = GameRuleRegistry.register(
                "impalingEnchantment",        GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.INFINITY_ENCHANTMENT                 = GameRuleRegistry.register(
                "infinityEnchantment",        GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.KNOCKBACK_ENCHANTMENT                = GameRuleRegistry.register(
                "knockbackEnchantment",       GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LOOTING_ENCHANTMENT                  = GameRuleRegistry.register(
                "lootingEnchantment",         GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LOYALTY_ENCHANTMENT                  = GameRuleRegistry.register(
                "loyaltyEnchantment",         GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LUCK_OF_THE_SEA_ENCHANTMENT          = GameRuleRegistry.register(
                "luckOfTheSeaEnchantment",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.LURE_ENCHANTMENT                     = GameRuleRegistry.register(
                "lureEnchantment",            GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.MENDING_ENCHANTMENT                  = GameRuleRegistry.register(
                "mendingEnchantment",         GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.MULTISHOT_ENCHANTMENT                = GameRuleRegistry.register(
                "multishotEnchantment",       GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PIERCING_ENCHANTMENT                 = GameRuleRegistry.register(
                "piercingEnchantment",        GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.POWER_ENCHANTMENT                    = GameRuleRegistry.register(
                "powerEnchantment",           GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PROJECTILE_PROTECTION_ENCHANTMENT    = GameRuleRegistry.register(
                "projectileProtectionEnchantment", GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PROTECTION_ENCHANTMENT               = GameRuleRegistry.register(
                "protectionEnchantment",      GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.PUNCH_ENCHANTMENT                    = GameRuleRegistry.register(
                "punchEnchantment",           GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.QUICK_CHARGE_ENCHANTMENT             = GameRuleRegistry.register(
                "quickChargeEnchantment",     GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RESPIRATION_ENCHANTMENT              = GameRuleRegistry.register(
                "respirationEnchantment",     GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.RIPTIDE_ENCHANTMENT                  = GameRuleRegistry.register(
                "riptideEnchantment",         GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SHARPNESS_ENCHANTMENT                = GameRuleRegistry.register(
                "sharpnessEnchantment",       GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SILK_TOUCH_ENCHANTMENT               = GameRuleRegistry.register(
                "silkTouchEnchantment",       GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SMITE_ENCHANTMENT                    = GameRuleRegistry.register(
                "smiteEnchantment",           GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SOUL_SPEED_ENCHANTMENT               = GameRuleRegistry.register(
                "soulSpeedEnchantment",       GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.SWEEPING_ENCHANTMENT                 = GameRuleRegistry.register(
                "sweepingEnchantment",        GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.THORNS_ENCHANTMENT                   = GameRuleRegistry.register(
                "thornsEnchantment",          GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.UNBREAKING_ENCHANTMENT               = GameRuleRegistry.register(
                "unbreakingEnchantment",      GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.BINDING_CURSE                        = GameRuleRegistry.register(
                "bindingCurse",               GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            Gamerules.VANISHING_CURSE                      = GameRuleRegistry.register(
                "vanishingCurse",             GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            if (VanillaDisableMixinConfigPlugin.enchantment_conflicts) {
                Gamerules.BOOT_ENCHANTMENT_CONFLICTS           = GameRuleRegistry.register(
                    "bootEnchantmentConflicts",   GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
                Gamerules.BOW_ENCHANTMENT_CONFLICTS            = GameRuleRegistry.register(
                    "bowEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
                Gamerules.CROSSBOW_ENCHANTMENT_CONFLICTS       = GameRuleRegistry.register(
                    "crossbowEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
                Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS         = GameRuleRegistry.register(
                    "damageEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
                Gamerules.MINING_ENCHANTMENT_CONFLICTS         = GameRuleRegistry.register(
                    "miningEnchantmentConflicts", GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
                Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS     = GameRuleRegistry.register(
                    "protectionEnchantmentConflicts",  GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
                Gamerules.TRIDENT_ENCHANTMENT_CONFLICTS        = GameRuleRegistry.register(
                    "tridentEnchantmentConflicts",GameruleCategories.VD_ENCHANTMENTS, GameRuleFactory.createBooleanRule(true));
            }
        }
        if (VanillaDisableMixinConfigPlugin.worldgen) {
            Gamerules.BASTION_REMNANT_GENERATION     = GameRuleRegistry.register(
                "bastionRemnantGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.BURIED_TREASURE_GENERATION     = GameRuleRegistry.register(
                "buriedTreasureGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.DESERT_PYRAMID_GENERATION      = GameRuleRegistry.register(
                "desertPyramidGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.END_CITY_GENERATION            = GameRuleRegistry.register(
                "endCityGeneration",   GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.FORTRESS_GENERATION            = GameRuleRegistry.register(
                "fortressGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.IGLOO_GENERATION               = GameRuleRegistry.register(
                "iglooGeneration",     GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.JUNGLE_PYRAMID_GENERATION      = GameRuleRegistry.register(
                "junglePyramidGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.MANSION_GENERATION             = GameRuleRegistry.register(
                "mansionGeneration",   GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.MINESHAFT_GENERATION           = GameRuleRegistry.register(
                "mineshaftGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.MONUMENT_GENERATION            = GameRuleRegistry.register(
                "monumentGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.NETHER_FOSSIL_GENERATION       = GameRuleRegistry.register(
                "netherFossilGeneration",   GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.OCEAN_RUIN_GENERATION          = GameRuleRegistry.register(
                "oceanRuinGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.PILLAGER_OUTPOST_GENERATION    = GameRuleRegistry.register(
                "pillagerOutpostGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.RUINED_PORTAL_GENERATION        = GameRuleRegistry.register(
                "ruinedPortalGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.SHIPWRECK_GENERATION           = GameRuleRegistry.register(
                "shipwreckGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.STRONGHOLD_GENERATION          = GameRuleRegistry.register(
                "strongholdGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.SWAMP_HUT_GENERATION           = GameRuleRegistry.register(
                "swampHutGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.VILLAGE_GENERATION             = GameRuleRegistry.register(
                "villageGeneration",   GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.END_VEGETATION                 = GameRuleRegistry.register(
                "endVegetation",       GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.NETHER_VEGETATION              = GameRuleRegistry.register(
                "netherVegetation",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.OCEAN_VEGETATION               = GameRuleRegistry.register(
                "oceanVegetation",     GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.OVERWORLD_VEGETATION           = GameRuleRegistry.register(
                "overworldVegetation", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.UNDERGROUND_VEGETATION         = GameRuleRegistry.register(
                "undergroundVegetation",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.AMETHYST_GEODE_GENERATION      = GameRuleRegistry.register(
                "amethystGeodeGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.BASALT_BLACKSTONE_GENERATION   = GameRuleRegistry.register(
                "basaltBlackstoneGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.DESERT_WELL_GENERATION         = GameRuleRegistry.register(
                "desertWellGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.DRIPSTONE_GENERATION           = GameRuleRegistry.register(
                "dripstoneGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.DUNGEON_GENERATION             = GameRuleRegistry.register(
                "dungeonGeneration",   GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.END_FEATURES_GENERATION        = GameRuleRegistry.register(
                "endFeaturesGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.FOSSIL_GENERATION              = GameRuleRegistry.register(
                "fossilGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.GLOWSTONE_GENERATION           = GameRuleRegistry.register(
                "glowstoneGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.ICE_GENERATION                 = GameRuleRegistry.register(
                "iceGeneration",       GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.LAVA_LAKE_GENERATION           = GameRuleRegistry.register(
                "lavaLakeGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.MAGMA_GENERATION               = GameRuleRegistry.register(
                "magmaGeneration",     GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.NETHER_FIRE_GENERATION         = GameRuleRegistry.register(
                "netherFireGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.NETHER_ORE_GENERATION          = GameRuleRegistry.register(
                "netherOreGeneration", GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.OCEAN_FLOOR_GENERATION         = GameRuleRegistry.register(
                "oceanFloorGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.ORE_GENERATION                 = GameRuleRegistry.register(
                "oreGeneration",       GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.SPRING_GENERATION              = GameRuleRegistry.register(
                "springGeneration",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.TREE_GENERATION                = GameRuleRegistry.register(
                "treeGeneration",      GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.WELL_GENERATION                = GameRuleRegistry.register(
                "wellGeneration",      GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.END_PILLAR_CAGE_GENERATION     = GameRuleRegistry.register(
                "endPillarCageGeneration",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(true));
            Gamerules.REMOVE_OVERWORLD_BIOMES        = GameRuleRegistry.register(
                "removeOverworldBiomes",GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(false));
            Gamerules.REMOVE_NETHER_BIOMES           = GameRuleRegistry.register(
                "removeNetherBiomes",  GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(false));
            Gamerules.REMOVE_END_BIOMES              = GameRuleRegistry.register(
                "removeEndBiomes",     GameruleCategories.VD_WORLDGEN, GameRuleFactory.createBooleanRule(false));
        }
        if (VanillaDisableMixinConfigPlugin.player) {
            Gamerules.PLAYER_CAN_BE_ON_FIRE   = GameRuleRegistry.register(
                "playerCanBeOnFire",   GameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAYER_CAN_SPRINT       = GameRuleRegistry.register(
                "playerCanSprint",GameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAYER_CAN_CROUCH       = GameRuleRegistry.register(
                "playerCanCrouch",GameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAYER_CAN_SWIM         = GameRuleRegistry.register(
                "playerCanSwim",  GameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAYER_CAN_JUMP         = GameRuleRegistry.register(
                "playerCanJump",  GameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
            Gamerules.PLAYER_CAN_BE_INVISIBLE = GameRuleRegistry.register(
                "playerCanBeInvisible",GameruleCategories.VD_PLAYER, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.redstone) {
            Gamerules.REPEATER_BASE_DELAY         = GameRuleRegistry.register(
                "repeaterBaseDelay",  GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            Gamerules.REPEATER_SIGNAL            = GameRuleRegistry.register(
                "repeaterSignal",     GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(15, 0, 15));
            Gamerules.COMPARATOR_BASE_DELAY       = GameRuleRegistry.register(
                "comparatorBaseDelay",GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            Gamerules.COMPARATOR_ENABLED          = GameRuleRegistry.register(
                "comparatorEnabled",  GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.TORCH_REDSTONE_SIGNAL       = GameRuleRegistry.register(
                "torchRedstoneSignal",GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(15, 0, 15));
            Gamerules.REDSTONE_WIRE_ENABLED       = GameRuleRegistry.register(
                "redstoneWireEnabled",GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.DROPPER_ENABLED             = GameRuleRegistry.register(
                "dropperEnabled",     GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.DISPENSER_ENABLED           = GameRuleRegistry.register(
                "dispenserEnabled",   GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.DAYLIGHT_SENSOR_ENABLED     = GameRuleRegistry.register(
                "daylightSensorEnabled",   GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.WOOD_BUTTON_PRESS_DURATION  = GameRuleRegistry.register(
                "woodButtonPressDuration", GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(30, 0));
            Gamerules.STONE_BUTTON_PRESS_DURATION = GameRuleRegistry.register(
                "stoneButtonPressDuration",GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(20, 0));
            Gamerules.BUTTON_ENABLED              = GameRuleRegistry.register(
                "buttonEnabled",      GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.LEVER_ENABLED               = GameRuleRegistry.register(
                "leverEnabled",       GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.LIGHTNING_ROD_ENABLED       = GameRuleRegistry.register(
                "lightningRodEnabled",GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.OBSERVER_DELAY              = GameRuleRegistry.register(
                "observerDelay",      GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            Gamerules.OBSERVER_DURATION           = GameRuleRegistry.register(
                "observerDuration",   GameruleCategories.VD_REDSTONE, GameRuleFactory.createIntRule(2, 0));
            Gamerules.OBSERVER_ENABLED            = GameRuleRegistry.register(
                "observerEnabled",GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.PRESSURE_PLATE_ENABLED      = GameRuleRegistry.register(
                "pressurePlateEnabled",GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.TARGET_BLOCK_ENABLED        = GameRuleRegistry.register(
                "targetBlockEnabled", GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.TRAPPED_CHEST_ENABLED       = GameRuleRegistry.register(
                "trappedChestEnabled",GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.TRIPWIRE_HOOK_ENABLED       = GameRuleRegistry.register(
                "tripwireHookEnabled",GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
            Gamerules.PISTON_ENABLED              = GameRuleRegistry.register(
                "pistonEnabled",      GameruleCategories.VD_REDSTONE, GameRuleFactory.createBooleanRule(true));
        }
        if (VanillaDisableMixinConfigPlugin.misc) {
            Gamerules.NETHER_PORTALS_ENABLED   = GameRuleRegistry.register(
                "netherPortalsEnabled", GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.END_PORTALS_ENABLED      = GameRuleRegistry.register(
                "endPortalsEnabled",GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.END_GATEWAYS_ENABLED     = GameRuleRegistry.register(
                "endGatewaysEnabled",   GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.NETHER_PORTAL_COOLDOWN   = GameRuleRegistry.register(
                "netherPortalCooldown", GameruleCategories.VD_MISC, GameRuleFactory.createIntRule(300));
            Gamerules.CROP_TRAMPLING           = GameRuleRegistry.register(
                "cropTrampling",   GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            if (VanillaDisableMixinConfigPlugin.misc_hunger) {
                Gamerules.OLD_HUNGER               = GameRuleRegistry.register(
                    "oldHunger",       GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            }
            Gamerules.OLD_BOATS                = GameRuleRegistry.register(
                "oldBoats",        GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            Gamerules.BEACONS_ENABLED          = GameRuleRegistry.register(
                "beaconsEnabled",  GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.CONDUITS_ENABLED         = GameRuleRegistry.register(
                "conduitsEnabled", GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.ICE_SLIDING              = GameRuleRegistry.register(
                "iceSliding",      GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.TOTEMS_ENABLED           = GameRuleRegistry.register(
                "totemsEnabled",   GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            Gamerules.BOW_SPAMMING             = GameRuleRegistry.register(
                "bowSpamming",     GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            Gamerules.CROSSBOW_SPAMMING        = GameRuleRegistry.register(
                "crossbowSpamming",GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            Gamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS = GameRuleRegistry.register(
                "creativeSwordCanBreakBlocks", GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            Gamerules.PUSHABLE_BUDDING_AMETHYST       = GameRuleRegistry.register(
                "pushableBuddingAmethyst",GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(false));
            if (VanillaDisableMixinConfigPlugin.misc_container) {
                Gamerules.CONTAINER_OPENING_BLOCKED       = GameRuleRegistry.register(
                    "containerOpeningBlocked",GameruleCategories.VD_MISC, GameRuleFactory.createBooleanRule(true));
            }
        }
    }
}
