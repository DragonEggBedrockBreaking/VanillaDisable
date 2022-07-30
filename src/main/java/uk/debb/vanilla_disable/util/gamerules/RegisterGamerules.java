package uk.debb.vanilla_disable.util.gamerules;

public class RegisterGamerules {
    public static void registerGamerules() {
        for (BooleanGamerules rule : BooleanGamerules.values()) {
            if (rule.getCategory().isEnabled() && rule.isEnabled()) {
                GameruleHelper.register(rule);
            }
        }
        for (IntegerGamerules rule : IntegerGamerules.values()) {
            if (rule.getCategory().isEnabled() /*&& rule.isEnabled()*/) {
                GameruleHelper.register(rule);
            }
        }
    }
}