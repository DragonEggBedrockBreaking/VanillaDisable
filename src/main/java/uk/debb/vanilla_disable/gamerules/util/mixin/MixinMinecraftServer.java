package uk.debb.vanilla_disable.gamerules.util.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.lists.PopulateLists;
import uk.debb.vanilla_disable.gamerules.util.maps.PopulateMaps;

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
        Gamerules.server = (MinecraftServer) (Object) this;
        PopulateMaps.populateBiomeMap();
        PopulateLists.populateBiomeLists();
    }

    @Inject(
            method = "runServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;buildServerStatus()Lnet/minecraft/network/protocol/status/ServerStatus;",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void afterServerSetup(CallbackInfo ci) {
        PopulateMaps.populateMapsPostServer();
    }
}