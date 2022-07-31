package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ComparatorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int modifyDelay(int original) {
        return IntegerGamerules.COMPARATOR_BASE_DELAY.getValue();
    }

    @ModifyReturnValue(method = "getOutputSignal", at = @At("RETURN"))
    private int modifyOutputSignal(int original) {
        if (!BooleanGamerules.COMPARATOR_ENABLED.getValue()) {
            return 0;
        }
        return original;
    }
}