package uk.debb.vanilla_disable.config.worldgen;

import com.moandjiezana.toml.Toml;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.Objects;

public class WorldgenConfigScreen extends Screen {
    private final Screen lastScreen;

    public WorldgenConfigScreen(Screen lastScreen) {
        super(Component.translatable("vd.worldgen_config").withStyle(ChatFormatting.BOLD));
        this.lastScreen = lastScreen;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(
                new Button(
                        width / 2 - 240, height / 8 * 5, 150, 20,
                        Component.translatable("vd.worldgen_config.open_directory"),
                        (button) -> Util.getPlatform().openUri(WorldgenDataHandler.DIRECTORY.toURI()), null
                ) {
                }
        );
        this.addRenderableWidget(
                new Button(
                        width / 2 - 80, height / 8 * 5, 150, 20,
                        Component.translatable("vd.worldgen_config.open_file"),
                        (button) -> Util.getPlatform().openFile(WorldgenDataHandler.PATH), null
                ) {
                }
        );
        this.addRenderableWidget(
                new Button(
                        width / 2 + 80, height / 8 * 5, 150, 20,
                        Component.translatable("vd.worldgen_config.done"),
                        (button) -> this.onClose(), null
                ) {
                }
        );
        super.init();
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g, mouseX, mouseY, partialTick);
        super.render(g, mouseX, mouseY, partialTick);
        g.drawCenteredString(this.font, this.title, width / 2, 48, 16777215);
        g.drawCenteredString(this.font, Component.translatable("vd.worldgen_config.description.1"), width / 2, 72, 16777215);
        g.drawCenteredString(this.font, Component.translatable("vd.worldgen_config.description.2"), width / 2, 82, 16777215);
        g.drawCenteredString(this.font, Component.translatable("vd.worldgen_config.description.3"), width / 2, 92, 16777215);
    }

    @Override
    public void onClose() {
        Objects.requireNonNull(this.minecraft).setScreen(this.lastScreen);
        WorldgenDataHandler.toml = new Toml().read(WorldgenDataHandler.PATH);
        WorldgenDataHandler.continueGeneration = true;
    }
}
