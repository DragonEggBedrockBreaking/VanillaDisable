package uk.debb.vanilla_disable.gamerules.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(Mob.class)
public abstract class MixinMob {
    @ModifyReturnValue(method = "isSunBurnTick", at = @At("RETURN"))
    private boolean stopBurning(boolean original) {
        if (!Gamerules.MOBS_BURN_IN_SUNLIGHT.getBool()) {
            return false;
        }
        return original;
    }
}