package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.world.level.block.piston.PistonBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(PistonBaseBlock.class)
public abstract class MixinPistonBaseBlock {
    @Inject(method = "triggerEvent", at = @At("HEAD"), cancellable = true)
    private void cancelTriggeringEvent(CallbackInfoReturnable<Boolean> cir) {
        if (!Gamerules.PISTON_ENABLED.getBool()) {
            cir.setReturnValue(false);
        }
    }
}