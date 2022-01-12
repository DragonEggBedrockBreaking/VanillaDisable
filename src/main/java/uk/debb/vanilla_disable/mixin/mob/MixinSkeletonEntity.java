package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.mob.SkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SkeletonEntity.class)
public abstract class MixinSkeletonEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop skeletons from converting into strays
     * @param ci the callback info
     */
    @Inject(method = "convertToStray", at = @At("HEAD"), cancellable = true)
    private void cancelConversionToStray(CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.SKELETONS_CONVERT_TO_STRAYS)) {
            ci.cancel();
        }
    }
}
