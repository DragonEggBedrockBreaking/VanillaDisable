package uk.debb.vanilla_disable.mixin.util;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    /**
     * @author DragonEggBedrockBreaking
     * @reason write this ourselves instead of depending on an entire api
     * @param ci the callback info
     */
    @Inject(
        method = "runServer",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
        )
    )
    private void beforeServerSetup(CallbackInfo ci) {
        RegisterGamerules.setServer((MinecraftServer) (Object) this);
    }
}
