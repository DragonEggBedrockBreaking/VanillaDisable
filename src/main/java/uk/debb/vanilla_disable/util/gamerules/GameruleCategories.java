package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GameruleCategories {
    public static final CustomGameRuleCategory VD_DAMAGE = createCustomGameRuleCategory("gamerule.category.vd.damage");
    public static final CustomGameRuleCategory VD_KNOCKBACK = createCustomGameRuleCategory("gamerule.category.vd.knockback");
    public static final CustomGameRuleCategory VD_SPAWNING = createCustomGameRuleCategory("gamerule.category.vd.spawning");
    public static final CustomGameRuleCategory VD_DESPAWNING = createCustomGameRuleCategory("gamerule.category.vd.despawning");
    public static final CustomGameRuleCategory VD_SPAWN_LIMITS = createCustomGameRuleCategory("gamerule.category.vd.spawn_limits");
    public static final CustomGameRuleCategory VD_COMMANDS = createCustomGameRuleCategory("gamerule.category.vd.commands");
    public static final CustomGameRuleCategory VD_FLUIDS = createCustomGameRuleCategory("gamerule.category.vd.fluids");
    public static final CustomGameRuleCategory VD_MOBS = createCustomGameRuleCategory("gamerule.category.vd.mobs");
    public static final CustomGameRuleCategory VD_MOB_TOGGLES = createCustomGameRuleCategory("gamerule.category.vd.mob_toggles");
    public static final CustomGameRuleCategory VD_EFFECTS = createCustomGameRuleCategory("gamerule.category.vd.effects");
    public static final CustomGameRuleCategory VD_ENCHANTMENTS = createCustomGameRuleCategory("gamerule.category.vd.enchantments");
    public static final CustomGameRuleCategory VD_WORLDGEN = createCustomGameRuleCategory("gamerule.category.vd.worldgen");
    public static final CustomGameRuleCategory VD_PLAYER = createCustomGameRuleCategory("gamerule.category.vd.player");
    public static final CustomGameRuleCategory VD_REDSTONE = createCustomGameRuleCategory("gamerule.category.vd.redstone");
    public static final CustomGameRuleCategory VD_AI = createCustomGameRuleCategory("gamerule.category.vd.ai");
    public static final CustomGameRuleCategory VD_BLOCKS = createCustomGameRuleCategory("gamerule.category.vd.blocks");
    public static final CustomGameRuleCategory VD_FOOD = createCustomGameRuleCategory("gamerule.category.vd.food");
    public static final CustomGameRuleCategory VD_POTIONS = createCustomGameRuleCategory("gamerule.category.vd.potions");
    public static final CustomGameRuleCategory VD_DEATH = createCustomGameRuleCategory("gamerule.category.vd.death");
    public static final CustomGameRuleCategory VD_ITEMS = createCustomGameRuleCategory("gamerule.category.vd.items");
    public static final CustomGameRuleCategory VD_DISPENSER = createCustomGameRuleCategory("gamerule.category.vd.dispenser");
    public static final CustomGameRuleCategory VD_ARROW = createCustomGameRuleCategory("gamerule.category.vd.arrow");
    public static final CustomGameRuleCategory VD_ADVANCEMENT = createCustomGameRuleCategory("gamerule.category.vd.advancement");
    public static final CustomGameRuleCategory VD_MISC = createCustomGameRuleCategory("gamerule.category.vd.misc");

    private static CustomGameRuleCategory createCustomGameRuleCategory(String identifier) {
        return new CustomGameRuleCategory(
                new ResourceLocation(identifier),
                Component.translatable(identifier)
                        .withStyle(ChatFormatting.BOLD)
                        .withStyle(ChatFormatting.DARK_GREEN)
        );
    }
}