package uk.debb.vanilla_disable.util.gamerules;

import net.minecraft.world.level.GameRules;

public enum IntegerGamerules {
    MIN_SPAWN_DISTANCE(null, 24, 0, 512),

    MONSTER_MAX_DESPAWN(null, 128, 0, 512),
    CREATURE_MAX_DESPAWN(null, 128, 0, 512),
    AMBIENT_MAX_DESPAWN(null, 128, 0, 512),
    AXOLOTL_MAX_DESPAWN(null, 128, 0, 512),
    GLOWSQUID_MAX_DESPAWN(null, 128, 0, 512),
    WATER_CREATURE_MAX_DESPAWN(null, 128, 0, 512),
    WATER_AMBIENT_MAX_DESPAWN(null, 64, 0, 512),
    MONSTER_MIN_DESPAWN(null, 32, 0, 512),
    CREATURE_MIN_DESPAWN(null, 32, 0, 512),
    AMBIENT_MIN_DESPAWN(null, 32, 0, 512),
    AXOLOTL_MIN_DESPAWN(null, 32, 0, 512),
    GLOWSQUID_MIN_DESPAWN(null, 32, 0, 512),
    WATER_CREATURE_MIN_DESPAWN(null, 32, 0, 512),
    WATER_AMBIENT_MIN_DESPAWN(null, 32, 0, 512),
    ITEM_DESPAWN_TIME(null, 300, 0, Integer.MAX_VALUE),

    MONSTER_MOBCAP(null, 70, 0, Integer.MAX_VALUE),
    CREATURE_MOBCAP(null, 10, 0, Integer.MAX_VALUE),
    AMBIENT_MOBCAP(null, 15, 0, Integer.MAX_VALUE),
    AXOLOTL_MOBCAP(null, 5, 0, Integer.MAX_VALUE),
    GLOWSQUID_MOBCAP(null, 5, 0, Integer.MAX_VALUE),
    WATER_CREATURE_MOBCAP(null, 5, 0, Integer.MAX_VALUE),
    WATER_AMBIENT_MOBCAP(null, 20, 0, Integer.MAX_VALUE),
    MONSTER_MAX_LIGHT_LEVEL(null, 0, 0, 15),

    WATER_FLOW_SPEED(null, 5, 1, 128),
    LAVA_FLOW_SPEED(null, 30, 1, 128),
    LAVA_FLOW_SPEED_NETHER(null, 10, 1, 128),

    VILLAGER_DAILY_RESTOCKS(null, 2, 0, Integer.MAX_VALUE),

    REPEATER_BASE_DELAY(null, 2, 0, Integer.MAX_VALUE),
    REPEATER_SIGNAL(null, 15, 0, 15),
    COMPARATOR_BASE_DELAY(null, 2, 0, Integer.MAX_VALUE),
    WOOD_BUTTON_PRESS_DURATION(null, 30, 0, Integer.MAX_VALUE),
    STONE_BUTTON_PRESS_DURATION(null, 20, 0, Integer.MAX_VALUE),
    OBSERVER_DELAY(null, 2, 0, Integer.MAX_VALUE),
    OBSERVER_DURATION(null, 2, 0, Integer.MAX_VALUE),
    PISTON_PUSH_LIMIT(null, 12, 0, Integer.MAX_VALUE),

    NETHER_PORTAL_COOLDOWN(null, 300, 0, Integer.MAX_VALUE),
    DEFAULT_BLOCK_FRICTION(null, 60, 0, Integer.MAX_VALUE),
    ICE_FRICTION(null, 98, 0, Integer.MAX_VALUE),
    SLIME_FRICTION(null, 80, 0, Integer.MAX_VALUE),
    DEFAULT_BLOCK_SPEED(null, 100, 0, Integer.MAX_VALUE),
    SOUL_SAND_SPEED(null, 40, 0, Integer.MAX_VALUE),
    HONEY_BLOCK_SPEED(null, 40, 0, Integer.MAX_VALUE),
    DEFAULT_BLOCK_JUMP(null, 100, 0, Integer.MAX_VALUE),
    HONEY_BLOCK_JUMP(null, 50, 0, Integer.MAX_VALUE),

