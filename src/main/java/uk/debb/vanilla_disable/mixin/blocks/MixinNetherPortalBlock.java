package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.NetherPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(NetherPortalBlock.class)
public abstract class MixinNetherPortalBlock {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void cancelInsideEntity(CallbackInfo ci) {
        if (!Gamerules.NETHER_PORTALS_ENABLED.getBool()) {
            ci.cancel();
        }
    }
}