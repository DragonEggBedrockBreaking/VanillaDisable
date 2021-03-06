package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ThrownEnderpearl.class)
public abstract class MixinThrownEnderpearl {
    /**
     * @param ci callback info
     * @author DragonEggBedrockBreaking
     * @reason don't delete ender pearls on player death
     */
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/ThrownEnderpearl;discard()V"
            ),
            cancellable = true
    )
    private void cancelDiscard(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.ENDER_PEARLS_DESPAWN_ON_DEATH)) {
            ci.cancel();
        }
    }
}