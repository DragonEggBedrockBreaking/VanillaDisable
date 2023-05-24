package uk.debb.vanilla_disable.command.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(
            method = "runServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
            )
    )
    private void beforeServerSetup(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        DataHandler.handleDatabase(server);
    }

    @Inject(method = "stopServer", at = @At("TAIL"))
    private void afterServerShutdown(CallbackInfo ci) {
        DataHandler.closeConnection();
    }
}
