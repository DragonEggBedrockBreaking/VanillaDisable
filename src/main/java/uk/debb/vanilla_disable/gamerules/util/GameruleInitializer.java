package uk.debb.vanilla_disable.gamerules.util;

import net.fabricmc.api.ModInitializer;
import uk.debb.vanilla_disable.gamerules.util.gamerules.RegisterGamerules;
import uk.debb.vanilla_disable.gamerules.util.lists.PopulateLists;
import uk.debb.vanilla_disable.gamerules.util.maps.PopulateMaps;

import java.io.IOException;

public class GameruleInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            RegisterGamerules.registerGamerules();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PopulateMaps.populateMaps();
        PopulateLists.populateLists();
    }
}
