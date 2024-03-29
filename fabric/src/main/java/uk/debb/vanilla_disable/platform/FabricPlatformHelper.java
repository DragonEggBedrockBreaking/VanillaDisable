package uk.debb.vanilla_disable.platform;

import net.fabricmc.loader.api.FabricLoader;
import uk.debb.vanilla_disable.platform.services.IPlatformHelper;


public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}