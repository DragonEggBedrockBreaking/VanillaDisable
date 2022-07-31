package uk.debb.vanilla_disable.util.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.DoubleGamerules;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

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
        BooleanGamerules.server = (MinecraftServer) (Object) this;
        DoubleGamerules.server = (MinecraftServer) (Object) this;
        IntegerGamerules.server = (MinecraftServer) (Object) this;
    }
}