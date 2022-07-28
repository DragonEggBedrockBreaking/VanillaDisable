package uk.debb.vanilla_disable.util.mixin;

import net.minecraft.server.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.LangFileManager;
import uk.debb.vanilla_disable.util.gamerules.RegisterGamerules;
import uk.debb.vanilla_disable.util.maps.PopulateMaps;

import java.io.IOException;

@Mixin(Bootstrap.class)
public abstract class MixinBootstrap {
    @Inject(
            method = "bootStrap",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/Registry;freezeBuiltins()V"
            )
    )
    private static void onInitialize(CallbackInfo ci) throws IOException {
        RegisterGamerules.registerGamerules();
        LangFileManager.langFileFallback();
        PopulateMaps.populateMaps();
    }
}