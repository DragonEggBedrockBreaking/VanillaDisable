package uk.debb.vanilla_disable.data.gamerule;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.List;

public class GameruleMigrationDataHandler {
    public static final ObjectList<SqlDataHolder> sqlData = new ObjectArrayList<>() {{
        add(new SqlDataHolder("projectileDamage", "entities", "minecraft:player", "mob_projectile_damage", "true"));
        add(new SqlDataHolder("explosionDamage", "entities", "minecraft:player", "explosion_damage", "true"));
        add(new SqlDataHolder("voidDamage", "entities", "minecraft:player", "fell_out_of_world_damage", "true"));
        add(new SqlDataHolder("magicDamage", "entities", "minecraft:player", "magic_damage", "true"));
        add(new SqlDataHolder("creativePlayerDamage", "entities", "minecraft:player", "player_attack_damage", "true"));
        add(new SqlDataHolder("lightningDamage", "entities", "minecraft:player", "lightning_bolt_damage", "true"));
        add(new SqlDataHolder("wallDamage", "entities", "minecraft:player", "in_wall_damage", "true"));
        add(new SqlDataHolder("crammingDamage", "entities", "minecraft:player", "cramming_damage", "true"));
        add(new SqlDataHolder("starvationDamage", "entities", "minecraft:player", "starve_damage", "true"));
        add(new SqlDataHolder("cactusDamage", "entities", "minecraft:player", "cactus_damage", "true"));
        add(new SqlDataHolder("flyIntoWallDamage", "entities", "minecraft:player", "fly_into_wall_damage", "true"));
        add(new SqlDataHolder("witherDamage", "entities", "minecraft:player", "wither_damage", "true"));
        add(new SqlDataHolder("dragonDamage", "entities", "minecraft:player", "dragon_breath_damage", "true"));
        add(new SqlDataHolder("sweetBerryBushDamage", "entities", "minecraft:player", "sweet_berry_bush_damage", "true"));
        add(new SqlDataHolder("entityDamage", "entities", "minecraft:player", "mob_attack_damage", "true"));

        add(new SqlDataHolder("fireballKnockback", "entities", "minecraft:player", "fireball_knockback", "true"));
        add(new SqlDataHolder("witherSkullKnockback", "entities", "minecraft:player", "wither_skull_knockback", "true"));
        add(new SqlDataHolder("dragonKnockback", "entities", "minecraft:player", "dragon_fireball_knockback", "true"));
        add(new SqlDataHolder("arrowKnockback", "entities", "minecraft:player", "arrow_knockback", "true"));
        add(new SqlDataHolder("tridentKnockback", "entities", "minecraft:player", "trident_knockback", "true"));
        add(new SqlDataHolder("llamaSpitKnockback", "entities", "minecraft:player", "llama_spit_knockback", "true"));
        add(new SqlDataHolder("shulkerBulletKnockback", "entities", "minecraft:player", "shulker_bullet_knockback", "true"));
        add(new SqlDataHolder("playerAttackKnockback", "entities", "minecraft:player", "player_knockback", "true"));
        add(new SqlDataHolder("explosionKnockback", "entities", "minecraft:player", "explosion_knockback", "true"));

        add(new SqlDataHolder("pigSpawners", "entities", "minecraft:pig", "spawner", "true"));
        add(new SqlDataHolder("caveSpiderSpawners", "entities", "minecraft:cave_spider", "spawner", "true"));
        add(new SqlDataHolder("silverfishSpawners", "entities", "minecraft:silverfish", "spawner", "true"));
        add(new SqlDataHolder("zombieSpawners", "entities", "minecraft:zombie", "spawner", "true"));
        add(new SqlDataHolder("skeletonSpawners", "entities", "minecraft:skeleton", "spawner", "true"));
        add(new SqlDataHolder("blazeSpawners", "entities", "blaze", "spawner", "true"));
        add(new SqlDataHolder("spiderSpawners", "entities", "minecraft:spider", "spawner", "true"));
        add(new SqlDataHolder("magmaCubeSpawners", "entities", "minecraft:magma_cube", "spawner", "true"));

        add(new SqlDataHolder("enderPearlsDespawnOnDeath", "entities", "minecraft:ender_pearl", "despawn_on_player_death", "true"));

        add(new SqlDataHolder("monsterMobcap", "mob_categories", "monster", "mobcap", "70"));
        add(new SqlDataHolder("creatureMobcap", "mob_categories", "creature", "mobcap", "10"));
        add(new SqlDataHolder("ambientMobcap", "mob_categories", "ambient", "mobcap", "15"));
        add(new SqlDataHolder("axolotlMobcap", "mob_categories", "axolotls", "mobcap", "5"));
        add(new SqlDataHolder("glowsquidMobcap", "mob_categories", "underground_water_creature", "mobcap", "5"));
        add(new SqlDataHolder("waterCreatureMobcap", "mob_categories", "water_creature", "mobcap", "5"));
        add(new SqlDataHolder("waterAmbientMobcap", "mob_categories", "water_ambient", "mobcap", "20"));

        add(new SqlDataHolder("advancementCommand", "commands", "/advancement", "enabled", "true"));
        add(new SqlDataHolder("attributeCommand", "commands", "/attribute", "enabled", "true"));
        add(new SqlDataHolder("bossBarCommand", "commands", "/bossbar", "enabled", "true"));
        add(new SqlDataHolder("chaseCommand", "commands", "/chase", "enabled", "true"));
        add(new SqlDataHolder("clearCommand", "commands", "/clear", "enabled", "true"));
        add(new SqlDataHolder("cloneCommand", "commands", "/clone", "enabled", "true"));
        add(new SqlDataHolder("damageCommand", "commands", "/damage", "enabled", "true"));
        add(new SqlDataHolder("datapackCommand", "commands", "/datapack", "enabled", "true"));
        add(new SqlDataHolder("dataCommand", "commands", "/data", "enabled", "true"));
        add(new SqlDataHolder("debugCommand", "commands", "/debug", "enabled", "true"));
        add(new SqlDataHolder("defaultGamemodeCommand", "commands", "/defaultgamemode", "enabled", "true"));
        add(new SqlDataHolder("difficultyCommand", "commands", "/difficulty", "enabled", "true"));
        add(new SqlDataHolder("effectCommand", "commands", "/effect", "enabled", "true"));
        add(new SqlDataHolder("enchantCommand", "commands", "/enchant", "enabled", "true"));
        add(new SqlDataHolder("executeCommand", "commands", "/execute", "enabled", "true"));
        add(new SqlDataHolder("experienceCommand", "commands", "/experience", "enabled", "true"));
        add(new SqlDataHolder("fillCommand", "commands", "/fill", "enabled", "true"));
        add(new SqlDataHolder("fillBiomeCommand", "commands", "/fillbiome", "enabled", "true"));
        add(new SqlDataHolder("forceLoadCommand", "commands", "/forceload", "enabled", "true"));
        add(new SqlDataHolder("functionCommand", "commands", "/function", "enabled", "true"));
        add(new SqlDataHolder("gameModeCommand", "commands", "/gamemode", "enabled", "true"));
        add(new SqlDataHolder("giveCommand", "commands", "/give", "enabled", "true"));
        add(new SqlDataHolder("helpCommand", "commands", "/help", "enabled", "true"));
        add(new SqlDataHolder("itemCommand", "commands", "/item", "enabled", "true"));
        add(new SqlDataHolder("jfrCommand", "commands", "/jfr", "enabled", "true"));
        add(new SqlDataHolder("kickCommand", "commands", "/kick", "enabled", "true"));
        add(new SqlDataHolder("killCommand", "commands", "/kill", "enabled", "true"));
        add(new SqlDataHolder("listCommand", "commands", "/list", "enabled", "true"));
        add(new SqlDataHolder("locateCommand", "commands", "/locate", "enabled", "true"));
        add(new SqlDataHolder("lootCommand", "commands", "/loot", "enabled", "true"));
        add(new SqlDataHolder("meCommand", "commands", "/me", "enabled", "true"));
        add(new SqlDataHolder("messageCommand", "commands", "/message", "enabled", "true"));
        add(new SqlDataHolder("particleCommand", "commands", "/particle", "enabled", "true"));
        add(new SqlDataHolder("placeCommand", "commands", "/place", "enabled", "true"));
        add(new SqlDataHolder("playSoundCommand", "commands", "/playsound", "enabled", "true"));
        add(new SqlDataHolder("publishCommand", "commands", "/publish", "enabled", "true"));
        add(new SqlDataHolder("raidCommand", "commands", "/raid", "enabled", "true"));
        add(new SqlDataHolder("recipeCommand", "commands", "/recipe", "enabled", "true"));
        add(new SqlDataHolder("reloadCommand", "commands", "/reload", "enabled", "true"));
        add(new SqlDataHolder("resetChunksCommand", "commands", "/resetchunks", "enabled", "true"));
        add(new SqlDataHolder("returnCommand", "commands", "/return", "enabled", "true"));
        add(new SqlDataHolder("rideCommand", "commands", "/ride", "enabled", "true"));
        add(new SqlDataHolder("sayCommand", "commands", "/say", "enabled", "true"));
        add(new SqlDataHolder("scheduleCommand", "commands", "/schedule", "enabled", "true"));
        add(new SqlDataHolder("scoreboardCommand", "commands", "/scoreboard", "enabled", "true"));
        add(new SqlDataHolder("seedCommand", "commands", "/seed", "enabled", "true"));
        add(new SqlDataHolder("setBlockCommand", "commands", "/setblock", "enabled", "true"));
        add(new SqlDataHolder("setWorldSpawnCommand", "commands", "/setworldspawn", "enabled", "true"));
        add(new SqlDataHolder("spawnPointCommand", "commands", "/spawnwpoint", "enabled", "true"));
        add(new SqlDataHolder("spectateCommand", "commands", "/spectate", "enabled", "true"));
        add(new SqlDataHolder("spreadPlayersCommand", "commands", "/spreadplayers", "enabled", "true"));
        add(new SqlDataHolder("stopSoundCommand", "commands", "/stopsound", "enabled", "true"));
        add(new SqlDataHolder("summonCommand", "commands", "/summon", "enabled", "true"));
        add(new SqlDataHolder("tagCommand", "commands", "/tag", "enabled", "true"));
        add(new SqlDataHolder("teamCommand", "commands", "/team", "enabled", "true"));
        add(new SqlDataHolder("teamMsgCommand", "commands", "/teammsg", "enabled", "true"));
        add(new SqlDataHolder("teleportCommand", "commands", "/teleport", "enabled", "true"));
        add(new SqlDataHolder("tellRawCommand", "commands", "/tellraw", "enabled", "true"));
        add(new SqlDataHolder("timeCommand", "commands", "/time", "enabled", "true"));
        add(new SqlDataHolder("titleCommand", "commands", "/title", "enabled", "true"));
        add(new SqlDataHolder("triggerCommand", "commands", "/trigger", "enabled", "true"));
        add(new SqlDataHolder("wardenSpawnTrackerCommand", "commands", "/warden_spawn_tracker", "enabled", "true"));
        add(new SqlDataHolder("weatherCommand", "commands", "/weather", "enabled", "true"));
        add(new SqlDataHolder("worldBorderCommand", "commands", "/worldboarder", "enabled", "true"));
        add(new SqlDataHolder("banDedicatedCommand", "commands", "/bam", "enabled", "true"));
        add(new SqlDataHolder("banIpDedicatedCommand", "commands", "/ban-ip", "enabled", "true"));
        add(new SqlDataHolder("banListDedicatedCommand", "commands", "/banlist", "enabled", "true"));
        add(new SqlDataHolder("deOpDedicatedCommand", "commands", "/deop", "enabled", "true"));
        add(new SqlDataHolder("opDedicatedCommand", "commands", "/op", "enabled", "true"));
        add(new SqlDataHolder("pardonDedicatedCommand", "commands", "/pardon", "enabled", "true"));
        add(new SqlDataHolder("pardonIpDedicatedCommand", "commands", "/pardon-ip", "enabled", "true"));
        add(new SqlDataHolder("perfDedicatedCommand", "commands", "/perf", "enabled", "true"));
        add(new SqlDataHolder("saveAllDedicatedCommand", "commands", "/save-all", "enabled", "true"));
        add(new SqlDataHolder("saveOffDedicatedCommand", "commands", "/save-off", "enabled", "true"));
        add(new SqlDataHolder("saveOnDedicatedCommand", "commands", "/save-on", "enabled", "true"));
        add(new SqlDataHolder("setIdleTimeoutDedicatedCommand", "commands", "/setidletimeout", "enabled", "true"));
        add(new SqlDataHolder("stopDedicatedCommand", "commands", "/stop", "enabled", "true"));
        add(new SqlDataHolder("whitelistDedicatedCommand", "commands", "/whitelist", "enabled", "true"));

        add(new SqlDataHolder("waterPlaceableInNether", "blocks", "minecraft:water", "can_place_in_nether", "false"));
        add(new SqlDataHolder("bubbleColumnsEnabled", "blocks", "minecraft:bubble_column", "works", "true"));

        add(new SqlDataHolder("curableZillagers", "entities", "minecraft:villager", "can_be_converted_to", "true"));
        add(new SqlDataHolder("villagersConvertToZillagers", "entities", "minecraft:zombie_villager", "can_be_converted_to", "true"));
        add(new SqlDataHolder("villagersConvertToWitches", "entities", "minecraft:witch", "can_be_converted_to", "true"));
        add(new SqlDataHolder("piglinsConvertToZiglins", "entities", "minecraft:zombified_piglin", "can_be_converted_to", "true"));
        add(new SqlDataHolder("hoglinsConvertToZoglins", "entities", "minecraft:hoglin", "can_be_converted_to", "true"));
        add(new SqlDataHolder("husksConvertToZombies", "entities", "minecraft:zombie", "can_be_converted_to", "true"));
        add(new SqlDataHolder("zombiesConvertToDrowned", "entities", "minecraft:drowned", "can_be_converted_to", "true"));
        add(new SqlDataHolder("skeletonsConvertToStrays", "entities", "minecraft:skeleton", "can_be_converted_to", "true"));
        add(new SqlDataHolder("pigsBreedWithWheat", "entities", "minecraft:pig", "can_breed_with_wheat", "false"));
        add(new SqlDataHolder("dragonFireballs", "entities", "minecraft:ender_dragon", "can_shoot_fireballs", "true"));
        add(new SqlDataHolder("fireAspectIgnitesCreepers", "entities", "minecraft:creeper", "can_player_interact", "false"));
        add(new SqlDataHolder("villagersSpawnCats", "entities", "minecraft:pig", "spawned_by_villagers", "true"));
        add(new SqlDataHolder("villagersSpawnGolems", "entities", "minecraft:iron_golem", "spawned_by_villagers", "true"));

        add(new SqlDataHolder("allaysEnabled", "entities", "minecraft:allay", "enabled", "true"));
        add(new SqlDataHolder("batsEnabled", "entities", "minecraft:bat", "enabled", "true"));
        add(new SqlDataHolder("camelsEnabled", "entities", "minecraft:camel", "enabled", "true"));
        add(new SqlDataHolder("catsEnabled", "entities", "minecraft:cat", "enabled", "true"));
        add(new SqlDataHolder("chickensEnabled", "entities", "minecraft:chicken", "enabled", "true"));
        add(new SqlDataHolder("codsEnabled", "entities", "minecraft:cod", "enabled", "true"));
        add(new SqlDataHolder("cowsEnabled", "entities", "minecraft:cow", "enabled", "true"));
        add(new SqlDataHolder("donkeysEnabled", "entities", "minecraft:donkey", "enabled", "true"));
        add(new SqlDataHolder("foxesEnabled", "entities", "minecraft:fox", "enabled", "true"));
        add(new SqlDataHolder("frogsEnabled", "entities", "minecraft:frog", "enabled", "true"));
        add(new SqlDataHolder("horsesEnabled", "entities", "minecraft:horse", "enabled", "true"));
        add(new SqlDataHolder("mooshroomsEnabled", "entities", "minecraft:mooshroom", "enabled", "true"));
        add(new SqlDataHolder("mulesEnabled", "entities", "minecraft:mule", "enabled", "true"));
        add(new SqlDataHolder("ocelotsEnabled", "entities", "minecraft:ocelot", "enabled", "true"));
        add(new SqlDataHolder("parrotsEnabled", "entities", "minecraft:parrot", "enabled", "true"));
        add(new SqlDataHolder("pigsEnabled", "entities", "minecraft:pig", "enabled", "true"));
        add(new SqlDataHolder("pufferfishEnabled", "entities", "minecraft:pufferfish", "enabled", "true"));
        add(new SqlDataHolder("rabbitsEnabled", "entities", "minecraft:rabbit", "enabled", "true"));
        add(new SqlDataHolder("salmonsEnabled", "entities", "minecraft:salmon", "enabled", "true"));
        add(new SqlDataHolder("sheepEnabled", "entities", "minecraft:sheep", "enabled", "true"));
        add(new SqlDataHolder("skeletonHorsesEnabled", "entities", "minecraft:skeleton_horse", "enabled", "true"));
        add(new SqlDataHolder("sniffersEnabled", "entities", "minecraft:sniffer", "enabled", "true"));
        add(new SqlDataHolder("snowGolemsEnabled", "entities", "minecraft:snow_golem", "enabled", "true"));
        add(new SqlDataHolder("squidsEnabled", "entities", "minecraft:squid", "enabled", "true"));
        add(new SqlDataHolder("stridersEnabled", "entities", "minecraft:strider", "enabled", "true"));
        add(new SqlDataHolder("tadpolesEnabled", "entities", "minecraft:tadpole", "enabled", "true"));
        add(new SqlDataHolder("tropicalFishEnabled", "entities", "minecraft:tropical_fish", "enabled", "true"));
        add(new SqlDataHolder("turtlesEnabled", "entities", "minecraft:turtle", "enabled", "true"));
        add(new SqlDataHolder("villagersEnabled", "entities", "minecraft:villager", "enabled", "true"));
        add(new SqlDataHolder("wanderingTradersEnabled", "entities", "minecraft:wandering_trader", "enabled", "true"));
        add(new SqlDataHolder("beesEnabled", "entities", "minecraft:bee", "enabled", "true"));
        add(new SqlDataHolder("caveSpidersEnabled", "entities", "minecraft:cave_spider", "enabled", "true"));
        add(new SqlDataHolder("dolphinsEnabled", "entities", "minecraft:dolphin", "enabled", "true"));
        add(new SqlDataHolder("endermenEnabled", "entities", "minecraft:enderman", "enabled", "true"));
        add(new SqlDataHolder("goatsEnabled", "entities", "minecraft:goat", "enabled", "true"));
        add(new SqlDataHolder("ironGolemsEnabled", "entities", "minecraft:iron_golem", "enabled", "true"));
        add(new SqlDataHolder("llamasEnabled", "entities", "minecraft:llama", "enabled", "true"));
        add(new SqlDataHolder("pandasEnabled", "entities", "minecraft:panda", "enabled", "true"));
        add(new SqlDataHolder("piglinsEnabled", "entities", "minecraft:piglin", "enabled", "true"));
        add(new SqlDataHolder("polarBearsEnabled", "entities", "minecraft:polar_bear", "enabled", "true"));
        add(new SqlDataHolder("spidersEnabled", "entities", "minecraft:spider", "enabled", "true"));
        add(new SqlDataHolder("traderLlamasEnabled", "entities", "minecraft:trader_llama", "enabled", "true"));
        add(new SqlDataHolder("wolvesEnabled", "entities", "minecraft:wolf", "enabled", "true"));
        add(new SqlDataHolder("zombifiedPiglinsEnabled", "entities", "minecraft:zombified_piglin", "enabled", "true"));
        add(new SqlDataHolder("blazesEnabled", "entities", "minecraft:blaze", "enabled", "true"));
        add(new SqlDataHolder("creepersEnabled", "entities", "minecraft:creeper", "enabled", "true"));
        add(new SqlDataHolder("drownedEnabled", "entities", "minecraft:drowned", "enabled", "true"));
        add(new SqlDataHolder("elderGuardiansEnabled", "entities", "minecraft:elder_guardian", "enabled", "true"));
        add(new SqlDataHolder("endermitesEnabled", "entities", "minecraft:endermite", "enabled", "true"));
        add(new SqlDataHolder("evokersEnabled", "entities", "minecraft:evoker", "enabled", "true"));
        add(new SqlDataHolder("ghastsEnabled", "entities", "minecraft:ghast", "enabled", "true"));
        add(new SqlDataHolder("guardiansEnabled", "entities", "minecraft:guardian", "enabled", "true"));
        add(new SqlDataHolder("hoglinsEnabled", "entities", "minecraft:hoglin", "enabled", "true"));
        add(new SqlDataHolder("husksEnabled", "entities", "minecraft:husk", "enabled", "true"));
        add(new SqlDataHolder("magmaCubesEnabled", "entities", "minecraft:magma_cube", "enabled", "true"));
        add(new SqlDataHolder("phantomsEnabled", "entities", "minecraft:phantom", "enabled", "true"));
        add(new SqlDataHolder("piglinBrutesEnabled", "entities", "minecraft:piglin_brute", "enabled", "true"));
        add(new SqlDataHolder("pillagersEnabled", "entities", "minecraft:pillager", "enabled", "true"));
        add(new SqlDataHolder("ravagersEnabled", "entities", "minecraft:ravager", "enabled", "true"));
        add(new SqlDataHolder("shulkersEnabled", "entities", "shulker", "enabled", "true"));
        add(new SqlDataHolder("silverfishEnabled", "entities", "minecraft:silverfish", "enabled", "true"));
        add(new SqlDataHolder("skeletonsEnabled", "entities", "minecraft:skeleton", "enabled", "true"));
        add(new SqlDataHolder("slimesEnabled", "entities", "minecraft:slime", "enabled", "true"));
        add(new SqlDataHolder("straysEnabled", "entities", "minecraft:stray", "enabled", "true"));
        add(new SqlDataHolder("vexesEnabled", "entities", "minecraft:vex", "enabled", "true"));
        add(new SqlDataHolder("vindicatorsEnabled", "entities", "minecraft:vindicator", "enabled", "true"));
        add(new SqlDataHolder("wardensEnabled", "entities", "minecraft:warden", "enabled", "true"));
        add(new SqlDataHolder("witchesEnabled", "entities", "minecraft:witch", "enabled", "true"));
        add(new SqlDataHolder("witherSkeletonsEnabled", "entities", "minecraft:wither_skeleton", "enabled", "true"));
        add(new SqlDataHolder("zoglinsEnabled", "entities", "minecraft:zoglin", "enabled", "true"));
        add(new SqlDataHolder("zombiesEnabled", "entities", "minecraft:zombie", "enabled", "true"));
        add(new SqlDataHolder("zombieVillagersEnabled", "entities", "minecraft:zombie_villager", "enabled", "true"));
        add(new SqlDataHolder("dragonsEnabled", "entities", "minecraft:ender_dragon", "enabled", "true"));
        add(new SqlDataHolder("withersEnabled", "entities", "minecraft:wither", "enabled", "true"));

        add(new SqlDataHolder("absorptionEffect", "entities", "minecraft:player", "absorption_effect", "true"));
        add(new SqlDataHolder("badOmenEffect", "entities", "minecraft:player", "bad_omen_effect", "true"));
        add(new SqlDataHolder("blindnessEffect", "entities", "minecraft:player", "blindness_effect", "true"));
        add(new SqlDataHolder("conduitPowerEffect", "entities", "minecraft:player", "conduit_power_effect", "true"));
        add(new SqlDataHolder("darknessEffect", "entities", "minecraft:player", "darkness_effect", "true"));
        add(new SqlDataHolder("dolphinsGraceEffect", "entities", "minecraft:player", "dolphins_grace_effect", "true"));
        add(new SqlDataHolder("fireResistanceEffect", "entities", "minecraft:player", "fire_resistance_effect", "true"));
        add(new SqlDataHolder("glowingEffect", "entities", "minecraft:player", "glowing_effect", "true"));
        add(new SqlDataHolder("hasteEffect", "entities", "minecraft:player", "haste_effect", "true"));
        add(new SqlDataHolder("healthBoostEffect", "entities", "minecraft:player", "health_boost_effect", "true"));
        add(new SqlDataHolder("hungerEffect", "entities", "minecraft:player", "hunger_effect", "true"));
        add(new SqlDataHolder("instantDamageEffect", "entities", "minecraft:player", "instant_damage_effect", "true"));
        add(new SqlDataHolder("instantHealthEffect", "entities", "minecraft:player", "instant_health_effect", "true"));
        add(new SqlDataHolder("invisibilityEffect", "entities", "minecraft:player", "invisibility_effect", "true"));
        add(new SqlDataHolder("jumpBoostEffect", "entities", "minecraft:player", "jump_boost_effect", "true"));
        add(new SqlDataHolder("levitationEffect", "entities", "minecraft:player", "levitation_effect", "true"));
        add(new SqlDataHolder("luckEffect", "entities", "minecraft:player", "luck_effect", "true"));
        add(new SqlDataHolder("miningFatigueEffect", "entities", "minecraft:player", "mining_fatigue_effect", "true"));
        add(new SqlDataHolder("nauseaEffect", "entities", "minecraft:player", "nausea_effect", "true"));
        add(new SqlDataHolder("nightVisionEffect", "entities", "minecraft:player", "night_vision_effect", "true"));
        add(new SqlDataHolder("poisonEffect", "entities", "minecraft:player", "poison_effect", "true"));
        add(new SqlDataHolder("regenerationEffect", "entities", "minecraft:player", "regeneration_effect", "true"));
        add(new SqlDataHolder("resistanceEffect", "entities", "minecraft:player", "resistance_effect", "true"));
        add(new SqlDataHolder("saturationEffect", "entities", "minecraft:player", "saturation_effect", "true"));
        add(new SqlDataHolder("slownessEffect", "entities", "minecraft:player", "slowness_effect", "true"));
        add(new SqlDataHolder("slowFallingEffect", "entities", "minecraft:player", "slow_falling_effect", "true"));
        add(new SqlDataHolder("speedEffect", "entities", "minecraft:player", "speed_effect", "true"));
        add(new SqlDataHolder("strengthEffect", "entities", "minecraft:player", "strength_effect", "true"));
        add(new SqlDataHolder("unluckEffect", "entities", "minecraft:player", "unluck_effect", "true"));
        add(new SqlDataHolder("waterBreathingEffect", "entities", "minecraft:player", "water_breathing_effect", "true"));
        add(new SqlDataHolder("weaknessEffect", "entities", "minecraft:player", "weakness_effect", "true"));
        add(new SqlDataHolder("witherEffect", "entities", "minecraft:player", "wither_effect", "true"));

        add(new SqlDataHolder("bootEnchantmentConflicts", "enchantments", "minecraft:frost_walker", "compatible_with_depth_strider", "true"));
        add(new SqlDataHolder("bootEnchantmentConflicts", "enchantments", "minecraft:depth_strider", "compatible_with_frost_walker", "true"));
        add(new SqlDataHolder("bowEnchantmentConflicts", "enchantments", "minecraft:infinity", "compatible_with_mending", "true"));
        add(new SqlDataHolder("bowEnchantmentConflicts", "enchantments", "minecraft:mending", "compatible_with_infinity", "true"));
        add(new SqlDataHolder("crossbowEnchantmentConflicts", "enchantments", "minecraft:multishot", "compatible_with_piercing", "true"));
        add(new SqlDataHolder("crossbowEnchantmentConflicts", "enchantments", "minecraft:piercing", "compatible_with_multishot", "true"));
        add(new SqlDataHolder("damageEnchantmentConflicts", "enchantments", "minecraft:sharpness", "compatible_with_smite", "true"));
        add(new SqlDataHolder("damageEnchantmentConflicts", "enchantments", "minecraft:smite", "compatible_with_sharpness", "true"));
        add(new SqlDataHolder("damageEnchantmentConflicts", "enchantments", "minecraft:sharpness", "compatible_with_bane_of_arthropods", "true"));
        add(new SqlDataHolder("damageEnchantmentConflicts", "enchantments", "minecraft:bane_of_arthropods", "compatible_with_sharpness", "true"));
        add(new SqlDataHolder("damageEnchantmentConflicts", "enchantments", "minecraft:smite", "compatible_with_bane_of_arthropods", "true"));
        add(new SqlDataHolder("damageEnchantmentConflicts", "enchantments", "minecraft:bane_of_arthropods", "compatible_with_smite", "true"));
        add(new SqlDataHolder("miningEnchantmentConflicts", "enchantments", "minecraft:fortune", "compatible_with_silk_touch", "true"));
        add(new SqlDataHolder("miningEnchantmentConflicts", "enchantments", "minecraft:silk_touch", "compatible_with_fortune", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:protection", "compatible_with_blast_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:blast_protection", "compatible_with_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:protection", "compatible_with_fire_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:fire_protection", "compatible_with_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:protection", "compatible_with_projectile_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:projectile_protection", "compatible_with_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:blast_protection", "compatible_with_fire_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:fire_protection", "compatible_with_blast_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:blast_protection", "compatible_with_projectile_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:projectile_protection", "compatible_with_blast_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:fire_protection", "compatible_with_projectile_protection", "true"));
        add(new SqlDataHolder("protectionEnchantmentConflicts", "enchantments", "minecraft:projectile_protection", "compatible_with_fire_protection", "true"));
        add(new SqlDataHolder("tridentEnchantmentConflicts", "enchantments", "minecraft:riptide", "compatible_with_channeling", "true"));
        add(new SqlDataHolder("tridentEnchantmentConflicts", "enchantments", "minecraft:channeling", "compatible_with_riptide", "true"));
        add(new SqlDataHolder("tridentEnchantmentConflicts", "enchantments", "minecraft:riptide", "compatible_with_loyalty", "true"));
        add(new SqlDataHolder("tridentEnchantmentConflicts", "enchantments", "minecraft:loyalty", "compatible_with_riptide", "true"));

        add(new SqlDataHolder("playerCanBeOnFire", "entities", "minecraft:player", "can_be_on_fire", "true"));
        add(new SqlDataHolder("playerCanSprint", "entities", "minecraft:player", "can_sprint", "true"));
        add(new SqlDataHolder("playerCanCrouch", "entities", "minecraft:player", "can_crouch", "true"));
        add(new SqlDataHolder("playerCanSwim", "entities", "minecraft:player", "can_swim", "true"));
        add(new SqlDataHolder("playerCanJump", "entities", "minecraft:player", "can_jump", "true"));
        add(new SqlDataHolder("playerCanBeInvisible", "entities", "minecraft:player", "can_be_invisible", "true"));
        add(new SqlDataHolder("playerFlyingSpeed", "entities", "minecraft:player", "flying_speed", "0.05"));

        add(new SqlDataHolder("repeaterBaseDelay", "blocks", "minecraft:repeater", "redstone_delay", "2"));
        add(new SqlDataHolder("repeaterSignal", "blocks", "minecraft:repeater", "works", "15"));
        add(new SqlDataHolder("comparatorBaseDelay", "blocks", "minecraft:comparator", "redstone_delay", "2"));
        add(new SqlDataHolder("comparatorEnabled", "blocks", "minecraft:comparator", "works", "true"));
        add(new SqlDataHolder("redstoneTorchEnabled", "blocks", "minecraft:redstone_torch", "works", "true"));
        add(new SqlDataHolder("redstoneWireEnabled", "blocks", "minecraft:redstone_wire", "works", "true"));
        add(new SqlDataHolder("dropperEnabled", "blocks", "minecraft:dropper", "works", "true"));
        add(new SqlDataHolder("dispenserEnabled", "blocks", "minecraft:dispenser", "works", "true"));
        add(new SqlDataHolder("daylightSensorEnabled", "blocks", "minecraft:daylight_sensor", "works", "true"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "oak_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "birch_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "spruce_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "jungle_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "acacia_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "dark_oak_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "warped_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "crimson_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "cherry_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "mangrove_button", "redstone_duration", "30"));
        add(new SqlDataHolder("woodButtonPressDuration", "blocks", "bamboo_button", "redstone_duration", "30"));
        add(new SqlDataHolder("stoneButtonPressDuration", "blocks", "%stone_button", "redstone_duration", "20"));
        add(new SqlDataHolder("buttonEnabled", "blocks", "%_button", "works", "true"));
        add(new SqlDataHolder("leverEnabled", "blocks", "minecraft:lever", "works", "true"));
        add(new SqlDataHolder("lightningRodEnabled", "blocks", "minecraft:lightning_rod", "works", "true"));
        add(new SqlDataHolder("observerDelay", "blocks", "minecraft:observer", "redstone_delay", "2"));
        add(new SqlDataHolder("observerDuration", "blocks", "minecraft:observer", "redstone_duration", "2"));
        add(new SqlDataHolder("observerEnabled", "blocks", "minecraft:observer", "works", "true"));
        add(new SqlDataHolder("pressurePlateEnabled", "blocks", "%_pressure_plate", "works", "true"));
        add(new SqlDataHolder("targetBlockEnabled", "blocks", "minecraft:target_block", "works", "true"));
        add(new SqlDataHolder("trappedChestEnabled", "blocks", "minecraft:trapped_chest", "works", "true"));
        add(new SqlDataHolder("tripwireHookEnabled", "blocks", "minecraft:tripwire_hook", "works", "true"));
        add(new SqlDataHolder("pistonEnabled", "blocks", "%_piston", "works", "true"));
        add(new SqlDataHolder("sculkSensorEnabled", "blocks", "minecraft:sculk_sensor", "works", "true"));
        add(new SqlDataHolder("jukeboxEmitsRedstone", "blocks", "minecraft:jukebox", "works", "true"));
        add(new SqlDataHolder("chiseledBookshelfEmitsRedstone", "blocks", "minecraft:chiseled_bookshelf", "works", "true"));

        add(new SqlDataHolder("beeAi", "entities", "minecraft:bee", "ai", "true"));
        add(new SqlDataHolder("blazeAi", "entities", "minecraft:blaze", "ai", "true"));
        add(new SqlDataHolder("catAi", "entities", "minecraft:cat", "ai", "true"));
        add(new SqlDataHolder("dolphinAi", "entities", "minecraft:dolphin", "ai", "true"));
        add(new SqlDataHolder("drownedAi", "entities", "minecraft:drowned", "ai", "true"));
        add(new SqlDataHolder("endermanAi", "entities", "minecraft:enderman", "ai", "true"));
        add(new SqlDataHolder("fishAi", "entities", "minecraft:fish", "ai", "true"));
        add(new SqlDataHolder("foxAi", "entities", "minecraft:fox", "ai", "true"));
        add(new SqlDataHolder("ghastAi", "entities", "minecraft:ghast", "ai", "true"));
        add(new SqlDataHolder("golemAi", "entities", "minecraft:irongolem", "ai", "true"));
        add(new SqlDataHolder("guardianAi", "entities", "minecraft:guardian", "ai", "true"));
        add(new SqlDataHolder("llamaAi", "entities", "minecraft:llama", "ai", "true"));
        add(new SqlDataHolder("ocelotAi", "entities", "minecraft:ocelot", "ai", "true"));
        add(new SqlDataHolder("pandaAi", "entities", "minecraft:panda", "ai", "true"));
        add(new SqlDataHolder("parrotAi", "entities", "minecraft:parrot", "ai", "true"));
        add(new SqlDataHolder("phantomAi", "entities", "minecraft:phantom", "ai", "true"));
        add(new SqlDataHolder("polarBearAi", "entities", "minecraft:polar_bear", "ai", "true"));
        add(new SqlDataHolder("pufferfishAi", "entities", "minecraft:pufferfish", "ai", "true"));
        add(new SqlDataHolder("rabbitAi", "entities", "minecraft:rabbit", "ai", "true"));
        add(new SqlDataHolder("raiderAi", "entities", "minecraft:raider", "ai", "true"));
        add(new SqlDataHolder("shulkerAi", "entities", "minecraft:shulker", "ai", "true"));
        add(new SqlDataHolder("silverfishAi", "entities", "minecraft:silverfish", "ai", "true"));
        add(new SqlDataHolder("slimeAi", "entities", "minecraft:slime", "ai", "true"));
        add(new SqlDataHolder("spiderAi", "entities", "minecraft:spider", "ai", "true"));
        add(new SqlDataHolder("squidAi", "entities", "minecraft:squid", "ai", "true"));
        add(new SqlDataHolder("striderAi", "entities", "minecraft:strider", "ai", "true"));
        add(new SqlDataHolder("turtleAi", "entities", "minecraft:turtle", "ai", "true"));
        add(new SqlDataHolder("wolfAi", "entities", "minecraft:wolf", "ai", "true"));
        add(new SqlDataHolder("zombieAi", "entities", "minecraft:zombie", "ai", "true"));

        add(new SqlDataHolder("netherPortalsEnabled", "blocks", "minecraft:nether_portal", "works", "true"));
        add(new SqlDataHolder("endPortalsEnabled", "blocks", "minecraft:end_portal", "works", "true"));
        add(new SqlDataHolder("endGatewaysEnabled", "blocks", "minecraft:end_gateway", "works", "true"));
        add(new SqlDataHolder("netherPortalCooldown", "blocks", "minecraft:nether_portal", "cooldown", "300"));
        add(new SqlDataHolder("cropTrampling", "blocks", "minecraft:farmland", "can_be_trampled", "true"));
        add(new SqlDataHolder("beaconsEnabled", "blocks", "minecraft:beacon", "works", "true"));
        add(new SqlDataHolder("conduitsEnabled", "blocks", "minecraft:conduit", "works", "true"));
        add(new SqlDataHolder("creativeSwordCanBreakBlocks", "items", "%_sword", "can_break_blocks_in_creative", "false"));
        add(new SqlDataHolder("containerOpeningBlocked", "items", "%_chest", "opening_blockable", "true"));
        add(new SqlDataHolder("containerOpeningBlocked", "items", "%_shulker_box", "opening_blockable", "true"));
        add(new SqlDataHolder("oldTnt", "blocks", "minecraft:tnt", "alpha_behaviour", "false"));
        add(new SqlDataHolder("fillingCauldrons", "items", "minecraft:%_bucket", "cauldron_interaction", "true"));
        add(new SqlDataHolder("emptyingCauldrons", "items", "minecraft:bucket", "cauldron_interaction", "true"));
        add(new SqlDataHolder("cauldronsCleanLeatherArmour", "items", "%_leather_%", "cauldron_interaction", "true"));
        add(new SqlDataHolder("cauldronsCleanBanners", "items", "%_banner", "cauldron_interaction", "true"));
        add(new SqlDataHolder("cauldronsCleanShulkerBoxes", "items", "%_shulker_box", "cauldron_interaction", "true"));
        add(new SqlDataHolder("dripstoneFillsCauldrons", "blocks", "minecraft:cauldron", "can_be_filled_by_dripstone", "true"));

        add(new SqlDataHolder("anvilInteractions", "blocks", "minecraft:anvil", "can_interact", "true"));
        add(new SqlDataHolder("barrelInteractions", "blocks", "minecraft:barrel", "can_interact", "true"));
        add(new SqlDataHolder("beaconInteractions", "blocks", "minecraft:beacon", "can_interact", "true"));
        add(new SqlDataHolder("bedInteractions", "blocks", "minecraft:bed", "can_interact", "true"));
        add(new SqlDataHolder("bellInteractions", "blocks", "minecraft:bell", "can_interact", "true"));
        add(new SqlDataHolder("blastFurnaceInteractions", "blocks", "minecraft:blast_furnace", "can_interact", "true"));
        add(new SqlDataHolder("brewingStandInteractions", "blocks", "minecraft:brewing_stand", "can_interact", "true"));
        add(new SqlDataHolder("buttonInteractions", "blocks", "minecraft:button", "can_interact", "true"));
        add(new SqlDataHolder("cakeInteractions", "blocks", "minecraft:cake", "can_interact", "true"));
        add(new SqlDataHolder("campfireInteractions", "blocks", "minecraft:campfire", "can_interact", "true"));
        add(new SqlDataHolder("cartographyTableInteractions", "blocks", "minecraft:cartography_table", "can_interact", "true"));
        add(new SqlDataHolder("chestInteractions", "blocks", "minecraft:chest", "can_interact", "true"));
        add(new SqlDataHolder("commandBlockInteractions", "blocks", "minecraft:command_block", "can_interact", "true"));
        add(new SqlDataHolder("craftingTableInteractions", "blocks", "minecraft:crafting_table", "can_interact", "true"));
        add(new SqlDataHolder("dispenserInteractions", "blocks", "minecraft:dispenser", "can_interact", "true"));
        add(new SqlDataHolder("doorInteractions", "blocks", "minecraft:door", "can_interact", "true"));
        add(new SqlDataHolder("dragonEggInteractions", "blocks", "minecraft:dragon_egg", "can_interact", "true"));
        add(new SqlDataHolder("dropperInteractions", "blocks", "minecraft:dropper", "can_interact", "true"));
        add(new SqlDataHolder("enderChestInteractions", "blocks", "minecraft:ender_chest", "can_interact", "true"));
        add(new SqlDataHolder("furnaceInteractions", "blocks", "minecraft:furnace", "can_interact", "true"));
        add(new SqlDataHolder("grindstoneInteractions", "blocks", "minecraft:grindstone", "can_interact", "true"));
        add(new SqlDataHolder("hopperInteractions", "blocks", "minecraft:hopper", "can_interact", "true"));
        add(new SqlDataHolder("jigsawBlockInteractions", "blocks", "minecraft:jigsaw_block", "can_interact", "true"));
        add(new SqlDataHolder("jukeboxInteractions", "blocks", "minecraft:jukebox", "can_interact", "true"));
        add(new SqlDataHolder("lecternInteractions", "blocks", "minecraft:lectern", "can_interact", "true"));
        add(new SqlDataHolder("loomInteractions", "blocks", "minecraft:loom", "can_interact", "true"));
        add(new SqlDataHolder("noteblockInteractions", "blocks", "minecraft:noteblock", "can_interact", "true"));
        add(new SqlDataHolder("shulkerBoxInteractions", "blocks", "minecraft:shulker_box", "can_interact", "true"));
        add(new SqlDataHolder("smithingTableInteractions", "blocks", "minecraft:smithing_table", "can_interact", "true"));
        add(new SqlDataHolder("smokerInteractions", "blocks", "minecraft:smoker", "can_interact", "true"));
        add(new SqlDataHolder("stonecutterInteractions", "blocks", "minecraft:stonecutter", "can_interact", "true"));
        add(new SqlDataHolder("structureBlockInteractions", "blocks", "minecraft:structure_block", "can_interact", "true"));
        add(new SqlDataHolder("trapdoorInteractions", "blocks", "minecraft:trapdoor", "can_interact", "true"));

        add(new SqlDataHolder("appleNutrition", "items", "minecraft:apple", "nutrition", "4"));
        add(new SqlDataHolder("bakedPotatoNutrition", "items", "minecraft:baked_potato", "nutrition", "5"));
        add(new SqlDataHolder("beefNutrition", "items", "minecraft:beef", "nutrition", "3"));
        add(new SqlDataHolder("beetrootNutrition", "items", "minecraft:beetroot", "nutrition", "1"));
        add(new SqlDataHolder("beetrootSoupNutrition", "items", "minecraft:beetroot_soup", "nutrition", "6"));
        add(new SqlDataHolder("breadNutrition", "items", "minecraft:bread", "nutrition", "5"));
        add(new SqlDataHolder("carrotNutrition", "items", "minecraft:carrot", "nutrition", "3"));
        add(new SqlDataHolder("chickenNutrition", "items", "minecraft:chicken", "nutrition", "2"));
        add(new SqlDataHolder("chorusFruitNutrition", "items", "minecraft:chorus_fruit", "nutrition", "4"));
        add(new SqlDataHolder("codNutrition", "items", "minecraft:cod", "nutrition", "2"));
        add(new SqlDataHolder("cookedBeefNutrition", "items", "minecraft:cooked_beef", "nutrition", "8"));
        add(new SqlDataHolder("cookedChickenNutrition", "items", "minecraft:cooked_chicken", "nutrition", "6"));
        add(new SqlDataHolder("cookedCodNutrition", "items", "minecraft:cooked_cod", "nutrition", "5"));
        add(new SqlDataHolder("cookedMuttonNutrition", "items", "minecraft:cooked_mutton", "nutrition", "6"));
        add(new SqlDataHolder("cookedPorkchopNutrition", "items", "minecraft:cooked_porkchop", "nutrition", "8"));
        add(new SqlDataHolder("cookedRabbitNutrition", "items", "minecraft:cooked_rabbit", "nutrition", "5"));
        add(new SqlDataHolder("cookedSalmonNutrition", "items", "minecraft:cooked_salmon", "nutrition", "6"));
        add(new SqlDataHolder("cookieNutrition", "items", "minecraft:cookie", "nutrition", "2"));
        add(new SqlDataHolder("driedKelpNutrition", "items", "minecraft:dried_kelp", "nutrition", "1"));
        add(new SqlDataHolder("enchantedGoldenAppleNutrition", "items", "minecraft:enchanted_golden_apple", "nutrition", "4"));
        add(new SqlDataHolder("goldenAppleNutrition", "items", "minecraft:golden_apple", "nutrition", "4"));
        add(new SqlDataHolder("goldenCarrotNutrition", "items", "minecraft:golden_carrot", "nutrition", "6"));
        add(new SqlDataHolder("honeyBottleNutrition", "items", "minecraft:honey_bottle", "nutrition", "6"));
        add(new SqlDataHolder("melonSliceNutrition", "items", "minecraft:melon_slice", "nutrition", "2"));
        add(new SqlDataHolder("mushroomStewNutrition", "items", "minecraft:mushroom_stew", "nutrition", "6"));
        add(new SqlDataHolder("muttonNutrition", "items", "minecraft:mutton", "nutrition", "2"));
        add(new SqlDataHolder("poisonousPotatoNutrition", "items", "minecraft:poisonous_potato", "nutrition", "2"));
        add(new SqlDataHolder("porkchopNutrition", "items", "minecraft:porkchop", "nutrition", "3"));
        add(new SqlDataHolder("potatoNutrition", "items", "minecraft:potato", "nutrition", "1"));
        add(new SqlDataHolder("pufferfishNutrition", "items", "minecraft:pufferfish", "nutrition", "1"));
        add(new SqlDataHolder("pumpkinPieNutrition", "items", "minecraft:pumpkin_pie", "nutrition", "8"));
        add(new SqlDataHolder("rabbitNutrition", "items", "minecraft:rabbit", "nutrition", "3"));
        add(new SqlDataHolder("rabbitStewNutrition", "items", "minecraft:rabbit_stew", "nutrition", "10"));
        add(new SqlDataHolder("rottenFleshNutrition", "items", "minecraft:rotten_flesh", "nutrition", "4"));
        add(new SqlDataHolder("salmonNutrition", "items", "minecraft:salmon", "nutrition", "2"));
        add(new SqlDataHolder("spiderEyeNutrition", "items", "minecraft:spider_eye", "nutrition", "2"));
        add(new SqlDataHolder("suspiciousStewNutrition", "items", "minecraft:suspicious_stew", "nutrition", "6"));
        add(new SqlDataHolder("sweetBerriesNutrition", "items", "minecraft:sweet_berries", "nutrition", "2"));
        add(new SqlDataHolder("glowBerriesNutrition", "items", "minecraft:glow_berries", "nutrition", "2"));
        add(new SqlDataHolder("tropicalFishNutrition", "items", "minecraft:tropical_fish", "nutrition", "1"));

        add(new SqlDataHolder("appleSaturationModifier", "items", "minecraft:apple", "saturation", "0.3"));
        add(new SqlDataHolder("bakedPotatoSaturationModifier", "items", "minecraft:baked_potato", "saturation", "0.6"));
        add(new SqlDataHolder("beefSaturationModifier", "items", "minecraft:beef", "saturation", "0.3"));
        add(new SqlDataHolder("beetrootSaturationModifier", "items", "minecraft:beetroot", "saturation", "0.6"));
        add(new SqlDataHolder("beetrootSoupSaturationModifier", "items", "minecraft:beetroot_soup", "saturation", "0.6"));
        add(new SqlDataHolder("breadSaturationModifier", "items", "minecraft:bread", "saturation", "0.6"));
        add(new SqlDataHolder("carrotSaturationModifier", "items", "minecraft:carrot", "saturation", "0.6"));
        add(new SqlDataHolder("chickenSaturationModifier", "items", "minecraft:chicken", "saturation", "0.3"));
        add(new SqlDataHolder("chorusFruitSaturationModifier", "items", "minecraft:chorus_fruit", "saturation", "0.3"));
        add(new SqlDataHolder("codSaturationModifier", "items", "minecraft:cod", "saturation", "0.1"));
        add(new SqlDataHolder("cookedBeefSaturationModifier", "items", "minecraft:cooked_beef", "saturation", "0.8"));
        add(new SqlDataHolder("cookedChickenSaturationModifier", "items", "minecraft:cooked_chicken", "saturation", "0.6"));
        add(new SqlDataHolder("cookedCodSaturationModifier", "items", "minecraft:cooked_cod", "saturation", "0.6"));
        add(new SqlDataHolder("cookedMuttonSaturationModifier", "items", "minecraft:cooked_mutton", "saturation", "0.8"));
        add(new SqlDataHolder("cookedPorkchopSaturationModifier", "items", "minecraft:cooked_porkchop", "saturation", "0.8"));
        add(new SqlDataHolder("cookedRabbitSaturationModifier", "items", "minecraft:cooked_rabbit", "saturation", "0.6"));
        add(new SqlDataHolder("cookedSalmonSaturationModifier", "items", "minecraft:cooked_salmon", "saturation", "0.8"));
        add(new SqlDataHolder("cookieSaturationModifier", "items", "minecraft:cookie", "saturation", "0.2"));
        add(new SqlDataHolder("driedKelpSaturationModifier", "items", "minecraft:dried_kelp", "saturation", "0.3"));
        add(new SqlDataHolder("enchantedGoldenAppleSaturationModifier", "items", "minecraft:enchanted_golden_apple", "saturation", "0.12"));
        add(new SqlDataHolder("goldenAppleSaturationModifier", "items", "minecraft:golden_apple", "saturation", "0.12"));
        add(new SqlDataHolder("goldenCarrotSaturationModifier", "items", "minecraft:golden_carrot", "saturation", "0.12"));
        add(new SqlDataHolder("honeyBottleSaturationModifier", "items", "minecraft:honey_bottle", "saturation", "0.1"));
        add(new SqlDataHolder("melonSliceSaturationModifier", "items", "minecraft:melon_slice", "saturation", "0.3"));
        add(new SqlDataHolder("mushroomStewSaturationModifier", "items", "minecraft:mushroom_stew", "saturation", "0.3"));
        add(new SqlDataHolder("muttonSaturationModifier", "items", "minecraft:mutton", "saturation", "0.3"));
        add(new SqlDataHolder("poisonousPotatoSaturationModifier", "items", "minecraft:poisonous_potato", "saturation", "0.3"));
        add(new SqlDataHolder("porkchopSaturationModifier", "items", "minecraft:porkchop", "saturation", "0.3"));
        add(new SqlDataHolder("potatoSaturationModifier", "items", "minecraft:potato", "saturation", "0.1"));
        add(new SqlDataHolder("pufferfishSaturationModifier", "items", "minecraft:pufferfish", "saturation", "0.3"));
        add(new SqlDataHolder("pumpkinPieSaturationModifier", "items", "minecraft:pumpkin_pie", "saturation", "0.3"));
        add(new SqlDataHolder("rabbitSaturationModifier", "items", "minecraft:rabbit", "saturation", "0.3"));
        add(new SqlDataHolder("rabbitStewSaturationModifier", "items", "minecraft:rabbit_stew", "saturation", "0.6"));
        add(new SqlDataHolder("rottenFleshSaturationModifier", "items", "minecraft:rotten_flesh", "saturation", "0.1"));
        add(new SqlDataHolder("salmonSaturationModifier", "items", "minecraft:salmon", "saturation", "0.1"));
        add(new SqlDataHolder("spiderEyeSaturationModifier", "items", "minecraft:spider_eye", "saturation", "0.8"));
        add(new SqlDataHolder("suspiciousStewSaturationModifier", "items", "minecraft:suspicious_stew", "saturation", "0.6"));
        add(new SqlDataHolder("sweetBerriesSaturationModifier", "items", "minecraft:sweet_berries", "saturation", "0.1"));
        add(new SqlDataHolder("glowBerriesSaturationModifier", "items", "minecraft:glow_berries", "saturation", "0.1"));
        add(new SqlDataHolder("tropicalFishSaturationModifier", "items", "minecraft:tropical_fish", "saturation", "0.1"));
        add(new SqlDataHolder("oldHunger", "entities", "minecraft:player", "beta_hunger", "false"));

        add(new SqlDataHolder("normalPotionsEnabled", "items", "minecraft:potion", "works", "true"));
        add(new SqlDataHolder("splashPotionsEnabled", "items", "minecraft:splash_potion", "works", "true"));
        add(new SqlDataHolder("lingeringPotionsEnabled", "items", "minecraft:lingering_potion", "works", "true"));
        add(new SqlDataHolder("fireResistancePotion", "items", "%_potion", "fire_resistance_effect", "true"));
        add(new SqlDataHolder("harmingPotion", "items", "%_potion", "harming_effect", "true"));
        add(new SqlDataHolder("healingPotion", "items", "%_potion", "healing_effect", "true"));
        add(new SqlDataHolder("invisibilityPotion", "items", "%_potion", "invisibility_effect", "true"));
        add(new SqlDataHolder("leapingPotion", "items", "%_potion", "leaping_effect", "true"));
        add(new SqlDataHolder("luckPotion", "items", "%_potion", "luck_effect", "true"));
        add(new SqlDataHolder("nightVisionPotion", "items", "%_potion", "night_vision_effect", "true"));
        add(new SqlDataHolder("poisonPotion", "items", "%_potion", "poison_effect", "true"));
        add(new SqlDataHolder("regenerationPotion", "items", "%_potion", "regeneration_effect", "true"));
        add(new SqlDataHolder("slownessPotion", "items", "%_potion", "slowness_effect", "true"));
        add(new SqlDataHolder("slowFallingPotion", "items", "%_potion", "slow_falling_effect", "true"));
        add(new SqlDataHolder("strengthPotion", "items", "%_potion", "strength_effect", "true"));
        add(new SqlDataHolder("swiftnessPotion", "items", "%_potion", "swiftness_effect", "true"));
        add(new SqlDataHolder("turtleMasterPotion", "items", "%_potion", "turtle_master_effect", "true"));
        add(new SqlDataHolder("waterBreathingPotion", "items", "%_potion", "water_breathing_effect", "true"));
        add(new SqlDataHolder("weaknessPotion", "items", "%_potion", "weakness_effect", "true"));

        add(new SqlDataHolder("anvilDeath", "entities", "minecraft:player", "falling_anvil_death", "true"));
        add(new SqlDataHolder("cactusDeath", "entities", "minecraft:player", "cactus_death", "true"));
        add(new SqlDataHolder("crammingDeath", "entities", "minecraft:player", "cramming_death", "true"));
        add(new SqlDataHolder("dragonBreathDeath", "entities", "minecraft:player", "dragon_breath_death", "true"));
        add(new SqlDataHolder("drowningDeath", "entities", "minecraft:player", "drown_death", "true"));
        add(new SqlDataHolder("explosionDeath", "entities", "minecraft:player", "explosion_death", "true"));
        add(new SqlDataHolder("fallingBlockDeath", "entities", "minecraft:player", "falling_block_death", "true"));
        add(new SqlDataHolder("fallingDeath", "entities", "minecraft:player", "fall_death", "true"));
        add(new SqlDataHolder("fallingStalactiteDeath", "entities", "minecraft:player", "falling_stalactite_death", "true"));
        add(new SqlDataHolder("flyIntoWallDeath", "entities", "minecraft:player", "fly_into_wall_death", "true"));
        add(new SqlDataHolder("freezingDeath", "entities", "minecraft:player", "freeze_death", "true"));
        add(new SqlDataHolder("hotFloorDeath", "entities", "minecraft:player", "hot_floor_death", "true"));
        add(new SqlDataHolder("inFireDeath", "entities", "minecraft:player", "in_fire_death", "true"));
        add(new SqlDataHolder("inWallDeath", "entities", "minecraft:player", "in_wall_death", "true"));
        add(new SqlDataHolder("lavaDeath", "entities", "minecraft:player", "lava_death", "true"));
        add(new SqlDataHolder("lightningBoltDeath", "entities", "minecraft:player", "lightning_bolt_death", "true"));
        add(new SqlDataHolder("magicDeath", "entities", "minecraft:player", "magic_death", "true"));
        add(new SqlDataHolder("mobDeath", "entities", "minecraft:player", "mob_attack_death", "true"));
        add(new SqlDataHolder("onFireDeath", "entities", "minecraft:player", "on_fire_death", "true"));
        add(new SqlDataHolder("outOfWorldDeath", "entities", "minecraft:player", "fell_out_of_world_death", "true"));
        add(new SqlDataHolder("playerDeath", "entities", "minecraft:player", "player_attack_death", "true"));
        add(new SqlDataHolder("sonicBoomDeath", "entities", "minecraft:player", "sonic_boom_death", "true"));
        add(new SqlDataHolder("stalagmiteDeath", "entities", "minecraft:player", "stalagmite_death", "true"));
        add(new SqlDataHolder("starvationDeath", "entities", "minecraft:player", "starve_death", "true"));
        add(new SqlDataHolder("stingingDeath", "entities", "minecraft:player", "sting_death", "true"));
        add(new SqlDataHolder("sweetBerryBushDeath", "entities", "minecraft:player", "sweet_berry_bush_death", "true"));
        add(new SqlDataHolder("thornsDeath", "entities", "minecraft:player", "thorns_death", "true"));
        add(new SqlDataHolder("witherDeath", "entities", "minecraft:player", "wither_death", "true"));

        add(new SqlDataHolder("boatsEnabled", "items", "%_boat", "works", "true"));
        add(new SqlDataHolder("booksEnabled", "items", "%_book", "works", "true"));
        add(new SqlDataHolder("bottlesEnabled", "items", "minecraft:glass_bottle", "works", "true"));
        add(new SqlDataHolder("bowsEnabled", "items", "minecraft:bow", "works", "true"));
        add(new SqlDataHolder("bucketsEnabled", "items", "%_bucket", "works", "true"));
        add(new SqlDataHolder("bundlesEnabled", "items", "minecraft:bundle", "works", "true"));
        add(new SqlDataHolder("crossbowsEnabled", "items", "minecraft:crossbow", "works", "true"));
        add(new SqlDataHolder("eggsEnabled", "items", "minecraft:egg", "works", "true"));
        add(new SqlDataHolder("enderEyesEnabled", "items", "minecraft:ender_eye", "works", "true"));
        add(new SqlDataHolder("enderPearlsEnabled", "items", "minecraft:ender_pearl", "works", "true"));
        add(new SqlDataHolder("experienceBottlesEnabled", "items", "minecraft:experience_bottle", "works", "true"));
        add(new SqlDataHolder("fireworksEnabled", "items", "minecraft:firework_rocket", "works", "true"));
        add(new SqlDataHolder("fishingEnabled", "items", "minecraft:fishing_rod", "works", "true"));
        add(new SqlDataHolder("foodOnSticksEnabled", "items", "%_on_a_stick", "works", "true"));
        add(new SqlDataHolder("goatHornsEnabled", "items", "minecraft:goat_horn", "works", "true"));
        add(new SqlDataHolder("mapsEnabled", "items", "minecraft:map", "works", "true"));
        add(new SqlDataHolder("shieldsEnabled", "items", "minecraft:shield", "works", "true"));
        add(new SqlDataHolder("snowballsEnabled", "items", "minecraft:snowball", "works", "true"));
        add(new SqlDataHolder("spyglassesEnabled", "items", "minecraft:spyglass", "works", "true"));
        add(new SqlDataHolder("tridentsEnabled", "items", "minecraft:trident", "works", "true"));
        add(new SqlDataHolder("axesEnabled", "items", "minecraft:axe", "works", "true"));
        add(new SqlDataHolder("boneMealEnabled", "items", "minecraft:bone_meal", "works", "true"));
        add(new SqlDataHolder("compassesEnabled", "items", "minecraft:compass", "works", "true"));
        add(new SqlDataHolder("endCrystalsEnabled", "items", "minecraft:end_crystal", "works", "true"));
        add(new SqlDataHolder("fireChargesEnabled", "items", "minecraft:fire_charge", "works", "true"));
        add(new SqlDataHolder("flintAndSteelEnabled", "items", "minecraft:flint_and_steel", "works", "true"));
        add(new SqlDataHolder("hangingEntitiesEnabled", "items", "%_item_frame", "works", "true"));
        add(new SqlDataHolder("hangingEntitiesEnabled", "items", "minecraft:painting", "works", "true"));
        add(new SqlDataHolder("hoesEnabled", "items", "%_hoe", "works", "true"));
        add(new SqlDataHolder("honeycombsEnabled", "items", "minecraft:honeycomb", "works", "true"));
        add(new SqlDataHolder("leadsEnabled", "items", "minecraft:lead", "works", "true"));
        add(new SqlDataHolder("minecartsEnabled", "items", "%_minecart", "works", "true"));
        add(new SqlDataHolder("recordsEnabled", "items", "minecraft:music_disc_%", "works", "true"));
        add(new SqlDataHolder("shearsEnabled", "items", "minecraft:shears", "works", "true"));
        add(new SqlDataHolder("shovelsEnabled", "items", "%_shovel", "works", "true"));
        add(new SqlDataHolder("dyesEnabled", "items", "%_dye", "works", "true"));
        add(new SqlDataHolder("nametagsEnabled", "items", "minecraft:name_tag", "works", "true"));
        add(new SqlDataHolder("saddlesEnabled", "items", "minecraft:saddle", "works", "true"));
        add(new SqlDataHolder("chorusFruitEffectsEnabled", "items", "minecraft:chorus_fruit", "works", "true"));
        add(new SqlDataHolder("honeyBottleEffectsEnabled", "items", "minecraft:honey_bottle", "works", "true"));
        add(new SqlDataHolder("milkBucketEffectsEnabled", "items", "minecraft:milk_bucket", "works", "true"));
        add(new SqlDataHolder("suspiciousStewEffectsEnabled", "items", "minecraft:suspicious_stew", "works", "true"));
        add(new SqlDataHolder("totemsEnabled", "items", "minecraft:totem_of_undying", "works", "true"));

        add(new SqlDataHolder("bowSpamming", "items", "minecraft:bow", "can_spam", "false"));
        add(new SqlDataHolder("crossbowSpamming", "items", "minecraft:crossbow", "can_spam", "false"));

        add(new SqlDataHolder("bowDurability", "items", "minecraft:bow", "durability", "384"));
        add(new SqlDataHolder("carrotOnStickDurability", "items", "minecraft:carrot_on_a_stick", "durability", "25"));
        add(new SqlDataHolder("crossbowDurability", "items", "minecraft:crossbow", "durability", "465"));
        add(new SqlDataHolder("elytraDurability", "items", "minecraft:elytra", "durability", "432"));
        add(new SqlDataHolder("fishingRodDurability", "items", "minecraft:fishing_rod", "durability", "64"));
        add(new SqlDataHolder("flintAndSteelDurability", "items", "minecraft:flint_and_steel", "durability", "64"));
        add(new SqlDataHolder("shearsDurability", "items", "minecraft:shears", "durability", "238"));
        add(new SqlDataHolder("shieldDurability", "items", "minecraft:shield", "durability", "336"));
        add(new SqlDataHolder("tridentDurability", "items", "minecraft:trident", "durability", "250"));
        add(new SqlDataHolder("warpedFungusOnStickDurability", "items", "minecraft:warped_fungus_on_a_stick", "durability", "100"));
        add(new SqlDataHolder("woodenToolDurability", "items", "minecraft:wooden_%", "durability", "59"));
        add(new SqlDataHolder("stoneToolDurability", "items", "minecraft:stone_%", "durability", "131"));
        add(new SqlDataHolder("ironToolDurability", "items", "minecraft:iron_axe", "durability", "250"));
        add(new SqlDataHolder("ironToolDurability", "items", "minecraft:iron_pickaxe", "durability", "250"));
        add(new SqlDataHolder("ironToolDurability", "items", "minecraft:iron_shovel", "durability", "250"));
        add(new SqlDataHolder("ironToolDurability", "items", "minecraft:iron_sword", "durability", "250"));
        add(new SqlDataHolder("ironToolDurability", "items", "minecraft:iron_hoe", "durability", "250"));
        add(new SqlDataHolder("goldenToolDurability", "items", "minecraft:golden_axe", "durability", "32"));
        add(new SqlDataHolder("goldenToolDurability", "items", "minecraft:golden_pickaxe", "durability", "32"));
        add(new SqlDataHolder("goldenToolDurability", "items", "minecraft:golden_shovel", "durability", "32"));
        add(new SqlDataHolder("goldenToolDurability", "items", "minecraft:golden_sword", "durability", "32"));
        add(new SqlDataHolder("goldenToolDurability", "items", "minecraft:golden_hoe", "durability", "32"));
        add(new SqlDataHolder("diamondToolDurability", "items", "minecraft:diamond_axe", "durability", "1561"));
        add(new SqlDataHolder("diamondToolDurability", "items", "minecraft:diamond_pickaxe", "durability", "1561"));
        add(new SqlDataHolder("diamondToolDurability", "items", "minecraft:diamond_shovel", "durability", "1561"));
        add(new SqlDataHolder("diamondToolDurability", "items", "minecraft:diamond_sword", "durability", "1561"));
        add(new SqlDataHolder("diamondToolDurability", "items", "minecraft:diamond_hoe", "durability", "1561"));
        add(new SqlDataHolder("netheriteToolDurability", "items", "minecraft:netherite_axe", "durability", "2031"));
        add(new SqlDataHolder("netheriteToolDurability", "items", "minecraft:netherite_pickaxe", "durability", "2031"));
        add(new SqlDataHolder("netheriteToolDurability", "items", "minecraft:netherite_shovel", "durability", "2031"));
        add(new SqlDataHolder("netheriteToolDurability", "items", "minecraft:netherite_sword", "durability", "2031"));
        add(new SqlDataHolder("netheriteToolDurability", "items", "minecraft:netherite_hoe", "durability", "2031"));
        add(new SqlDataHolder("leatherHelmetDurability", "items", "minecraft:leather_helmet", "durability", "55"));
        add(new SqlDataHolder("leatherChestplateDurability", "items", "minecraft:leather_chestplate", "durability", "80"));
        add(new SqlDataHolder("leatherLeggingsDurability", "items", "minecraft:leather_leggings", "durability", "75"));
        add(new SqlDataHolder("leatherBootsDurability", "items", "minecraft:leather_boots", "durability", "65"));
        add(new SqlDataHolder("chainmailHelmetDurability", "items", "minecraft:chainmail_helmet", "durability", "165"));
        add(new SqlDataHolder("chainmailChestplateDurability", "items", "minecraft:chainmail_chestplate", "durability", "240"));
        add(new SqlDataHolder("chainmailLeggingsDurability", "items", "minecraft:chainmail_leggings", "durability", "225"));
        add(new SqlDataHolder("chainmailBootsDurability", "items", "minecraft:chainmail_boots", "durability", "195"));
        add(new SqlDataHolder("ironHelmetDurability", "items", "minecraft:iron_helmet", "durability", "165"));
        add(new SqlDataHolder("ironChestplateDurability", "items", "minecraft:iron_chestplate", "durability", "240"));
        add(new SqlDataHolder("ironLeggingsDurability", "items", "minecraft:iron_leggings", "durability", "225"));
        add(new SqlDataHolder("ironBootsDurability", "items", "minecraft:iron_boots", "durability", "195"));
        add(new SqlDataHolder("goldenHelmetDurability", "items", "minecraft:golden_helmet", "durability", "77"));
        add(new SqlDataHolder("goldenChestplateDurability", "items", "minecraft:golden_chestplate", "durability", "112"));
        add(new SqlDataHolder("goldenLeggingsDurability", "items", "minecraft:golden_leggings", "durability", "105"));
        add(new SqlDataHolder("goldenBootsDurability", "items", "minecraft:golden_boots", "durability", "91"));
        add(new SqlDataHolder("diamondHelmetDurability", "items", "minecraft:diamond_helmet", "durability", "363"));
        add(new SqlDataHolder("diamondChestplateDurability", "items", "minecraft:diamond_chestplate", "durability", "528"));
        add(new SqlDataHolder("diamondLeggingsDurability", "items", "minecraft:diamond_leggings", "durability", "495"));
        add(new SqlDataHolder("diamondBootsDurability", "items", "minecraft:diamond_boots", "durability", "429"));
        add(new SqlDataHolder("netheriteHelmetDurability", "items", "minecraft:netherite_helmet", "durability", "407"));
        add(new SqlDataHolder("netheriteChestplateDurability", "items", "minecraft:netherite_chestplate", "durability", "592"));
        add(new SqlDataHolder("netheriteLeggingsDurability", "items", "minecraft:netherite_leggings", "durability", "555"));
        add(new SqlDataHolder("netheriteBootsDurability", "items", "minecraft:netherite_boots", "durability", "481"));
        add(new SqlDataHolder("turtleHelmetDurability", "items", "minecraft:turtle_helmet", "durability", "275"));
        add(new SqlDataHolder("netheriteBurns", "items", "%netherite%", "", "false"));
        add(new SqlDataHolder("netheriteBurns", "blocks", "%netherite%", "", "false"));
        add(new SqlDataHolder("netheriteBurns", "blocks", "minecraft:ancient_debris", "", "false"));

        add(new SqlDataHolder("dispenserBonemealsPlants", "items", "minecraft:bone_meal", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserBucketsItems", "items", "%_bucket", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserEquipsArmour", "items", "%_chestplate", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserEquipsArmour", "items", "%_helmet", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserEquipsArmour", "items", "%_leggings", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserEquipsArmour", "items", "%_boots", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFillsBottles", "items", "minecraft:glass_bottle", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFillsRespawnAnchor", "items", "minecraft:glowstone_block", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:arrow", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:tipped_arrow", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:spectral_arrow", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:egg", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:snowball", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:experience_bottle", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:splash_potion", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFiresProjectiles", "items", "minecraft:lingering_potion", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserFlintsAndSteels", "items", "minecraft:flint_and_steel", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserLaunchesFireworks", "items", "minecraft:firework_rocket", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserLaunchesFireCharges", "items", "minecraft:fire_charge", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserLightsTnt", "items", "minecraft:tnt", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserPlacesArmourStands", "items", "minecraft:armour_stand", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserPlacesBoats", "items", "minecraft:boat", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserPlacesHeads", "items", "%_head", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserPlacesMinecarts", "items", "%_minecart", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserPlacesShulkerBoxes", "items", "%_shulker_box", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserShearsSheep", "items", "minecraft:shears", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserSpawnsMobs", "items", "%_spawn_egg", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserWatersMud", "items", "minecraft:potion", "dispenser_interaction", "true"));
        add(new SqlDataHolder("dispenserWaxesCopper", "items", "minecraft:honeycomb", "dispenser_interaction", "true"));

        add(new SqlDataHolder("fireResistanceTippedArrow", "items", "minecraft:tipped_arrow", "fire_resistance_effect", "true"));
        add(new SqlDataHolder("harmingTippedArrow", "items", "minecraft:tipped_arrow", "harming_effect", "true"));
        add(new SqlDataHolder("healingTippedArrow", "items", "minecraft:tipped_arrow", "healing_effect", "true"));
        add(new SqlDataHolder("invisibilityTippedArrow", "items", "minecraft:tipped_arrow", "invisibility_effect", "true"));
        add(new SqlDataHolder("leapingTippedArrow", "items", "minecraft:tipped_arrow", "leaping_effect", "true"));
        add(new SqlDataHolder("luckTippedArrow", "items", "minecraft:tipped_arrow", "luck_effect", "true"));
        add(new SqlDataHolder("nightVisionTippedArrow", "items", "minecraft:tipped_arrow", "night_vision_effect", "true"));
        add(new SqlDataHolder("poisonTippedArrow", "items", "minecraft:tipped_arrow", "poison_effect", "true"));
        add(new SqlDataHolder("regenerationTippedArrow", "items", "minecraft:tipped_arrow", "regeneration_effect", "true"));
        add(new SqlDataHolder("slownessTippedArrow", "items", "minecraft:tipped_arrow", "slowness_effect", "true"));
        add(new SqlDataHolder("slowFallingTippedArrow", "items", "minecraft:tipped_arrow", "slow_falling_effect", "true"));
        add(new SqlDataHolder("strengthTippedArrow", "items", "minecraft:tipped_arrow", "strength_effect", "true"));
        add(new SqlDataHolder("swiftnessTippedArrow", "items", "minecraft:tipped_arrow", "swiftness_effect", "true"));
        add(new SqlDataHolder("turtleMasterTippedArrow", "items", "minecraft:tipped_arrow", "turtle_master_effect", "true"));
        add(new SqlDataHolder("waterBreathingTippedArrow", "items", "minecraft:tipped_arrow", "water_breathing_effect", "true"));
        add(new SqlDataHolder("weaknessTippedArrow", "items", "minecraft:tipped_arrow", "weakness_effect", "true"));
        add(new SqlDataHolder("spectralArrowsEnabled", "items", "minecraft:spectral_arrow", "works", "true"));

        add(new SqlDataHolder("adventureAdvancementAdventure", "advancements", "minecraft:adventure/root", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementSweetDreams", "advancements", "minecraft:adventure/slept_in_bed", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementAdventuringTime", "advancements", "minecraft:adventure/adventuring_time", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementWhatADeal", "advancements", "minecraft:adventure/trade", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementStarTrader", "advancements", "minecraft:adventure/trade_at_world_height", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementMonsterHunter", "advancements", "minecraft:adventure/kill_a_mob", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementMonstersHunted", "advancements", "minecraft:adventure/kill_all_mobs", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementTakeAim", "advancements", "minecraft:adventure/shoot_arrow", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementThrowawayJoke", "advancements", "minecraft:adventure/throw_trident", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementVeryFrightening", "advancements", "minecraft:adventure/very_very_frightening", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementHiredHelp", "advancements", "minecraft:adventure/summon_iron_golem", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementSniperDuel", "advancements", "minecraft:adventure/sniper_duel", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementPostmortal", "advancements", "minecraft:adventure/totem_of_undying", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementOldBetsy", "advancements", "minecraft:adventure/ol_betsy", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementWhosThePillagerNow", "advancements", "minecraft:adventure/whos_the_pillager_now", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementTwoBirdsOneArrow", "advancements", "minecraft:adventure/two_birds_one_arrow", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementArbalistic", "advancements", "minecraft:adventure/arbalistic", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementVoluntaryExile", "advancements", "minecraft:adventure/voluntary_exile", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementHeroOfVillage", "advancements", "minecraft:adventure/hero_of_the_village", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementStickySituation", "advancements", "minecraft:adventure/honey_block_slide", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementBullseye", "advancements", "minecraft:adventure/bullseye", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementLightAsRabbit", "advancements", "minecraft:adventure/walk_on_powder_snow_with_leather_boots", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementSurgeProtector", "advancements", "minecraft:adventure/lightning_rod_with_villager_no_fire", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementIsItABird", "advancements", "minecraft:adventure/spyglass_at_parrot", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementIsItABalloon", "advancements", "minecraft:adventure/spyglass_at_ghast", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementSoundOfMusic", "advancements", "minecraft:adventure/play_jukebox_in_meadows", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementIsItAPlane", "advancements", "minecraft:adventure/spyglass_at_dragon", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementCavesAndCliffs", "advancements", "minecraft:adventure/fall_from_world_height", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementItSpreads", "advancements", "minecraft:adventure/kill_mob_near_sculk_catalyst", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementSneak100", "advancements", "minecraft:adventure/avoid_vibration", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementRespectingTheRemnants", "advancements", "minecraft:adventure/salvage_sherd", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementCareefulRestoration", "advancements", "minecraft:adventure/craft_decorated_pot_using_only_sherds", "enabled", "true"));
        add(new SqlDataHolder("adventureAdvancementThePowerOfBooks", "advancements", "minecraft:adventure/read_power_of_chiseled_bookshelf", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementHusbandry", "advancements", "minecraft:husbandry/root", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementSeedyPlace", "advancements", "minecraft:husbandry/plant_seed", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementParrotsAndBats", "advancements", "minecraft:husbandry/breed_an_animal", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementBalancedDiet", "advancements", "minecraft:husbandry/balanced_diet", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementSeriousDedication", "advancements", "minecraft:husbandry/obtain_netherite_hoe", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementBestFriends", "advancements", "minecraft:husbandry/tame_an_animal", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementTwoByTwo", "advancements", "minecraft:husbandry/bred_all_animals", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementFishyBusiness", "advancements", "minecraft:husbandry/fishy_business", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementTacticalFishing", "advancements", "minecraft:husbandry/tactical_fishing", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementCutestPredator", "advancements", "minecraft:husbandry/axolotl_in_a_bucket", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementHealingFriendship", "advancements", "minecraft:husbandry/kill_axolotl_target", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementCompleteCatalogue", "advancements", "minecraft:husbandry/complete_catalogue", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementBeeOurGuest", "advancements", "minecraft:husbandry/safely_harvest_honey", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementWaxOn", "advancements", "minecraft:husbandry/wax_on", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementWaxOff", "advancements", "minecraft:husbandry/wax_off", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementBukkit", "advancements", "minecraft:husbandry/tadpole_in_a_bucket", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementSquadHopsIntoTown", "advancements", "minecraft:husbandry/leash_all_frog_variants", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementPowersCombined", "advancements", "minecraft:husbandry/froglights", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementBeelocation", "advancements", "minecraft:husbandry/silk_touch_nest", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementFloatsYourGoat", "advancements", "minecraft:husbandry/ride_a_boat_with_a_goat", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementGlowAndBehold", "advancements", "minecraft:husbandry/make_a_sign_glow", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementFriendInMe", "advancements", "minecraft:husbandry/allay_deliver_item_to_player", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementBirthdaySong", "advancements", "minecraft:husbandry/allay_deliver_cake_to_note_block", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementSmellsFunny", "advancements", "minecraft:husbandry/obtain_sniffer_egg", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementLittleSniffs", "advancements", "minecraft:husbandry/feed_snifflet", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementPlantingThePast", "advancements", "minecraft:husbandry/plant_any_sniffer_seed", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementCraftingANewLook", "advancements", "minecraft:adventure/trim_with_any_armor_pattern", "enabled", "true"));
        add(new SqlDataHolder("husbandryAdvancementSmithingWithStyle", "advancements", "minecraft:adventure/trim_with_all_exclusive_armor_patterns", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementNether", "advancements", "minecraft:nether/root", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementReturnToSender", "advancements", "minecraft:nether/return_to_sender", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementTerribleFortress", "advancements", "minecraft:nether/find_fortress", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementSubspaceBubble", "advancements", "minecraft:nether/fast_travel", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementUneasyAlliance", "advancements", "minecraft:nether/killed_ghast", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementSpookyScarySkeleton", "advancements", "minecraft:nether/get_wither_skull", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementWitheringHeights", "advancements", "minecraft:nether/summon_wither", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementIntoFire", "advancements", "minecraft:nether/obtain_blaze_rod", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementBringHomeBeacon", "advancements", "minecraft:nether/create_beacon", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementBeaconator", "advancements", "minecraft:nether/create_full_beacon", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementLocalBrewery", "advancements", "minecraft:nether/brew_potion", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementFuriousCocktail", "advancements", "minecraft:nether/all_potions", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementHowDidWeGetHere", "advancements", "minecraft:nether/all_effects", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementHiddenInDepths", "advancements", "minecraft:nether/obtain_ancient_debris", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementCoverInDebris", "advancements", "minecraft:nether/netherite_armor", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementCountryLode", "advancements", "minecraft:nether/use_lodestone", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementCuttingOnions", "advancements", "minecraft:nether/obtain_crying_obsidian", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementNotQuiteNineLives", "advancements", "minecraft:nether/charge_respawn_anchor", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementBoatHasLegs", "advancements", "minecraft:nether/ride_strider", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementFeelsLikeHome", "advancements", "minecraft:nether/ride_strider_in_overworld_lava", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementHotTouristDestinations", "advancements", "minecraft:nether/explore_nether", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementThoseWereTheDays", "advancements", "minecraft:nether/find_bastion", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementWarPigs", "advancements", "minecraft:nether/loot_bastion", "enabled", "true"));
        add(new SqlDataHolder("netherAdvancementOhShiny", "advancements", "minecraft:nether/distract_piglin", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementMinecraft", "advancements", "minecraft:story/root", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementStoneAge", "advancements", "minecraft:story/mine_stone", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementGettingUpgrade", "advancements", "minecraft:story/mine_stone", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementAcquireHardware", "advancements", "minecraft:story/mine_stone", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementIronPick", "advancements", "minecraft:story/iron_tools", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementDiamonds", "advancements", "minecraft:story/mine_diamond", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementHotStuff", "advancements", "minecraft:story/lava_bucket", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementSuitUp", "advancements", "minecraft:story/obtain_armor", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementEnchanter", "advancements", "minecraft:story/enchant_item", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementIceBucketChallenge", "advancements", "minecraft:story/form_obsidian", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementNotTodayThanks", "advancements", "minecraft:story/deflect_arrow", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementCoverWithDiamonds", "advancements", "minecraft:story/shiny_gear", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementGoDeeper", "advancements", "minecraft:story/enter_the_nether", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementZombieDoctor", "advancements", "minecraft:story/cure_zombie_villager", "enabled", "true"));
        add(new SqlDataHolder("storyAdvancementEyeSpy", "advancements", "minecraft:story/follow_ender_eye", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementTheEnd", "advancements", "minecraft:end/enter_the_end", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementFreeTheEnd", "advancements", "minecraft:end/kill_dragon", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementRemoteGetaway", "advancements", "minecraft:end/enter_end_gateway", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementEndAgain", "advancements", "minecraft:end/respawn_dragon", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementCityAtEnd", "advancements", "minecraft:end/find_end_city", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementYouNeedMint", "advancements", "minecraft:end/dragon_breath", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementGreatView", "advancements", "minecraft:end/levitate", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementSkyIsLimit", "advancements", "minecraft:end/elytra", "enabled", "true"));
        add(new SqlDataHolder("theEndAdvancementNextGeneration", "advancements", "minecraft:end/dragon_egg", "enabled", "true"));

        add(new SqlDataHolder("itemStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("entityKillStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("timeStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("distanceStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("damageStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("guiBlockInteractionStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("generalBlockInteractionStats", "entities", "minecraft:player", "", "true"));
        add(new SqlDataHolder("generalStats", "entities", "minecraft:player", "", "true"));

        add(new SqlDataHolder("albanPainting", "entities", "minecraft:painting", "alban_painting", "true"));
        add(new SqlDataHolder("aztec2Painting", "entities", "minecraft:painting", "aztec2_painting", "true"));
        add(new SqlDataHolder("aztecPainting", "entities", "minecraft:painting", "aztec_painting", "true"));
        add(new SqlDataHolder("bombPainting", "entities", "minecraft:painting", "bomb_painting", "true"));
        add(new SqlDataHolder("burningSkullPainting", "entities", "minecraft:painting", "burning_skull_painting", "true"));
        add(new SqlDataHolder("bustPainting", "entities", "minecraft:painting", "bust_painting", "true"));
        add(new SqlDataHolder("courbetPainting", "entities", "minecraft:painting", "courbet_painting", "true"));
        add(new SqlDataHolder("creebetPainting", "entities", "minecraft:painting", "creebet_painting", "true"));
        add(new SqlDataHolder("donkeyKongPainting", "entities", "minecraft:painting", "donkey_kong_painting", "true"));
        add(new SqlDataHolder("earthPainting", "entities", "minecraft:painting", "earth_painting", "true"));
        add(new SqlDataHolder("fightersPainting", "entities", "minecraft:painting", "fighters_painting", "true"));
        add(new SqlDataHolder("firePainting", "entities", "minecraft:painting", "fire_painting", "true"));
        add(new SqlDataHolder("grahamPainting", "entities", "minecraft:painting", "graham_painting", "true"));
        add(new SqlDataHolder("kebabPainting", "entities", "minecraft:painting", "kebab_painting", "true"));
        add(new SqlDataHolder("matchPainting", "entities", "minecraft:painting", "match_painting", "true"));
        add(new SqlDataHolder("pigscenePainting", "entities", "minecraft:painting", "pigscene_painting", "true"));
        add(new SqlDataHolder("plantPainting", "entities", "minecraft:painting", "plant_painting", "true"));
        add(new SqlDataHolder("pointerPainting", "entities", "minecraft:painting", "pointer_painting", "true"));
        add(new SqlDataHolder("poolPainting", "entities", "minecraft:painting", "pool_painting", "true"));
        add(new SqlDataHolder("seaPainting", "entities", "minecraft:painting", "sea_painting", "true"));
        add(new SqlDataHolder("skeletonPainting", "entities", "minecraft:painting", "skeleton_painting", "true"));
        add(new SqlDataHolder("skullAndRosesPainting", "entities", "minecraft:painting", "skull_and_roses_painting", "true"));
        add(new SqlDataHolder("stagePainting", "entities", "minecraft:painting", "stage_painting", "true"));
        add(new SqlDataHolder("sunsetPainting", "entities", "minecraft:painting", "sunset_painting", "true"));
        add(new SqlDataHolder("voidPainting", "entities", "minecraft:painting", "void_painting", "true"));
        add(new SqlDataHolder("wandererPainting", "entities", "minecraft:painting", "wanderer_painting", "true"));
        add(new SqlDataHolder("wastelandPainting", "entities", "minecraft:painting", "wasteland_painting", "true"));
        add(new SqlDataHolder("waterPainting", "entities", "minecraft:painting", "water_painting", "true"));
        add(new SqlDataHolder("windPainting", "entities", "minecraft:painting", "wind_painting", "true"));
        add(new SqlDataHolder("witherPainting", "entities", "minecraft:painting", "wither_painting", "true"));

        add(new SqlDataHolder("oldBoats", "entities", "minecraft:boat", "alpha_behaviour", "false"));
        add(new SqlDataHolder("dragonEggsFall", "blocks", "minecraft:dragon_egg", "can_fall", "true"));
        add(new SqlDataHolder("anvilsFall", "blocks", "minecraft:anvil", "can_fall", "true"));
        add(new SqlDataHolder("concretePowderFalls", "blocks", "%_concrete_powder", "can_fall", "true"));
        add(new SqlDataHolder("gravelFalls", "blocks", "minecraft:gravel", "can_fall", "true"));
        add(new SqlDataHolder("sandFalls", "blocks", "minecraft:sand", "can_fall", "true"));
        add(new SqlDataHolder("pointedDripstoneFalls", "blocks", "minecraft:pointed_dripstone", "can_fall", "true"));
        add(new SqlDataHolder("enderDragonsDropXp", "entities", "minecraft:ender_dragon", "can_drop_xp", "true"));
        add(new SqlDataHolder("furnacesDropXp", "blocks", "minecraft:furnace", "can_drop_xp", "true"));
        add(new SqlDataHolder("grindstonesDropXp", "blocks", "minecraft:grindstone", "can_drop_xp", "true"));
        add(new SqlDataHolder("xpBottlesDropXp", "items", "minecraft:experience_bottle", "can_drop_xp", "true"));

        add(new SqlDataHolder("villagerDailyRestocks", "entities", "minecraft:villager", "daily_restocks", "2"));
        add(new SqlDataHolder("infiniteTrading", "entities", "minecraft:villager", "can_infinitely_trade", "false"));
        add(new SqlDataHolder("villagerTradingEnabled", "entities", "minecraft:villager", "can_trade", "true"));
        add(new SqlDataHolder("piglinBarteringEnabled", "entities", "minecraft:piglin", "can_trade", "true"));
        add(new SqlDataHolder("desertVillagerType", "entities", "minecraft:villager", "desert_type", "true"));
        add(new SqlDataHolder("jungleVillagerType", "entities", "minecraft:villager", "jungle_type", "true"));
        add(new SqlDataHolder("savannaVillagerType", "entities", "minecraft:villager", "savanna_type", "true"));
        add(new SqlDataHolder("snowVillagerType", "entities", "minecraft:villager", "snow_type", "true"));
        add(new SqlDataHolder("swampVillagerType", "entities", "minecraft:villager", "swamp_type", "true"));
        add(new SqlDataHolder("taigaVillagerType", "entities", "minecraft:villager", "taiga_type", "true"));
        add(new SqlDataHolder("armourerVillagerProfession", "entities", "minecraft:villager", "armourer_profession", "true"));
        add(new SqlDataHolder("butcherVillagerProfession", "entities", "minecraft:villager", "butcher_profession", "true"));
        add(new SqlDataHolder("cartographerVillagerProfession", "entities", "minecraft:villager", "cartographer_profession", "true"));
        add(new SqlDataHolder("clericVillagerProfession", "entities", "minecraft:villager", "cleric_profession", "true"));
        add(new SqlDataHolder("farmerVillagerProfession", "entities", "minecraft:villager", "farmer_profession", "true"));
        add(new SqlDataHolder("fishermanVillagerProfession", "entities", "minecraft:villager", "fisherman_profession", "true"));
        add(new SqlDataHolder("fletcherVillagerProfession", "entities", "minecraft:villager", "fletcher_profession", "true"));
        add(new SqlDataHolder("leatherworkerVillagerProfession", "entities", "minecraft:villager", "leatherworker_profession", "true"));
        add(new SqlDataHolder("librarianVillagerProfession", "entities", "minecraft:villager", "librarian_profession", "true"));
        add(new SqlDataHolder("masonVillagerProfession", "entities", "minecraft:villager", "mason_profession", "true"));
        add(new SqlDataHolder("shepherdVillagerProfession", "entities", "minecraft:villager", "shepherd_profession", "true"));
        add(new SqlDataHolder("toolsmithVillagerProfession", "entities", "minecraft:villager", "toolsmith_profession", "true"));
        add(new SqlDataHolder("weaponsmithVillagerProfession", "entities", "minecraft:villager", "weaponsmith_profession", "true"));
    }};
    public static final ObjectList<MassColumnSqlDataHolder> massColumnSqlData = new ObjectArrayList<>() {{
        List<String> potions = List.of(
                "water_effect", "mundane_effect", "thick_effect", "awkward_effect", "night_vision_effect", "long_night_vision_effect", "invisibility_effect", "long_invisibility_effect",
                "leaping_effect", "long_leaping_effect", "strong_leaping_effect", "fire_resistance_effect", "long_fire_resistance_effect", "swiftness_effect", "long_swiftness_effect",
                "strong_swiftness_effect", "slowness_effect", "long_slowness_effect", "strong_slowness_effect", "turtle_master_effect", "long_turtle_master_effect",
                "strong_turtle_master_effect", "water_breathing_effect", "long_water_breathing_effect", "healing_effect", "strong_healing_effect", "harming_effect",
                "strong_harming_effect", "poison_effect", "long_poison_effect", "strong_poison_effect", "regeneration_effect", "long_regeneration_effect", "strong_regeneration_effect",
                "strength_effect", "long_strength_effect", "strong_strength_effect", "weakness_effect", "long_weakness_effect", "luck_effect", "slow_falling_effect", "long_slow_falling_effect"
        );
        List<String> damage_types = List.of(
                "in_fire", "lightning_bolt", "on_fire", "lava", "hot_floor", "in_wall",
                "cramming", "drown", "starve", "cactus", "fall", "fly_into_wall", "out_of_world",
                "generic", "magic", "wither", "dragon_breath", "dry_out", "sweet_berry_bush",
                "freeze", "stalagmite", "falling_block", "falling_anvil", "falling_stalactite",
                "sting", "mob_attack", "mob_attack_no_aggro", "player_attack", "arrow",
                "trident", "mob_projectile", "fireworks", "fireball", "unattributed_fireball", "wither_skull",
                "thrown", "indirect_magic", "thorns", "explosion", "player_explosion", "sonic_boom", "bad_respawn_point", "outside_border", "generic_kill"
        );
        add(new MassColumnSqlDataHolder("damageEnabled", "entities", "minecraft:player", damage_types.stream().map(s -> s + "_damage").toList()));
        add(new MassColumnSqlDataHolder("effectsEnabled", "entities", "minecraft:player", List.of(
                "speed_effect", "slowness_effect", "haste_effect", "mining_fatigue_effect", "strength_effect", "instant_health_effect", "instant_damage_effect", "jump_boost_effect",
                "nausea_effect", "regeneration_effect", "resistance_effect", "fire_resistance_effect", "water_breathing_effect", "invisibility_effect", "blindness_effect",
                "night_vision_effect", "hunger_effect", "weakness_effect", "poison_effect", "wither_effect", "health_boost_effect", "absorption_effect", "saturation_effect", "glowing_effect",
                "levitation_effect", "luck_effect", "unluck_effect", "slow_falling_effect", "conduit_power_effect", "dolphins_grace_effect", "bad_omen_effect", "hero_of_the_village_effect", "darkness_effect"
        )));
        add(new MassColumnSqlDataHolder("potionsEnabled", "items", "%_potion", potions));
        add(new MassColumnSqlDataHolder("deathEnabled", "entities", "minecraft:player", damage_types.stream().map(s -> s + "_death").toList()));
        add(new MassColumnSqlDataHolder("tippedArrowsEnabled", "items", "minecraft:tipped_arrow", potions));
        add(new MassColumnSqlDataHolder("statsEnabled", "entities", "minecraft:player", List.of(
                "mined_stat_type", "crafted_stat_type", "used_stat_type", "broken_stat_type", "picked_up_stat_type",
                "dropped_stat_type", "killed_stat_type", "killed_by_stat_type", "leave_game_custom_stat", "play_time_custom_stat",
                "total_world_time_custom_stat", "time_since_death_custom_stat", "time_since_rest_custom_stat", "sneak_time_custom_stat",
                "walk_one_cm_custom_stat", "crouch_one_cm_custom_stat", "sprint_one_cm_custom_stat", "walk_on_water_one_cm_custom_stat",
                "fall_one_cm_custom_stat", "climb_one_cm_custom_stat", "fly_one_cm_custom_stat", "walk_under_water_one_cm_custom_stat",
                "minecart_one_cm_custom_stat", "boat_one_cm_custom_stat", "pig_one_cm_custom_stat", "horse_one_cm_custom_stat",
                "aviate_one_cm_custom_stat", "swim_one_cm_custom_stat", "strider_one_cm_custom_stat", "jump_custom_stat",
                "drop_custom_stat", "damage_dealt_custom_stat", "damage_dealt_absorbed_custom_stat", "damage_dealt_resisted_custom_stat",
                "damage_taken_custom_stat", "damage_blocked_by_shield_custom_stat", "damage_absorbed_custom_stat", "damage_resisted_custom_stat",
                "deaths_custom_stat", "mob_kills_custom_stat", "animals_bred_custom_stat", "player_kills_custom_stat", "fish_caught_custom_stat",
                "talked_to_villager_custom_stat", "traded_with_villager_custom_stat", "eat_cake_slice_custom_stat", "fill_cauldron_custom_stat",
                "use_cauldron_custom_stat", "clean_armor_custom_stat", "clean_banner_custom_stat", "clean_shulker_box_custom_stat",
                "interact_with_brewingstand_custom_stat", "interact_with_beacon_custom_stat", "inspect_dropper_custom_stat",
                "inspect_hopper_custom_stat", "inspect_dispenser_custom_stat", "play_noteblock_custom_stat", "tune_noteblock_custom_stat",
                "pot_flower_custom_stat", "trigger_trapped_chest_custom_stat", "open_enderchest_custom_stat", "enchant_item_custom_stat",
                "play_record_custom_stat", "interact_with_furnace_custom_stat", "interact_with_crafting_table_custom_stat", "open_chest_custom_stat",
                "sleep_in_bed_custom_stat", "open_shulker_box_custom_stat", "open_barrel_custom_stat", "interact_with_blast_furnace_custom_stat",
                "interact_with_smoker_custom_stat", "interact_with_lectern_custom_stat", "interact_with_campfire_custom_stat",
                "interact_with_cartography_table_custom_stat", "interact_with_loom_custom_stat", "interact_with_stonecutter_custom_stat",
                "bell_ring_custom_stat", "raid_trigger_custom_stat", "raid_win_custom_stat", "interact_with_anvil_custom_stat",
                "interact_with_grindstone_custom_stat", "target_hit_custom_stat", "interact_with_smithing_table_custom_stat"
        )));
        add(new MassColumnSqlDataHolder("alternatePaintingsEnabled", "entities", "minecraft:painting", List.of(
                "kebab_painting", "aztec_painting", "alban_painting", "aztec2_painting", "bomb_painting", "plant_painting",
                "wasteland_painting", "pool_painting", "courbet_painting", "sea_painting", "sunset_painting", "creebet_painting",
                "wanderer_painting", "graham_painting", "match_painting", "bust_painting", "stage_painting", "void_painting",
                "skull_and_roses_painting", "wither_painting", "fighters_painting", "pointer_painting", "pigscene_painting",
                "burning_skull_painting", "skeleton_painting", "donkey_kong_painting", "earth_painting", "wind_painting",
                "water_painting", "fire_painting"
        )));
        add(new MassColumnSqlDataHolder("villagerTypesEnabled", "entities", "minecraft:villager", List.of(
                "desert_type", "jungle_type", "plains_type", "savanna_type", "snow_type", "swamp_type", "taiga_type"
        )));
        add(new MassColumnSqlDataHolder("villagerProfessionsEnabled", "entities", "minecraft:villager", List.of(
                "none_profession", "armorer_profession", "butcher_profession", "cartographer_profession", "cleric_profession",
                "farmer_profession", "fisherman_profession", "fletcher_profession", "leatherworker_profession", "librarian_profession",
                "mason_profession", "nitwit_profession", "shepherd_profession", "toolsmith_profession", "weaponsmith_profession"
        )));
    }};
    public static final ObjectList<AllRowSqlDataHolder> allRowSqlDataHolders = new ObjectArrayList<>() {{
        add(new AllRowSqlDataHolder("animalBreeding", "entities", "can_breed"));
        add(new AllRowSqlDataHolder("spawnersEnabled", "entities", "spawner"));
        add(new AllRowSqlDataHolder("commandsEnabled", "commands", "enabled"));
        add(new AllRowSqlDataHolder("advancementsEnabled", "advancements", "enabled"));
        add(new AllRowSqlDataHolder("mobsDropXp", "entities", "can_drop_xp"));
        add(new AllRowSqlDataHolder("blocksDropXp", "blocks", "can_drop_xp"));
    }};
    public static final ObjectList<PropertiesDataHolder> propertiesData = new ObjectArrayList<>() {{
        add(new PropertiesDataHolder("ancientCityGeneration", "structures", "ancient_city"));
        add(new PropertiesDataHolder("bastionRemnantGeneration", "structures", "bastion_remnant"));
        add(new PropertiesDataHolder("buriedTreasureGeneration", "structures", "buried_treasure"));
        add(new PropertiesDataHolder("desertPyramidGeneration", "structures", "desert_pyramid"));
        add(new PropertiesDataHolder("endCityGeneration", "structures", "end_city"));
        add(new PropertiesDataHolder("fortressGeneration", "structures", "fortress"));
        add(new PropertiesDataHolder("iglooGeneration", "structures", "igloo"));
        add(new PropertiesDataHolder("junglePyramidGeneration", "structures", "jungle_pyramid"));
        add(new PropertiesDataHolder("mansionGeneration", "structures", "mansion"));
        add(new PropertiesDataHolder("mineshaftGeneration", "structures", "mineshaft"));
        add(new PropertiesDataHolder("monumentGeneration", "structures", "monument"));
        add(new PropertiesDataHolder("netherFossilGeneration", "structures", "nether_fossil"));
        add(new PropertiesDataHolder("oceanRuinGeneration", "structures", "ocean_ruin"));
        add(new PropertiesDataHolder("pillagerOutpostGeneration", "structures", "pillager_outpost"));
        add(new PropertiesDataHolder("ruinedPortalGeneration", "structures", "ruined_portal"));
        add(new PropertiesDataHolder("shipwreckGeneration", "structures", "shipwreck"));
        add(new PropertiesDataHolder("strongholdGeneration", "structures", "stronghold"));
        add(new PropertiesDataHolder("swampHutGeneration", "structures", "swamp_hut"));
        add(new PropertiesDataHolder("trailRuinsGeneration", "structures", "trail_ruins"));
        add(new PropertiesDataHolder("villageGeneration", "structures", "village"));

        add(new PropertiesDataHolder("badlandsBiome", "biomes", "badlands"));
        add(new PropertiesDataHolder("bambooJungleBiome", "biomes", "bamboo_jungle"));
        add(new PropertiesDataHolder("beachBiome", "biomes", "beach"));
        add(new PropertiesDataHolder("birchForestBiome", "biomes", "birch_forest"));
        add(new PropertiesDataHolder("cherryGroveBiome", "biomes", "cherry_grove"));
        add(new PropertiesDataHolder("coldOceanBiome", "biomes", "cold_ocean"));
        add(new PropertiesDataHolder("darkForestBiome", "biomes", "dark_forest"));
        add(new PropertiesDataHolder("deepColdOceanBiome", "biomes", "deep_cold_ocean"));
        add(new PropertiesDataHolder("deepDarkBiome", "biomes", "deep_dark"));
        add(new PropertiesDataHolder("deepFrozenOceanBiome", "biomes", "deep_frozen_ocean"));
        add(new PropertiesDataHolder("deepLukewarmOceanBiome", "biomes", "deep_lukewarm_ocean"));
        add(new PropertiesDataHolder("deepOceanBiome", "biomes", "deep_ocean"));
        add(new PropertiesDataHolder("desertBiome", "biomes", "desert"));
        add(new PropertiesDataHolder("dripstoneCavesBiome", "biomes", "dripstone_caves"));
        add(new PropertiesDataHolder("erodedBadlandsBiome", "biomes", "eroded_badlands"));
        add(new PropertiesDataHolder("flowerForestBiome", "biomes", "flower_forest"));
        add(new PropertiesDataHolder("forestBiome", "biomes", "forest"));
        add(new PropertiesDataHolder("frozenOceanBiome", "biomes", "frozen_ocean"));
        add(new PropertiesDataHolder("frozenPeaksBiome", "biomes", "frozen_peaks"));
        add(new PropertiesDataHolder("frozenRiverBiome", "biomes", "frozen_river"));
        add(new PropertiesDataHolder("groveBiome", "biomes", "grove"));
        add(new PropertiesDataHolder("iceSpikesBiome", "biomes", "ice_spikes"));
        add(new PropertiesDataHolder("jaggedPeaksBiome", "biomes", "jagged_peaks"));
        add(new PropertiesDataHolder("jungleBiome", "biomes", "jungle"));
        add(new PropertiesDataHolder("lukewarmOceanBiome", "biomes", "lukewarm_ocean"));
        add(new PropertiesDataHolder("lushCavesBiome", "biomes", "lush_caves"));
        add(new PropertiesDataHolder("mangroveSwampBiome", "biomes", "mangrove_swamp"));
        add(new PropertiesDataHolder("meadowBiome", "biomes", "meadow"));
        add(new PropertiesDataHolder("mushroomFieldsBiome", "biomes", "mushroom_fields"));
        add(new PropertiesDataHolder("oceanBiome", "biomes", "ocean"));
        add(new PropertiesDataHolder("oldGrowthBirchForestBiome", "biomes", "old_growth_birch_forest"));
        add(new PropertiesDataHolder("oldGrowthPineTaigaBiome", "biomes", "old_growth_pine_taiga"));
        add(new PropertiesDataHolder("oldGrowthSpruceTaigaBiome", "biomes", "old_growth_spruce_taiga"));
        add(new PropertiesDataHolder("riverBiome", "biomes", "river"));
        add(new PropertiesDataHolder("savannaBiome", "biomes", "savanna"));
        add(new PropertiesDataHolder("savannaPlateauBiome", "biomes", "savanna_plateau"));
        add(new PropertiesDataHolder("snowyBeachBiome", "biomes", "snowy_beach"));
        add(new PropertiesDataHolder("snowyPlainsBiome", "biomes", "snowy_plains"));
        add(new PropertiesDataHolder("snowySlopesBiome", "biomes", "snowy_slopes"));
        add(new PropertiesDataHolder("snowyTaigaBiome", "biomes", "snowy_taiga"));
        add(new PropertiesDataHolder("sparseJungleBiome", "biomes", "sparse_jungle"));
        add(new PropertiesDataHolder("stonyPeaksBiome", "biomes", "stony_peaks"));
        add(new PropertiesDataHolder("stonyShoreBiome", "biomes", "stony_shore"));
        add(new PropertiesDataHolder("sunflowerPlainsBiome", "biomes", "sunflower_plains"));
        add(new PropertiesDataHolder("swampBiome", "biomes", "swamp"));
        add(new PropertiesDataHolder("taigaBiome", "biomes", "taiga"));
        add(new PropertiesDataHolder("warmOceanBiome", "biomes", "warm_ocean"));
        add(new PropertiesDataHolder("windsweptForestBiome", "biomes", "windswept_forest"));
        add(new PropertiesDataHolder("windsweptGravellyHillsBiome", "biomes", "windswept_gravelly_hills"));
        add(new PropertiesDataHolder("windsweptHillsBiome", "biomes", "windswept_hills"));
        add(new PropertiesDataHolder("windsweptSavannaBiome", "biomes", "windswept_savanna"));
        add(new PropertiesDataHolder("woodedBadlandsBiome", "biomes", "wooded_badlands"));
        add(new PropertiesDataHolder("basaltDeltasBiome", "biomes", "basalt_deltas"));
        add(new PropertiesDataHolder("crimsonForestBiome", "biomes", "crimson_forest"));
        add(new PropertiesDataHolder("soulSandValleyBiome", "biomes", "soul_sand_valley"));
        add(new PropertiesDataHolder("warpedForestBiome", "biomes", "warped_forest"));
        add(new PropertiesDataHolder("endBarrensBiome", "biomes", "end_barrens"));
        add(new PropertiesDataHolder("endHighlandsBiome", "biomes", "end_highlands"));
        add(new PropertiesDataHolder("endMidlandsBiome", "biomes", "end_midlands"));
        add(new PropertiesDataHolder("smallEndIslandsBiome", "biomes", "small_end_islands"));
    }};
    public static String biomesEnabled = "";

    public static void updateSql() {
        sqlData.forEach(sqlDataHolder -> {
            if (sqlDataHolder.value != null && !sqlDataHolder.value.isEmpty() && !sqlDataHolder.value.equals(sqlDataHolder.defaultValue)) {
                CommandDataHandler.setMatching(sqlDataHolder.table, sqlDataHolder.column, sqlDataHolder.value, false, sqlDataHolder.pattern);
            }
        });
        massColumnSqlData.forEach(massColumnSqlDataHolder -> {
            if (massColumnSqlDataHolder.value != null && massColumnSqlDataHolder.value.equals("false")) {
                massColumnSqlDataHolder.columns.forEach(column ->
                        CommandDataHandler.setMatching(massColumnSqlDataHolder.table, column, "false", false, massColumnSqlDataHolder.pattern));
            }
        });
        allRowSqlDataHolders.forEach(allRowSqlDataHolder -> {
            if (allRowSqlDataHolder.value != null && allRowSqlDataHolder.value.equals("false")) {
                CommandDataHandler.setAll(allRowSqlDataHolder.table, allRowSqlDataHolder.column, "false", false);
            }
        });
    }

    public static void updateProperties() {
        Object2ObjectMap<String, Boolean> structureMap = new Object2ObjectOpenHashMap<>();
        Object2ObjectMap<String, Boolean> placedFeatureMap = new Object2ObjectOpenHashMap<>();
        Object2ObjectMap<String, Boolean> biomeMap = new Object2ObjectOpenHashMap<>();
        propertiesData.forEach(propertiesDataHolder -> {
            if (propertiesDataHolder.value != null && propertiesDataHolder.value.equals("false")) {
                switch (propertiesDataHolder.table) {
                    case "structures" -> structureMap.put(propertiesDataHolder.attribute, false);
                    case "placed_features" -> placedFeatureMap.put(propertiesDataHolder.attribute, false);
                    case "biomes" -> biomeMap.put(propertiesDataHolder.attribute, false);
                }
            }
        });
        if (biomesEnabled.equals("false")) {
            biomeMap.replaceAll((k, v) -> false);
        }
        WorldgenDataHandler.updateVals(structureMap, placedFeatureMap, biomeMap);
    }

    public static class SqlDataHolder {
        public final String rule;
        public final String table;
        public final String pattern;
        public final String column;
        public final String defaultValue;
        public String value;

        public SqlDataHolder(String rule, String table, String pattern, String column, String defaultValue) {
            this.rule = rule;
            this.table = table;
            this.pattern = pattern;
            this.column = column;
            this.defaultValue = defaultValue;
        }
    }

    public static class MassColumnSqlDataHolder {
        public final String rule;
        public final String table;
        public final String pattern;
        public final List<String> columns;
        public String value;

        public MassColumnSqlDataHolder(String rule, String table, String pattern, List<String> columns) {
            this.rule = rule;
            this.table = table;
            this.pattern = pattern;
            this.columns = columns;
        }
    }

    public static class AllRowSqlDataHolder {
        public final String rule;
        public final String table;
        public final String column;
        public String value;

        public AllRowSqlDataHolder(String rule, String table, String column) {
            this.rule = rule;
            this.table = table;
            this.column = column;
        }
    }

    public static class PropertiesDataHolder {
        public final String rule;
        public final String table;
        public final String attribute;
        public String value;

        public PropertiesDataHolder(String rule, String table, String attribute) {
            this.rule = rule;
            this.table = table;
            this.attribute = attribute;
        }
    }
}
