package uk.debb.vanilla_disable.mixin.util.command;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(method = "createLevels", at = @At("RETURN"))
    private void vanillaDisable$createLevels(CallbackInfo ci) {
        CommandDataHandler.server = (MinecraftServer) (Object) this;
        CommandDataHandler.populateRegistries();
        if (!CommandDataHandler.populationDone) {
            CommandDataHandler.populate();
        }
        CommandDataHandler.handleDatabase();
    }

    @Inject(method = "stopServer", at = @At("TAIL"))
    private void vanillaDisable$stopServer(CallbackInfo ci) {
        CommandDataHandler.migrated = false;
        CommandDataHandler.shouldMigrate = true;
        CommandDataHandler.closeConnection();
    }
}
