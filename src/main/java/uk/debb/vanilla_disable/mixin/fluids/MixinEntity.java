package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "onAboveBubbleCol", at = @At("HEAD"), cancellable = true)
    private void cancelAboveBubbleCol(CallbackInfo ci) {
        if (!GameruleHelper.getBool(Gamerules.BUBBLE_COLUMNS_ENABLED)) {
            ci.cancel();
        }
    }

    @Inject(method = "onInsideBubbleColumn", at = @At("HEAD"), cancellable = true)
    private void cancelInsideBubbleCol(CallbackInfo ci) {
        if (!GameruleHelper.getBool(Gamerules.BUBBLE_COLUMNS_ENABLED)) {
            ci.cancel();
        }
    }
}