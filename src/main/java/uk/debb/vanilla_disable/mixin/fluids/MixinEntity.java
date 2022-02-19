package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent entities from being affected by bubble columns
     * @param drag the drag
     * @param ci the callback info
     */
    @Inject(method = "onAboveBubbleCol", at = @At("HEAD"), cancellable = true)
    private void cancelAboveBubbleCol(boolean drag, CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.BUBBLE_COLUMNS_ENABLED)) {
            ci.cancel();
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent entities from being affected by bubble columns
     * @param drag the drag
     * @param ci the callback info
     */
    @Inject(method = "onInsideBubbleColumn", at = @At("HEAD"), cancellable = true)
    private void cancelInsideBubbleCol(boolean drag, CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.BUBBLE_COLUMNS_ENABLED)) {
            ci.cancel();
        }
    }
}
