package uk.debb.vanilla_disable.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason make crossbows quickly charge
     */
    @ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
    private static int lowerChargeDuration(int original) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.CROSSBOW_SPAMMING)) {
            return 1;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason make crossbows quickly shoot
     */
    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int lowerUseDuration(int original) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.CROSSBOW_SPAMMING)) {
            return 2;
        }
        return original;
    }
}