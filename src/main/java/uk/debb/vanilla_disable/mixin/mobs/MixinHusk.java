package uk.debb.vanilla_disable.mixin.mobs;

import net.minecraft.world.entity.monster.Husk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Husk.class)
public abstract class MixinHusk {
    @Inject(method = "doUnderWaterConversion", at = @At("HEAD"), cancellable = true)
    private void cancelConversionInWater(CallbackInfo ci) {
        if (!Gamerules.HUSKS_CONVERT_TO_ZOMBIES.getValue(Boolean::parseBoolean)) {
            ci.cancel();
        }
    }
}