package uk.debb.vanilla_disable.mixin.arrow;

import net.minecraft.world.entity.projectile.SpectralArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(SpectralArrow.class)
public abstract class MixinSpectralArrow {
    @Inject(method = "doPostHurtEffects", at = @At("HEAD"), cancellable = true)
    private void cancelPostHurtEffects(CallbackInfo ci) {
        if (!GameruleHelper.getBool(BooleanGamerules.SPECTRAL_ARROWS_ENABLED)) {
            ci.cancel();
        }
    }
}
