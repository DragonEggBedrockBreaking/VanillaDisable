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
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(RepeaterBlock.class)
public abstract class MixinRepeaterBlock extends DiodeBlock {
    protected MixinRepeaterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /**
     * @param original   the original value
     * @param blockState the state of the block
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int modifyDelay(int original, BlockState blockState) {
        return blockState.getValue(BlockStateProperties.DELAY) * GameruleHelper.getInt(Gamerules.REPEATER_BASE_DELAY);
    }

    /**
     * @param blockGetter the getter for the repeater
     * @param blockPos    the position of the repeater
     * @param blockState  the state of the repeater
     * @return the signal outputted
     * @author DragonEggBedrockBreaking
     */
    @Override
    protected int getOutputSignal(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        if (blockState.getValue(BlockStateProperties.POWERED)) {
            return GameruleHelper.getInt(Gamerules.REPEATER_SIGNAL);
        }
        return 0;
    }
}