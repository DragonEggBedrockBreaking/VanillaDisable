package uk.debb.vanilla_disable.gamerules.mixin.mobs;

import net.minecraft.world.entity.monster.Skeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(Skeleton.class)
public abstract class MixinSkeleton {
    @Inject(method = "doFreezeConversion", at = @At("HEAD"), cancellable = true)
    private void cancelFreezeConversion(CallbackInfo ci) {
        if (!Gamerules.SKELETONS_CONVERT_TO_STRAYS.getBool()) {
            ci.cancel();
        }
    }
}