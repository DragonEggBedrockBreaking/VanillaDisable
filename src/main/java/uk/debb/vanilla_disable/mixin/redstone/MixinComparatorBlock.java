package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    /**
     * @param blockState the state of the block
     * @param cir        the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason edit the delay of the redstone component
     */
    @Inject(method = "getDelay", at = @At("HEAD"), cancellable = true)
    private void modifyDelay(BlockState blockState, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        cir.setReturnValue(GameruleHelper.getInt(Gamerules.COMPARATOR_BASE_DELAY));
    }

    /**
     * @param blockGetter the block getter
     * @param blockPos    the position of the block
     * @param blockState  the state of the block
     * @param cir         the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason edit the signal that is outputted by the redstone component
     */
    @Inject(method = "getOutputSignal", at = @At("HEAD"), cancellable = true)
    private void modifyOutputSignal(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.COMPARATOR_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}