package uk.debb.vanilla_disable.mixin.worldgen;

import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld {
    /**
     * @author DragonEggBedrockBreaking
     * @reason disable generation of the end spawn platform
     * @param world the server world
     * @param ci the callback info
     */
    @Inject(method = "createEndSpawnPlatform", at = @At("HEAD"), cancellable = true)
    private static void cancelEndSpawnPlatformCreation(ServerWorld world, CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.END_SPAWN_PLATFORM_GENERATION)) {
            ci.cancel();
        }
    }
}
