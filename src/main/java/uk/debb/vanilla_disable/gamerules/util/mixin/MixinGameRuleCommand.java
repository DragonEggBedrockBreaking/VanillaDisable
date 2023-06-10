package uk.debb.vanilla_disable.gamerules.util.mixin;

import com.google.common.base.CaseFormat;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import uk.debb.vanilla_disable.gamerules.util.gamerules.GameruleCategories;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.lists.Lists;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

import java.util.Random;

@Mixin(GameRuleCommand.class)
public abstract class MixinGameRuleCommand implements Lists, Maps {
    /**
     * @param commandDispatcher the command dispatcher
     * @author DragonEggBedrockBreaking
     * @reason Rewrite the command to be much more powerful
     */
    @Overwrite
    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        final LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder = Commands.literal("gamerule").requires(arg -> arg.hasPermission(2));
        GameRules.visitGameRuleTypes(
                new GameRules.GameRuleTypeVisitor() {
                    @Override
                    public <T extends GameRules.Value<T>> void visit(GameRules.Key<T> arg, GameRules.Type<T> arg2) {
                        String id = arg.getId();
                        literalArgumentBuilder.then(
                                Commands.literal("get")
                                        .then(Commands.literal(id)
                                                .executes(commandContext -> queryRule(commandContext.getSource(), arg)))
                        ).then(
                                Commands.literal("set")
                                        .then(Commands.literal(id)
                                                .then(arg2.createArgument("value")
                                                        .executes(commandContext -> GameRuleCommand.setRule(commandContext, arg))))
                        ).then(
                                Commands.literal("reset")
                                        .then(Commands.literal("rule")
                                                .then(Commands.literal(id)
                                                        .executes(commandContext -> resetRule(commandContext.getSource(), arg))))
                                        .then(Commands.literal("category")
                                                .then(Commands.argument("name", StringArgumentType.string())
                                                        .executes(commandContext -> resetCategory(commandContext.getSource(), arg, StringArgumentType.getString(commandContext, "name")))))
                                        .then(Commands.literal("all")
                                                .executes(commandContext -> resetCategory(commandContext.getSource(), arg, "all")))
                        ).then(
                                Commands.literal("list")
                                        .then(Commands.literal("category")
                                                .then(Commands.argument("name", StringArgumentType.string())
                                                        .executes(commandContext -> listGamerules(commandContext.getSource(), arg, StringArgumentType.getString(commandContext, "name")))))
                                        .then(Commands.literal("all")
                                                .executes(commandContext -> listGamerules(commandContext.getSource(), arg, "all")))
                        ).then(
                                Commands.literal("randomise")
                                        .then(Commands.literal("rule")
                                                .then(Commands.literal(id)
                                                        .executes(commandContext -> randomiseRule(commandContext.getSource(), arg))))
                                        .then(Commands.literal("category")
                                                .then(Commands.argument("name", StringArgumentType.string())
                                                        .executes(commandContext -> randomiseCategory(commandContext.getSource(), arg, StringArgumentType.getString(commandContext, "name")))))
                                        .then(Commands.literal("all")
                                                .executes(commandContext -> randomiseCategory(commandContext.getSource(), arg, "all")))
                        );
                    }
                }
        );
        commandDispatcher.register(literalArgumentBuilder);
    }

    @Unique
    private static <T extends GameRules.Value<T>> int listGamerules(CommandSourceStack source, GameRules.Key<T> rule, String group) {
        T lv = source.getServer().getGameRules().getRule(rule);
        ObjectList<String> gamerules = new ObjectArrayList<>();
        if (!group.equals("all")) {
            GameRules.Category vanilla_category = stringToVanillaGameruleCategoryMap.get(group);
            for (GameRules.Key<?> gamerule : vanillaGamerules) {
                if (gamerule.getCategory().equals(vanilla_category)) {
                    MutableComponent description = Component.translatable(gamerule.getDescriptionId());
                    gamerules.add("`" + gamerule.getId() + "`: " + description.getString());
                }
            }
            GameruleCategories category = stringToVanillaDisableGameruleCategoryMap.get(group);
            for (Gamerules gamerule : Gamerules.values()) {
                if (gamerule.getCategory().equals(category)) {
                    String fixed = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString());
                    MutableComponent description = Component.translatable("gamerule." + fixed);
                    gamerules.add("`" + fixed + "`: " + description.getString());
                }
            }
        } else {
            for (GameRules.Key<?> gamerule : vanillaGamerules) {
                MutableComponent description = Component.translatable(gamerule.getDescriptionId());
                gamerules.add("`" + gamerule.getId() + "`: " + description.getString());
            }
            for (Gamerules gamerule : Gamerules.values()) {
                String fixed = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString());
                MutableComponent description = Component.translatable("gamerule." + fixed);
                gamerules.add("`" + fixed + "`: " + description.getString());
            }
        }
        if (!gamerules.isEmpty()) {
            String joined = String.join("\n", gamerules);
            joined = "Here are the gamerules for the category:\n\n" + joined;
            String finalJoined = joined;
            source.sendSuccess(() -> Component.literal(finalJoined), true);
        } else {
            source.sendSuccess(() -> Component.translatable("commands.gamerule.category.invalid"), true);
        }
        return lv.getCommandResult();
    }

    @Unique
    private static <T extends GameRules.Value<T>> int resetRule(CommandSourceStack arg, GameRules.Key<T> arg2) {
        T lv = arg.getServer().getGameRules().getRule(arg2);
        String id = arg2.getId();
        if (stringToDefaultBooleanMap.containsKey(id)) {
            boolean defaultBoolean = stringToDefaultBooleanMap.getBoolean(id);
            arg.getServer().getCommands().performPrefixedCommand(arg, String.format("/gamerule set %s %s", id, defaultBoolean));
        } else if (stringToDefaultIntMap.containsKey(id)) {
            int defaultInt = stringToDefaultIntMap.getInt(id);
            arg.getServer().getCommands().performPrefixedCommand(arg, String.format("/gamerule set %s %s", id, defaultInt));
        } else if (stringToDefaultDoubleMap.containsKey(id)) {
            double defaultDouble = stringToDefaultDoubleMap.getDouble(id);
            arg.getServer().getCommands().performPrefixedCommand(arg, String.format("/gamerule set %s %s", id, defaultDouble));
        } else {
            arg.sendFailure(Component.translatable("commands.gamerule.rule.not.registered"));
        }
        return lv.getCommandResult();
    }

    @Unique
    private static <T extends GameRules.Value<T>> int resetCategory(CommandSourceStack source, GameRules.Key<T> rule, String group) {
        T lv = source.getServer().getGameRules().getRule(rule);
        ObjectList<String> gamerules = new ObjectArrayList<>();
        if (!group.equals("all")) {
            GameRules.Category vanilla_category = stringToVanillaGameruleCategoryMap.get(group);
            for (GameRules.Key<?> gamerule : vanillaGamerules) {
                if (gamerule.getCategory().equals(vanilla_category)) {
                    gamerules.add(gamerule.getId());
                }
            }
            GameruleCategories category = stringToVanillaDisableGameruleCategoryMap.get(group);
            for (Gamerules gamerule : Gamerules.values()) {
                if (gamerule.getCategory().equals(category)) {
                    gamerules.add(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString()));
                }
            }
        } else {
            for (GameRules.Key<?> gamerule : vanillaGamerules) {
                gamerules.add(gamerule.getId());
            }
            for (Gamerules gamerule : Gamerules.values()) {
                gamerules.add(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString()));
            }
        }
        for (String gamerule : gamerules) {
            if (stringToDefaultBooleanMap.containsKey(gamerule)) {
                boolean defaultBoolean = stringToDefaultBooleanMap.getBoolean(gamerule);
                source.getServer().getCommands().performPrefixedCommand(source, String.format("/gamerule set %s %s", gamerule, defaultBoolean));
            } else if (stringToDefaultIntMap.containsKey(gamerule)) {
                int defaultInt = stringToDefaultIntMap.getInt(gamerule);
                source.getServer().getCommands().performPrefixedCommand(source, String.format("/gamerule set %s %s", gamerule, defaultInt));
            } else {
                double defaultDouble = stringToDefaultDoubleMap.getDouble(gamerule);
                source.getServer().getCommands().performPrefixedCommand(source, String.format("/gamerule set %s %s", gamerule, defaultDouble));
            }
        }
        return lv.getCommandResult();
    }

    @Unique
    private static <T extends GameRules.Value<T>> int queryRule(CommandSourceStack arg, GameRules.Key<T> arg2) {
        T lv = arg.getServer().getGameRules().getRule(arg2);
        MutableComponent description = Component.translatable(arg2.getDescriptionId() + ".description");
        String id = arg2.getId();
        String defaultVal;
        if (stringToDefaultBooleanMap.containsKey(id)) {
            defaultVal = String.valueOf(stringToDefaultBooleanMap.getBoolean(id));
        } else if (stringToDefaultIntMap.containsKey(id)) {
            defaultVal = String.valueOf(stringToDefaultIntMap.getInt(id));
        } else if (stringToDefaultDoubleMap.containsKey(id)) {
            defaultVal = String.valueOf(stringToDefaultDoubleMap.getDouble(id));
        } else {
            return GameRuleCommand.queryRule(arg, arg2);
        }
        if (!description.getString().equals(arg2.getDescriptionId() + ".description")) {
            arg.sendSuccess(() -> description, false);
        }
        arg.sendSuccess(() -> Component.translatable("commands.gamerule.query", id, lv.toString()), false);
        if (lv.toString().equals(defaultVal)) {
            arg.sendSuccess(() -> Component.translatable("commands.gamerule.already_default"), false);
        } else {
            arg.sendSuccess(() -> Component.translatable("commands.gamerule.default", defaultVal), false);
        }
        return lv.getCommandResult();
    }

    @Unique
    private static <T extends GameRules.Value<T>> int randomiseRule(CommandSourceStack arg, GameRules.Key<T> arg2) {
        T lv = arg.getServer().getGameRules().getRule(arg2);
        String id = arg2.getId();
        Random random = new Random();
        if (stringToDefaultBooleanMap.containsKey(id)) {
            arg.getServer().getCommands().performPrefixedCommand(arg, String.format("/gamerule set %s %s", id, random.nextBoolean()));
        } else if (stringToDefaultIntMap.containsKey(id)) {
            arg.getServer().getCommands().performPrefixedCommand(arg, String.format("/gamerule set %s %s", id, random.nextInt(stringToMinIntMap.getOrDefault(id, Integer.MIN_VALUE), stringToMaxIntMap.getOrDefault(id, Integer.MAX_VALUE) + 1)));
        } else if (stringToDefaultDoubleMap.containsKey(id)) {
            arg.getServer().getCommands().performPrefixedCommand(arg, String.format("/gamerule set %s %s", id, String.format("%.2f", random.nextDouble(stringToMinDoubleMap.getOrDefault(id, Double.MIN_VALUE), stringToMaxDoubleMap.getOrDefault(id, Double.MAX_VALUE)))));
        } else {
            arg.sendFailure(Component.translatable("commands.gamerule.rule.not.registered"));
        }
        return lv.getCommandResult();
    }

    @Unique
    private static <T extends GameRules.Value<T>> int randomiseCategory(CommandSourceStack source, GameRules.Key<T> rule, String group) {
        T lv = source.getServer().getGameRules().getRule(rule);
        ObjectList<String> gamerules = new ObjectArrayList<>();
        if (!group.equals("all")) {
            GameRules.Category vanilla_category = stringToVanillaGameruleCategoryMap.get(group);
            for (GameRules.Key<?> gamerule : vanillaGamerules) {
                if (gamerule.getCategory().equals(vanilla_category)) {
                    gamerules.add(gamerule.getId());
                }
            }
            GameruleCategories category = stringToVanillaDisableGameruleCategoryMap.get(group);
            for (Gamerules gamerule : Gamerules.values()) {
                if (gamerule.getCategory().equals(category)) {
                    gamerules.add(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString()));
                }
            }
        } else {
            for (GameRules.Key<?> gamerule : vanillaGamerules) {
                gamerules.add(gamerule.getId());
            }
            for (Gamerules gamerule : Gamerules.values()) {
                gamerules.add(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString()));
            }
        }
        Random random = new Random();
        for (String gamerule : gamerules) {
            if (stringToDefaultBooleanMap.containsKey(gamerule)) {
                source.getServer().getCommands().performPrefixedCommand(source, String.format("/gamerule set %s %s", gamerule, random.nextBoolean()));
            } else if (stringToDefaultIntMap.containsKey(gamerule)) {
                source.getServer().getCommands().performPrefixedCommand(source, String.format("/gamerule set %s %s", gamerule, random.nextInt(stringToMinIntMap.getOrDefault(gamerule, Integer.MIN_VALUE), stringToMaxIntMap.getOrDefault(gamerule, Integer.MAX_VALUE) + 1)));
            } else {
                source.getServer().getCommands().performPrefixedCommand(source, String.format("/gamerule set %s %s", gamerule, String.format("%.2f", random.nextDouble(stringToMinDoubleMap.getOrDefault(gamerule, Double.MIN_VALUE), stringToMaxDoubleMap.getOrDefault(gamerule, Double.MAX_VALUE)))));
            }
        }
        return lv.getCommandResult();
    }
}
