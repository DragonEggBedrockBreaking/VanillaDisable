package uk.debb.vanilla_disable.config.command;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.TextBoxComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.GridLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandConfigColumnScreen extends BaseOwoScreen<FlowLayout> {
    private final String row;
    private final Object2ObjectMap<String, Object2ObjectMap<String, Component>> data;
    private final Screen lastScreen;
    private final String table;
    private String searchTerm = "";

    public CommandConfigColumnScreen(String row, Object2ObjectMap<String, Object2ObjectMap<String, Component>> data, Screen lastScreen, String table) {
        super();
        this.row = row;
        this.data = data;
        this.lastScreen = lastScreen;
        this.table = table;
    }

    public CommandConfigColumnScreen(String row, Object2ObjectMap<String, Object2ObjectMap<String, Component>> data, Screen lastScreen, String table, String searchTerm) {
        super();
        this.row = row;
        this.data = data;
        this.lastScreen = lastScreen;
        this.table = table;
        this.searchTerm = searchTerm;
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        rootComponent.child(Components.label(
                Component.translatable("vd.command_config.base").withStyle(ChatFormatting.BOLD).append(
                        Component.literal(" - ").withStyle(ChatFormatting.BOLD).append(
                                Component.translatable("vd.command_config.base." + this.table).withStyle(ChatFormatting.BOLD).append(
                                        Component.literal(" - ").withStyle(ChatFormatting.BOLD).append(
                                                Component.literal(this.row).withStyle(ChatFormatting.BOLD)))))).margins(Insets.bottom(10)));

        FlowLayout overall = Containers.verticalFlow(Sizing.fill(100), Sizing.content());
        new TreeMap<>(this.data).forEach((group, groupData) -> {
            FlowLayout entries = Containers.verticalFlow(Sizing.fill(100), Sizing.content());
            new TreeMap<>(groupData).forEach((col, description) -> {
                if (!col.toLowerCase().contains(this.searchTerm.toLowerCase())) return;

                GridLayout entry = Containers.grid(Sizing.fill(100), Sizing.content(), 1, 2);
                entry.child(Components.label(Component.literal(col)), 0, 0);

                switch (CommandDataHandler.cols.get(this.table).get(col)) {
                    case BOOLEAN -> {
                        AtomicBoolean value = new AtomicBoolean(CommandDataHandler.getCachedBoolean(this.table, this.row, col));
                        entry.child(Components.button(Component.literal(Boolean.toString(value.get())), (button -> {
                            value.set(!value.get());
                            CommandDataHandler.setValue(this.table, this.row, col, Boolean.toString(value.get()), false);
                            button.setMessage(Component.literal(Boolean.toString(value.get())));
                        })).horizontalSizing(Sizing.fixed(60)).verticalSizing(Sizing.fixed(25)).margins(Insets.left((int) (this.width * 0.3 - 73))), 0, 1);
                    }

                    case INTEGER -> {
                        int value = CommandDataHandler.getCachedInt(this.table, this.row, col);
                        TextBoxComponent textBoxComponent = Components.textBox(Sizing.fixed(57)).text(Integer.toString(value));
                        textBoxComponent.onChanged().subscribe((event) -> {
                            try {
                                if (Integer.parseInt(textBoxComponent.getValue()) >= 0 &&
                                        (!CommandDataHandler.intRowMaximums.containsKey(col) ||
                                                Integer.parseInt(textBoxComponent.getValue()) <= CommandDataHandler.intRowMaximums.getInt(col))) {
                                    CommandDataHandler.setValue(this.table, this.row, col, textBoxComponent.getValue(), false);
                                    textBoxComponent.setTextColor(14737632);
                                } else {
                                    textBoxComponent.setTextColor(16733525);
                                }
                            } catch (NumberFormatException ignored) {
                                textBoxComponent.setTextColor(16733525);
                            }
                        });
                        entry.child(textBoxComponent.margins(Insets.left((int) (this.width * 0.3 - 71))), 0, 1);
                    }

                    case REAL -> {
                        double value = CommandDataHandler.getCachedDouble(this.table, this.row, col);
                        TextBoxComponent textBoxComponent = Components.textBox(Sizing.fixed(57)).text(Double.toString(value));
                        textBoxComponent.onChanged().subscribe((event) -> {
                            try {
                                if (Double.parseDouble(textBoxComponent.getValue()) >= 0 &&
                                        (!CommandDataHandler.doubleRowMaximums.containsKey(col) ||
                                                Double.parseDouble(textBoxComponent.getValue()) <= CommandDataHandler.doubleRowMaximums.getDouble(col))) {
                                    CommandDataHandler.setValue(this.table, this.row, col, textBoxComponent.getValue(), false);
                                    textBoxComponent.setTextColor(14737632);
                                } else {
                                    textBoxComponent.setTextColor(16733525);
                                }
                            } catch (NumberFormatException ignored) {
                                textBoxComponent.setTextColor(16733525);
                            }
                        });
                        entry.child(textBoxComponent.margins(Insets.left((int) (this.width * 0.3 - 71))), 0, 1);
                    }

                    case CLOB -> {
                        String value = CommandDataHandler.getCachedString(this.table, this.row, col);
                        List<String> possible = CommandDataHandler.stringColSuggestions.get(col);
                        entry.child(Components.button(Component.literal(value), (button -> {
                            String current = button.getMessage().getString();
                            CommandDataHandler.setValue(this.table, this.row, col, current, true);
                            if (current.equals(possible.get(possible.size() - 1))) {
                                current = possible.get(0);
                            } else {
                                current = possible.get(possible.indexOf(current) + 1);
                            }
                            button.setMessage(Component.literal(current));
                        })).horizontalSizing(Sizing.fixed(60)).verticalSizing(Sizing.fixed(25)).margins(Insets.left((int) (this.width * 0.3 - 73))), 0, 1);
                    }
                }
                entries.child(entry.tooltip(description));
            });
            if (entries.children().size() > 0) {
                overall.child(Containers.collapsible(Sizing.fill(100), Sizing.content(), Component.literal(group).withStyle(ChatFormatting.GREEN), true).child(entries));
            }
        });
        rootComponent.child(Containers.verticalScroll(Sizing.fill(60), Sizing.fill(75), overall)
                .scrollbar(ScrollContainer.Scrollbar.flat(Color.ofArgb(0xA0FFFFFF))));

        TextBoxComponent textBoxComponent = Components.textBox(Sizing.fill(60));
        textBoxComponent.setValue(this.searchTerm);
        rootComponent.child(textBoxComponent.margins(Insets.top(10)));

        GridLayout gridLayout = Containers.grid(Sizing.fill(60), Sizing.content(), 1, 2);
        gridLayout.child(Components.button(Component.translatable("vd.command.search.search"), (button ->
                Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigColumnScreen(this.row, this.data, this.lastScreen, this.table, textBoxComponent.getValue())))
        ).horizontalSizing(Sizing.fill(50)), 0, 0);
        gridLayout.child(Components.button(Component.translatable("vd.command.search.clear"), (button ->
                Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigColumnScreen(this.row, this.data, this.lastScreen, this.table)))
        ).horizontalSizing(Sizing.fill(50)), 0, 1);
        rootComponent.child(gridLayout);
    }

    @Override
    public void onClose() {
        Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen);
    }
}
