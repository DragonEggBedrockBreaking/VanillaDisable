package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Mob.class)
public abstract class MixinMob {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason prevent zombies/skeletons from burning in sunlight
     */
    @ModifyReturnValue(method = "isSunBurnTick", at = @At("RETURN"))
    private boolean stopBurning(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.MOBS_BURN_IN_SUNLIGHT)) {
            return false;
        }
        return original;
    }
}