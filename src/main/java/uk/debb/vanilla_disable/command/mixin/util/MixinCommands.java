package uk.debb.vanilla_disable.command.mixin.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;
import uk.debb.vanilla_disable.command.data.DataType;

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
    private void onRegister(Commands.CommandSelection commandSelection, CommandBuildContext commandBuildContext, CallbackInfo ci) {
        Thread t = new Thread(() -> {
            while (!DataHandler.populationDone) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            RegistryAccess registryAccess = DataHandler.server.registryAccess();

            LiteralArgumentBuilder<CommandSourceStack> overallResetDBBuilder = literal("reset");
            LiteralArgumentBuilder<CommandSourceStack> resetDBBuilder = literal("all").executes(context -> {
                DataHandler.resetAll();
                context.getSource().sendSuccess(
                        () -> Component.literal("All databases have been reset."),
                        false
                );
                return 1;
            });
            overallResetDBBuilder.then(resetDBBuilder);
            Stream.of("entities", "blocks", "items", "others").forEach(table -> {
                LiteralArgumentBuilder<CommandSourceStack> tableBuilder = literal(table).executes(context -> {
                    DataHandler.resetOne(table);
                    context.getSource().sendSuccess(
                            () -> Component.literal("The " + table + " table has been reset."),
                            false
                    );
                    return 1;
                });
                Object2ObjectMap<String, Object2ObjectMap<String, String>> groups = switch (table) {
                    case "entities" -> DataHandler.entityData;
                    case "blocks" -> DataHandler.blockData;
                    case "items" -> DataHandler.itemData;
                    default -> new Object2ObjectOpenHashMap<>();
                };
                groups.forEach((group, data) -> tableBuilder.then(literal(group).executes(context -> {
                    DataHandler.resetPartial(table, data.keySet());
                    context.getSource().sendSuccess(
                            () -> Component.literal("The " + group + " group in the " + table + " table has been reset."),
                            false
                    );
                    return 1;
                })));
                overallResetDBBuilder.then(tableBuilder);
            });

            this.getDispatcher().register(literal("vd").then(literal("rule")
                            .then(majorBuilder("entity", DataHandler.entities, DataHandler.entityData, "entities"))
                            .then(majorBuilder("block", DataHandler.blocks, DataHandler.blockData, "blocks"))
                            .then(majorBuilder("item", DataHandler.items, DataHandler.itemData, "items"))
                            .then(literal("other")
                                    .then(minorBuilderComplex("advancement", DataHandler.server.getAdvancements().getAllAdvancements()
                                            .stream().map(a -> a.getId().toString()).filter(a -> !a.contains("recipe")), "minecraft:%/%"))
                                    .then(minorBuilderComplex("command", this.getDispatcher().getRoot().getChildren().stream().map(commandNode -> "/" + commandNode.getName()), "/%"))
                                    .then(minorBuilderSimple("biome", registryAccess.registryOrThrow(Registries.BIOME).keySet().stream().map(Object::toString)))
                                    .then(minorBuilderSimple("structure", registryAccess.registryOrThrow(Registries.STRUCTURE).keySet().stream().map(Object::toString)))
                                    .then(minorBuilder(new ObjectArrayList<>() {{
                                        add(BuiltInRegistries.FEATURE.keySet().stream().map(Object::toString));
                                        add(registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet().stream().map(Object::toString));
                                    }}))
                            )
                    ).then(overallResetDBBuilder)
            );
        });
        t.start();
    }

    /**
     * This method creates a literal argument builder to get a value.
     * @param type The data type of the argument
     * @param col The column name of the argument
     * @return The literal argument builder
     */
    private ArgumentType<?> getArgumentTypeForType(DataType type, String col) {
        return switch (type) {
            case BOOLEAN -> BoolArgumentType.bool();
            case INTEGER ->
                    IntegerArgumentType.integer(0, DataHandler.intRowMaximums.getOrDefault(col, Integer.MAX_VALUE));
            case REAL ->
                    DoubleArgumentType.doubleArg(0.0, DataHandler.doubleRowMaximums.getOrDefault(col, Double.MAX_VALUE));
            case CLOB -> StringArgumentType.greedyString();
        };
    }

    /**
     * This method gets the value of an argument based on its data type.
     * @param type The data type of the argument
     * @param context The command context
     * @return The value of the argument
     */
    private String getArgumentValueForType(DataType type, CommandContext<?> context) {
        return switch (type) {
            case BOOLEAN -> String.valueOf(BoolArgumentType.getBool(context, "value"));
            case INTEGER -> String.valueOf(IntegerArgumentType.getInteger(context, "value"));
            case REAL -> String.valueOf(DoubleArgumentType.getDouble(context, "value"));
            case CLOB -> StringArgumentType.getString(context, "value");
        };
    }

    /**
     * This method creates a standard command that sets a row-column value in a table.
     * @param literalArgumentBuilder The command builder
     * @param table The table to update
     * @param row The row that the value refers to
     * @param col The column that the value refers to
     * @param description The description that should be printed when querying the value
     * @param defaultValue The default value that should be printed when querying the value
     * @param type The type of the value to be updated
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, String description, String defaultValue, DataType type) {
        literalArgumentBuilder.executes(context -> {
            String value = switch (type) {
                case BOOLEAN -> String.valueOf(DataHandler.getBoolean(table, row, col));
                case INTEGER -> String.valueOf(DataHandler.getInt(table, row, col));
                case REAL -> String.valueOf(DataHandler.getDouble(table, row, col));
                case CLOB -> "";
            };
            context.getSource().sendSuccess(
                    () -> Component.literal(description + "\nThe current value is: " + value + "\nThe default value is: " + defaultValue.replace("'", "")),
                    false
            );
            return 1;
        }).then(
                argument("value", getArgumentTypeForType(type, col)).executes(context -> {
                    String value = getArgumentValueForType(type, context);
                    DataHandler.setValue(table, row, col, value, type.equals(DataType.CLOB));
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the value to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a row-column value (that has specific string options) in a table.
     * @param literalArgumentBuilder The command builder
     * @param table The table to update
     * @param row The row that the value refers to
     * @param col The column that the value refers to
     * @param description The description that should be printed when querying the value
     * @param defaultValue The default value that should be printed when querying the value
     * @param options The potential values of the value to be updated
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, String description, String defaultValue, List<String> options) {
        literalArgumentBuilder.executes(context -> {
            context.getSource().sendSuccess(
                    () -> Component.literal(description + "\nThe current value is: " + DataHandler.getString(table, row, col) + "\nThe default value is: " + defaultValue.replace("'", "")),
                    false
            );
            return 1;
        }).then(
                argument("value", StringArgumentType.word()).suggests((ctx, builder) -> SharedSuggestionProvider.suggest(options, builder)).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    if (!options.contains(value)) {
                        context.getSource().sendFailure(
                                Component.literal("Invalid value.")
                        );
                        return 0;
                    }
                    DataHandler.setValue(table, row, col, value, true);
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the value to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value for all rows in a table.
     * @param literalArgumentBuilder The command builder
     * @param table The table to update
     * @param col The column that the value refers to
     * @param type The type of the value to be updated
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, DataType type) {
        literalArgumentBuilder.then(
                argument("value", getArgumentTypeForType(type, col)).executes(context -> {
                    String value = getArgumentValueForType(type, context);
                    DataHandler.setAll(table, col, value, type.equals(DataType.CLOB));
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the values to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value (that has specific string options) for all rows in a table.
     * @param literalArgumentBuilder The command builder
     * @param table The table to update
     * @param col The column that the value refers to
     * @param options The potential values of the value to be updated
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, List<String> options) {
        literalArgumentBuilder.then(
                argument("value", StringArgumentType.word()).suggests((ctx, builder) -> SharedSuggestionProvider.suggest(options, builder)).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    if (!options.contains(value)) {
                        context.getSource().sendFailure(
                                Component.literal("Invalid value.")
                        );
                        return 0;
                    }
                    DataHandler.setAll(table, col, value, true);
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the values to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets all rows in a minor group (from the 'others' table) to a value.
     * @param literalArgumentBuilder The command builder
     * @param condition The condition that the rows should satisfy
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String condition) {
        literalArgumentBuilder.then(
                argument("value", BoolArgumentType.bool()).executes(context -> {
                    String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
                    DataHandler.setWithCondition(value, condition);
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the values to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value for all rows in a table.
     * @param literalArgumentBuilder The command builder
     * @param table The table to update
     * @param col The column that the value refers to
     * @param type The type of the value to be updated
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, DataType type, String argumentName) {
        literalArgumentBuilder.then(
                argument("value", getArgumentTypeForType(type, col)).executes(context -> {
                    String value = getArgumentValueForType(type, context);
                    String pattern = StringArgumentType.getString(context, argumentName);
                    DataHandler.setMatching(table, col, value, type.equals(DataType.CLOB), pattern);
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the values to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets a column value (that has specific string options) for all rows in a table.
     * @param literalArgumentBuilder The command builder
     * @param table The table to update
     * @param col The column that the value refers to
     * @param options The potential values of the value to be updated
     */
    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String col, List<String> options, String argumentName) {
        literalArgumentBuilder.then(
                argument("value", StringArgumentType.word()).suggests((ctx, builder) -> SharedSuggestionProvider.suggest(options, builder)).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    String pattern = StringArgumentType.getString(context, argumentName);
                    if (!options.contains(value)) {
                        context.getSource().sendFailure(
                                Component.literal("Invalid value.")
                        );
                        return 0;
                    }
                    DataHandler.setMatching(table, col, value, true, pattern);
                    context.getSource().sendSuccess(
                            () -> Component.literal("Successfully set the values to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    /**
     * This method creates a command that sets all columns in a group corresponding to a row from a table to a specific value.
     * @param groupBuilder The command builder
     * @param table The table to update
     * @param row The row that the value refers to
     * @param group The group that the rows are in
     * @param info The descriptions for the columns
     * @param possible The columns that the command should update
     */
    private void allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String row, String group, Object2ObjectMap<String, String> info, ObjectSet<String> possible) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            info.keySet().stream().filter(possible::contains).forEach((groupProperty ->
                    DataHandler.setValue(table, row, groupProperty, value, false)));
            context.getSource().sendSuccess(
                    () -> Component.literal("Successfully set the value of all " + group + " properties to " + value + "."),
                    false
            );
            return 1;
        })));
    }

    /**
     * This method creates a command that sets all columns in a group corresponding to all rows in a table to a specific value.
     * @param groupBuilder The command builder
     * @param table The table to update
     * @param group The group that the rows are in
     * @param info The descriptions for the columns
     */
    private void allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String group, Object2ObjectMap<String, String> info) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            info.keySet().forEach((groupProperty ->
                    DataHandler.setAll(table, groupProperty, value, false)));
            context.getSource().sendSuccess(
                    () -> Component.literal("Successfully set the value of all " + group + " properties to " + value + "."),
                    false
            );
            return 1;
        })));
    }

    /**
     * This method creates a command that sets all columns in a group corresponding to all rows in a table to a specific value.
     * @param groupBuilder The command builder
     * @param table The table to update
     * @param group The group that the rows are in
     * @param info The descriptions for the columns
     * @param argumentName The pattern that the rows must match
     */
    private void allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String group, Object2ObjectMap<String, String> info, String argumentName) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            String pattern = StringArgumentType.getString(context, argumentName);
            info.keySet().forEach((groupProperty ->
                    DataHandler.setMatching(table, groupProperty, value, false, pattern)));
            context.getSource().sendSuccess(
                    () -> Component.literal("Successfully set the value of all " + group + " properties to " + value + "."),
                    false
            );
            return 1;
        })));
    }

    /**
     * This method creates a command to control all the rows and columns of a table.
     * @param base The base command string
     * @param data Data about which columns have data in each row
     * @param otherData Data containing the descriptions for each column
     * @param table The table to update
     * @return The command builder
     */
    private LiteralArgumentBuilder<CommandSourceStack> majorBuilder(String base, Object2ObjectMap<String, Object2ObjectMap<String, String>> data, Object2ObjectMap<String, Object2ObjectMap<String, String>> otherData, String table) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal(base);
        data.forEach((row, map) -> {
            LiteralArgumentBuilder<CommandSourceStack> rowBuilder = literal(row);
            otherData.forEach((group, info) -> {
                Object2ObjectMap<String, String> properties = info.entrySet().stream().filter(entry -> map.containsKey(entry.getKey()))
                        .collect(Object2ObjectOpenHashMap::new, (m, entry) -> m.put(entry.getKey(), entry.getValue()), Object2ObjectOpenHashMap::putAll);
                if (properties.isEmpty()) return;
                LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                properties.forEach((key, value) -> {
                    LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(key);
                    if (DataHandler.stringColSuggestions.containsKey(key)) {
                        execute(propertyBuilder, table, row, key, value, map.get(key), DataHandler.stringColSuggestions.get(key));
                    } else {
                        execute(propertyBuilder, table, row, key, value, map.get(key),
                                DataHandler.cols.get(table).get(key));
                    }
                    groupBuilder.then(propertyBuilder);
                });
                if (!DataHandler.differentDataTypes.contains(group)) {
                    allCols(groupBuilder, table, row, group, info, map.keySet());
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
                if (DataHandler.stringColSuggestions.containsKey(property)) {
                    execute(propertyBuilder, table, property, DataHandler.stringColSuggestions.get(property));
                } else {
                    execute(propertyBuilder, table, property, DataHandler.cols.get(table).get(property));
                }
                groupBuilder.then(propertyBuilder);
            });
            if (!DataHandler.differentDataTypes.contains(group)) {
                allCols(groupBuilder, table, group, info);
            }
            allBuilder.then(groupBuilder);
        });
        overallBuilder.then(allBuilder);

        RequiredArgumentBuilder<CommandSourceStack, String> matchesBuilder = argument("pattern", StringArgumentType.string());
        otherData.forEach((group, info) -> {
            LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
            info.keySet().forEach((property) -> {
                LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property);
                if (DataHandler.stringColSuggestions.containsKey(property)) {
                    execute(propertyBuilder, table, property, DataHandler.stringColSuggestions.get(property), "pattern");
                } else {
                    execute(propertyBuilder, table, property, DataHandler.cols.get(table).get(property), "pattern");
                }
                groupBuilder.then(propertyBuilder);
            });
            if (!DataHandler.differentDataTypes.contains(group)) {
                allCols(groupBuilder, table, group, info, "pattern");
            }
            matchesBuilder.then(groupBuilder);
        });
        overallBuilder.then(literal("matches").then(matchesBuilder));

        return overallBuilder;
    }

    /**
     * This method creates a command to control all the rows of the 'others' table that have a complex condition.
     * @param base The base command string
     * @param stream The stream of rows
     * @param condition The condition to check
     * @return The command builder
     */
    private LiteralArgumentBuilder<CommandSourceStack> minorBuilderComplex(String base, Stream<String> stream, String condition) {
        return minorBuilder(base, stream, condition, "");
    }

    /**
     * This method creates a command to control all the rows of the 'others' table that have a simple condition.
     * @param base The base command string
     * @param stream The stream of rows
     * @return The command builder
     */
    private LiteralArgumentBuilder<CommandSourceStack> minorBuilderSimple(String base, Stream<String> stream) {
        return minorBuilder(base, stream, "%_" + base, "_" + base);
    }

    /**
     * This method creates a command to control certain rows of the 'others' table that correspond to only one registry location.
     * @param base The base command string
     * @param stream The stream of rows
     * @param condition The condition to check
     * @param ending The ending of the row name
     * @return The command builder
     */
    private LiteralArgumentBuilder<CommandSourceStack> minorBuilder(String base, Stream<String> stream, String condition, String ending) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal(base);
        stream.forEach((property) -> {
            LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
            execute(temp, "others", property + ending, "enabled", DataHandler.otherData.get(property + ending), "true", DataType.BOOLEAN);
            overallBuilder.then(literal(property).then(temp));
        });

        LiteralArgumentBuilder<CommandSourceStack> allBuilder = literal("enabled");
        execute(allBuilder, condition);
        overallBuilder.then(literal("all").then(allBuilder));

        return overallBuilder;
    }

    /**
     * This method creates a command to control all the rows of the 'others' table that correspond to multiple registries.
     * @param streams The streams of the rows
     * @return The command builder
     */
    private LiteralArgumentBuilder<CommandSourceStack> minorBuilder(ObjectList<Stream<String>> streams) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal("feature");
        streams.forEach(stream -> stream.forEach((property) -> {
            LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
            execute(temp, "others", property + "_feature", "enabled", DataHandler.otherData.get(property + "_feature"), "true", DataType.BOOLEAN);
            overallBuilder.then(literal(property).then(temp));
        }));

        LiteralArgumentBuilder<CommandSourceStack> allBuilder = literal("all");
        execute(allBuilder, "%_feature");
        overallBuilder.then(allBuilder);

        return overallBuilder;
    }
}
