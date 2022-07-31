package uk.debb.vanilla_disable.mixin.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    @ModifyReturnValue(method = "getPowerForTime", at = @At("RETURN"))
    private static float modifyPower(float original) {
        if (BooleanGamerules.BOW_SPAMMING.getValue()) {
            return 1.0F;
        }
        return original;
    }

    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int lowerUseDuration(int original) {
        if (BooleanGamerules.BOW_SPAMMING.getValue()) {
            return 4;
        }
        return original;
    }
}