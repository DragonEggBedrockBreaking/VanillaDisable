package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.monster.Skeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Skeleton.class)
public abstract class MixinSkeleton {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop skeletons from converting into strays
     * @param ci the callback info
     */
    @Inject(method = "doFreezeConversion", at = @At("HEAD"), cancellable = true)
    private void cancelFreezeConversion(CallbackInfo ci) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.SKELETONS_CONVERT_TO_STRAYS)) {
            ci.cancel();
        }
    }
}
