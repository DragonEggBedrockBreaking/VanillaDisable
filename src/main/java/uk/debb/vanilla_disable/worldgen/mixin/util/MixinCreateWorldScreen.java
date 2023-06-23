package uk.debb.vanilla_disable.worldgen.mixin.util;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;
import uk.debb.vanilla_disable.config.VanillaDisableConfig;
import uk.debb.vanilla_disable.worldgen.data.WorldgenDataHandler;

@Mixin(CreateWorldScreen.class)
public abstract class MixinCreateWorldScreen {
    @Inject(method = "onCreate", at = @At("HEAD"))
    private void onCreate(CallbackInfo ci) {
        CommandDataHandler.shouldMigrate = false;
        WorldgenDataHandler.shouldMigrate = false;
        if (VanillaDisableConfig.worldLoadingScreen) {
            WorldgenDataHandler.continueGeneration = false;
        }
    }
}
