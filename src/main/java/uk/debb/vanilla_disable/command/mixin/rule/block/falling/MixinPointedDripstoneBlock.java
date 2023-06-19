package uk.debb.vanilla_disable.command.mixin.rule.block.falling;

import net.minecraft.world.level.block.PointedDripstoneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(PointedDripstoneBlock.class)
public abstract class MixinPointedDripstoneBlock {
    @Inject(method = "spawnFallingStalactite", at = @At("HEAD"), cancellable = true)
    private static void spawnFallingStalactite(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("blocks", "minecraft:pointed_dripstone", "can_fall")) {
            ci.cancel();
        }
    }
}
