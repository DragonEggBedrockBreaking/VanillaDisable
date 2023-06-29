package uk.debb.vanilla_disable.mixin.command.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(FireBlock.class)
public abstract class MixinFireBlock {
    @ModifyReturnValue(method = "getBurnOdds", at = @At("RETURN"))
    private int getBurnOdds(int original, BlockState state) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(state.getBlock());
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)
                ? 0 : CommandDataHandler.getCachedInt("blocks", block, "burn_odds");
    }

    @ModifyReturnValue(method = "getIgniteOdds", at = @At("RETURN"))
    private int getIgniteOdds(int original, BlockState state) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(state.getBlock());
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)
                ? 0 : CommandDataHandler.getCachedInt("blocks", block, "ignite_odds");
    }
}
