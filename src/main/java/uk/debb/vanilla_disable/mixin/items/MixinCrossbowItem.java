package uk.debb.vanilla_disable.mixin.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    @ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
    private static int lowerChargeDuration(int original) {
        if (Gamerules.CROSSBOW_SPAMMING.getBool()) {
            return 1;
        }
        return original;
    }

    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int lowerUseDuration(int original) {
        if (Gamerules.CROSSBOW_SPAMMING.getBool()) {
            return 2;
        }
        return original;
    }
}