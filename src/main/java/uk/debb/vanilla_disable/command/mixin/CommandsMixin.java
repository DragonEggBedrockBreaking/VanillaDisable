package uk.debb.vanilla_disable.command.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
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

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Mixin(Commands.class)
public abstract class CommandsMixin {
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
            while (DataHandler.itemData.isEmpty()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            RegistryAccess registryAccess = DataHandler.server.registryAccess();

            LiteralArgumentBuilder<CommandSourceStack> overallEntityBuilder = literal("entity");
            majorBuilder(overallEntityBuilder, DataHandler.entities, DataHandler.entityData, "entities", true);

            LiteralArgumentBuilder<CommandSourceStack> overallBlockBuilder = literal("block");
            majorBuilder(overallBlockBuilder, DataHandler.blocks, DataHandler.blockData, "blocks", false);

            LiteralArgumentBuilder<CommandSourceStack> overallItemBuilder = literal("item");
            majorBuilder(overallItemBuilder, DataHandler.items, DataHandler.itemData, "items", true);

            LiteralArgumentBuilder<CommandSourceStack> overallAdvancementBuilder = literal("advancement");
            minorBuilder(overallAdvancementBuilder, DataHandler.server.getAdvancements().getAllAdvancements()
                    .stream().map(a -> a.getId().toString()).filter(a -> !a.contains("recipe")));

            LiteralArgumentBuilder<CommandSourceStack> overallCommandBuilder = literal("command");
            minorBuilder(overallCommandBuilder, this.getDispatcher().getRoot().getChildren().stream().map(commandNode -> "/" + commandNode.getName()));

            LiteralArgumentBuilder<CommandSourceStack> overallBiomeBuilder = literal("biome");
            minorBuilder(overallBiomeBuilder, registryAccess.registryOrThrow(Registries.BIOME).keySet().stream().map(Object::toString));

            LiteralArgumentBuilder<CommandSourceStack> overallStructureBuilder = literal("structure");
            minorBuilder(overallStructureBuilder, registryAccess.registryOrThrow(Registries.STRUCTURE).keySet().stream().map(Object::toString));

            LiteralArgumentBuilder<CommandSourceStack> overallFeatureBuilder = literal("feature");
            minorBuilder(overallFeatureBuilder, BuiltInRegistries.FEATURE.keySet().stream().map(Object::toString));
            minorBuilder(overallFeatureBuilder, registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet().stream().map(Object::toString));

            LiteralArgumentBuilder<CommandSourceStack> forceUpdateDB = literal("forceUpdateDB").executes(context -> {
                DataHandler.forceUpdateDB();
                context.getSource().sendSuccess(
                        Component.literal("Close and re-open the world. The database will be forcefully updated. Run this if you added/updated other mods and want changes to be reflected. You can cancel this with \"/vd forceUpdateDB cancel\"."),
                        false
                );
                return 1;
            });
            forceUpdateDB.then(literal("cancel").executes(context -> {
                DataHandler.resetDBMeta();
                context.getSource().sendSuccess(
                        Component.literal("The force update has been cancelled."),
                        false
                );
                return 1;
            }));

            this.getDispatcher().register(literal("vd").then(overallEntityBuilder).then(overallBlockBuilder).then(overallItemBuilder).then(overallAdvancementBuilder).then(overallCommandBuilder).then(overallBiomeBuilder).then(overallStructureBuilder).then(overallFeatureBuilder).then(forceUpdateDB));
        });
        t.start();
    }

    private void executeBool(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, String description, String defaultValue) {
        literalArgumentBuilder.executes(context -> {
            boolean value = DataHandler.getBoolean(table, row, col);
            context.getSource().sendSuccess(
                    Component.literal(description + "\nThe current value is: " + value + "\nThe default value is: " + defaultValue),
                    false
            );
            return 1;
        }).then(
                argument("value", BoolArgumentType.bool()).executes(context -> {
                    String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
                    DataHandler.setValue(table, row, col, value);
                    context.getSource().sendSuccess(
                            Component.literal("Successfully set the value to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    private void executeInt(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, String description, String defaultValue) {
        literalArgumentBuilder.executes(context -> {
            int value = DataHandler.getInt(table, row, col);
            context.getSource().sendSuccess(
                    Component.literal(description + "\nThe current value is: " + value + "\nThe default value is: " + defaultValue),
                    false
            );
            return 1;
        }).then(
                argument("value", IntegerArgumentType.integer(0, DataHandler.intRowMaximums.getOrDefault(col, Integer.MAX_VALUE))).executes(context -> {
                    String value = String.valueOf(IntegerArgumentType.getInteger(context, "value"));
                    DataHandler.setValue(table, row, col, value);
                    context.getSource().sendSuccess(
                            Component.literal("Successfully set the value to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    private void executeDouble(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, String description, String defaultValue) {
        literalArgumentBuilder.executes(context -> {
            double value = DataHandler.getDouble(table, row, col);
            context.getSource().sendSuccess(
                    Component.literal(description + "\nThe current value is: " + value + "\nThe default value is: " + defaultValue),
                    false
            );
            return 1;
        }).then(
                argument("value", DoubleArgumentType.doubleArg(0.0, DataHandler.doubleRowMaximums.getOrDefault(col, Double.MAX_VALUE))).executes(context -> {
                    String value = String.valueOf(DoubleArgumentType.getDouble(context, "value"));
                    DataHandler.setValue(table, row, col, value);
                    context.getSource().sendSuccess(
                            Component.literal("Successfully set the value to " + value + "."),
                            false
                    );
                    return 1;
                })
        );
    }

    private void allCols(LiteralArgumentBuilder<CommandSourceStack> groupBuilder, String table, String row, String group, ObjectList<Pair<String, String>> info, ObjectList<String> possible) {
        groupBuilder.then(literal("all").then(argument("value", BoolArgumentType.bool()).executes(context -> {
            String value = String.valueOf(BoolArgumentType.getBool(context, "value"));
            info.stream().map(Pair::first).filter(possible::contains).forEach((groupProperty ->
                    DataHandler.setValue(table, row, groupProperty, value)));
            context.getSource().sendSuccess(
                    Component.literal("Successfully set the value of all " +  group + " properties to " + value + "."),
                    false
            );
            return 1;
        })));
    }

    private void majorBuilder(LiteralArgumentBuilder<CommandSourceStack> overallBuilder, Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> data, Object2ObjectMap<String, ObjectList<Pair<String, String>>> otherData, String table, boolean includeOverall) {
        data.forEach((row, pair) -> {
            LiteralArgumentBuilder<CommandSourceStack> rowBuilder = literal(row);
            otherData.forEach((group, info) -> {
                List<Pair<String, String>> properties = info.stream().filter(p -> pair.first().contains(p.first())).toList();
                if (properties.isEmpty()) return;
                LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                properties.forEach((property) -> {
                    LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property.first());
                    switch (DataHandler.cols.get(table).get(property.first())) {
                        case "BOOLEAN" ->
                                executeBool(propertyBuilder, table, row, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                        case "INTEGER" ->
                                executeInt(propertyBuilder, table, row, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                        case "REAL" ->
                                executeDouble(propertyBuilder, table, row, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                    }
                    groupBuilder.then(propertyBuilder);
                });
                if (includeOverall && !group.equals("others")) {
                    allCols(groupBuilder, table, row, group, info, pair.first());
                }
                rowBuilder.then(groupBuilder);
            });
            overallBuilder.then(rowBuilder);
        });
    }

    private void minorBuilder(LiteralArgumentBuilder<CommandSourceStack> overallBuilder, Stream<String> stream) {
        stream.forEach((property) -> {
            LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
            executeBool(temp, "others", property, "enabled", DataHandler.otherData.get(property), "true");
            overallBuilder.then(literal(property).then(temp));
        });
    }
}
