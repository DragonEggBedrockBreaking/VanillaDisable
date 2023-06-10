package uk.debb.vanilla_disable.gamerules.mixin_plugins;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import net.fabricmc.loader.api.FabricLoader;

public class CaffeineConfigMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {
    public static CaffeineConfig caffeineConfig;

    @Override
    protected CaffeineConfig createConfig() {
        caffeineConfig = CaffeineConfig.builder("VanillaDisable")
                .addMixinOption("advancement", true)
                .addMixinOption("ai", true)
                .addMixinOption("arrow", true)
                .addMixinOption("biome", true)
                .addMixinOption("blocks", true)
                .addMixinOption("commands", true)
                .addMixinOption("damage", true)
                .addMixinOption("death", true)
                .addMixinOption("despawning", true)
                .addMixinOption("dispenser", true)
                .addMixinOption("effects", true)
                .addMixinOption("enchantments", true)
                .addMixinOption("entity", true)
                .addMixinOption("fluids", true)
                .addMixinOption("food", true)
                .addMixinOption("items", true)
                .addMixinOption("knockback", true)
                .addMixinOption("merchant", true)
                .addMixinOption("misc", true)
                .addMixinOption("mobs", true)
                .addMixinOption("mob_toggles", true)
                .addMixinOption("player", true)
                .addMixinOption("potions", true)
                .addMixinOption("redstone", true)
                .addMixinOption("spawn_limits", true)
                .addMixinOption("spawning", true)
                .addMixinOption("stats", true)
                .addMixinOption("worldgen", true)
                .withInfoUrl("https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/Mixin-Configuration-File")
                .build(FabricLoader.getInstance().getConfigDir().resolve("vanilla-disable-mixin.properties"));

        return caffeineConfig;
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.gamerules.mixin.";
    }
}