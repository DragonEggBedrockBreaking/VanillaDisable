package uk.debb.vanilla_disable.config.command;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;

public class CommandConfigBaseScreen extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);
        rootComponent.child(Components.label(Component.translatable("vd.command_config.base").withStyle(ChatFormatting.BOLD))
                .margins(Insets.of(7)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.entities"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.entities, CommandDataHandler.entityData, this, "entities"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.blocks"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.blocks, CommandDataHandler.blockData, this, "blocks"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.items"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.items, CommandDataHandler.itemData, this, "items"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.enchantments"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.enchantments, CommandDataHandler.enchantmentData, this, "enchantments"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.commands"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.commands, CommandDataHandler.commandData, this, "commands"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.advancements"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.advancements, CommandDataHandler.advancementData, this, "advancements"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));

        rootComponent.child(Components.button(Component.translatable("vd.command_config.base.mob_categories"),
                (button -> Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(CommandDataHandler.mobCategories, CommandDataHandler.mobCategoryData, this, "mob_categories"))))
                .margins(Insets.of(3)).horizontalSizing(Sizing.fixed(120)).verticalSizing(Sizing.fixed(20)));
    }
}
