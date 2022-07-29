package uk.debb.vanilla_disable.util.gamerules;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import static uk.debb.vanilla_disable.mixin_plugins.CaffeineConfigMixinConfigPlugin.*;
import static uk.debb.vanilla_disable.util.gamerules.GameruleCategories.*;

public class RegisterGamerules {
    private static final Object2BooleanMap<GameruleCategories> categoryMap = new Object2BooleanOpenHashMap<>();
    private static final Object2BooleanMap<BooleanGamerules> gameruleMap = new Object2BooleanOpenHashMap<>();

    private static void addOptionsToMaps() {
        categoryMap.put(VD_AI, ai);
        categoryMap.put(VD_ADVANCEMENT, advancement);
        categoryMap.put(VD_ARROW, arrow);
        categoryMap.put(VD_BLOCKS, blocks);
        categoryMap.put(VD_COMMANDS, commands);
        categoryMap.put(VD_DAMAGE, damage);
        categoryMap.put(VD_DEATH, death);
        categoryMap.put(VD_DESPAWNING, despawning);
        categoryMap.put(VD_DISPENSER, dispenser);
        categoryMap.put(VD_EFFECTS, effects);
        categoryMap.put(VD_ENCHANTMENTS, enchantments);
        categoryMap.put(VD_FLUIDS, fluids);
        categoryMap.put(VD_FOOD, food);
        categoryMap.put(VD_ITEMS, items);
        categoryMap.put(VD_KNOCKBACK, knockback);
        categoryMap.put(VD_MISC, misc);
        categoryMap.put(VD_MOBS, mob);
        categoryMap.put(VD_MOB_TOGGLES, mob_toggles);
        categoryMap.put(VD_PLAYER, player);
        categoryMap.put(VD_POTIONS, potions);
        categoryMap.put(VD_REDSTONE, redstone);
        categoryMap.put(VD_SPAWN_LIMITS, spawn_limits);
        categoryMap.put(VD_SPAWNING, spawning);
        categoryMap.put(VD_WORLDGEN, worldgen);

        gameruleMap.put(BooleanGamerules.CONTAINER_OPENING_BLOCKED, blocks_container);
        gameruleMap.put(BooleanGamerules.BOOT_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
        gameruleMap.put(BooleanGamerules.BOW_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
        gameruleMap.put(BooleanGamerules.CROSSBOW_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
        gameruleMap.put(BooleanGamerules.DAMAGE_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
        gameruleMap.put(BooleanGamerules.MINING_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
        gameruleMap.put(BooleanGamerules.PROTECTION_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
        gameruleMap.put(BooleanGamerules.TRIDENT_ENCHANTMENT_CONFLICTS, enchantment_conflicts);
    }
    public static void registerGamerules() {
        addOptionsToMaps();
        for (BooleanGamerules rule : BooleanGamerules.values()) {
            if (categoryMap.getBoolean(rule.getCategory()) &&
                    gameruleMap.getOrDefault(rule.getGameRule(), true)) {
                GameruleHelper.register(rule);
            }
        }
        for (IntegerGamerules rule : IntegerGamerules.values()) {
            if (categoryMap.getBoolean(rule.getCategory()) &&
                    gameruleMap.getOrDefault(rule.getGameRule(), true)) {
                GameruleHelper.register(rule);
            }
        }
    }
}