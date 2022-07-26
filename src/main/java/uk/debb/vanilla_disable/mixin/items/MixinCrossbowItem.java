package uk.debb.vanilla_disable.mixin.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
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