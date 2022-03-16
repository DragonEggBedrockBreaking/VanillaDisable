package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent totems from activating
     * @param damageSource the cause of totem activtion
     * @param cir the returnable callback info
     */
    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    private void totemsDoNotWork(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.TOTEMS_ENABLED)) {
            cir.setReturnValue(false);
        } 
    }
}
