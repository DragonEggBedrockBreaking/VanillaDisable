package uk.debb.vanilla_disable.command.mixin.rule.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(PistonBaseBlock.class)
public abstract class MixinPistonBaseBlock {
    @Inject(method = "triggerEvent", at = @At("HEAD"), cancellable = true)
    private void triggerEvent(BlockState blockState, Level level, BlockPos blockPos, int i, int j, CallbackInfoReturnable<Boolean> cir) {
        String type = DataHandler.getKeyFromBlockRegistry(blockState.getBlock());
        if (!DataHandler.getBoolean("blocks", type, "works")) {
            cir.setReturnValue(false);
        }
    }
}
