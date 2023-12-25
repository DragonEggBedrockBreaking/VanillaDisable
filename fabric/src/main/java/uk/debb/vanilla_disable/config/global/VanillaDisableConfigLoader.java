package uk.debb.vanilla_disable.config.global;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class VanillaDisableConfigLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        VanillaDisableConfig.PATH = FabricLoader.getInstance().getConfigDir().toString() + "/vanilla_disable.properties";
        if (!new File(VanillaDisableConfig.PATH).exists()) {
            VanillaDisableConfig.generateConfig();
        } else {
            VanillaDisableConfig.updateConfig();
        }
    }
}
