package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ObserverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ObserverBlock.class)
public abstract class MixinObserverBlock {
    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (!Gamerules.OBSERVER_ENABLED.getValue(Boolean::parseBoolean)) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        if (!Gamerules.OBSERVER_ENABLED.getValue(Boolean::parseBoolean)) {
            return 0;
        }
        return original;
    }

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