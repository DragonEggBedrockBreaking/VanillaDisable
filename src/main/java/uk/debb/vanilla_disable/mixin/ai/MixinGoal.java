package uk.debb.vanilla_disable.mixin.ai;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Goal.class)
public abstract class MixinGoal implements Maps {
    @ModifyReturnValue(method = "canContinueToUse", at = @At("RETURN"))
    private boolean blockContinuance(boolean original) {
        GameRules.Key<GameRules.BooleanValue> gameRule = goalClassMap.get(this.getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            return false;
        }
        return original;
    }
}