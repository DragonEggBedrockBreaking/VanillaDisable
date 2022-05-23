package uk.debb.vanilla_disable.mixin.worldgen;

import net.minecraft.world.level.levelgen.feature.SpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(SpikeFeature.EndSpike.class)
public abstract class MixinSpikeFeature$EndSpike {
    /**
     * @author DragonEggBedrockBreaking
     * @reason remove cages on the two end pillars which have them
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isGuarded", at = @At("HEAD"), cancellable = true)
    private void notGuarded(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!VDServer.getServer().getGameRules().getBoolean(Gamerules.END_PILLAR_CAGE_GENERATION)) {
            cir.setReturnValue(false);
        }
    }
}
