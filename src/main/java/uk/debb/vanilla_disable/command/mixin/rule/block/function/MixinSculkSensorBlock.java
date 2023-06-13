package uk.debb.vanilla_disable.command.mixin.rule.block.function;

import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(SculkSensorBlock.class)
public abstract class MixinSculkSensorBlock {
    @Inject(method = "canActivate", at = @At("HEAD"), cancellable = true)
    private static void canActivate(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        String type = DataHandler.getKeyFromBlockRegistry(blockState.getBlock());
        if (!DataHandler.getBoolean("blocks", type, "works")) {
            cir.setReturnValue(false);
        }
    }
}
