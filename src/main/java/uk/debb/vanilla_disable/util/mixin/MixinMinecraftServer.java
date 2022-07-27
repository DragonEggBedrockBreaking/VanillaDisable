package uk.debb.vanilla_disable.util.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    /**
     * @param ci the callback info
     * @author DragonEggBedrockBreaking
     * @reason write this ourselves instead of depending on an entire api
     */
    @Inject(
            method = "runServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
            )
    )
    private void beforeServerSetup(CallbackInfo ci) {
        GameruleHelper.setServer((MinecraftServer) (Object) this);
    }
}