package uk.debb.vanilla_disable.mixin.util.worldgen;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

@Mixin(CreateWorldScreen.class)
public abstract class MixinCreateWorldScreen {
    @Inject(method = "onCreate", at = @At("HEAD"))
    private void vanillaDisable$onCreate(CallbackInfo ci) {
        CommandDataHandler.shouldMigrate = false;
        WorldgenDataHandler.shouldMigrate = false;
    }

    @Inject(method = "popScreen", at = @At("HEAD"))
    private void vanillaDisable$popScreen(CallbackInfo ci) {
        WorldgenDataHandler.biomeMap.clear();
        WorldgenDataHandler.structureMap.clear();
        WorldgenDataHandler.placedFeatureMap.clear();
    }
}
