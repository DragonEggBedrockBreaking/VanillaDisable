package uk.debb.vanilla_disable.mixin.spawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(BreedGoal.class)
public abstract class MixinBreedGoal {
    @ModifyReturnValue(method = "canUse", at = @At(value = "RETURN"))
    private boolean cannotUse(boolean original) {
        if (!BooleanGamerules.ANIMAL_BREEDING.getValue()) {
            return false;
        }
        return original;
    }

    @ModifyReturnValue(method = "canContinueToUse", at = @At(value = "RETURN"))
    private boolean shouldContinueToUse(boolean original) {
        if (!BooleanGamerules.ANIMAL_BREEDING.getValue()) {
            return false;
        }
        return original;
    }
}