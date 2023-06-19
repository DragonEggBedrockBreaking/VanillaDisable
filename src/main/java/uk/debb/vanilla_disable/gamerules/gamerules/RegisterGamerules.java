package uk.debb.vanilla_disable.gamerules.gamerules;

import net.fabricmc.api.ModInitializer;

import java.io.IOException;

public class RegisterGamerules implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            for (GameruleCategories category : GameruleCategories.values()) {
                category.toggle();
            }
            for (Gamerules rule : Gamerules.values()) {
                rule.register();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}