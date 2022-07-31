package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(RedstoneTorchBlock.class)
public abstract class MixinRedstoneTorchBlock {
    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (!BooleanGamerules.REDSTONE_TORCH_ENABLED.getValue()) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        if (!BooleanGamerules.REDSTONE_TORCH_ENABLED.getValue()) {
            return 0;
        }
        return original;
    }
}