package uk.debb.vanilla_disable.mixin.ai;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.goal.Goal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Goal.class)
public abstract class MixinGoal implements Maps {
    @ModifyReturnValue(method = "canContinueToUse", at = @At("RETURN"))
    private boolean blockContinuance(boolean original) {
        Gamerules gameRule = goalClassMap.get(this.getClass());
        if (gameRule != null && !gameRule.getValue(Boolean::parseBoolean)) {
            return false;
        }
        return original;
    }
}