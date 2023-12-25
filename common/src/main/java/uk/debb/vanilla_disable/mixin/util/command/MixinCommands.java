package uk.debb.vanilla_disable.mixin.util.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;
import uk.debb.vanilla_disable.data.command.DataType;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Mixin(Commands.class)
public abstract class MixinCommands {
    @Shadow
    public static LiteralArgumentBuilder<CommandSourceStack> literal(String pString0) {
        return null;
    }

    @Shadow
    public static <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(String pString0, ArgumentType<T> pArgumentType1) {
        return null;
    }

    @Shadow
    public abstract CommandDispatcher<CommandSourceStack> getDispatcher();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void vanillaDisable$init(Commands.CommandSelection commandSelection, CommandBuildContext commandBuildContext, CallbackInfo ci) {
        Thread t = new Thread(() -> {
            while (!CommandDataHandler.populationDone) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }

            LiteralArgumentBuilder<CommandSourceStack> overallResetDBBuilder = literal("reset");
            LiteralArgumentBuilder<CommandSourceStack> resetDBBuilder = literal("all").executes(context -> {
                CommandDataHandler.resetAll();
                context.getSource().sendSuccess(
                        () -> Component.translatable("vd.command.all_db_reset"),
                        false
                );
                return 1;
            });
            overallResetDBBuilder.then(resetDBBuilder);
            Stream.of("entities", "blocks", "items", "others").forEach(table -> {
                LiteralArgumentBuilder<CommandSourceStack> tableBuilder = literal(table).executes(context -> {
                    CommandDataHandler.resetOne(table);
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.one_db_reset", table),
                            false
                    );
                    return 1;
                });
                Object2ObjectMap<String, Object2ObjectMap<String, Component>> groups = switch (table) {
                    case "entities" -> CommandDataHandler.entityData;
                    case "blocks" -> CommandDataHandler.blockData;
                    case "items" -> CommandDataHandler.itemData;
                    default -> new Object2ObjectOpenHashMap<>();
                };
                groups.forEach((group, data) -> tableBuilder.then(literal(group).executes(context -> {
                    CommandDataHandler.resetPartial(table, data.keySet());
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.one_group_reset", group, table),
                            false
                    );
                    return 1;
                })));
                overallResetDBBuilder.then(tableBuilder);
            });

