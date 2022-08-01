package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.world.level.block.ObserverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ObserverBlock.class)
public abstract class MixinObserverBlock {
    @ModifyArg(
            method = "startSignal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelAccessor;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int modifyObserverDelay(int delay) {
        return Gamerules.OBSERVER_DELAY.getValue(Integer::parseInt);
    }

    @ModifyArg(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"
            ),
            index = 2
    )
    private int modifyObserverDuration(int duration) {
        return Gamerules.OBSERVER_DURATION.getValue(Integer::parseInt);
    }
}