    APPLE_NUTRITION(null, 4, 0, 20),
    APPLE_SATURATION(null, 3, 0, 99),
    BAKED_POTATO_NUTRITION(null, 5, 0, 20),
    BAKED_POTATO_SATURATION(null, 6, 0, 99),
    BEEF_NUTRITION(null, 3, 0, 20),
    BEEF_SATURATION(null, 3, 0, 99),
    BEETROOT_NUTRITION(null, 1, 0, 20),
    BEETROOT_SATURATION(null, 6, 0, 99),
    BEETROOT_SOUP_NUTRITION(null, 6, 0, 20),
    BEETROOT_SOUP_SATURATION(null, 6, 0, 99),
    BREAD_NUTRITION(null, 5, 0, 20),
    BREAD_SATURATION(null, 6, 0, 99),
    CARROT_NUTRITION(null, 3, 0, 20),
    CARROT_SATURATION(null, 6, 0, 99),
    CHICKEN_NUTRITION(null, 2, 0, 20),
    CHICKEN_SATURATION(null, 3, 0, 99),
    CHORUS_FRUIT_NUTRITION(null, 4, 0, 20),
    CHORUS_FRUIT_SATURATION(null, 3, 0, 99),
    COD_NUTRITION(null, 2, 0, 20),
    COD_SATURATION(null, 1, 0, 99),
    COOKED_BEEF_NUTRITION(null, 8, 0, 20),
    COOKED_BEEF_SATURATION(null, 8, 0, 99),
    COOKED_CHICKEN_NUTRITION(null, 6, 0, 20),
    COOKED_CHICKEN_SATURATION(null, 6, 0, 99),
    COOKED_COD_NUTRITION(null, 5, 0, 20),
    COOKED_COD_SATURATION(null, 6, 0, 99),
    COOKED_MUTTON_NUTRITION(null, 6, 0, 20),
    COOKED_MUTTON_SATURATION(null, 8, 0, 99),
    COOKED_PORKCHOP_NUTRITION(null, 8, 0, 20),
    COOKED_PORKCHOP_SATURATION(null, 8, 0, 99),
    COOKED_RABBIT_NUTRITION(null, 5, 0, 20),
    COOKED_RABBIT_SATURATION(null, 6, 0, 99),
    COOKED_SALMON_NUTRITION(null, 6, 0, 20),
    COOKED_SALMON_SATURATION(null, 8, 0, 99),
    COOKIE_NUTRITION(null, 2, 0, 20),
    COOKIE_SATURATION(null, 2, 0, 99),
    DRIED_KELP_NUTRITION(null, 1, 0, 20),
    DRIED_KELP_SATURATION(null, 3, 0, 99),
    ENCHANTED_GOLDEN_APPLE_NUTRITION(null, 4, 0, 20),
    ENCHANTED_GOLDEN_APPLE_SATURATION(null, 12, 0, 99),
    GOLDEN_APPLE_NUTRITION(null, 4, 0, 20),
    GOLDEN_APPLE_SATURATION(null, 12, 0, 99),
    GOLDEN_CARROT_NUTRITION(null, 6, 0, 20),
    GOLDEN_CARROT_SATURATION(null, 12, 0, 99),
    HONEY_BOTTLE_NUTRITION(null, 6, 0, 20),
    HONEY_BOTTLE_SATURATION(null, 1, 0, 99),
    MELON_SLICE_NUTRITION(null, 2, 0, 20),
    MELON_SLICE_SATURATION(null, 3, 0, 99),
    MUSHROOM_STEW_NUTRITION(null, 6, 0, 20),
    MUSHROOM_STEW_SATURATION(null, 3, 0, 99),
    MUTTON_NUTRITION(null, 2, 0, 20),
    MUTTON_SATURATION(null, 3, 0, 99),
    POISONOUS_POTATO_NUTRITION(null, 2, 0, 20),
    POISONOUS_POTATO_SATURATION(null, 3, 0, 99),
    PORKCHOP_NUTRITION(null, 3, 0, 20),
    PORKCHOP_SATURATION(null, 3, 0, 99),
    POTATO_NUTRITION(null, 1, 0, 20),
    POTATO_SATURATION(null, 1, 0, 99),
    PUFFERFISH_NUTRITION(null, 1, 0, 20),
    PUFFERFISH_SATURATION(null, 3, 0, 99),
    PUMPKIN_PIE_NUTRITION(null, 8, 0, 20),
    PUMPKIN_PIE_SATURATION(null, 3, 0, 99),
    RABBIT_NUTRITION(null, 3, 0, 20),
    RABBIT_SATURATION(null, 3, 0, 99),
    RABBIT_STEW_NUTRITION(null, 10, 0, 20),
    RABBIT_STEW_SATURATION(null, 6, 0, 99),
    ROTTEN_FLESH_NUTRITION(null, 4, 0, 20),
    ROTTEN_FLESH_SATURATION(null, 1, 0, 99),
    SALMON_NUTRITION(null, 2, 0, 20),
    SALMON_SATURATION(null, 1, 0, 99),
    SPIDER_EYE_NUTRITION(null, 2, 0, 20),
    SPIDER_EYE_SATURATION(null, 8, 0, 99),
    SUSPICIOUS_STEW_NUTRITION(null, 6, 0, 20),
    SUSPICIOUS_STEW_SATURATION(null, 6, 0, 99),
    SWEET_BERRIES_NUTRITION(null, 2, 0, 20),
    SWEET_BERRIES_SATURATION(null, 1, 0, 99),
    GLOW_BERRIES_NUTRITION(null, 2, 0, 20),
    GLOW_BERRIES_SATURATION(null, 1, 0, 99),
    TROPICAL_FISH_NUTRITION(null, 1, 0, 20),
    TROPICAL_FISH_SATURATION(null, 1, 0, 99);

    private final int defaultInt;
    private final int minValue;
    private final int maxValue;
    private GameRules.Key<GameRules.IntegerValue> gameRule;

    IntegerGamerules(GameRules.Key<GameRules.IntegerValue> gameRule, int defaultInt, int minValue, int maxValue) {
        this.gameRule = gameRule;
        this.defaultInt = defaultInt;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public GameRules.Key<GameRules.IntegerValue> getGameRule() {
        return this.gameRule;
    }

    public void setGameRule(GameRules.Key<GameRules.IntegerValue> gameRule) {
        this.gameRule = gameRule;
    }

    public int getDefaultInt() {
        return this.defaultInt;
    }
    public int getMinInt() {
        return this.minValue;
    }
    public int getMaxInt() {
        return this.maxValue;
    }
}
