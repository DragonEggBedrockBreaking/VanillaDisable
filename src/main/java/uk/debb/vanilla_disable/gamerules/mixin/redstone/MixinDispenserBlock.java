package uk.debb.vanilla_disable.gamerules.mixin.redstone;

import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock {
    @Inject(method = "dispenseFrom", at = @At("HEAD"), cancellable = true)
    private void cancelDispensing(CallbackInfo ci) {
        if (!Gamerules.DISPENSER_ENABLED.getBool()) {
            ci.cancel();
        }
    }
}