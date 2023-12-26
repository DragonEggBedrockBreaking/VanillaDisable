package uk.debb.vanilla_disable.config.global;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;
import uk.debb.vanilla_disable.Constants;

@Mod(Constants.MOD_ID)
public class ModMenuConfigScreenFactory {
    public ModMenuConfigScreenFactory() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((minecraft, screen) -> new VanillaDisableConfigScreen(screen)));
    }
}