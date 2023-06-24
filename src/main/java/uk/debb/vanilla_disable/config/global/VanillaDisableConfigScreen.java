package uk.debb.vanilla_disable.config.global;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Objects;

public class VanillaDisableConfigScreen extends Screen {
    private final Screen lastScreen;

    public VanillaDisableConfigScreen(Screen screen) {
        super(Component.translatable("vd.main_config").withStyle(ChatFormatting.BOLD));
        this.lastScreen = screen;
    }

    @Override
    protected void init() {
        int length = Component.translatable("vd.main_config.auto_migration").toString().length() * 3;
        this.addRenderableWidget(
                new Checkbox(
                        width / 2 - length, height / 2 - 10 - 10, 200, 20,
                        Component.translatable("vd.main_config.auto_migration"),
                        VanillaDisableConfig.autoMigration
                ) {
                    @Override
                    public void onPress() {
                        VanillaDisableConfig.autoMigration = !VanillaDisableConfig.autoMigration;
                        super.onPress();
                    }
                }
        );
        this.addRenderableWidget(
                new Checkbox(
                        width / 2 - length, height / 2 - 10 + 10, 200, 20,
                        Component.translatable("vd.main_config.world_loading_screen"),
                        VanillaDisableConfig.worldLoadingScreen
                ) {
                    @Override
                    public void onPress() {
                        VanillaDisableConfig.worldLoadingScreen = !VanillaDisableConfig.worldLoadingScreen;
                        super.onPress();
                    }
                }
        );
        this.addRenderableWidget(
                new Button(
                        this.width / 4 * 3, this.height / 5 * 4 - 12, 100, 20,
                        Component.translatable("vd.main_config.done"), (button) -> {
                            VanillaDisableConfig.saveConfig();
                            this.onClose();
                            }, null
                ) {}
        );
        this.addRenderableWidget(
                new Button(
                        this.width / 4 * 3, this.height / 5 * 4 + 12, 100, 20,
                        Component.translatable("vd.main_config.cancel"), (button) -> this.onClose(),
                        null
                ) {}
        );
        super.init();
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g);
        super.render(g, mouseX, mouseY, partialTick);
        g.drawCenteredString(this.font, this.title, width / 2, 36, 16777215);
    }

    @Override
    public void onClose() {
        Objects.requireNonNull(minecraft).setScreen(lastScreen);
        VanillaDisableConfig.resetConfig();
    }
}
