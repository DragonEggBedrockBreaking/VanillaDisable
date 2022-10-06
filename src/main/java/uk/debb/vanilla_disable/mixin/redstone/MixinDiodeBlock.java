package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.DiodeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(DiodeBlock.class)
public abstract class MixinDiodeBlock {
    @ModifyReturnValue(method = "getOutputSignal", at = @At("RETURN"))
    private int modifyOutputSignal(int original) {
        return Gamerules.REPEATER_SIGNAL.getInt();
    }
}
