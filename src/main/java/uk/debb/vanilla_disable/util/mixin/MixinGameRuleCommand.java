package uk.debb.vanilla_disable.util.mixin;

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
import uk.debb.vanilla_disable.util.gamerules.GameruleCategories;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(GameRuleCommand.class)
public abstract class MixinGameRuleCommand {
    /**
     * @author DragonEggBedrockBreaking
     * @param commandDispatcher the command dispatcher
     * @reason Add an extra parameter
     */
    @Overwrite
    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        final LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder = Commands.literal("gamerule").requires(arg -> arg.hasPermission(2));
        GameRules.visitGameRuleTypes(
                new GameRules.GameRuleTypeVisitor() {
                    @Override
                    public <T extends GameRules.Value<T>> void visit(GameRules.Key<T> arg, GameRules.Type<T> arg2) {
                        literalArgumentBuilder.then(
                                Commands.literal("get")
                                        .then(Commands.literal(arg.getId())
                                                .executes(commandContext -> queryRule(commandContext.getSource(), arg)))
                        ).then(
                                Commands.literal("set")
                                        .then(Commands.literal(arg.getId())
                                                .then(arg2.createArgument("value")
                                                        .executes(commandContext -> GameRuleCommand.setRule(commandContext, arg))))
                        ).then(
                                Commands.literal("list")
                                        .then(Commands.argument("name", StringArgumentType.string())
                                                .executes(commandContext -> listGamerules(commandContext.getSource(), arg, StringArgumentType.getString(commandContext, "name"))))
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
            GameruleCategories category = Maps.stringToGameruleCategoryMap.get(group);
            for (Gamerules gamerule : Gamerules.values()) {
                if (gamerule.getCategory().equals(category)) {
                    gamerules.add(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString()));
                }
            }
        } else {
            for (Gamerules gamerule : Gamerules.values()) {
                gamerules.add(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, gamerule.toString()));
            }
        }
        String joined = String.join("\n", gamerules);
        joined = "Here are the gamerules for the category:\n\n" + joined;
        source.sendSuccess(Component.literal(joined), true);
        return lv.getCommandResult();
    }

    @Unique
    private static <T extends GameRules.Value<T>> int queryRule(CommandSourceStack arg, GameRules.Key<T> arg2) {
        T lv = arg.getServer().getGameRules().getRule(arg2);
        MutableComponent description = Component.translatable(arg2.getDescriptionId() + ".description");
        if (!description.getString().equals(arg2.getDescriptionId() + ".description")) {
            arg.sendSuccess(description, false);
        }
        String id = arg2.getId();
        arg.sendSuccess(Component.translatable("commands.gamerule.query", id, lv.toString()), false);
        String defaultVal;
        if (Maps.stringToDefaultBooleanMap.containsKey(id)) {
            defaultVal = String.valueOf(Maps.stringToDefaultBooleanMap.getBoolean(id));
        } else if (Maps.stringToDefaultIntMap.containsKey(id)) {
            defaultVal = String.valueOf(Maps.stringToDefaultIntMap.getInt(id));
        } else {
            defaultVal = String.valueOf(Maps.stringToDefaultDoubleMap.getDouble(id));
        }
        arg.sendSuccess(Component.translatable("commands.gamerule.default", defaultVal), false);
        return lv.getCommandResult();
    }
}
