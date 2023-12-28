package uk.debb.vanilla_disable.config.command;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.tabs.GridLayoutTab;
import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;
import uk.debb.vanilla_disable.data.command.DataType;

import java.util.List;
import java.util.Objects;

public class CommandConfigScreen extends Screen {
    public static KeyMapping keyMapping;
    private final Screen lastScreen;
    private final TabManager tabManager = new TabManager(this::addRenderableWidget, this::removeWidget);
    private TabNavigationBar tabNavigationBar;
    private String labelText = "";
    private String category = "";
    private int setValues = 0;

    public CommandConfigScreen(Screen screen) {
        super(Component.translatable("vd.command_config").withStyle(ChatFormatting.BOLD));
        this.lastScreen = screen;
    }

    @Override
    protected void init() {
        this.tabNavigationBar = TabNavigationBar.builder(this.tabManager, this.width)
                .addTabs(new Tab(Component.translatable("vd.command_config.tab.advancements"), CommandDataHandler.advancementData, CommandDataHandler.advancements, "advancements"),
                        new Tab(Component.translatable("vd.command_config.tab.blocks"), CommandDataHandler.blockData, CommandDataHandler.blocks, "blocks"),
                        new Tab(Component.translatable("vd.command_config.tab.commands"), CommandDataHandler.commandData, CommandDataHandler.commands, "commands"),
                        new Tab(Component.translatable("vd.command_config.tab.enchantments"), CommandDataHandler.enchantmentData, CommandDataHandler.enchantments, "enchantments"),
                        new Tab(Component.translatable("vd.command_config.tab.entities"), CommandDataHandler.entityData, CommandDataHandler.entities, "entities"),
                        new Tab(Component.translatable("vd.command_config.tab.items"), CommandDataHandler.itemData, CommandDataHandler.items, "items"),
                        new Tab(Component.translatable("vd.command_config.tab.mob_categories"), CommandDataHandler.mobCategoryData, CommandDataHandler.mobCategories, "mob_categories")
                ).build();
        this.addRenderableWidget(this.tabNavigationBar);
        tabNavigationBar.selectTab(0, false);
        this.repositionElements();
    }

