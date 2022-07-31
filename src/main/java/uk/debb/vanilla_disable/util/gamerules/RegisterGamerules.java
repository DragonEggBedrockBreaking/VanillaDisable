package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.api.EnvType;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;

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
            if (!rule.isAllowedInSingleplayer() && MinecraftQuiltLoader.getEnvironmentType().equals(EnvType.CLIENT)) {
                continue;
            }
            if (rule.getCategory().isEnabled()) {
                GameruleHelper.register(rule);
            }
        }
        for (IntegerGamerules rule : IntegerGamerules.values()) {
            if (rule.getCategory().isEnabled()) {
                GameruleHelper.register(rule);
            }
        }
        for (DoubleGamerules rule : DoubleGamerules.values()) {
            if (rule.getCategory().isEnabled()) {
                GameruleHelper.register(rule);
            }
        }
    }
}