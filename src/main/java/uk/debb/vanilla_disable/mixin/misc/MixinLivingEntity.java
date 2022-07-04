package uk.debb.vanilla_disable.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason prevent totems from activating
     */
    @ModifyReturnValue(method = "checkTotemDeathProtection", at = @At("RETURN"))
    private boolean totemsDoNotWork(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.TOTEMS_ENABLED)) {
            return false;
        }
        return original;
    }
}