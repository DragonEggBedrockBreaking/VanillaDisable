package uk.debb.vanilla_disable.gamerules.util.maps;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.GameRules;
import uk.debb.vanilla_disable.gamerules.util.gamerules.GameruleCategories;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

public interface Maps {
    Object2ObjectMap<MobCategory, Gamerules> mobCategoryMobCategoryMapMobcap = new Object2ObjectOpenHashMap<>();
    Object2ObjectMap<Pair<Enchantment, Enchantment>, Gamerules> enchantmentPairEnchantmentMap = new Object2ObjectOpenHashMap<>();
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
