package uk.debb.vanilla_disable.util;

import net.fabricmc.api.ModInitializer;
import uk.debb.vanilla_disable.util.gamerules.RegisterGamerules;
import uk.debb.vanilla_disable.util.lists.PopulateLists;
import uk.debb.vanilla_disable.util.maps.PopulateMaps;

import java.io.IOException;

public class VanillaDisable implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            RegisterGamerules.registerGamerules();
            LangFileManager.langFileFallback();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PopulateMaps.populateMaps();
        PopulateLists.populateLists();
    }
}
