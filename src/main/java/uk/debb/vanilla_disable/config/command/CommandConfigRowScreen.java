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
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.TreeMap;

public class CommandConfigRowScreen extends BaseOwoScreen<FlowLayout> {
    private final Object2ObjectMap<String, Object2ObjectMap<String, String>> data;
    private final Object2ObjectMap<String, Object2ObjectMap<String, Component>> subData;
    private final Screen lastScreen;
    private final String table;
    private String searchTerm = "";

    public CommandConfigRowScreen(Object2ObjectMap<String, Object2ObjectMap<String, String>> data, Object2ObjectMap<String, Object2ObjectMap<String, Component>> subData, Screen lastScreen, String table) {
        super();
        this.data = data;
        this.subData = subData;
        this.lastScreen = lastScreen;
        this.table = table;
    }

    public CommandConfigRowScreen(Object2ObjectMap<String, Object2ObjectMap<String, String>> data, Object2ObjectMap<String, Object2ObjectMap<String, Component>> subData, Screen lastScreen, String table, String searchTerm) {
        super();
        this.data = data;
        this.subData = subData;
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
                                Component.translatable("vd.command_config.base." + this.table).withStyle(ChatFormatting.BOLD)))).margins(Insets.bottom(10)));

        FlowLayout entries = Containers.verticalFlow(Sizing.fill(100), Sizing.content());
        new TreeMap<>(data).forEach((row, rowData) -> {
            if (!row.toLowerCase().contains(this.searchTerm.toLowerCase())) return;

            Object2ObjectMap<String, Object2ObjectMap<String, Component>> relevantData = new Object2ObjectOpenHashMap<>();
            subData.forEach((group, groupData) -> groupData.forEach((col, description) -> {
                if (rowData.containsKey(col)) {
                    relevantData.putIfAbsent(group, new Object2ObjectOpenHashMap<>());
                    relevantData.get(group).put(col, groupData.get(col));
                }
            }));

            GridLayout entry = Containers.grid(Sizing.fill(100), Sizing.content(), 1, 2);
            entry.child(Components.label(Component.literal(row)), 0, 0);
            entry.child(Components.button(Component.literal("Edit"), (button ->
                    Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigColumnScreen(row, relevantData, this, table))))
                    .horizontalSizing(Sizing.fixed(60)).verticalSizing(Sizing.fixed(25)).margins(Insets.left((int) (this.width * 0.3 - 65))), 0, 1);
            entries.child(entry);
        });

        rootComponent.child(Containers.verticalScroll(Sizing.fill(65), Sizing.fill(75),
                        entries.horizontalAlignment(HorizontalAlignment.RIGHT).margins(Insets.top(10)))
                .scrollbar(ScrollContainer.Scrollbar.flat(Color.ofArgb(0xA0FFFFFF))));

        TextBoxComponent textBoxComponent = Components.textBox(Sizing.fill(65));
        textBoxComponent.setValue(this.searchTerm);
        rootComponent.child(textBoxComponent.margins(Insets.top(10)));

        GridLayout gridLayout = Containers.grid(Sizing.fill(65), Sizing.content(), 1, 2);
        gridLayout.child(Components.button(Component.translatable("vd.command.search.search"), (button ->
                Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(data, subData, this.lastScreen, table, textBoxComponent.getValue())))
        ).horizontalSizing(Sizing.fill(50)), 0, 0);
        gridLayout.child(Components.button(Component.translatable("vd.command.search.clear"), (button ->
                Objects.requireNonNull(this.minecraft).setScreen(new CommandConfigRowScreen(data, subData, this.lastScreen, table)))
        ).horizontalSizing(Sizing.fill(50)), 0, 1);
        rootComponent.child(gridLayout);
    }

    @Override
    public void onClose() {
        Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen);
    }
}
