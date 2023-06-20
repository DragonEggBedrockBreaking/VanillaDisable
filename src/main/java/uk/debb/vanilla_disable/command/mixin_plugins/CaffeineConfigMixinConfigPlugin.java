package uk.debb.vanilla_disable.command.mixin_plugins;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import net.fabricmc.loader.api.FabricLoader;

public class CaffeineConfigMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {
    public static CaffeineConfig caffeineConfig;

    @Override
    protected CaffeineConfig createConfig() {
        caffeineConfig = CaffeineConfig.builder("VanillaDisable")
                .addMixinOption("advancement", true)
                .addMixinOption("block", true)
                .addMixinOption("block.command", true)
                .addMixinOption("block.container", true)
                .addMixinOption("block.experience", true)
                .addMixinOption("block.falling", true)
                .addMixinOption("block.fluid", true)
                .addMixinOption("block.function", true)
                .addMixinOption("block.other", true)
                .addMixinOption("block.piston", true)
                .addMixinOption("block.placement", true)
                .addMixinOption("block.redstone_timings", true)
                .addMixinOption("command", true)
                .addMixinOption("enchantment", true)
                .addMixinOption("enchantment.compatibility", true)
                .addMixinOption("enchantment.item", true)
                .addMixinOption("entity", true)
                .addMixinOption("entity.breeding", true)
                .addMixinOption("entity.breeding.tempt_goal", true)
                .addMixinOption("entity.conversions", true)
                .addMixinOption("entity.experience", true)
                .addMixinOption("entity.knockback", true)
                .addMixinOption("entity.other", true)
                .addMixinOption("entity.player", true)
                .addMixinOption("entity.player.hunger", true)
                .addMixinOption("entity.spawning", true)
                .addMixinOption("entity.spawning.despawning", true)
                .addMixinOption("entity.spawning.spawn_limits", true)
                .addMixinOption("entity.spawning.spawning", true)
                .addMixinOption("entity.trading", true)
                .addMixinOption("item", true)
                .addMixinOption("item.creative_breaking", true)
                .addMixinOption("item.function", true)
                .addMixinOption("item.other", true)
                .addMixinOption("item.potion", true)
                .addMixinOption("item.spam", true)
                .addMixinOption("mob_category", true)
                .withInfoUrl("https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/Mixin-Configuration-File")
                .build(FabricLoader.getInstance().getConfigDir().resolve("vanilla-disable-command-mixin.properties"));

        return caffeineConfig;
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.command.mixin.rule.";
    }
}
