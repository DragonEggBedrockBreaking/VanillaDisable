package uk.debb.vanilla_disable.mixin.util.worldgen;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.concurrent.TimeUnit;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Inject(
            method = "runServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"
            )
    )
    private void initServer(CallbackInfo ci) {
        WorldgenDataHandler.server = (MinecraftServer) (Object) this;
        WorldgenDataHandler.init();
    }

    @Inject(method = "createLevels", at = @At("HEAD"))
    private void createLevels(CallbackInfo ci) {
        while (!WorldgenDataHandler.continueGeneration) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Inject(method = "stopServer", at = @At("TAIL"))
    private void afterServerShutdown(CallbackInfo ci) {
        WorldgenDataHandler.shouldMigrate = true;
    }
}