package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ButtonBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ButtonBlock.class)
public abstract class MixinButtonBlock {
    @Shadow
    @Final
    private boolean sensitive;

    @ModifyReturnValue(method = "getPressDuration", at = @At("RETURN"))
    private int modifyPressDuration(int original) {
        if (this.sensitive) {
            return Gamerules.WOOD_BUTTON_PRESS_DURATION.getInt();
        } else {
            return Gamerules.STONE_BUTTON_PRESS_DURATION.getInt();
        }
    }
}