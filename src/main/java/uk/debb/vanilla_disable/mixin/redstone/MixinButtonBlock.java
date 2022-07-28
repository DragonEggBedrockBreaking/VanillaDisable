package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ButtonBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

@Mixin(ButtonBlock.class)
public abstract class MixinButtonBlock {
    @Shadow
    @Final
    private boolean sensitive;

    @ModifyReturnValue(method = "getPressDuration", at = @At("RETURN"))
    private int modifyPressDuration(int original) {
        if (this.sensitive) {
            return GameruleHelper.getInt(IntegerGamerules.WOOD_BUTTON_PRESS_DURATION);
        } else {
            return GameruleHelper.getInt(IntegerGamerules.STONE_BUTTON_PRESS_DURATION);
        }
    }

    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (!GameruleHelper.getBool(BooleanGamerules.BUTTON_ENABLED)) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        if (!GameruleHelper.getBool(BooleanGamerules.BUTTON_ENABLED)) {
            return 0;
        }
        return original;
    }
}