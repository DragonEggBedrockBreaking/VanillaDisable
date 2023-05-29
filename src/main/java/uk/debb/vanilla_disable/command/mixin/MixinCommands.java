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
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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
            while (DataHandler.itemData.isEmpty()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            RegistryAccess registryAccess = DataHandler.server.registryAccess();

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

            this.getDispatcher().register(literal("vd")
                    .then(majorBuilder("entity", DataHandler.entities, DataHandler.entityData, "entities", true))
                    .then(majorBuilder("block", DataHandler.blocks, DataHandler.blockData, "blocks", false))
                    .then(majorBuilder("item", DataHandler.items, DataHandler.itemData, "items", true))
                    .then(minorBuilder("advancement", DataHandler.server.getAdvancements().getAllAdvancements()
                            .stream().map(a -> a.getId().toString()).filter(a -> !a.contains("recipe"))))
                    .then(minorBuilder("command", this.getDispatcher().getRoot().getChildren().stream().map(commandNode -> "/" + commandNode.getName())))
                    .then(minorBuilder("biome", registryAccess.registryOrThrow(Registries.BIOME).keySet().stream().map(Object::toString)))
                    .then(minorBuilder("structure", registryAccess.registryOrThrow(Registries.STRUCTURE).keySet().stream().map(Object::toString)))
                    .then(minorBuilder("feature", new ObjectArrayList<>() {{
                        add(BuiltInRegistries.FEATURE.keySet().stream().map(Object::toString));
                        add(registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet().stream().map(Object::toString));
                    }}))
                    .then(forceUpdateDB)
            );
        });
        t.start();
    }

    private void execute(LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder, String table, String row, String col, String description, String defaultValue, DataType type) {
        ArgumentType<?> argumentType = switch (type) {
            case BOOLEAN -> BoolArgumentType.bool();
            case INTEGER ->
                    IntegerArgumentType.integer(0, DataHandler.intRowMaximums.getOrDefault(col, Integer.MAX_VALUE));
            case REAL ->
                    DoubleArgumentType.doubleArg(0.0, DataHandler.doubleRowMaximums.getOrDefault(col, Double.MAX_VALUE));
        };
        literalArgumentBuilder.executes(context -> {
            String value = switch (type) {
                case BOOLEAN -> String.valueOf(DataHandler.getBoolean(table, row, col));
                case INTEGER -> String.valueOf(DataHandler.getInt(table, row, col));
                case REAL -> String.valueOf(DataHandler.getDouble(table, row, col));
            };
            context.getSource().sendSuccess(
                    Component.literal(description + "\nThe current value is: " + value + "\nThe default value is: " + defaultValue),
                    false
            );
            return 1;
        }).then(
                argument("value", argumentType).executes(context -> {
                    String value = switch (type) {
                        case BOOLEAN -> String.valueOf(BoolArgumentType.getBool(context, "value"));
                        case INTEGER -> String.valueOf(IntegerArgumentType.getInteger(context, "value"));
                        case REAL -> String.valueOf(DoubleArgumentType.getDouble(context, "value"));
                    };
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

    private LiteralArgumentBuilder<CommandSourceStack> majorBuilder(String base, Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> data, Object2ObjectMap<String, ObjectList<Pair<String, String>>> otherData, String table, boolean includeOverall) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal(base);
        data.forEach((row, pair) -> {
            LiteralArgumentBuilder<CommandSourceStack> rowBuilder = literal(row);
            otherData.forEach((group, info) -> {
                List<Pair<String, String>> properties = info.stream().filter(p -> pair.first().contains(p.first())).toList();
                if (properties.isEmpty()) return;
                LiteralArgumentBuilder<CommandSourceStack> groupBuilder = literal(group);
                properties.forEach((property) -> {
                    LiteralArgumentBuilder<CommandSourceStack> propertyBuilder = literal(property.first());
                    execute(propertyBuilder, table, row, property.first(), property.second(), pair.second().get(pair.first().indexOf(property.first())),
                            DataHandler.cols.get(table).get(property.first()));
                    groupBuilder.then(propertyBuilder);
                });
                if (includeOverall && !group.equals("other")) {
                    allCols(groupBuilder, table, row, group, info, pair.first());
                }
                rowBuilder.then(groupBuilder);
            });
            overallBuilder.then(rowBuilder);
        });
        return overallBuilder;
    }

    private LiteralArgumentBuilder<CommandSourceStack> minorBuilder(String base, Stream<String> stream) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal(base);
        stream.forEach((property) -> {
            LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
            execute(temp, "others", property, "enabled", DataHandler.otherData.get(property), "true", DataType.BOOLEAN);
            overallBuilder.then(literal(property).then(temp));
        });
        return overallBuilder;
    }

    private LiteralArgumentBuilder<CommandSourceStack> minorBuilder(String base, ObjectList<Stream<String>> streams) {
        LiteralArgumentBuilder<CommandSourceStack> overallBuilder = literal(base);
        streams.forEach(stream -> {
            stream.forEach((property) -> {
                LiteralArgumentBuilder<CommandSourceStack> temp = literal("enabled");
                execute(temp, "others", property, "enabled", DataHandler.otherData.get(property), "true", DataType.BOOLEAN);
                overallBuilder.then(literal(property).then(temp));
            });
        });
        return overallBuilder;
    }
}
