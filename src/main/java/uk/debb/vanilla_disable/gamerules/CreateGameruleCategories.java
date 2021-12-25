package uk.debb.vanilla_disable.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class CreateGameruleCategories {
    public static final CustomGameRuleCategory VD_DAMAGE = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.damage"),
        new TranslatableText("gamerule.category.vd.damage")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_KNOCKBACK = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.knockback"),
        new TranslatableText("gamerule.category.vd.knockback")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
}
