package uk.debb.vanilla_disable.gamerules.util.maps;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.StructureType;
import uk.debb.vanilla_disable.gamerules.util.gamerules.GameruleCategories;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

public interface Maps {
    Object2ObjectMap<String, Gamerules> biomeGenerationSettingsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<StructureType<?>, Gamerules> structureCheckStructureTypeMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, Gamerules> structureCheckStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, Gamerules> commandsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, Gamerules> commandsStringMapDedicated = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<MobCategory, Gamerules> mobCategoryMobCategoryMapMobcap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, Gamerules> playerAdvancementsStringMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Pair<Enchantment, Enchantment>, Gamerules> enchantmentPairEnchantmentMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Holder<Biome>, Gamerules> biomeSourceBiomeHolderMap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<String, GameruleCategories> stringToVanillaDisableGameruleCategoryMap = new Object2ObjectOpenHashMap<>();
    Object2BooleanMap<String> stringToDefaultBooleanMap = new Object2BooleanOpenHashMap<>();
    Object2IntMap<String> stringToDefaultIntMap = new Object2IntOpenHashMap<>();
    Object2DoubleMap<String> stringToDefaultDoubleMap = new Object2DoubleOpenHashMap<>();
    Object2IntMap<String> stringToMinIntMap = new Object2IntOpenHashMap<>();
    Object2IntMap<String> stringToMaxIntMap = new Object2IntOpenHashMap<>();
    Object2DoubleMap<String> stringToMinDoubleMap = new Object2DoubleOpenHashMap<>();
    Object2DoubleMap<String> stringToMaxDoubleMap = new Object2DoubleOpenHashMap<>();
    Object2ObjectMap<String, GameRules.Category> stringToVanillaGameruleCategoryMap = new Object2ObjectOpenHashMap<>();
}
