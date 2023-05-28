package uk.debb.vanilla_disable.command.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import it.unimi.dsi.fastutil.Pair;
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
            DataHandler.entities.forEach((entity, pair) -> {
                LiteralArgumentBuilder<CommandSourceStack> entityBuilder = literal(entity);
                DataHandler.entityData.forEach((group, info) -> {
                    List<Pair<String, String>> properties = info.stream().filter(p -> pair.first().contains(p.first())).toList();
                    if (properties.isEmpty()) return;
                    LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                    properties.forEach((property) -> {
                        LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property.first());
                        switch (DataHandler.cols.get("entities").get(property.first())) {
                            case "BOOLEAN" ->
                                    executeBool(propertyBuilder, "entities", entity, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                            case "INTEGER" ->
                                    executeInt(propertyBuilder, "entities", entity, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                            case "REAL" ->
                                    executeDouble(propertyBuilder, "entities", entity, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                        }
                        groupBuilder.then(propertyBuilder);
                    });
                    entityBuilder.then(groupBuilder);
                });
                overallEntityBuilder.then(entityBuilder);
            });

            LiteralArgumentBuilder<CommandSourceStack> overallBlockBuilder = literal("block");
            DataHandler.blocks.forEach((block, pair) -> {
                LiteralArgumentBuilder<CommandSourceStack> blockBuilder = literal(block);
                DataHandler.blockData.forEach((group, info) -> {
                    List<Pair<String, String>> properties = info.stream().filter(p -> pair.first().contains(p.first())).toList();
                    if (properties.isEmpty()) return;
                    LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                    properties.forEach((property) -> {
                        LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property.first());
                        switch (DataHandler.cols.get("blocks").get(property.first())) {
                            case "BOOLEAN" ->
                                    executeBool(propertyBuilder, "blocks", block, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                            case "INTEGER" ->
                                    executeInt(propertyBuilder, "blocks", block, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                            case "REAL" ->
                                    executeDouble(propertyBuilder, "blocks", block, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                        }
                        groupBuilder.then(propertyBuilder);
                    });
                    blockBuilder.then(groupBuilder);
                });
                overallBlockBuilder.then(blockBuilder);
            });

            LiteralArgumentBuilder<CommandSourceStack> overallItemBuilder = literal("item");
            DataHandler.items.forEach((item, pair) -> {
                LiteralArgumentBuilder<CommandSourceStack> itemBuilder = literal(item);
                DataHandler.itemData.forEach((group, info) -> {
                    List<Pair<String, String>> properties = info.stream().filter(p -> pair.first().contains(p.first())).toList();
                    if (properties.isEmpty()) return;
                    LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                    properties.forEach((property) -> {
                        LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property.first());
                        switch (DataHandler.cols.get("items").get(property.first())) {
                            case "BOOLEAN" ->
                                    executeBool(propertyBuilder, "items", item, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                            case "INTEGER" ->
                                    executeInt(propertyBuilder, "items", item, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                            case "REAL" ->
                                    executeDouble(propertyBuilder, "items", item, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())));
                        }
                        groupBuilder.then(propertyBuilder);
                    });
                    itemBuilder.then(groupBuilder);
                });
                overallItemBuilder.then(itemBuilder);
            });

            LiteralArgumentBuilder<CommandSourceStack> overallAdvancementBuilder = literal("advancement");
            DataHandler.server.getAdvancements().getAllAdvancements()
                    .stream().map(a -> a.getId().toString()).filter(a -> !a.contains("recipe")).forEach((advancement) -> {
                        LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                        executeBool(temp, "others", advancement, "enabled", DataHandler.otherData.get(advancement), "true");
                        overallAdvancementBuilder.then(literal(advancement).then(temp));
                    });

            LiteralArgumentBuilder<CommandSourceStack> overallCommandBuilder = literal("command");
            this.getDispatcher().getRoot().getChildren().stream().map(commandNode -> "/" + commandNode.getName()).forEach((command) -> {
                LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                executeBool(temp, "others", command, "enabled", DataHandler.otherData.get(command), "true");
                overallCommandBuilder.then(literal(command).then(temp));
            });

            LiteralArgumentBuilder<CommandSourceStack> overallBiomeBuilder = literal("biome");
            registryAccess.registryOrThrow(Registries.BIOME).keySet().stream().map(Object::toString).forEach((biome) -> {
                LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                executeBool(temp, "others", biome, "enabled", DataHandler.otherData.get(biome), "true");
                overallBiomeBuilder.then(literal(biome).then(temp));
            });

            LiteralArgumentBuilder<CommandSourceStack> overallStructureBuilder = literal("structure");
            registryAccess.registryOrThrow(Registries.STRUCTURE).keySet().stream().map(Object::toString).forEach((structure) -> {
                LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                executeBool(temp, "others", structure, "enabled", DataHandler.otherData.get(structure), "true");
                overallStructureBuilder.then(literal(structure).then(temp));
            });

            LiteralArgumentBuilder<CommandSourceStack> overallFeatureBuilder = literal("feature");
            BuiltInRegistries.FEATURE.keySet().stream().map(Object::toString).forEach((feature) -> {
                LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                executeBool(temp, "others", feature, "enabled", DataHandler.otherData.get(feature), "true");
                overallFeatureBuilder.then(literal(feature).then(temp));
            });
            registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet().stream().map(Object::toString).forEach((placedFeature -> {
                LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                executeBool(temp, "others", placedFeature, "enabled", DataHandler.otherData.get(placedFeature), "true");
                overallFeatureBuilder.then(literal(placedFeature).then(temp));
            }));

            this.getDispatcher().register(literal("vd").then(overallEntityBuilder).then(overallBlockBuilder).then(overallItemBuilder).then(overallAdvancementBuilder).then(overallCommandBuilder).then(overallBiomeBuilder).then(overallStructureBuilder).then(overallFeatureBuilder));
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
                argument("value", StringArgumentType.string()).suggests((context, builder) -> {
                    builder.suggest("true");
                    builder.suggest("false");
                    return builder.buildFuture();
                }).executes(context -> {
                    String value = StringArgumentType.getString(context, "value");
                    DataHandler.setValue(table, row, col, value);
                    context.getSource().sendSuccess(
                            Component.literal("Successfully set the value to " + value),
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
                argument("value", IntegerArgumentType.integer()).executes(context -> {
                    String value = String.valueOf(IntegerArgumentType.getInteger(context, "value"));
                    DataHandler.setValue(table, row, col, value);
                    context.getSource().sendSuccess(
                            Component.literal("Successfully set the value to " + value),
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
                argument("value", DoubleArgumentType.doubleArg()).executes(context -> {
                    String value = String.valueOf(DoubleArgumentType.getDouble(context, "value"));
                    DataHandler.setValue(table, row, col, value);
                    context.getSource().sendSuccess(
                            Component.literal("Successfully set the value to " + value),
                            false
                    );
                    return 1;
                })
        );
    }
}
