package uk.debb.vanilla_disable.mixin.command.item.function;

import net.minecraft.world.entity.projectile.SpectralArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(SpectralArrow.class)
public abstract class MixinSpectralArrow {
    @Inject(method = "doPostHurtEffects", at = @At("HEAD"), cancellable = true)
    private void doPostHurtEffects(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("items", "minecraft:spectral_arrow", "works")) {
            ci.cancel();
        }
    }
}