    @Override
    public void repositionElements() {
        if (this.tabNavigationBar != null) {
            this.tabNavigationBar.setWidth(this.width);

            int i = this.width - 28;
            int j = Mth.roundToward(i / this.tabNavigationBar.tabs.size(), 2);

            for (TabButton tabButton : this.tabNavigationBar.tabButtons) {
                tabButton.setWidth(j);
            }

            this.tabNavigationBar.layout.arrangeElements();
            this.tabNavigationBar.layout.setX(Mth.roundToward((this.width - i) / 2, 2));
            this.tabNavigationBar.layout.setY(0);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderDirtBackground(g);
        for (Renderable renderable : this.renderables) {
            renderable.render(g, mouseX, mouseY, partialTick);
        }
        ObjectSet<String> leftList = ((Tab) Objects.requireNonNull(this.tabManager.getCurrentTab())).leftList;
        if (!(leftList.contains(this.labelText) || leftList.contains("minecraft:" + this.labelText))) {
            this.labelText = "n/a";
        }
        g.drawString(Objects.requireNonNull(CommandConfigScreen.this.minecraft).font, Component.translatable("vd.command_config.selected", this.labelText), this.width / 2, 30, 0xFFFFFF);
    }

    private void refreshRightList(RightList rl) {
        String labelText = CommandConfigScreen.this.labelText;
        if (labelText.equals("n/a") || labelText.isEmpty()) {
            return;
        }
        rl.children().clear();
        String selected = labelText.contains("/") && !labelText.startsWith("/") && !labelText.contains(":") ? "minecraft:" + labelText : labelText;
        Object2ObjectMap<String, Component> tooltipData = rl.dataData.get(CommandConfigScreen.this.category);
        ObjectSet<String> cols = new ObjectOpenHashSet<>(tooltipData.keySet());
        cols.retainAll(rl.data.get(selected).keySet());
        cols.forEach(col -> {
            switch (rl.dataTypeData.get(col)) {
                case BOOLEAN -> rl.addEntry(new RightBooleanEntry(col, tooltipData.get(col), rl.table));
                case INTEGER -> rl.addEntry(new RightIntEntry(col, tooltipData.get(col), rl.table));
                case REAL -> rl.addEntry(new RightDoubleEntry(col, tooltipData.get(col), rl.table));
                case CLOB -> rl.addEntry(new RightStringEntry(col, tooltipData.get(col), rl.table));
            }
        });
    }

    @Override
    public void onClose() {
        setValues = 0;
        Objects.requireNonNull(minecraft).setScreen(lastScreen);
    }

    class Tab extends GridLayoutTab {
        public final ObjectSet<String> leftList;
        String search;

        Tab(Component title, Object2ObjectMap<String, Object2ObjectMap<String, Component>> dataData, Object2ObjectMap<String, Object2ObjectMap<String, String>> data, String colsKey) {
            super(title);
            String[] categories = dataData.keySet().toArray(new String[0]);
            ObjectSet<String> leftList = data.keySet();

            CommandConfigScreen.this.category = categories[0];
            this.leftList = leftList;

            GridLayout.RowHelper rowHelper = this.layout.rowSpacing(8).createRowHelper(1);
            int width = CommandConfigScreen.this.width;
            RightList rl = new RightList(categories.length, dataData, data, CommandDataHandler.cols.get(colsKey), colsKey);
            rowHelper.addChild(rl);
            LeftList ll = new LeftList(leftList, rl);
            rowHelper.addChild(ll);
            EditBox searchBox = getEditBox(leftList, width, ll, rl);
            rowHelper.addChild(searchBox);

            if (categories.length > 1) {
                rowHelper.addChild(CycleButton.<String>builder(string -> Component.literal(String.join(" ", string.toUpperCase().split("_"))))
                        .withValues(categories)
                        .withInitialValue(CommandConfigScreen.this.category)
                        .create(width / 2, 45, 210, 20, Component.translatable("vd.command_config.selector"), (button, value) -> {
                            CommandConfigScreen.this.category = value;
                            CommandConfigScreen.this.refreshRightList(rl);
                        }));
            }

            Button defaultsButton = Button.builder(Component.translatable("vd.command_config.defaults"), button -> {
                CommandDataHandler.resetAll();
                Objects.requireNonNull(CommandConfigScreen.this.minecraft).setScreen(new CommandConfigScreen(CommandConfigScreen.this.lastScreen));
            }).build();
            Button cancelButton = Button.builder(Component.translatable("vd.command_config.cancel"), button -> {
                CommandDataHandler.undo(CommandConfigScreen.this.setValues);
                CommandConfigScreen.this.onClose();
            }).build();
            Button doneButton = Button.builder(Component.translatable("vd.command_config.done"), button ->
                    CommandConfigScreen.this.onClose()).build();

            defaultsButton.setPosition(CommandConfigScreen.this.width / 2 - 270, CommandConfigScreen.this.height - 22);
            cancelButton.setPosition(CommandConfigScreen.this.width / 2 - 90, CommandConfigScreen.this.height - 22);
            doneButton.setPosition(CommandConfigScreen.this.width / 2 + 90, CommandConfigScreen.this.height - 22);
            rowHelper.addChild(defaultsButton);
            rowHelper.addChild(cancelButton);
            rowHelper.addChild(doneButton);
        }

        @NotNull
        private EditBox getEditBox(ObjectSet<String> leftList, int width, LeftList ll, RightList rl) {
            EditBox searchBox = new EditBox(CommandConfigScreen.this.font, 0, 25, (int) (width * 0.45), 20, Component.translatable("vd.command_config.search"));
            searchBox.setResponder(string -> {
                this.search = string;

                ll.children().clear();
                leftList.stream().sorted().forEach(leftItem -> {
                    if (leftItem.contains(this.search)) {
                        if (leftItem.contains("/") && leftItem.contains(":")) {
                            ll.addEntry(new LeftEntry(leftItem.split(":")[1], rl));
                        } else {
                            ll.addEntry(new LeftEntry(leftItem, rl));
                        }
                    }
                });
            });
            return searchBox;
        }
    }

    class LeftEntry extends ContainerObjectSelectionList.Entry<LeftEntry> {
        final Button button;
        final Component label;

        LeftEntry(String label, RightList rl) {
            this.label = Component.literal(label);
            width = CommandConfigScreen.this.width;
            this.button = Button.builder(this.label, button -> {
                CommandConfigScreen.this.labelText = label;
                if (!rl.dataData.containsKey(category)) {
                    CommandConfigScreen.this.category = rl.dataData.keySet().toArray(new String[0])[0];
                }
                CommandConfigScreen.this.refreshRightList(rl);
            }).build();
        }

        @Override
        public void render(@NotNull GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            this.button.setX(left - Math.max(0, (int) (width * 0.05)));
            this.button.setY(top);
            this.button.setWidth(width);
            this.button.setHeight(height);
            this.button.render(guiGraphics, mouseX, mouseY, partialTick);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return ImmutableList.of(button);
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return ImmutableList.of(button);
        }
    }

    class LeftList extends ContainerObjectSelectionList<LeftEntry> {
        public LeftList(ObjectSet<String> leftItems, RightList rightList) {
            super(Objects.requireNonNull(CommandConfigScreen.this.minecraft), (int) (CommandConfigScreen.this.width * 0.43), CommandConfigScreen.this.height - 75, 50, 24);
            this.setX((int) (CommandConfigScreen.this.width * 0.02));
            leftItems.stream().sorted().forEach(leftItem -> {
                if (leftItem.contains("/") && leftItem.contains(":")) {
                    this.addEntry(new LeftEntry(leftItem.split(":")[1], rightList));
                } else {
                    this.addEntry(new LeftEntry(leftItem, rightList));
                }
            });
        }
    }

    class RightEntry extends ContainerObjectSelectionList.Entry<RightEntry> {
        protected final List<AbstractWidget> children = Lists.newArrayList();
        final String main;
        final Component tooltip;

        RightEntry(String main, Component tooltip) {
            this.main = main;
            this.tooltip = tooltip;
        }

        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            int newLeft = left > 300 ? left - width / 10 : left + width / 10;
            int newMouseX = mouseX > 300 ? mouseX + width / 20 * 3 : mouseX - width / 20;
            guiGraphics.drawString(Objects.requireNonNull(CommandConfigScreen.this.minecraft).font, main, newLeft, top + 10, 0xFFFFFF);
            if (this.isMouseOver(newMouseX, mouseY)) {
                guiGraphics.renderTooltip(Objects.requireNonNull(CommandConfigScreen.this.minecraft).font, tooltip, mouseX, mouseY);
            }
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return children;
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return children;
        }
    }

