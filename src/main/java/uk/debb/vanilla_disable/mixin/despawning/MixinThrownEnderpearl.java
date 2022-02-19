package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ThrownEnderpearl.class)
public abstract class MixinThrownEnderpearl {
    /**
     * @author DragonEggBedrockBreaking
     * @reason don't delete ender pearls on player death
     * @param ci
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
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ENDER_PEARLS_DESPAWN_ON_DEATH)) {
            ci.cancel();
        }
    }
}
