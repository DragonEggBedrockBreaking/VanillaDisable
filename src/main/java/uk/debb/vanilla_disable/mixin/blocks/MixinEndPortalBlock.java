package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.EndPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(EndPortalBlock.class)
public abstract class MixinEndPortalBlock {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void cancelEntityInside(CallbackInfo ci) {
        if (!GameruleHelper.getBool(BooleanGamerules.END_PORTALS_ENABLED)) {
            ci.cancel();
        }
    }
}