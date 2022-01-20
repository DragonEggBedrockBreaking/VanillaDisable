package uk.debb.vanilla_disable.mixin;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import net.fabricmc.loader.api.FabricLoader;

public class VanillaDisableMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {
    @Override
    protected CaffeineConfig createConfig() {
        return CaffeineConfig.builder("VanillaDisable")
            .addMixinOption("commands", true)
            .addMixinOption("damage", true)
            .addMixinOption("despawning", true)
            .addMixinOption("effects", true)
            .addMixinOption("enchantments", true)
            .addMixinOption("fluids", true)
            .addMixinOption("knockback", true)
            .addMixinOption("misc", true)
            .addMixinOption("mob", true)
            .addMixinOption("spawn_limits", true)
            .addMixinOption("spawning", true)
            .addMixinOption("worldgen", true)
            .withInfoUrl("https://github.com/DragonEggBedrockBreaking/VanillaDisable/wiki/Mixin-Configuration-File")
            .build(FabricLoader.getInstance().getConfigDir().resolve("vanilla-disable-mixin.properties"));
    }

    @Override
    protected String mixinPackageRoot() {
        return "uk.debb.vanilla_disable.mixin.";
    }
}
