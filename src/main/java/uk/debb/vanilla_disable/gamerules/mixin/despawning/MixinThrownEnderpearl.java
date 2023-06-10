package uk.debb.vanilla_disable.gamerules.mixin.despawning;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(ThrownEnderpearl.class)
public abstract class MixinThrownEnderpearl {
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/ThrownEnderpearl;discard()V"
            ),
            cancellable = true
    )
    private void cancelDiscard(CallbackInfo ci) {
        if (!Gamerules.ENDER_PEARLS_DESPAWN_ON_DEATH.getBool()) {
            ci.cancel();
        }
    }
}