    class RightBooleanEntry extends RightEntry {
        final CycleButton<Boolean> cycleButton;

        RightBooleanEntry(String main, Component tooltip, String table) {
            super(main, tooltip);
            String labelText = CommandConfigScreen.this.labelText;
            String selected = labelText.contains("/") && !labelText.startsWith("/") && !labelText.contains(":") ? "minecraft:" + labelText : labelText;
            boolean value = CommandDataHandler.getCachedBoolean(table, selected, main);

            cycleButton = CycleButton.onOffBuilder(value)
                    .displayOnlyValue()
                    .withCustomNarration(booleanCycleButton -> booleanCycleButton.createDefaultNarrationMessage().append("\n").append(main))
                    .create(10, 5, 44, 20, Component.literal(main), (button, value1) -> {
                        System.out.println(value1);
                        CommandDataHandler.setValue(table, selected, main, Boolean.toString(value1), false);
                        ++CommandConfigScreen.this.setValues;
                    });

            this.children.add(cycleButton);
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            super.render(guiGraphics, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);
            cycleButton.setX(left + width - 44);
            cycleButton.setY(top);
            cycleButton.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    class RightIntEntry extends RightEntry {
        final EditBox editBox;

        RightIntEntry(String main, Component tooltip, String table) {
            super(main, tooltip);
            String labelText = CommandConfigScreen.this.labelText;
            String selected = labelText.contains("/") && !labelText.startsWith("/") && !labelText.contains(":") ? "minecraft:" + labelText : labelText;
            int value = CommandDataHandler.getCachedInt(table, selected, main);

            editBox = new EditBox(Objects.requireNonNull(CommandConfigScreen.this.minecraft).font, 10, 5, 44, 20, Component.literal(main + "\n"));
            editBox.setValue(Integer.toString(value));
            editBox.setResponder(string -> {
                try {
                    if (Integer.parseInt(string) >= 0 &&
                            (!CommandDataHandler.intRowMaximums.containsKey(main) ||
                                    Integer.parseInt(string) <= CommandDataHandler.intRowMaximums.getInt(main))) {
                        editBox.setTextColor(14737632);
                        CommandDataHandler.setValue(table, selected, main, string, false);
                        ++CommandConfigScreen.this.setValues;
                    } else {
                        editBox.setTextColor(16711680);
                    }
                } catch (NumberFormatException ignored) {
                    editBox.setTextColor(16711680);
                }
            });

            this.children.add(editBox);
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            super.render(guiGraphics, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);
            this.editBox.setX(left + width - 44);
            this.editBox.setY(top);
            this.editBox.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    class RightDoubleEntry extends RightEntry {
        final EditBox editBox;

        RightDoubleEntry(String main, Component tooltip, String table) {
            super(main, tooltip);
            String labelText = CommandConfigScreen.this.labelText;
            String selected = labelText.contains("/") && !labelText.startsWith("/") && !labelText.contains(":") ? "minecraft:" + labelText : labelText;
            double value = CommandDataHandler.getCachedDouble(table, selected, main);

            editBox = new EditBox(Objects.requireNonNull(CommandConfigScreen.this.minecraft).font, 10, 5, 44, 20, Component.literal(main + "\n"));
            editBox.setValue(Double.toString(value));
            editBox.setResponder(string -> {
                try {
                    if (Double.parseDouble(string) >= 0 &&
                            (!CommandDataHandler.doubleRowMaximums.containsKey(main) ||
                                    Double.parseDouble(string) <= CommandDataHandler.doubleRowMaximums.getDouble(main))) {
                        editBox.setTextColor(14737632);
                        CommandDataHandler.setValue(table, selected, main, string, false);
                        ++CommandConfigScreen.this.setValues;
                    } else {
                        editBox.setTextColor(16711680);
                    }
                } catch (NumberFormatException ignored) {
                    editBox.setTextColor(16711680);
                }
            });

            this.children.add(editBox);
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            super.render(guiGraphics, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);
            this.editBox.setX(left + width - 44);
            this.editBox.setY(top);
            this.editBox.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    class RightStringEntry extends RightEntry {
        final Button button;

        RightStringEntry(String main, Component tooltip, String table) {
            super(main, tooltip);
            String labelText = CommandConfigScreen.this.labelText;
            String selected = labelText.contains("/") && !labelText.startsWith("/") && !labelText.contains(":") ? "minecraft:" + labelText : labelText;
            String value = CommandDataHandler.getCachedString(table, selected, main);

            List<String> possible = CommandDataHandler.stringColSuggestions.get(main);
            button = Button.builder(Component.literal(value), (buttonWidget) -> {
                int currentIndex = possible.indexOf(buttonWidget.getMessage().getString());
                if (currentIndex == possible.size() - 1) {
                    currentIndex = 0;
                } else {
                    ++currentIndex;
                }
                String newVal = possible.get(currentIndex);
                buttonWidget.setMessage(Component.literal(newVal));
                CommandDataHandler.setValue(table, selected, main, newVal, true);
                ++CommandConfigScreen.this.setValues;
            }).pos(10, 5).size(66, 20).build();
            this.children.add(button);
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            super.render(guiGraphics, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);
            button.setX(left + width - 66);
            button.setY(top);
            button.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    class RightList extends ContainerObjectSelectionList<RightEntry> {
        final Object2ObjectMap<String, Object2ObjectMap<String, Component>> dataData;
        final Object2ObjectMap<String, Object2ObjectMap<String, String>> data;
        final Object2ObjectMap<String, DataType> dataTypeData;
        final String table;

        public RightList(int numCategories, Object2ObjectMap<String, Object2ObjectMap<String, Component>> dataData, Object2ObjectMap<String, Object2ObjectMap<String, String>> data, Object2ObjectMap<String, DataType> dataTypeData, String table) {
            super(Objects.requireNonNull(CommandConfigScreen.this.minecraft), (int) (CommandConfigScreen.this.width * 0.48), CommandConfigScreen.this.height - (numCategories <= 1 ? 75 : 100), numCategories <= 1 ? 50 : 75, 24);
            this.setX(CommandConfigScreen.this.width / 2);
            this.dataData = dataData;
            this.data = data;
            this.dataTypeData = dataTypeData;
            this.table = table;
        }

        @Override
        protected int getScrollbarPosition() {
            return (int) (CommandConfigScreen.this.width * 0.75 + 150);
        }
    }
}
