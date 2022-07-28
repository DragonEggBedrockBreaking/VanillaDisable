package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(ConduitBlockEntity.class)
public abstract class MixinConduitBlockEntity {
    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true)
    private static void cancelEffects(CallbackInfo ci) {
        if (!GameruleHelper.getBool(BooleanGamerules.CONDUITS_ENABLED)) {
            ci.cancel();
        }
    }
}