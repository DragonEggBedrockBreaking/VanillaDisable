package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class CreateGameruleCategories {
    public static final CustomGameRuleCategory DAMAGE = new CustomGameRuleCategory(
        new Identifier("gamerule.category.damage"),
        new TranslatableText("gamerule.category.damage")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory KNOCKBACK = new CustomGameRuleCategory(
        new Identifier("gamerule.category.knockback"),
        new TranslatableText("gamerule.category.knockback")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
}
