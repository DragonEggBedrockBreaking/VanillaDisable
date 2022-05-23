package uk.debb.vanilla_disable.mixin;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import org.quiltmc.loader.api.QuiltLoader;

public class VanillaDisableMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {
    private CaffeineConfig caffeineConfig;

    public static boolean commands;
    public static boolean damage;
    public static boolean despawning;
    public static boolean effects;
    public static boolean enchantments;
    public static boolean enchantment_conflicts;
    public static boolean fluids;
    public static boolean knockback;
    public static boolean misc;
    public static boolean misc_container;
    public static boolean misc_hunger;
    public static boolean mob;
    public static boolean player;
    public static boolean redstone;
    public static boolean spawn_limits;
    public static boolean spawning;
    public static boolean worldgen;

    @Override
    protected CaffeineConfig createConfig() {
        CaffeineConfig localCaffeineConfig = CaffeineConfig.builder("VanillaDisable")
            .addMixinOption("commands", true)
            .addMixinOption("damage", true)
            .addMixinOption("despawning", true)
            .addMixinOption("effects", true)
            .addMixinOption("enchantments", true)
            .addMixinOption("enchantments.enchantment_conflicts", true)
            .addMixinOption("fluids", true)
            .addMixinOption("knockback", true)
            .addMixinOption("misc", true)
            .addMixinOption("misc.container", true)
            .addMixinOption("misc.hunger", true)
            .addMixinOption("mob", true)
            .addMixinOption("player", true)
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
        commands              =  this.caffeineConfig.getEffectiveOptionForMixin("commands.MixinCommands").isEnabled();
        damage                =  this.caffeineConfig.getEffectiveOptionForMixin("damage.MixinPlayer").isEnabled();
        despawning            =  this.caffeineConfig.getEffectiveOptionForMixin("despawning.MixinItemEntity").isEnabled();
        effects               =  this.caffeineConfig.getEffectiveOptionForMixin("effects.MixinLivingEntity").isEnabled();
        enchantments          =  this.caffeineConfig.getEffectiveOptionForMixin("enchantments.MixinEnchantmentHelper").isEnabled();
        enchantment_conflicts =  this.caffeineConfig.getEffectiveOptionForMixin("enchantments.enchantment_conflicts.MixinDamageEnchantment").isEnabled();
        fluids                =  this.caffeineConfig.getEffectiveOptionForMixin("fluids.MixinBucketItem").isEnabled();
        knockback             =  this.caffeineConfig.getEffectiveOptionForMixin("knockback.MixinLivingEntity").isEnabled();
        misc                  =  this.caffeineConfig.getEffectiveOptionForMixin("misc.MixinBeaconBlockEntity").isEnabled();
        misc_container       =  this.caffeineConfig.getEffectiveOptionForMixin("misc.container.MixinChestBlock").isEnabled();
        misc_hunger           =  this.caffeineConfig.getEffectiveOptionForMixin("misc.hunger.MixinFoodData").isEnabled();
        mob                   =  this.caffeineConfig.getEffectiveOptionForMixin("mob.MixinAbstractPiglin").isEnabled();
        player                =  this.caffeineConfig.getEffectiveOptionForMixin("player.MixinEntity").isEnabled();
        redstone              =  this.caffeineConfig.getEffectiveOptionForMixin("redstone.MixinButtonBlock").isEnabled();
        spawn_limits          =  this.caffeineConfig.getEffectiveOptionForMixin("spawn_limits.MixinMobCategory").isEnabled();
        spawning              =  this.caffeineConfig.getEffectiveOptionForMixin("spawning.MixinBaseSpawner").isEnabled();
        worldgen              =  this.caffeineConfig.getEffectiveOptionForMixin("worldgen.MixinBiomeGenerationSettings").isEnabled();
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.mixin.";
    }
}