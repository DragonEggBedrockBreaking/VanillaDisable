package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ComparatorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason edit the delay of the redstone component
     */
    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int modifyDelay(int original) {
        if (VDServer.getServer() == null) return original;
        return GameruleHelper.getInt(Gamerules.COMPARATOR_BASE_DELAY);
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason edit the signal that is outputted by the redstone component
     */
    @ModifyReturnValue(method = "getOutputSignal", at = @At("RETURN"))
    private int modifyOutputSignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.COMPARATOR_ENABLED)) {
            return 0;
        }
        return original;
    }
}