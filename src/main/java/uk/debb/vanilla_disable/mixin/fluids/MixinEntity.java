package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Entity.class)
public abstract class MixinEntity {
    /**
     * @param drag the drag
     * @param ci   the callback info
     * @author DragonEggBedrockBreaking
     * @reason prevent entities from being affected by bubble columns
     */
    @Inject(method = "onAboveBubbleCol", at = @At("HEAD"), cancellable = true)
    private void cancelAboveBubbleCol(boolean drag, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.BUBBLE_COLUMNS_ENABLED)) {
            ci.cancel();
        }
    }

    /**
     * @param drag the drag
     * @param ci   the callback info
     * @author DragonEggBedrockBreaking
     * @reason prevent entities from being affected by bubble columns
     */
    @Inject(method = "onInsideBubbleColumn", at = @At("HEAD"), cancellable = true)
    private void cancelInsideBubbleCol(boolean drag, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.BUBBLE_COLUMNS_ENABLED)) {
            ci.cancel();
        }
    }
}