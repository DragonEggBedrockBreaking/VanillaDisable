package uk.debb.vanilla_disable.config.worldgen;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.*;

public class WorldgenConfigScreen extends Screen {
    private final CreateWorldScreen lastScreen;
    private final String[] extraPlacedFeatures = {"obsidian_platform", "end_spike_cage"};
    private Set<ResourceLocation> biomes;
    private Set<ResourceLocation> structures;
    private Set<ResourceLocation> placedFeatures;
    private String search = "";

    public WorldgenConfigScreen(CreateWorldScreen lastScreen) {
        super(Component.translatable("vd.worldgen_config"));
        this.lastScreen = lastScreen;
    }

    @Override
    protected void init() {
        RegistryAccess.Frozen registryAccess = this.lastScreen.getUiState().getSettings().worldgenRegistries().compositeAccess();
        biomes = registryAccess.registryOrThrow(Registries.BIOME).keySet();
        structures = registryAccess.registryOrThrow(Registries.STRUCTURE).keySet();
        placedFeatures = registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet();
        WorldgenConfigList worldgenConfigList = new WorldgenConfigList();
        this.addRenderableWidget(worldgenConfigList);

        EditBox searchBox = new EditBox(this.font, this.width / 2 - 100, 40, 200, 20, Component.translatable("vd.worldgen_config.search"));
        searchBox.setValue(this.search);
        searchBox.setResponder(string -> {
            this.search = string;
            this.rebuildWidgets();
            this.setFocused(searchBox);
        });
        this.addRenderableWidget(searchBox);

        Button resetButton = Button.builder(Component.translatable("vd.worldgen_config.reset_all"), (button) -> {
            this.biomes.forEach(biome -> WorldgenDataHandler.biomeMap.put(WorldgenDataHandler.cleanup(biome), true));
            this.structures.forEach(structure -> WorldgenDataHandler.structureMap.put(WorldgenDataHandler.cleanup(structure), true));
            this.placedFeatures.forEach(placedFeature -> WorldgenDataHandler.placedFeatureMap.put(WorldgenDataHandler.cleanup(placedFeature), true));
            Arrays.stream(extraPlacedFeatures).forEach(placedFeature -> WorldgenDataHandler.placedFeatureMap.put(placedFeature, true));
            worldgenConfigList.refreshEntries();
        }).width(80).build();
        Button disableButton = Button.builder(Component.translatable("vd.worldgen_config.disable_all"), (button) -> {
            this.biomes.forEach(biome -> WorldgenDataHandler.biomeMap.put(WorldgenDataHandler.cleanup(biome), false));
            this.structures.forEach(structure -> WorldgenDataHandler.structureMap.put(WorldgenDataHandler.cleanup(structure), false));
            this.placedFeatures.forEach(placedFeature -> WorldgenDataHandler.placedFeatureMap.put(WorldgenDataHandler.cleanup(placedFeature), false));
            Arrays.stream(extraPlacedFeatures).forEach(placedFeature -> WorldgenDataHandler.placedFeatureMap.put(placedFeature, false));
            worldgenConfigList.refreshEntries();
        }).width(80).build();
        Button doneButton = Button.builder(Component.translatable("vd.worldgen_config.done"), (button) -> {
            this.onClose();
            worldgenConfigList.refreshEntries();
        }).width(80).build();

        resetButton.setPosition(this.width / 2 - 150, this.height - 25);
        disableButton.setPosition(this.width / 2 - 50, this.height - 25);
        doneButton.setPosition(this.width / 2 + 50, this.height - 25);
        this.addRenderableWidget(resetButton);
        this.addRenderableWidget(disableButton);
        this.addRenderableWidget(doneButton);
    }

    @Override
    public void render(@NotNull GuiGraphics g, int i, int j, float f) {
        super.render(g, i, j, f);
        g.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
    }

