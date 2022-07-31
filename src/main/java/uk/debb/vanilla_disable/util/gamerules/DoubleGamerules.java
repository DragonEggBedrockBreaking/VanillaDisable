package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.world.level.GameRules;

import static uk.debb.vanilla_disable.util.gamerules.GameruleCategories.*;

public enum DoubleGamerules {
    DEFAULT_BLOCK_FRICTION(VD_BLOCKS, 0.6),
    ICE_FRICTION(VD_BLOCKS, 0.98),
    SLIME_FRICTION(VD_BLOCKS, 0.8),
    DEFAULT_BLOCK_SPEED(VD_BLOCKS, 1.0),
    SOUL_SAND_SPEED(VD_BLOCKS, 0.4),
    HONEY_BLOCK_SPEED(VD_BLOCKS, 0.4),
    DEFAULT_BLOCK_JUMP(VD_BLOCKS, 1.0),
    HONEY_BLOCK_JUMP(VD_BLOCKS, 0.5),

    APPLE_SATURATION(VD_FOOD, 0.3, 9.9),
    BAKED_POTATO_SATURATION(VD_FOOD, 0.6, 9.9),
    BEEF_SATURATION(VD_FOOD, 0.3, 9.9),
    BEETROOT_SATURATION(VD_FOOD, 0.6, 9.9),
    BEETROOT_SOUP_SATURATION(VD_FOOD, 0.6, 9.9),
    BREAD_SATURATION(VD_FOOD, 0.6, 9.9),
    CARROT_SATURATION(VD_FOOD, 0.6, 9.9),
    CHICKEN_SATURATION(VD_FOOD, 0.3, 9.9),
    CHORUS_FRUIT_SATURATION(VD_FOOD, 0.3, 9.9),
    COD_SATURATION(VD_FOOD, 0.1, 9.9),
    COOKED_BEEF_SATURATION(VD_FOOD, 0.8, 9.9),
    COOKED_CHICKEN_SATURATION(VD_FOOD, 0.6, 9.9),
    COOKED_COD_SATURATION(VD_FOOD, 0.6, 9.9),
    COOKED_MUTTON_SATURATION(VD_FOOD, 0.8, 9.9),
    COOKED_PORKCHOP_SATURATION(VD_FOOD, 0.8, 9.9),
    COOKED_RABBIT_SATURATION(VD_FOOD, 0.6, 9.9),
    COOKED_SALMON_SATURATION(VD_FOOD, 0.8, 9.9),
    COOKIE_SATURATION(VD_FOOD, 0.2, 9.9),
    DRIED_KELP_SATURATION(VD_FOOD, 0.3, 9.9),
    ENCHANTED_GOLDEN_APPLE_SATURATION(VD_FOOD, 0.12, 9.9),
    GOLDEN_APPLE_SATURATION(VD_FOOD, 0.12, 9.9),
    GOLDEN_CARROT_SATURATION(VD_FOOD, 0.12, 9.9),
    HONEY_BOTTLE_SATURATION(VD_FOOD, 0.1, 9.9),
    MELON_SLICE_SATURATION(VD_FOOD, 0.3, 9.9),
    MUSHROOM_STEW_SATURATION(VD_FOOD, 0.3, 9.9),
    MUTTON_SATURATION(VD_FOOD, 0.3, 9.9),
    POISONOUS_POTATO_SATURATION(VD_FOOD, 0.3, 9.9),
    PORKCHOP_SATURATION(VD_FOOD, 0.3, 9.9),
    POTATO_SATURATION(VD_FOOD, 0.1, 9.9),
    PUFFERFISH_SATURATION(VD_FOOD, 0.3, 9.9),
    PUMPKIN_PIE_SATURATION(VD_FOOD, 0.3, 9.9),
    RABBIT_SATURATION(VD_FOOD, 0.3, 9.9),
    RABBIT_STEW_SATURATION(VD_FOOD, 0.6, 9.9),
    ROTTEN_FLESH_SATURATION(VD_FOOD, 0.1, 9.9),
    SALMON_SATURATION(VD_FOOD, 0.1, 9.9),
    SPIDER_EYE_SATURATION(VD_FOOD, 0.8, 9.9),
    SUSPICIOUS_STEW_SATURATION(VD_FOOD, 0.6, 9.9),
    SWEET_BERRIES_SATURATION(VD_FOOD, 0.1, 9.9),
    GLOW_BERRIES_SATURATION(VD_FOOD, 0.1, 9.9),
    TROPICAL_FISH_SATURATION(VD_FOOD, 0.1, 9.9);

    private final double defaultDouble;
    private final double maxValue;
    private final GameruleCategories category;
    private GameRules.Key<DoubleRule> gameRule;

    DoubleGamerules(GameruleCategories category, double defaultDouble, double maxValue) {
        this.category = category;
        this.defaultDouble = defaultDouble;
        this.maxValue = maxValue;
    }

    DoubleGamerules(GameruleCategories category, double defaultDouble) {
        this.category = category;
        this.defaultDouble = defaultDouble;
        this.maxValue = Integer.MAX_VALUE;
    }

    public GameRules.Key<DoubleRule> getGameRule() {
        return this.gameRule;
    }

    public void setGameRule(GameRules.Key<DoubleRule> gameRule) {
        this.gameRule = gameRule;
    }

    public GameruleCategories getCategory() {
        return this.category;
    }

    public double getDefaultDouble() {
        return this.defaultDouble;
    }

    public double getMinDouble() {
        return 0.0;
    }

    public double getMaxDouble() {
        return this.maxValue;
    }
}