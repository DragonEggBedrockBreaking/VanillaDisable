package uk.debb.vanilla_disable.config.global;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import uk.debb.vanilla_disable.Constants;

import java.io.File;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VanillaDisableConfigLoader {
    @SubscribeEvent
    public static void onInitializeClient(final FMLClientSetupEvent event) {
        VanillaDisableConfig.PATH = FMLPaths.GAMEDIR.get().resolve("config") + "/vanilla_disable.properties";
        if (!new File(VanillaDisableConfig.PATH).exists()) {
            VanillaDisableConfig.generateConfig();
        } else {
            VanillaDisableConfig.updateConfig();
        }
    }
}
