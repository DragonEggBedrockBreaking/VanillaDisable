package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public interface GameruleCategories {
    CustomGameRuleCategory VD_DAMAGE = createCustomGameRuleCategory("gamerule.category.vd.damage");
    CustomGameRuleCategory VD_KNOCKBACK = createCustomGameRuleCategory("gamerule.category.vd.knockback");
    CustomGameRuleCategory VD_SPAWNING = createCustomGameRuleCategory("gamerule.category.vd.spawning");
    CustomGameRuleCategory VD_DESPAWNING = createCustomGameRuleCategory("gamerule.category.vd.despawning");
    CustomGameRuleCategory VD_SPAWN_LIMITS = createCustomGameRuleCategory("gamerule.category.vd.spawn_limits");
    CustomGameRuleCategory VD_COMMANDS = createCustomGameRuleCategory("gamerule.category.vd.commands");
    CustomGameRuleCategory VD_FLUIDS = createCustomGameRuleCategory("gamerule.category.vd.fluids");
    CustomGameRuleCategory VD_MOBS = createCustomGameRuleCategory("gamerule.category.vd.mobs");
    CustomGameRuleCategory VD_MOB_TOGGLES = createCustomGameRuleCategory("gamerule.category.vd.mob_toggles");
    CustomGameRuleCategory VD_EFFECTS = createCustomGameRuleCategory("gamerule.category.vd.effects");
    CustomGameRuleCategory VD_ENCHANTMENTS = createCustomGameRuleCategory("gamerule.category.vd.enchantments");
    CustomGameRuleCategory VD_WORLDGEN = createCustomGameRuleCategory("gamerule.category.vd.worldgen");
    CustomGameRuleCategory VD_PLAYER = createCustomGameRuleCategory("gamerule.category.vd.player");
    CustomGameRuleCategory VD_REDSTONE = createCustomGameRuleCategory("gamerule.category.vd.redstone");
    CustomGameRuleCategory VD_AI = createCustomGameRuleCategory("gamerule.category.vd.ai");
    CustomGameRuleCategory VD_BLOCKS = createCustomGameRuleCategory("gamerule.category.vd.blocks");
    CustomGameRuleCategory VD_FOOD = createCustomGameRuleCategory("gamerule.category.vd.food");
    CustomGameRuleCategory VD_POTIONS = createCustomGameRuleCategory("gamerule.category.vd.potions");
    CustomGameRuleCategory VD_DEATH = createCustomGameRuleCategory("gamerule.category.vd.death");
    CustomGameRuleCategory VD_ITEMS = createCustomGameRuleCategory("gamerule.category.vd.items");
    CustomGameRuleCategory VD_DISPENSER = createCustomGameRuleCategory("gamerule.category.vd.dispenser");
    CustomGameRuleCategory VD_ARROW = createCustomGameRuleCategory("gamerule.category.vd.arrow");
    CustomGameRuleCategory VD_ADVANCEMENT = createCustomGameRuleCategory("gamerule.category.vd.advancement");
    CustomGameRuleCategory VD_MISC = createCustomGameRuleCategory("gamerule.category.vd.misc");

    private static CustomGameRuleCategory createCustomGameRuleCategory(String identifier) {
        return new CustomGameRuleCategory(
                new ResourceLocation(identifier),
                Component.translatable(identifier)
                        .withStyle(ChatFormatting.BOLD)
                        .withStyle(ChatFormatting.DARK_GREEN)
        );
    }
}