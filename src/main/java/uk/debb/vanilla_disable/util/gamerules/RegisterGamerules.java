package uk.debb.vanilla_disable.util.gamerules;

import java.io.IOException;

public class RegisterGamerules {
    private static void toggleCategories() throws IOException {
        for (GameruleCategories category : GameruleCategories.values()) {
            category.toggle();
        }
    }

    public static void registerGamerules() throws IOException {
        toggleCategories();
        for (Gamerules rule : Gamerules.values()) {
            rule.register();
        }
    }
}