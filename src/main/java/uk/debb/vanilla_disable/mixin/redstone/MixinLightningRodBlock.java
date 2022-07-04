package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.LightningRodBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LightningRodBlock.class)
public abstract class MixinLightningRodBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     */
    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.LIGHTNING_ROD_ENABLED)) {
            return 0;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     */
    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.LIGHTNING_ROD_ENABLED)) {
            return 0;
        }
        return original;
    }
}