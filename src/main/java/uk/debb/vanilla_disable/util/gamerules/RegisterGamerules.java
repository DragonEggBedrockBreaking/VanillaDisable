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
        for (BooleanGamerules rule : BooleanGamerules.values()) {
            if (rule.getCategory().isEnabled()) {
                GameruleHelper.register(rule);
            }
        }
        for (IntegerGamerules rule : IntegerGamerules.values()) {
            if (rule.getCategory().isEnabled()) {
                GameruleHelper.register(rule);
            }
        }
    }
}