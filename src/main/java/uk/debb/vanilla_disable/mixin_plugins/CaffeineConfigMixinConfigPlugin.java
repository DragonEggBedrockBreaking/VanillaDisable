package uk.debb.vanilla_disable.mixin_plugins;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import org.quiltmc.loader.api.QuiltLoader;

public class CaffeineConfigMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {
    public static boolean ai;
    public static boolean blocks;
    public static boolean blocks_container;
    public static boolean commands;
    public static boolean damage;
    public static boolean death;
    public static boolean despawning;
    public static boolean effects;
    public static boolean enchantments;
    public static boolean enchantment_conflicts;
    public static boolean fluids;
    public static boolean food;
    public static boolean food_hunger;
    public static boolean knockback;
    public static boolean misc;
    public static boolean mob;
    public static boolean mob_toggles;
    public static boolean player;
    public static boolean potions;
    public static boolean redstone;
    public static boolean spawn_limits;
    public static boolean spawning;
    public static boolean worldgen;
    private CaffeineConfig caffeineConfig;

    @Override
    protected CaffeineConfig createConfig() {
        CaffeineConfig localCaffeineConfig = CaffeineConfig.builder("VanillaDisable")
                .addMixinOption("ai", true)
                .addMixinOption("blocks", true)
                .addMixinOption("blocks.container", true)
                .addMixinOption("commands", true)
                .addMixinOption("damage", true)
                .addMixinOption("death", true)
                .addMixinOption("despawning", true)
                .addMixinOption("effects", true)
                .addMixinOption("enchantments", true)
                .addMixinOption("enchantments.enchantment_conflicts", true)
                .addMixinOption("fluids", true)
                .addMixinOption("food", true)
                .addMixinOption("food.hunger", true)
                .addMixinOption("knockback", true)
                .addMixinOption("misc", true)
                .addMixinOption("mob", true)
                .addMixinOption("mob_toggles", true)
                .addMixinOption("player", true)
                .addMixinOption("potions", true)
                .addMixinOption("redstone", true)
                .addMixinOption("spawn_limits", true)
                .addMixinOption("spawning", true)
                .addMixinOption("worldgen", true)
                .withInfoUrl("https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/Mixin-Configuration-File")
                .build(QuiltLoader.getConfigDir().resolve("vanilla-disable-mixin.properties"));

        this.caffeineConfig = localCaffeineConfig;
        updateData();
        return localCaffeineConfig;
    }

    private void updateData() {
        ai = this.caffeineConfig.getEffectiveOptionForMixin("ai.MixinGoal").isEnabled();
        blocks = this.caffeineConfig.getEffectiveOptionForMixin("blocks.MixinBeaconBlockEntity").isEnabled();
        blocks_container = this.caffeineConfig.getEffectiveOptionForMixin("blocks.container.MixinChestBlock").isEnabled();
        commands = this.caffeineConfig.getEffectiveOptionForMixin("commands.MixinCommands").isEnabled();
        damage = this.caffeineConfig.getEffectiveOptionForMixin("damage.MixinPlayer").isEnabled();
        death = this.caffeineConfig.getEffectiveOptionForMixin("death.MixinPlayer").isEnabled();
        despawning = this.caffeineConfig.getEffectiveOptionForMixin("despawning.MixinItemEntity").isEnabled();
        effects = this.caffeineConfig.getEffectiveOptionForMixin("effects.MixinLivingEntity").isEnabled();
        enchantments = this.caffeineConfig.getEffectiveOptionForMixin("enchantments.MixinEnchantmentHelper").isEnabled();
        enchantment_conflicts = this.caffeineConfig.getEffectiveOptionForMixin("enchantments.enchantment_conflicts.MixinDamageEnchantment").isEnabled();
        fluids = this.caffeineConfig.getEffectiveOptionForMixin("fluids.MixinBucketItem").isEnabled();
        food = this.caffeineConfig.getEffectiveOptionForMixin("food.MixinFoodData").isEnabled();
        food_hunger = this.caffeineConfig.getEffectiveOptionForMixin("food.hunger.MixinFoodData").isEnabled();
        knockback = this.caffeineConfig.getEffectiveOptionForMixin("knockback.MixinLivingEntity").isEnabled();
        misc = this.caffeineConfig.getEffectiveOptionForMixin("misc.MixinBowItem").isEnabled();
        mob = this.caffeineConfig.getEffectiveOptionForMixin("mob.MixinAbstractPiglin").isEnabled();
        mob_toggles = this.caffeineConfig.getEffectiveOptionForMixin("mob_toggles.MixinMob").isEnabled();
        player = this.caffeineConfig.getEffectiveOptionForMixin("player.MixinEntity").isEnabled();
        potions = this.caffeineConfig.getEffectiveOptionForMixin("potions.MixinPotionUtils").isEnabled();
        redstone = this.caffeineConfig.getEffectiveOptionForMixin("redstone.MixinButtonBlock").isEnabled();
        spawn_limits = this.caffeineConfig.getEffectiveOptionForMixin("spawn_limits.MixinMobCategory").isEnabled();
        spawning = this.caffeineConfig.getEffectiveOptionForMixin("spawning.MixinBaseSpawner").isEnabled();
        worldgen = this.caffeineConfig.getEffectiveOptionForMixin("worldgen.MixinBiomeGenerationSettings").isEnabled();
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.mixin.";
    }
}