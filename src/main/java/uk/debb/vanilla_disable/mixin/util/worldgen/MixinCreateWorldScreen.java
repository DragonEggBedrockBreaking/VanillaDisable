package uk.debb.vanilla_disable.mixin.util.worldgen;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.config.global.VanillaDisableConfig;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

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
