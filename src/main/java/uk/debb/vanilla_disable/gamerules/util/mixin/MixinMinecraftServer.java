package uk.debb.vanilla_disable.gamerules.util.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.gamerules.RegisterGamerules;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Inject(
            method = "runServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
            )
    )
    private void beforeServerSetup(CallbackInfo ci) {
        RegisterGamerules.server = (MinecraftServer) (Object) this;
    }
}
