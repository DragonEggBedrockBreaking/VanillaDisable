package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LeverBlock.class)
public abstract class MixinLeverBlock {
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
        if (!GameruleHelper.getBool(Gamerules.LEVER_ENABLED)) {
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
        if (!GameruleHelper.getBool(Gamerules.LEVER_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}