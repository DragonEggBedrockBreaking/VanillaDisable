package uk.debb.vanilla_disable.util.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public enum GameruleCategories {
    VD_DAMAGE,
    VD_KNOCKBACK,
    VD_SPAWNING,
    VD_DESPAWNING,
    VD_SPAWN_LIMITS,
    VD_COMMANDS,
    VD_FLUIDS,
    VD_MOBS,
    VD_MOB_TOGGLES,
    VD_EFFECTS,
    VD_ENCHANTMENTS,
    VD_WORLDGEN,
    VD_PLAYER,
    VD_REDSTONE,
    VD_AI,
    VD_BLOCKS,
    VD_FOOD,
    VD_POTIONS,
    VD_DEATH,
    VD_ITEMS,
    VD_DISPENSER,
    VD_ARROW,
    VD_ADVANCEMENT,
    VD_MISC;

    private final CustomGameRuleCategory category;

    GameruleCategories() {
        this.category = createCustomGameRuleCategory(this.name());
    }

    private CustomGameRuleCategory createCustomGameRuleCategory(String name) {
        String identifier = "gamerule.category.vd." + name.split("_")[1].toLowerCase();
        return new CustomGameRuleCategory(
                new ResourceLocation(identifier),
                Component.translatable(identifier)
                        .withStyle(ChatFormatting.BOLD)
                        .withStyle(ChatFormatting.DARK_GREEN)
        );
    }

    public CustomGameRuleCategory get() {
        return this.category;
    }
}