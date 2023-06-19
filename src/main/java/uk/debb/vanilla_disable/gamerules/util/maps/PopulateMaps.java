package uk.debb.vanilla_disable.gamerules.util.maps;

import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import uk.debb.vanilla_disable.gamerules.util.gamerules.GameruleCategories;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

import static uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules.*;

public class PopulateMaps implements Maps {
    public static void populateMaps() {
        mobCategoryMobCategoryMapMobcap.put(MobCategory.MONSTER, MONSTER_MOBCAP);
        mobCategoryMobCategoryMapMobcap.put(MobCategory.CREATURE, CREATURE_MOBCAP);
        mobCategoryMobCategoryMapMobcap.put(MobCategory.AMBIENT, AMBIENT_MOBCAP);
        mobCategoryMobCategoryMapMobcap.put(MobCategory.AXOLOTLS, AXOLOTL_MOBCAP);
        mobCategoryMobCategoryMapMobcap.put(MobCategory.UNDERGROUND_WATER_CREATURE, GLOWSQUID_MOBCAP);
        mobCategoryMobCategoryMapMobcap.put(MobCategory.WATER_AMBIENT, WATER_AMBIENT_MOBCAP);
        mobCategoryMobCategoryMapMobcap.put(MobCategory.WATER_CREATURE, WATER_CREATURE_MOBCAP);

        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.FROST_WALKER, Enchantments.DEPTH_STRIDER), BOOT_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.INFINITY_ARROWS, Enchantments.MENDING), BOW_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.MULTISHOT, Enchantments.PIERCING), CROSSBOW_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.SHARPNESS, Enchantments.SMITE), DAMAGE_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.SHARPNESS, Enchantments.BANE_OF_ARTHROPODS), DAMAGE_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS), DAMAGE_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.BLOCK_FORTUNE, Enchantments.SILK_TOUCH), MINING_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.BLAST_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.PROJECTILE_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.FALL_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.FIRE_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.BLAST_PROTECTION, Enchantments.PROJECTILE_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.BLAST_PROTECTION, Enchantments.FALL_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.BLAST_PROTECTION, Enchantments.FIRE_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.PROJECTILE_PROTECTION, Enchantments.FALL_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.PROJECTILE_PROTECTION, Enchantments.FIRE_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.FALL_PROTECTION, Enchantments.FIRE_PROTECTION), PROTECTION_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.RIPTIDE, Enchantments.LOYALTY), TRIDENT_ENCHANTMENT_CONFLICTS);
        enchantmentPairEnchantmentMap.put(new ObjectObjectImmutablePair<>(Enchantments.RIPTIDE, Enchantments.CHANNELING), TRIDENT_ENCHANTMENT_CONFLICTS);

        for (GameruleCategories category : GameruleCategories.values()) {
            stringToVanillaDisableGameruleCategoryMap.put(category.toString().toLowerCase(), category);
        }

        for (Gamerules gamerule : Gamerules.values()) {
            switch (gamerule.getType()) {
                case "boolean" -> stringToDefaultBooleanMap.put(gamerule.getRuleName(), gamerule.getDefaultBoolean());
                case "integer" -> stringToDefaultIntMap.put(gamerule.getRuleName(), gamerule.getDefaultInt());
                case "double" -> stringToDefaultDoubleMap.put(gamerule.getRuleName(), gamerule.getDefaultDouble());
            }
        }
        stringToDefaultBooleanMap.put("doFireTick", true);
        stringToDefaultBooleanMap.put("mobGriefing", true);
        stringToDefaultBooleanMap.put("keepInventory", false);
        stringToDefaultBooleanMap.put("doMobSpawning", true);
        stringToDefaultBooleanMap.put("doMobLoot", true);
        stringToDefaultBooleanMap.put("doTileDrops", true);
        stringToDefaultBooleanMap.put("doEntityDrops", true);
        stringToDefaultBooleanMap.put("commandBlockOutput", true);
        stringToDefaultBooleanMap.put("naturalRegeneration", true);
        stringToDefaultBooleanMap.put("doDaylightCycle", true);
        stringToDefaultBooleanMap.put("logAdminCommands", true);
        stringToDefaultBooleanMap.put("showDeathMessages", true);
        stringToDefaultBooleanMap.put("sendCommandFeedback", true);
        stringToDefaultBooleanMap.put("reducedDebugInfo", false);
        stringToDefaultBooleanMap.put("spectatorsGenerateChunks", true);
        stringToDefaultBooleanMap.put("disableElytraMovementCheck", false);
        stringToDefaultBooleanMap.put("doWeatherCycle", true);
        stringToDefaultBooleanMap.put("doLimitedCrafting", false);
        stringToDefaultBooleanMap.put("announceAdvancements", true);
        stringToDefaultBooleanMap.put("disableRaids", false);
        stringToDefaultBooleanMap.put("doInsomnia", true);
        stringToDefaultBooleanMap.put("doImmediateRespawn", false);
        stringToDefaultBooleanMap.put("drowningDamage", true);
        stringToDefaultBooleanMap.put("fallDamage", true);
        stringToDefaultBooleanMap.put("fireDamage", true);
        stringToDefaultBooleanMap.put("freezeDamage", true);
        stringToDefaultBooleanMap.put("doPatrolSpawning", true);
        stringToDefaultBooleanMap.put("doTraderSpawning", true);
        stringToDefaultBooleanMap.put("doWardenSpawning", true);
        stringToDefaultBooleanMap.put("forgiveDeadPlayers", true);
        stringToDefaultBooleanMap.put("universalAnger", false);
        stringToDefaultIntMap.put("randomTickSpeed", 3);
        stringToDefaultIntMap.put("spawnRadius", 10);
        stringToDefaultIntMap.put("maxEntityCramming", 24);
        stringToDefaultIntMap.put("maxCommandChainLength", 65536);
        stringToDefaultIntMap.put("playersSleepingPercentage", 100);

        for (Gamerules gamerule : Gamerules.values()) {
            switch (gamerule.getType()) {
                case "integer" -> {
                    stringToMinIntMap.put(gamerule.getRuleName(), gamerule.getMinInt());
                    stringToMaxIntMap.put(gamerule.getRuleName(), gamerule.getMaxInt());
                }
                case "double" -> {
                    stringToMinDoubleMap.put(gamerule.getRuleName(), 0.0);
                    stringToMaxDoubleMap.put(gamerule.getRuleName(), gamerule.getMaxDouble());
                }
            }
        }

        stringToVanillaGameruleCategoryMap.put("vanilla_player", GameRules.Category.PLAYER);
        stringToVanillaGameruleCategoryMap.put("vanilla_mobs", GameRules.Category.MOBS);
        stringToVanillaGameruleCategoryMap.put("vanilla_spawning", GameRules.Category.SPAWNING);
        stringToVanillaGameruleCategoryMap.put("vanilla_drops", GameRules.Category.DROPS);
        stringToVanillaGameruleCategoryMap.put("vanilla_updates", GameRules.Category.UPDATES);
        stringToVanillaGameruleCategoryMap.put("vanilla_chat", GameRules.Category.CHAT);
        stringToVanillaGameruleCategoryMap.put("vanilla_misc", GameRules.Category.MISC);
    }
}