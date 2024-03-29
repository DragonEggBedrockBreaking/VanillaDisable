package uk.debb.vanilla_disable.mixin.worldgen.feature;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

@Mixin(SpikeFeature.EndSpike.class)
public abstract class MixinEndSpike {
    @ModifyReturnValue(method = "isGuarded", at = @At("RETURN"))
    private boolean vanillaDisable$isGuarded(boolean original) {
        return original && WorldgenDataHandler.get("placed_features", "end_spike_cage");
    }
}
