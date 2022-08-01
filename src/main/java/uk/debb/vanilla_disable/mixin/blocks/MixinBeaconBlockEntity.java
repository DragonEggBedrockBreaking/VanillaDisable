package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(BeaconBlockEntity.class)
public abstract class MixinBeaconBlockEntity {
    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true)
    private static void cancelEffects(CallbackInfo ci) {
        if (!Gamerules.BEACONS_ENABLED.getValue(Boolean::parseBoolean)) {
            ci.cancel();
        }
    }
}