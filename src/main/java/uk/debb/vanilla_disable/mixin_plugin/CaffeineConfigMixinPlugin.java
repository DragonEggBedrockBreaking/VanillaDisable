package uk.debb.vanilla_disable.mixin_plugin;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import net.fabricmc.loader.api.FabricLoader;

public class CaffeineConfigMixinPlugin extends AbstractCaffeineConfigMixinPlugin {
    public static CaffeineConfig caffeineConfig;

    @Override
    protected CaffeineConfig createConfig() {
        caffeineConfig = CaffeineConfig.builder("VanillaDisable")
                .addMixinOption("command", true)
                .addMixinOption("command.advancement", true)
                .addMixinOption("command.block.command", true)
                .addMixinOption("command.block.container", true)
                .addMixinOption("command.block.experience", true)
                .addMixinOption("command.block.falling", true)
                .addMixinOption("command.block.fluid", true)
                .addMixinOption("command.block.function", true)
                .addMixinOption("command.block.other", true)
                .addMixinOption("command.block.piston", true)
                .addMixinOption("command.block.placement", true)
                .addMixinOption("command.block.redstone_timings", true)
                .addMixinOption("command.command", true)
                .addMixinOption("command.enchantment", true)
                .addMixinOption("command.enchantment.compatibility", true)
                .addMixinOption("command.enchantment.item", true)
                .addMixinOption("command.entity", true)
                .addMixinOption("command.entity.breeding", true)
                .addMixinOption("command.entity.breeding.tempt_goal", true)
                .addMixinOption("command.entity.conversions", true)
                .addMixinOption("command.entity.experience", true)
                .addMixinOption("command.entity.knockback", true)
                .addMixinOption("command.entity.other", true)
                .addMixinOption("command.entity.player", true)
                .addMixinOption("command.entity.player.hunger", true)
                .addMixinOption("command.entity.spawning", true)
                .addMixinOption("command.entity.spawning.despawning", true)
                .addMixinOption("command.entity.spawning.spawn_limits", true)
                .addMixinOption("command.entity.spawning.spawning", true)
                .addMixinOption("command.entity.trading", true)
                .addMixinOption("command.item", true)
                .addMixinOption("command.item.creative_breaking", true)
                .addMixinOption("command.item.function", true)
                .addMixinOption("command.item.other", true)
                .addMixinOption("command.item.potion", true)
                .addMixinOption("command.item.spam", true)
                .addMixinOption("command.mob_category", true)
                .addMixinOption("gamerule", true)
                .addMixinOption("util", true)
                .addMixinOption("util.command", true)
                .addMixinOption("util.gamerule", true)
                .addMixinOption("util.gamerule.migration", true)
                .addMixinOption("util.gamerule.other", true)
                .addMixinOption("util.worldgen", true)
                .addMixinOption("worldgen", true)
                .addMixinOption("worldgen.biome", true)
                .addMixinOption("worldgen.feature", true)
                .addMixinOption("worldgen.structure", true)
                .addOptionDependency("command", "util.command", true)
                .addOptionDependency("gamerule", "util.gamerule.other", true)
                .addOptionDependency("worldgen", "util.worldgen", true)
                .withInfoUrl("https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/Mixin-Configuration-File")
                .build(FabricLoader.getInstance().getConfigDir().resolve("vanilla-disable-mixin.properties"));

        return caffeineConfig;
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.mixin.";
    }
}
