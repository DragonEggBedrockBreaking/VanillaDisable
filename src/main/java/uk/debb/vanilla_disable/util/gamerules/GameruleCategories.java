package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import uk.debb.vanilla_disable.mixin_plugins.CaffeineConfigMixinConfigPlugin;

public enum GameruleCategories {
    VD_DAMAGE("MixinPlayer"),
    VD_KNOCKBACK("MixinLivingEntity"),
    VD_SPAWNING("MixinBaseSpawner"),
    VD_DESPAWNING("MixinItemEntity"),
    VD_SPAWN_LIMITS("MixinMobCategory"),
    VD_COMMANDS("MixinCommands"),
    VD_FLUIDS("MixinBucketItem"),
    VD_MOBS("MixinAbstractPiglin"),
    VD_MOB_TOGGLES("MixinMob"),
    VD_EFFECTS("MixinLivingEntity"),
    VD_ENCHANTMENTS("MixinEnchantmentHelper"),
    VD_WORLDGEN("MixinBiomeGenerationSettings"),
    VD_PLAYER("MixinEntity"),
    VD_REDSTONE("MixinButtonBlock"),
    VD_AI("MixinGoal"),
    VD_BLOCKS("MixinBeaconBlockEntity"),
    VD_FOOD("MixinFoodData"),
    VD_POTIONS("MixinPotionUtils"),
    VD_DEATH("MixinPlayer"),
    VD_ITEMS("MixinBowItem"),
    VD_DISPENSER("MixinDispenserBlock"),
    VD_ARROW("MixinArrow"),
    VD_ADVANCEMENT("MixinPlayerAdvancements"),
    VD_MISC("MixinEntity");

    private final CustomGameRuleCategory category;
    private final String mixin;
    private boolean enabled;

    GameruleCategories(String mixin) {
        this.category = createCustomGameRuleCategory(this.name());
        this.mixin = mixin;
    }

    private CustomGameRuleCategory createCustomGameRuleCategory(String name) {
        String identifier = "gamerule.category.vd." + name.substring(3).toLowerCase();
        return new CustomGameRuleCategory(
                new ResourceLocation(identifier),
                Component.translatable(identifier)
                        .withStyle(ChatFormatting.BOLD)
                        .withStyle(ChatFormatting.DARK_GREEN)
        );
    }
    public void toggle() {
        enabled = CaffeineConfigMixinConfigPlugin.caffeineConfig.getEffectiveOptionForMixin(
                this.name().substring(3).toLowerCase() + "." + mixin
        ).isEnabled();
    }

    public CustomGameRuleCategory get() {
        return this.category;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
}