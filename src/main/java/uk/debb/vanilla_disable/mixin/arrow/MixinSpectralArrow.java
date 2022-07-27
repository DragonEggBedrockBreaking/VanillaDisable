package uk.debb.vanilla_disable.mixin.arrow;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SpectralArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(SpectralArrow.class)
public abstract class MixinSpectralArrow {
    /**
     * @param livingEntity the entity that is hit
     * @param ci the callback info
     * @author DragonEggBedrockBreaking
     */
    @Inject(method = "doPostHurtEffects", at = @At("HEAD"), cancellable = true)
    private void cancelPostHurtEffects(LivingEntity livingEntity, CallbackInfo ci) {
        if (!GameruleHelper.getBool(Gamerules.SPECTRAL_ARROWS_ENABLED)) {
            ci.cancel();
        }
    }
}
