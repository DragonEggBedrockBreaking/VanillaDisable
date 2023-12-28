package uk.debb.vanilla_disable.mixin.util.worldgen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import uk.debb.vanilla_disable.config.worldgen.WorldgenConfigScreen;

@Mixin(CreateWorldScreen.MoreTab.class)
public abstract class MixinCreateWorldScreenMoreTab {
    @Inject(method="<init>", at=@At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void vanillaDisable$init(CreateWorldScreen createWorldScreen, CallbackInfo ci, GridLayout.RowHelper rowHelper) {
        rowHelper.addChild(Button.builder(Component.translatable("vd.worldgen_config.button"), button -> {
            Minecraft.getInstance().setScreen(new WorldgenConfigScreen(createWorldScreen));
        }).width(210).build());
    }
}
