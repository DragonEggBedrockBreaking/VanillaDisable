package uk.debb.vanilla_disable.util.gamerules;

public class RegisterGamerules {
    private static void toggleCategories() {
        for (GameruleCategories category : GameruleCategories.values()) {
            category.toggle();
        }
    }
    public static void registerGamerules() {
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