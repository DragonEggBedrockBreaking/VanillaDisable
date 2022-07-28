package uk.debb.vanilla_disable.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.levelgen.feature.SpikeFeature.EndSpike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(EndSpike.class)
public abstract class MixinEndSpike {
    @ModifyReturnValue(method = "isGuarded", at = @At("RETURN"))
    private boolean notGuarded(boolean original) {
        if (!GameruleHelper.getBool(BooleanGamerules.END_PILLAR_CAGE_GENERATION)) {
            return false;
        }
        return original;
    }
}