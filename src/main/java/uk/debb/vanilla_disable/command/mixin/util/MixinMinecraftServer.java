package uk.debb.vanilla_disable.command.mixin.util;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(method = "createLevels", at = @At("RETURN"))
    private void afterLevelCreation(CallbackInfo ci) {
        CommandDataHandler.server = (MinecraftServer) (Object) this;
        CommandDataHandler.populateRegistries();
        if (!CommandDataHandler.populationDone) {
            CommandDataHandler.populate();
        }
        CommandDataHandler.handleDatabase();
    }

    @Inject(method = "stopServer", at = @At("TAIL"))
    private void afterServerShutdown(CallbackInfo ci) {
        CommandDataHandler.closeConnection();
    }
}
