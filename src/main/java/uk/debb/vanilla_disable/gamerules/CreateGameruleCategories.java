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
    public static final CustomGameRuleCategory VD_SPAWNING = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.spawning"),
        new TranslatableText("gamerule.category.vd.spawning")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_DESPAWNING = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.despawning"),
        new TranslatableText("gamerule.category.vd.despawning")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_SPAWN_LIMITS = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.spawn_limits"),
        new TranslatableText("gamerule.category.vd.spawn_limits")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_COMMANDS = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.commands"),
        new TranslatableText("gamerule.category.vd.commands")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_FLUIDS = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.fluids"),
        new TranslatableText("gamerule.category.vd.fluids")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_MOBS = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.mobs"),
        new TranslatableText("gamerule.category.vd.mobs")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_EFFECTS = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.effects"),
        new TranslatableText("gamerule.category.vd.effects")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_MISC = new CustomGameRuleCategory(
        new Identifier("gamerule.category.vd.misc"),
        new TranslatableText("gamerule.category.vd.misc")
            .formatted(Formatting.BOLD)
            .formatted(Formatting.YELLOW)
    );
}
