package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

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
        if (!BooleanGamerules.ENDER_PEARLS_DESPAWN_ON_DEATH.getValue()) {
            ci.cancel();
        }
    }
}