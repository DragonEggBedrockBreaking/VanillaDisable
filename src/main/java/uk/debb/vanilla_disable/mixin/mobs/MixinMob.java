package uk.debb.vanilla_disable.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Mob.class)
public abstract class MixinMob {
    @ModifyReturnValue(method = "isSunBurnTick", at = @At("RETURN"))
    private boolean stopBurning(boolean original) {
        if (!Gamerules.MOBS_BURN_IN_SUNLIGHT.getValue(Boolean::parseBoolean)) {
            return false;
        }
        return original;
    }
}