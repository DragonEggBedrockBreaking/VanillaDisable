package uk.debb.vanilla_disable.mixin.blocks.container;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.block.EnderChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(EnderChestBlock.class)
public abstract class MixinEnderChestBlock {
    @ModifyExpressionValue(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;isRedstoneConductor(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z"
            ),
            require = 0
    )
    private boolean isFullBlock(boolean original) {
        if (!Gamerules.CONTAINER_OPENING_BLOCKED.getBool()) {
            return false;
        }
        return original;
    }
}