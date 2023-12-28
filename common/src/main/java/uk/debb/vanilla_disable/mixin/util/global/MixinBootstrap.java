package uk.debb.vanilla_disable.mixin.util.global;

import net.minecraft.server.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.config.global.VanillaDisableConfig;

import java.io.File;
import java.nio.file.Paths;

@Mixin(Bootstrap.class)
public abstract class MixinBootstrap {
    @Inject(method = "bootStrap", at = @At("RETURN"))
    private static void vanillaDisable$bootStrap(CallbackInfo ci) {
        File configFile = new File(Paths.get(".").toAbsolutePath().toString(), "config/vanilla_disable.properties");
        VanillaDisableConfig.PATH = configFile.toString();
        if (configFile.exists()) {
            VanillaDisableConfig.updateConfig();
        } else {
            VanillaDisableConfig.generateConfig();
        }
    }
}
