package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ObserverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ObserverBlock.class)
public abstract class MixinObserverBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.OBSERVER_ENABLED)) {
            return 0;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.OBSERVER_ENABLED)) {
            return 0;
        }
        return original;
    }

    /**
     * @param delay the original delay of the block
     * @return the new delay that it will be changed to
     * @author DragonEggBedrockBreaking
     * @reason edit the delay of the redstone component
     */
    @ModifyArg(
            method = "startSignal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelAccessor;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int modifyObserverDelay(int delay) {
        if (VDServer.getServer() == null) return delay;
        return GameruleHelper.getInt(Gamerules.OBSERVER_DELAY);
    }

    /**
     * @param duration the original duration of the redstone pulse
     * @return the new duration that it will be changed to
     * @author DragonEggBedrockBreaking
     * @reason edit the duration of the redstone pulse
     */
    @ModifyArg(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"
            ),
            index = 2
    )
    private int modifyObserverDuration(int duration) {
        if (VDServer.getServer() == null) return duration;
        return GameruleHelper.getInt(Gamerules.OBSERVER_DURATION);
    }
}