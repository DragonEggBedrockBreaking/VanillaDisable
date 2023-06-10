package uk.debb.vanilla_disable.gamerules.mixin.arrow;

import net.minecraft.world.entity.projectile.SpectralArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(SpectralArrow.class)
public abstract class MixinSpectralArrow {
    @Inject(method = "doPostHurtEffects", at = @At("HEAD"), cancellable = true)
    private void cancelPostHurtEffects(CallbackInfo ci) {
        if (!Gamerules.SPECTRAL_ARROWS_ENABLED.getBool()) {
            ci.cancel();
        }
    }
}
