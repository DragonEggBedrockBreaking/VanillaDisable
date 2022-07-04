package uk.debb.vanilla_disable.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason make bows shoot with full power
     */
    @ModifyReturnValue(method = "getPowerForTime", at = @At("RETURN"))
    private static float modifyPower(float original) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.BOW_SPAMMING)) {
            return 1.0F;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason make bows quickly shoot
     */
    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int lowerUseDuration(int original) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.BOW_SPAMMING)) {
            return 4;
        }
        return original;
    }
}