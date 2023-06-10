package uk.debb.vanilla_disable.common;

import net.fabricmc.api.ModInitializer;

import java.io.IOException;

public class VanillaDisable implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            LangFileManager.langFileFallback();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
