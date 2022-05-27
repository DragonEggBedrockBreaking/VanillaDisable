package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(RepeaterBlock.class)
public abstract class MixinRepeaterBlock extends DiodeBlock {
    protected MixinRepeaterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit the delay of the redstone component
     * @param blockState the state of the block
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getDelay", at = @At("HEAD"), cancellable = true)
    private void modifyDelay(BlockState blockState, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        cir.setReturnValue(blockState.getValue(BlockStateProperties.DELAY) * GameruleHelper.getInt(Gamerules.REPEATER_BASE_DELAY));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     * @param blockGetter the getter for the repeater
     * @param blockPos the position of the repeater
     * @param blockState the state of the repeater
     * @return the signal outputted
     */
    @Override
    protected int getOutputSignal(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        if (VDServer.getServer() == null) return 0;
        if (blockState.getValue(BlockStateProperties.POWERED)) {
            return GameruleHelper.getInt(Gamerules.REPEATER_SIGNAL);
        }
        return 0;
    }
}
