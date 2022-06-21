package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent totems from activating
     * @param damageSource the cause of totem activation
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    private void totemsDoNotWork(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.TOTEMS_ENABLED)) {
            cir.setReturnValue(false);
        } 
    }
}
