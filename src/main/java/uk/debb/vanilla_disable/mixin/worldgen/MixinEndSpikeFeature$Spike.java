package uk.debb.vanilla_disable.mixin.worldgen;

import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(EndSpikeFeature.Spike.class)
public abstract class MixinEndSpikeFeature$Spike {
    /**
     * @author DragonEggBedrockBreaking
     * @reason remove cages on the two end pillars which have them
     * @param cir the returnable callback info
     */
    @Inject(method = "isGuarded", at = @At("HEAD"), cancellable = true)
    private void notGuarded(CallbackInfoReturnable<Boolean> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.END_PILLAR_CAGE_GENERATION)) {
            cir.setReturnValue(false);
        }
    }
}
