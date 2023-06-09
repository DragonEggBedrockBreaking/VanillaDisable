package uk.debb.vanilla_disable.command.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(method = "createLevels", at = @At("RETURN"))
    private void afterLevelCreation(CallbackInfo ci) {
        DataHandler.server = (MinecraftServer) (Object) this;
        if (!DataHandler.populationDone) {
            DataHandler.populate();
        }
        DataHandler.handleDatabase();
    }

    @Inject(method = "stopServer", at = @At("TAIL"))
    private void afterServerShutdown(CallbackInfo ci) {
        DataHandler.closeConnection();
    }
}