    @Override
    public void onClose() {
        Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen);
    }

    enum WorldgenConfigType {
        BIOME,
        STRUCTURE,
        PLACED_FEATURE
    }

    abstract static class WorldgenConfigEntry extends ContainerObjectSelectionList.Entry<WorldgenConfigScreen.WorldgenConfigEntry> {
        protected final List<AbstractWidget> children = Lists.newArrayList();
        protected final List<String> childrenNames = Lists.newArrayList();

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return this.children;
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return this.children;
        }
    }

    class WorldgenConfigCategoryEntry extends WorldgenConfigScreen.WorldgenConfigEntry {
        private final MutableComponent name;
        private final Button button;

        WorldgenConfigCategoryEntry(MutableComponent name, WorldgenConfigType type, WorldgenConfigList list) {
            this.name = name;
            this.button = Button.builder(Component.translatable("vd.worldgen_config.toggle_all"), button -> {
                switch (type) {
                    case BIOME -> {
                        boolean val = Collections.frequency(WorldgenDataHandler.biomeMap.values(), false) < WorldgenConfigScreen.this.biomes.size() / 2;
                        WorldgenConfigScreen.this.biomes.forEach(biome -> WorldgenDataHandler.biomeMap.put(WorldgenDataHandler.cleanup(biome), !val));
                    }
                    case STRUCTURE -> {
                        boolean val = Collections.frequency(WorldgenDataHandler.structureMap.values(), false) < WorldgenConfigScreen.this.structures.size() / 2;
                        WorldgenConfigScreen.this.structures.forEach(structure -> WorldgenDataHandler.structureMap.put(WorldgenDataHandler.cleanup(structure), !val));
                    }
                    case PLACED_FEATURE -> {
                        boolean val = Collections.frequency(WorldgenDataHandler.placedFeatureMap.values(), false) < WorldgenConfigScreen.this.placedFeatures.size() / 2;
                        WorldgenConfigScreen.this.placedFeatures.forEach(placedFeature -> WorldgenDataHandler.placedFeatureMap.put(WorldgenDataHandler.cleanup(placedFeature), !val));
                        Arrays.stream(extraPlacedFeatures).forEach(placedFeature -> WorldgenDataHandler.placedFeatureMap.put(placedFeature, !val));
                    }
                }
                list.refreshEntries();
            }).width(100).build();
            this.children.add(this.button);
        }

        @Override
        public void render(@NotNull GuiGraphics g, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
            g.drawString(WorldgenConfigScreen.this.font, this.name.withStyle(ChatFormatting.BOLD, ChatFormatting.DARK_GREEN), k, j + 15, 16777215);
            this.button.setX(k + 125);
            this.button.setY(j + 5);
            this.button.render(g, i, j, f);
        }
    }

    class WorldgenConfigToggleEntry extends WorldgenConfigScreen.WorldgenConfigEntry {
        private final Checkbox checkbox;

        WorldgenConfigToggleEntry(String name, WorldgenConfigType type) {
            String clean = WorldgenDataHandler.cleanup(name);
            boolean value = WorldgenDataHandler.biomeMap.getOrDefault(clean, true) && WorldgenDataHandler.structureMap.getOrDefault(clean, true) && WorldgenDataHandler.placedFeatureMap.getOrDefault(clean, true);
            this.checkbox = Checkbox.builder(Component.literal(name), WorldgenConfigScreen.this.font).selected(value).onValueChange((checkbox, newVal) -> {
                switch (type) {
                    case BIOME -> WorldgenDataHandler.biomeMap.put(clean, newVal);
                    case STRUCTURE -> WorldgenDataHandler.structureMap.put(clean, newVal);
                    case PLACED_FEATURE -> WorldgenDataHandler.placedFeatureMap.put(clean, newVal);
                }
            }).build();
            this.children.add(this.checkbox);
            this.childrenNames.add(name);
        }

        @Override
        public void render(@NotNull GuiGraphics g, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
            this.checkbox.setX(k);
            this.checkbox.setY(j + 10);
            this.checkbox.render(g, i, j, f);
        }
    }

    class WorldgenConfigList extends ContainerObjectSelectionList<WorldgenConfigScreen.WorldgenConfigEntry> {
        WorldgenConfigList() {
            super(Objects.requireNonNull(WorldgenConfigScreen.this.minecraft), WorldgenConfigScreen.this.width, WorldgenConfigScreen.this.height - 100, 68, 24);

            this.addEntry(new WorldgenConfigCategoryEntry(Component.translatable("vd.worldgen_config.category.biomes"), WorldgenConfigType.BIOME, this));
            WorldgenConfigScreen.this.biomes.stream().sorted().forEach(biome -> {
                if (biome.toString().equals("minecraft:plains")) return;
                if (biome.toString().equals("minecraft:the_nether")) return;
                if (biome.toString().equals("minecraft:the_end")) return;
                if (biome.toString().equals("minecraft:the_void")) return;
                if (biome.toString().contains(WorldgenConfigScreen.this.search)) {
                    this.addEntry(new WorldgenConfigToggleEntry(biome.toString(), WorldgenConfigType.BIOME));
                }
            });

            this.addEntry(new WorldgenConfigCategoryEntry(Component.translatable("vd.worldgen_config.category.structures"), WorldgenConfigType.STRUCTURE, this));
            WorldgenConfigScreen.this.structures.stream().sorted().forEach(structure -> {
                if (structure.toString().contains(WorldgenConfigScreen.this.search)) {
                    this.addEntry(new WorldgenConfigToggleEntry(structure.toString(), WorldgenConfigType.STRUCTURE));
                }
            });

            this.addEntry(new WorldgenConfigCategoryEntry(Component.translatable("vd.worldgen_config.category.placed_features"), WorldgenConfigType.PLACED_FEATURE, this));
            WorldgenConfigScreen.this.placedFeatures.stream().sorted().forEach(placedFeature -> {
                if (placedFeature.toString().contains(WorldgenConfigScreen.this.search)) {
                    this.addEntry(new WorldgenConfigToggleEntry(placedFeature.toString(), WorldgenConfigType.PLACED_FEATURE));
                }
            });
            Arrays.stream(extraPlacedFeatures).sorted().forEach(placedFeature -> {
                String adjustedPlacedFeature = "minecraft_unofficial:" + placedFeature;
                if (adjustedPlacedFeature.contains(WorldgenConfigScreen.this.search)) {
                    this.addEntry(new WorldgenConfigToggleEntry(adjustedPlacedFeature, WorldgenConfigType.PLACED_FEATURE));
                }
            });
        }

        public void refreshEntries() {
            this.children().forEach(child -> {
                if (child instanceof WorldgenConfigToggleEntry) {
                    int i = 0;
                    for (GuiEventListener c : child.children()) {
                        if (c instanceof Checkbox) {
                            String clean = WorldgenDataHandler.cleanup(child.childrenNames.get(i));
                            ((Checkbox) c).selected = WorldgenDataHandler.biomeMap.getOrDefault(clean, true) && WorldgenDataHandler.structureMap.getOrDefault(clean, true) && WorldgenDataHandler.placedFeatureMap.getOrDefault(clean, true);
                            ++i;
                        }
                    }
                }
            });
        }
    }
}
