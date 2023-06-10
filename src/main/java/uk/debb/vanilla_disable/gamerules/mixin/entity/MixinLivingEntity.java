package uk.debb.vanilla_disable.gamerules.mixin.entity;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void cancelDroppingExperience(CallbackInfo ci) {
        if (!Gamerules.MOBS_DROP_XP.getBool()) {
            ci.cancel();
        }
    }
}