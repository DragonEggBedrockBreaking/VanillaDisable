package uk.debb.vanilla_disable.util.gamerules;

import net.minecraft.world.level.GameRules;

import static uk.debb.vanilla_disable.util.gamerules.GameruleCategories.*;

public enum IntegerGamerules {
    MIN_SPAWN_DISTANCE(VD_DESPAWNING, 24, 0, 512),
    MONSTER_MAX_DESPAWN(VD_DESPAWNING, 128, 0, 512),
    CREATURE_MAX_DESPAWN(VD_DESPAWNING, 128, 0, 512),
    AMBIENT_MAX_DESPAWN(VD_DESPAWNING, 128, 0, 512),
    AXOLOTL_MAX_DESPAWN(VD_DESPAWNING, 128, 0, 512),
    GLOWSQUID_MAX_DESPAWN(VD_DESPAWNING, 128, 0, 512),
    WATER_CREATURE_MAX_DESPAWN(VD_DESPAWNING, 128, 0, 512),
    WATER_AMBIENT_MAX_DESPAWN(VD_DESPAWNING, 64, 0, 512),
    MONSTER_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    CREATURE_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    AMBIENT_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    AXOLOTL_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    GLOWSQUID_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    WATER_CREATURE_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    WATER_AMBIENT_MIN_DESPAWN(VD_DESPAWNING, 32, 0, 512),
    ITEM_DESPAWN_TIME(VD_DESPAWNING, 300),

    MONSTER_MOBCAP(VD_SPAWN_LIMITS, 70),
    CREATURE_MOBCAP(VD_SPAWN_LIMITS, 10),
    AMBIENT_MOBCAP(VD_SPAWN_LIMITS, 15),
    AXOLOTL_MOBCAP(VD_SPAWN_LIMITS, 5),
    GLOWSQUID_MOBCAP(VD_SPAWN_LIMITS, 5),
    WATER_CREATURE_MOBCAP(VD_SPAWN_LIMITS, 5),
    WATER_AMBIENT_MOBCAP(VD_SPAWN_LIMITS, 20),
    MONSTER_MAX_LIGHT_LEVEL(VD_SPAWN_LIMITS, 0, 0, 15),

    WATER_FLOW_SPEED(VD_FLUIDS, 5, 1, 128),
    LAVA_FLOW_SPEED(VD_FLUIDS, 30, 1, 128),
    LAVA_FLOW_SPEED_NETHER(VD_FLUIDS, 10, 1, 128),

    REPEATER_BASE_DELAY(VD_REDSTONE, 2),
    REPEATER_SIGNAL(VD_REDSTONE, 15, 0, 15),
    COMPARATOR_BASE_DELAY(VD_REDSTONE, 2),
    WOOD_BUTTON_PRESS_DURATION(VD_REDSTONE, 30),
    STONE_BUTTON_PRESS_DURATION(VD_REDSTONE, 20),
    OBSERVER_DELAY(VD_REDSTONE, 2),
    OBSERVER_DURATION(VD_REDSTONE, 2),
    PISTON_PUSH_LIMIT(VD_REDSTONE, 12),

    NETHER_PORTAL_COOLDOWN(VD_BLOCKS, 300),

    APPLE_NUTRITION(VD_FOOD, 4, 0, 20),
    BAKED_POTATO_NUTRITION(VD_FOOD, 5, 0, 20),
    BEEF_NUTRITION(VD_FOOD, 3, 0, 20),
    BEETROOT_NUTRITION(VD_FOOD, 1, 0, 20),
    BEETROOT_SOUP_NUTRITION(VD_FOOD, 6, 0, 20),
    BREAD_NUTRITION(VD_FOOD, 5, 0, 20),
    CARROT_NUTRITION(VD_FOOD, 3, 0, 20),
    CHICKEN_NUTRITION(VD_FOOD, 2, 0, 20),
    CHORUS_FRUIT_NUTRITION(VD_FOOD, 4, 0, 20),
    COD_NUTRITION(VD_FOOD, 2, 0, 20),
    COOKED_BEEF_NUTRITION(VD_FOOD, 8, 0, 20),
    COOKED_CHICKEN_NUTRITION(VD_FOOD, 6, 0, 20),
    COOKED_COD_NUTRITION(VD_FOOD, 5, 0, 20),
    COOKED_MUTTON_NUTRITION(VD_FOOD, 6, 0, 20),
    COOKED_PORKCHOP_NUTRITION(VD_FOOD, 8, 0, 20),
    COOKED_RABBIT_NUTRITION(VD_FOOD, 5, 0, 20),
    COOKED_SALMON_NUTRITION(VD_FOOD, 6, 0, 20),
    COOKIE_NUTRITION(VD_FOOD, 2, 0, 20),
    DRIED_KELP_NUTRITION(VD_FOOD, 1, 0, 20),
    ENCHANTED_GOLDEN_APPLE_NUTRITION(VD_FOOD, 4, 0, 20),
    GOLDEN_APPLE_NUTRITION(VD_FOOD, 4, 0, 20),
    GOLDEN_CARROT_NUTRITION(VD_FOOD, 6, 0, 20),
    HONEY_BOTTLE_NUTRITION(VD_FOOD, 6, 0, 20),
    MELON_SLICE_NUTRITION(VD_FOOD, 2, 0, 20),
    MUSHROOM_STEW_NUTRITION(VD_FOOD, 6, 0, 20),
    MUTTON_NUTRITION(VD_FOOD, 2, 0, 20),
    POISONOUS_POTATO_NUTRITION(VD_FOOD, 2, 0, 20),
    PORKCHOP_NUTRITION(VD_FOOD, 3, 0, 20),
    POTATO_NUTRITION(VD_FOOD, 1, 0, 20),
    PUFFERFISH_NUTRITION(VD_FOOD, 1, 0, 20),
    PUMPKIN_PIE_NUTRITION(VD_FOOD, 8, 0, 20),
    RABBIT_NUTRITION(VD_FOOD, 3, 0, 20),
    RABBIT_STEW_NUTRITION(VD_FOOD, 10, 0, 20),
    ROTTEN_FLESH_NUTRITION(VD_FOOD, 4, 0, 20),
    SALMON_NUTRITION(VD_FOOD, 2, 0, 20),
    SPIDER_EYE_NUTRITION(VD_FOOD, 2, 0, 20),
    SUSPICIOUS_STEW_NUTRITION(VD_FOOD, 6, 0, 20),
    SWEET_BERRIES_NUTRITION(VD_FOOD, 2, 0, 20),
    GLOW_BERRIES_NUTRITION(VD_FOOD, 2, 0, 20),
    TROPICAL_FISH_NUTRITION(VD_FOOD, 1, 0, 20),

    VILLAGER_DAILY_RESTOCKS(VD_MOBS, 2);

    private final int defaultInt;
    private final int minValue;
    private final int maxValue;
    private final GameruleCategories category;
    private GameRules.Key<GameRules.IntegerValue> gameRule;

    IntegerGamerules(GameruleCategories category, int defaultInt, int minValue, int maxValue) {
        this.category = category;
        this.defaultInt = defaultInt;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    IntegerGamerules(GameruleCategories category, int defaultInt) {
        this.category = category;
        this.defaultInt = defaultInt;
        this.minValue = 0;
        this.maxValue = Integer.MAX_VALUE;
    }

    public GameRules.Key<GameRules.IntegerValue> getGameRule() {
        return this.gameRule;
    }

    public void setGameRule(GameRules.Key<GameRules.IntegerValue> gameRule) {
        this.gameRule = gameRule;
    }

    public GameruleCategories getCategory() {
        return this.category;
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
