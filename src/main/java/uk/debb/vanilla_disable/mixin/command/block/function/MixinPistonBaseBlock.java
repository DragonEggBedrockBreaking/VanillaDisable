package uk.debb.vanilla_disable.mixin.command.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(PistonBaseBlock.class)
public abstract class MixinPistonBaseBlock {
    @Inject(method = "triggerEvent", at = @At("HEAD"), cancellable = true)
    private void triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param, CallbackInfoReturnable<Boolean> cir) {
        String type = CommandDataHandler.getKeyFromBlockRegistry(state.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", type, "works")) {
            cir.setReturnValue(false);
        }
    }
}
