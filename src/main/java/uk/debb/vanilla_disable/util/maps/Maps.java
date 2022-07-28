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
import net.minecraft.world.level.GameRules;

public interface Maps {
    Object2ObjectMap<MobEffect, GameRules.Key<GameRules.BooleanValue>> livingEntityMobEffectMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> biomeGenerationSettingsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Item, GameRules.Key<GameRules.BooleanValue>> dispenserBlockItemMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Potion, GameRules.Key<GameRules.BooleanValue>> arrowPotionMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> goalClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> structureCheckClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Potion, GameRules.Key<GameRules.BooleanValue>> potionUtilsPotionMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> itemStackClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> serverPlayerStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> commandsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> commandsStringMapDedicated = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> baseSpawnerClassMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, GameRules.Key<GameRules.BooleanValue>> naturalSpawnerMobCategoryMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<DamageSource, GameRules.Key<GameRules.BooleanValue>> playerDamageSourceMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<FoodProperties, GameRules.Key<GameRules.IntegerValue>> foodDataFoodPropertiesMapNutrition = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<FoodProperties, GameRules.Key<GameRules.IntegerValue>> foodDataFoodPropertiesMapSaturation = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> livingEntityClassMapKnockback = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> mobClassMapDespawn = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, GameRules.Key<GameRules.IntegerValue>> mobCategoryMobCategoryMapMax = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, GameRules.Key<GameRules.IntegerValue>> mobCategoryMobCategoryMapMin = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, GameRules.Key<GameRules.IntegerValue>> mobCategoryMobCategoryMapMobcap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Enchantment, GameRules.Key<GameRules.BooleanValue>> enchantmentHelperEnchantmentMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> mobClassMapToggle = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> playerAdvancementsStringMap = new Object2ObjectOpenHashMap<>();
}
