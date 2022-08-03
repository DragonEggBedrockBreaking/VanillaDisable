package uk.debb.vanilla_disable.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.levelgen.feature.SpikeFeature.EndSpike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(EndSpike.class)
public abstract class MixinEndSpike {
    @ModifyReturnValue(method = "isGuarded", at = @At("RETURN"))
    private boolean notGuarded(boolean original) {
        if (!Gamerules.END_PILLAR_CAGE_GENERATION.getBool()) {
            return false;
        }
        return original;
    }
}