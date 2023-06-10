package uk.debb.vanilla_disable.gamerules.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ComparatorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int modifyDelay(int original) {
        return Gamerules.COMPARATOR_BASE_DELAY.getInt();
    }

    @ModifyReturnValue(method = "getOutputSignal", at = @At("RETURN"))
    private int modifyOutputSignal(int original) {
        if (!Gamerules.COMPARATOR_ENABLED.getBool()) {
            return 0;
        }
        return original;
    }
}