package uk.debb.vanilla_disable.mixin.spawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(BreedGoal.class)
public abstract class MixinBreedGoal {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason Don't allow breeding to start
     */
    @ModifyReturnValue(method = "canUse", at = @At(value = "RETURN"))
    private boolean cannotUse(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.ANIMAL_BREEDING)) {
            return false;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason Don't allow breeding to continue
     */
    @ModifyReturnValue(method = "canContinueToUse", at = @At(value = "RETURN"))
    private boolean shouldContinueToUse(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.ANIMAL_BREEDING)) {
            return false;
        }
        return original;
    }
}