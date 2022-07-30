package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.PointedDripstoneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(PointedDripstoneBlock.class)
public abstract class MixinPointedDripstoneBlock {
    @Inject(method = "spawnFallingStalactite", at = @At("HEAD"), cancellable = true)
    private static void cancelSpawningFallingStalactite(CallbackInfo ci) {
        if (!GameruleHelper.getBool(BooleanGamerules.POINTED_DRIPSTONE_FALLS)) {
            ci.cancel();
        }
    }
}
