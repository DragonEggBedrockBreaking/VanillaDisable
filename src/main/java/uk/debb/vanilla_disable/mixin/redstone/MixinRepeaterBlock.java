package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

@Mixin(RepeaterBlock.class)
public abstract class MixinRepeaterBlock extends DiodeBlock {
    protected MixinRepeaterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int modifyDelay(int original, BlockState blockState) {
        return blockState.getValue(BlockStateProperties.DELAY) * IntegerGamerules.REPEATER_BASE_DELAY.getValue();
    }

    @Override
    protected int getOutputSignal(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        if (blockState.getValue(BlockStateProperties.POWERED)) {
            return IntegerGamerules.REPEATER_SIGNAL.getValue();
        }
        return 0;
    }
}