package uk.debb.vanilla_disable.command.mixin.rule.block.container;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.block.EnderChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

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
    private boolean isRedstoneConductor(boolean original) {
        return original && DataHandler.getBoolean("blocks", "minecraft:ender_chest", "opening_blockable");
    }
}