            this.getDispatcher().register(literal("vd").requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                    .then(literal("rule")
                            .then(vanillaDisable$init$builder("entity", CommandDataHandler.entities, CommandDataHandler.entityData, "entities"))
                            .then(vanillaDisable$init$builder("block", CommandDataHandler.blocks, CommandDataHandler.blockData, "blocks"))
                            .then(vanillaDisable$init$builder("item", CommandDataHandler.items, CommandDataHandler.itemData, "items"))
                            .then(vanillaDisable$init$builder("enchantment", CommandDataHandler.enchantments, CommandDataHandler.enchantmentData, "enchantments"))
                            .then(vanillaDisable$init$builder("command", CommandDataHandler.commands, CommandDataHandler.commandData, "commands"))
                            .then(vanillaDisable$init$builder("advancement", CommandDataHandler.advancements, CommandDataHandler.advancementData, "advancements"))
                            .then(vanillaDisable$init$builder("mob_category", CommandDataHandler.mobCategories, CommandDataHandler.mobCategoryData, "mob_categories"))
                    ).then(overallResetDBBuilder)
            );
        });
        t.start();
    }

    /**
     * This method creates a literal argument builder to get a value.
     *
     * @param type The data type of the argument
     * @param col  The column name of the argument
     * @return The literal argument builder
     */
    @Unique
    private ArgumentType<?> vanillaDisable$init$getArgumentTypeForType(DataType type, String col) {
        return switch (type) {
            case BOOLEAN -> BoolArgumentType.bool();
            case INTEGER ->
                    IntegerArgumentType.integer(0, CommandDataHandler.intRowMaximums.getOrDefault(col, Integer.MAX_VALUE));
            case REAL ->
                    DoubleArgumentType.doubleArg(0.0, CommandDataHandler.doubleRowMaximums.getOrDefault(col, Double.MAX_VALUE));
            case CLOB -> StringArgumentType.greedyString();
        };
    }

    /**
     * This method gets the value of an argument based on its data type.
     *
     * @param type    The data type of the argument
     * @param context The command context
     * @return The value of the argument
     */
    @Unique
    private String vanillaDisable$init$getArgumentValueForType(DataType type, CommandContext<?> context) {
        return switch (type) {
            case BOOLEAN -> String.valueOf(BoolArgumentType.getBool(context, "value"));
            case INTEGER -> String.valueOf(IntegerArgumentType.getInteger(context, "value"));
            case REAL -> String.valueOf(DoubleArgumentType.getDouble(context, "value"));
            case CLOB -> StringArgumentType.getString(context, "value");
        };
    }

    /**
     * This method creates a standard command that sets a row-column value in a table.
     *
     * @param literalArgumentBuilder The command builder
     * @param table                  The table to update
     * @param row                    The row that the value refers to
     * @param col                    The column that the value refers to
     * @param description            The description that should be printed when querying the value
     * @param defaultValue           The default value that should be printed when querying the value
     * @param type                   The type of the value to be updated
     */
    @Unique
    private void vanillaDisable$init$execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, Component description, String defaultValue, DataType type) {
        literalArgumentBuilder.executes(context -> {
            String value = switch (type) {
                case BOOLEAN -> String.valueOf(CommandDataHandler.getCachedBoolean(table, row, col));
                case INTEGER -> String.valueOf(CommandDataHandler.getCachedInt(table, row, col));
                case REAL -> String.valueOf(CommandDataHandler.getCachedDouble(table, row, col));
                case CLOB -> "";
            };
            context.getSource().sendSuccess(
                    () -> description,
                    false
            );
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.current_value", value),
                    false
            );
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.default_value", defaultValue.replace("'", "")),
                    false
            );
            return 1;
        }).then(
                argument("value", vanillaDisable$init$getArgumentTypeForType(type, col)).executes(context -> {
                    String value = vanillaDisable$init$getArgumentValueForType(type, context);
                    CommandDataHandler.setValue(table, row, col, value, type.equals(DataType.CLOB));
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.successfully_set_value", value),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a row-column value (that has specific string options) in a table.
     *
     * @param literalArgumentBuilder The command builder
     * @param table                  The table to update
     * @param row                    The row that the value refers to
     * @param col                    The column that the value refers to
     * @param description            The description that should be printed when querying the value
     * @param defaultValue           The default value that should be printed when querying the value
     * @param options                The potential values of the value to be updated
     */
    @Unique
    private void vanillaDisable$init$execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, Component description, String defaultValue, List<String> options) {
        literalArgumentBuilder.executes(context -> {
            context.getSource().sendSuccess(
                    () -> description,
                    false
            );
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.current_value", CommandDataHandler.getCachedString(table, row, col)),
                    false
            );
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.default_value", defaultValue.replace("'", "")),
                    false
            );
            return 1;
        }).then(
                argument("value", StringArgumentType.word()).suggests((ctx, builder) -> SharedSuggestionProvider.suggest(options, builder)).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    if (!options.contains(value)) {
                        context.getSource().sendFailure(
                                Component.translatable("vd.command.invalid_value")
                        );
                        return 0;
                    }
                    CommandDataHandler.setValue(table, row, col, value, true);
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.successfully_set_value", value),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value for all rows in a table.
     *
     * @param literalArgumentBuilder The command builder
     * @param table                  The table to update
     * @param col                    The column that the value refers to
     * @param type                   The type of the value to be updated
     */
    @Unique
    private void vanillaDisable$init$execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, DataType type) {
        literalArgumentBuilder.then(
                argument("value", vanillaDisable$init$getArgumentTypeForType(type, col)).executes(context -> {
                    String value = vanillaDisable$init$getArgumentValueForType(type, context);
                    CommandDataHandler.setAll(table, col, value, type.equals(DataType.CLOB));
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.successfully_set_values", value),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value (that has specific string options) for all rows in a table.
     *
     * @param literalArgumentBuilder The command builder
     * @param table                  The table to update
     * @param col                    The column that the value refers to
     * @param options                The potential values of the value to be updated
     */
    @Unique
    private void vanillaDisable$init$execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, List<String> options) {
        literalArgumentBuilder.then(
                argument("value", StringArgumentType.word()).suggests((ctx, builder) -> SharedSuggestionProvider.suggest(options, builder)).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    if (!options.contains(value)) {
                        context.getSource().sendFailure(
                                Component.translatable("vd.command.invalid_value")
                        );
                        return 0;
                    }
                    CommandDataHandler.setAll(table, col, value, true);
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.successfully_set_values", value),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value for all rows in a table.
     *
     * @param literalArgumentBuilder The command builder
     * @param table                  The table to update
     * @param col                    The column that the value refers to
     * @param type                   The type of the value to be updated
     */
    @Unique
    private void vanillaDisable$init$execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, DataType type, String argumentName) {
        literalArgumentBuilder.then(
                argument("value", vanillaDisable$init$getArgumentTypeForType(type, col)).executes(context -> {
                    String value = vanillaDisable$init$getArgumentValueForType(type, context);
                    String pattern = StringArgumentType.getString(context, argumentName);
                    CommandDataHandler.setMatching(table, col, value, type.equals(DataType.CLOB), pattern);
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.successfully_set_values", value),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value (that has specific string options) for all rows in a table.
     *
     * @param literalArgumentBuilder The command builder
     * @param table                  The table to update
     * @param col                    The column that the value refers to
     * @param options                The potential values of the value to be updated
     */
    @Unique
    private void vanillaDisable$init$execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, List<String> options, String argumentName) {
        literalArgumentBuilder.then(
                argument("value", StringArgumentType.word()).suggests((ctx, builder) -> SharedSuggestionProvider.suggest(options, builder)).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    String pattern = StringArgumentType.getString(context, argumentName);
                    if (!options.contains(value)) {
                        context.getSource().sendFailure(
                                Component.translatable("vd.command.invalid_value")
                        );
                        return 0;
                    }
                    CommandDataHandler.setMatching(table, col, value, true, pattern);
                    context.getSource().sendSuccess(
                            () -> Component.translatable("vd.command.successfully_set_values", value),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets all columns in a group corresponding to a row from a table to a specific value.
     *
     * @param groupBuilder The command builder
     * @param table        The table to update
     * @param row          The row that the value refers to
     * @param group        The group that the rows are in
     * @param info         The descriptions for the columns
     * @param possible     The columns that the command should update
     */
    @Unique
    private void vanillaDisable$init$allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String row, String group, Object2ObjectMap<String, Component> info, ObjectSet<String> possible) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            info.keySet().stream().filter(possible::contains).forEach((groupProperty ->
                    CommandDataHandler.setValue(table, row, groupProperty, value, false)));
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.successfully_set_all_properties", group, value),
                    false
            );
            return 1;
        })));
    }

    /**
     * This method creates a command that sets all columns in a group corresponding to all rows in a table to a specific value.
     *
     * @param groupBuilder The command builder
     * @param table        The table to update
     * @param group        The group that the rows are in
     * @param info         The descriptions for the columns
     */
    @Unique
    private void vanillaDisable$init$allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String group, Object2ObjectMap<String, Component> info) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            info.keySet().forEach((groupProperty ->
                    CommandDataHandler.setAll(table, groupProperty, value, false)));
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.successfully_set_all_properties", group, value),
                    false
            );
            return 1;
        })));
    }

    /**
     * This method creates a command that sets all columns in a group corresponding to all rows in a table to a specific value.
     *
     * @param groupBuilder The command builder
     * @param table        The table to update
     * @param group        The group that the rows are in
     * @param info         The descriptions for the columns
     * @param argumentName The pattern that the rows must match
     */
    @Unique
    private void vanillaDisable$init$allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String group, Object2ObjectMap<String, Component> info, String argumentName) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            String pattern = StringArgumentType.getString(context, argumentName);
            info.keySet().forEach((groupProperty ->
                    CommandDataHandler.setMatching(table, groupProperty, value, false, pattern)));
            context.getSource().sendSuccess(
                    () -> Component.translatable("vd.command.successfully_set_all_properties", group, value),
                    false
            );
            return 1;
        })));
    }

    /**
     * This method creates a command to control all the rows and columns of a table.
     *
     * @param base      The base command string
     * @param data      Data about which columns have data in each row
     * @param otherData Data containing the descriptions for each column
     * @param table     The table to update
     * @return The command builder
     */
    @Unique
    private LiteralArgumentBuilder<CommandSourceStack> vanillaDisable$init$builder(String base, Object2ObjectMap<String, Object2ObjectMap<String, String>> data, Object2ObjectMap<String, Object2ObjectMap<String, Component>> otherData, String table) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal(base);
        data.forEach((row, map) -> {
            LiteralArgumentBuilder<CommandSourceStack> rowBuilder = literal(row);
            otherData.forEach((group, info) -> {
                Object2ObjectMap<String, Component> properties = info.entrySet().stream().filter(entry -> map.containsKey(entry.getKey()))
                        .collect(Object2ObjectOpenHashMap::new, (m, entry) -> m.put(entry.getKey(), entry.getValue()), Object2ObjectOpenHashMap::putAll);
                if (properties.isEmpty()) return;
                LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                properties.forEach((key, value) -> {
                    LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(key);
                    if (CommandDataHandler.stringColSuggestions.containsKey(key)) {
                        vanillaDisable$init$execute(propertyBuilder, table, row, key, value, map.get(key), CommandDataHandler.stringColSuggestions.get(key));
                    } else {
                        vanillaDisable$init$execute(propertyBuilder, table, row, key, value, map.get(key),
                                CommandDataHandler.cols.get(table).get(key));
                    }
                    groupBuilder.then(propertyBuilder);
                });
                if (!CommandDataHandler.differentDataTypes.contains(group)) {
                    vanillaDisable$init$allCols(groupBuilder, table, row, group, info, map.keySet());
                }
                rowBuilder.then(groupBuilder);
            });
            overallBuilder.then(rowBuilder);
        });

        LiteralArgumentBuilder<CommandSourceStack> allBuilder = literal("all");
        otherData.forEach((group, info) -> {
            LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
            info.keySet().forEach((property) -> {
                LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property);
                if (CommandDataHandler.stringColSuggestions.containsKey(property)) {
                    vanillaDisable$init$execute(propertyBuilder, table, property, CommandDataHandler.stringColSuggestions.get(property));
                } else {
                    vanillaDisable$init$execute(propertyBuilder, table, property, CommandDataHandler.cols.get(table).get(property));
                }
                groupBuilder.then(propertyBuilder);
            });
            if (!CommandDataHandler.differentDataTypes.contains(group)) {
                vanillaDisable$init$allCols(groupBuilder, table, group, info);
            }
            allBuilder.then(groupBuilder);
        });
        overallBuilder.then(allBuilder);

        RequiredArgumentBuilder<CommandSourceStack, String> matchesBuilder = argument("pattern", StringArgumentType.string());
        otherData.forEach((group, info) -> {
            LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
            info.keySet().forEach((property) -> {
                LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property);
                if (CommandDataHandler.stringColSuggestions.containsKey(property)) {
                    vanillaDisable$init$execute(propertyBuilder, table, property, CommandDataHandler.stringColSuggestions.get(property), "pattern");
                } else {
                    vanillaDisable$init$execute(propertyBuilder, table, property, CommandDataHandler.cols.get(table).get(property), "pattern");
                }
                groupBuilder.then(propertyBuilder);
            });
            if (!CommandDataHandler.differentDataTypes.contains(group)) {
                vanillaDisable$init$allCols(groupBuilder, table, group, info, "pattern");
            }
            matchesBuilder.then(groupBuilder);
        });
        overallBuilder.then(literal("matches").then(matchesBuilder));

        return overallBuilder;
    }
}
