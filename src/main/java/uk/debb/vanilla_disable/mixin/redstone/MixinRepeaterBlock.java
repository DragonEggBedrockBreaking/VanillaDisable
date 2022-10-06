package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(RepeaterBlock.class)
public abstract class MixinRepeaterBlock {
    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int modifyDelay(int original, BlockState blockState) {
        return blockState.getValue(BlockStateProperties.DELAY) * Gamerules.REPEATER_BASE_DELAY.getInt();
    }
}