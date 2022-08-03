package uk.debb.vanilla_disable.mixin.entity;

import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Inject(method = "popExperience", at = @At("HEAD"), cancellable = true)
    private void cancelPoppingExperience(CallbackInfo ci) {
        if (!Gamerules.BLOCKS_DROP_XP.getBool()) {
            ci.cancel();
        }
    }
}
