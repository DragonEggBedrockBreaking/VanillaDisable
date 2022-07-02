package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ObserverBlock.class)
public abstract class MixinObserverBlock {
    /**
     * @param blockState  the state of the block
     * @param blockGetter the block getter
     * @param blockPos    the position of the block
     * @param direction   the direction of the block
     * @param cir         the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     */
    @Inject(method = "getSignal", at = @At("HEAD"), cancellable = true)
    private void modifySignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.OBSERVER_ENABLED)) {
            cir.setReturnValue(0);
        }
    }

    /**
     * @param blockState  the state of the block
     * @param blockGetter the block getter
     * @param blockPos    the position of the block
     * @param direction   the direction of the block
     * @param cir         the returnable callback info
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     */
    @Inject(method = "getDirectSignal", at = @At("HEAD"), cancellable = true)
    private void modifyDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.OBSERVER_ENABLED)) {
            cir.setReturnValue(0);
        }
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