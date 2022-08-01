package uk.debb.vanilla_disable.mixin.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @ModifyReturnValue(method = "checkTotemDeathProtection", at = @At("RETURN"))
    private boolean totemsDoNotWork(boolean original) {
        if (!Gamerules.TOTEMS_ENABLED.getValue(Boolean::parseBoolean)) {
            return false;
        }
        return original;
    }
}