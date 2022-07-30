package uk.debb.vanilla_disable.mixin_plugins;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import org.quiltmc.loader.api.QuiltLoader;
import static uk.debb.vanilla_disable.util.gamerules.GameruleCategories.*;

public class CaffeineConfigMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {
    private CaffeineConfig caffeineConfig;

    @Override
    protected CaffeineConfig createConfig() {
        CaffeineConfig localCaffeineConfig = CaffeineConfig.builder("VanillaDisable")
                .addMixinOption("advancement", true)
                .addMixinOption("ai", true)
                .addMixinOption("arrow", true)
                .addMixinOption("blocks", true)
                .addMixinOption("commands", true)
                .addMixinOption("damage", true)
                .addMixinOption("death", true)
                .addMixinOption("despawning", true)
                .addMixinOption("dispenser", true)
                .addMixinOption("effects", true)
                .addMixinOption("enchantments", true)
                .addMixinOption("fluids", true)
                .addMixinOption("food", true)
                .addMixinOption("items", true)
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
        VD_ADVANCEMENT.toggle(this.caffeineConfig.getEffectiveOptionForMixin("advancement.MixinPlayerAdvancements").isEnabled());
        VD_AI.toggle(this.caffeineConfig.getEffectiveOptionForMixin("ai.MixinGoal").isEnabled());
        VD_ARROW.toggle(this.caffeineConfig.getEffectiveOptionForMixin("arrow.MixinArrow").isEnabled());
        VD_BLOCKS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("blocks.MixinBeaconBlockEntity").isEnabled());
        VD_COMMANDS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("commands.MixinCommands").isEnabled());
        VD_DAMAGE.toggle(this.caffeineConfig.getEffectiveOptionForMixin("damage.MixinPlayer").isEnabled());
        VD_DEATH.toggle(this.caffeineConfig.getEffectiveOptionForMixin("death.MixinPlayer").isEnabled());
        VD_DESPAWNING.toggle(this.caffeineConfig.getEffectiveOptionForMixin("despawning.MixinItemEntity").isEnabled());
        VD_DISPENSER.toggle(this.caffeineConfig.getEffectiveOptionForMixin("dispenser.MixinDispenserBlock").isEnabled());
        VD_EFFECTS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("effects.MixinLivingEntity").isEnabled());
        VD_ENCHANTMENTS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("enchantments.MixinEnchantmentHelper").isEnabled());
        VD_FLUIDS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("fluids.MixinBucketItem").isEnabled());
        VD_FOOD.toggle(this.caffeineConfig.getEffectiveOptionForMixin("food.MixinFoodData").isEnabled());
        VD_ITEMS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("items.MixinBowItem").isEnabled());
        VD_KNOCKBACK.toggle(this.caffeineConfig.getEffectiveOptionForMixin("knockback.MixinLivingEntity").isEnabled());
        VD_MISC.toggle(this.caffeineConfig.getEffectiveOptionForMixin("misc.MixinEntity").isEnabled());
        VD_MOBS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("mob.MixinAbstractPiglin").isEnabled());
        VD_MOB_TOGGLES.toggle(this.caffeineConfig.getEffectiveOptionForMixin("mob_toggles.MixinMob").isEnabled());
        VD_PLAYER.toggle(this.caffeineConfig.getEffectiveOptionForMixin("player.MixinEntity").isEnabled());
        VD_POTIONS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("potions.MixinPotionUtils").isEnabled());
        VD_REDSTONE.toggle(this.caffeineConfig.getEffectiveOptionForMixin("redstone.MixinButtonBlock").isEnabled());
        VD_SPAWNING.toggle(this.caffeineConfig.getEffectiveOptionForMixin("spawning.MixinBaseSpawner").isEnabled());
        VD_SPAWN_LIMITS.toggle(this.caffeineConfig.getEffectiveOptionForMixin("spawn_limits.MixinMobCategory").isEnabled());
        VD_WORLDGEN.toggle(this.caffeineConfig.getEffectiveOptionForMixin("worldgen.MixinBiomeGenerationSettings").isEnabled());
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.mixin.";
    }
}