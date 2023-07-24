package uk.debb.vanilla_disable.mixin.command.block.function;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "onAboveBubbleCol", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$onAboveBubbleCol(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("blocks", "minecraft:bubble_column", "works")) {
            ci.cancel();
        }
    }

    @Inject(method = "onInsideBubbleColumn", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$onInsideBubbleColumn(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("blocks", "minecraft:bubble_column", "works")) {
            ci.cancel();
        }
    }
}
