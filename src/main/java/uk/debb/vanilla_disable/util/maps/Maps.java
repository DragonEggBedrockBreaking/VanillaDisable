package uk.debb.vanilla_disable.util.maps;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

public interface Maps {
    Object2ObjectMap<MobEffect, BooleanGamerules> livingEntityMobEffectMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, BooleanGamerules> biomeGenerationSettingsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Item, BooleanGamerules> dispenserBlockItemMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Potion, BooleanGamerules> arrowPotionMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> goalClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> structureCheckClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Potion, BooleanGamerules> potionUtilsPotionMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> itemStackClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, BooleanGamerules> serverPlayerStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, BooleanGamerules> commandsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, BooleanGamerules> commandsStringMapDedicated = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> baseSpawnerClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, BooleanGamerules> naturalSpawnerMobCategoryMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<DamageSource, BooleanGamerules> playerDamageSourceMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<FoodProperties, IntegerGamerules> foodDataFoodPropertiesMapNutrition = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<FoodProperties, IntegerGamerules> foodDataFoodPropertiesMapSaturation = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> livingEntityClassMapKnockback = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> mobClassMapDespawn = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, IntegerGamerules> mobCategoryMobCategoryMapMax = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, IntegerGamerules> mobCategoryMobCategoryMapMin = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, IntegerGamerules> mobCategoryMobCategoryMapMobcap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Enchantment, BooleanGamerules> enchantmentHelperEnchantmentMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, BooleanGamerules> mobClassMapToggle = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, BooleanGamerules> playerAdvancementsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Item, BooleanGamerules> abstractCauldronBlockItemMap = new Object2ObjectOpenHashMap<>();
}
