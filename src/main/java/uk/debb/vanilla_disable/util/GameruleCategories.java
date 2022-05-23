package uk.debb.vanilla_disable.util;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class GameruleCategories {
    public static final CustomGameRuleCategory VD_DAMAGE = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.damage"),
        new TranslatableComponent("gamerule.category.vd.damage")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_KNOCKBACK = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.knockback"),
        new TranslatableComponent("gamerule.category.vd.knockback")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_SPAWNING = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.spawning"),
        new TranslatableComponent("gamerule.category.vd.spawning")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_DESPAWNING = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.despawning"),
        new TranslatableComponent("gamerule.category.vd.despawning")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_SPAWN_LIMITS = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.spawn_limits"),
        new TranslatableComponent("gamerule.category.vd.spawn_limits")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_COMMANDS = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.commands"),
        new TranslatableComponent("gamerule.category.vd.commands")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_FLUIDS = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.fluids"),
        new TranslatableComponent("gamerule.category.vd.fluids")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_MOBS = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.mobs"),
        new TranslatableComponent("gamerule.category.vd.mobs")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_EFFECTS = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.effects"),
        new TranslatableComponent("gamerule.category.vd.effects")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_ENCHANTMENTS = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.enchantments"),
        new TranslatableComponent("gamerule.category.vd.enchantments")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_WORLDGEN = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.worldgen"),
        new TranslatableComponent("gamerule.category.vd.worldgen")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_PLAYER = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.player"),
        new TranslatableComponent("gamerule.category.vd.player")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_REDSTONE = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.redstone"),
        new TranslatableComponent("gamerule.category.vd.redstone")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
    public static final CustomGameRuleCategory VD_MISC = new CustomGameRuleCategory(
        new ResourceLocation("gamerule.category.vd.misc"),
        new TranslatableComponent("gamerule.category.vd.misc")
            .withStyle(ChatFormatting.BOLD)
            .withStyle(ChatFormatting.YELLOW)
    );
}
