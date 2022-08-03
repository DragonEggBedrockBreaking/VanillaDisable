package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.RedStoneWireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(RedStoneWireBlock.class)
public abstract class MixinRedStoneWireBlock {
    @ModifyReturnValue(method = "getWireSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (!Gamerules.REDSTONE_WIRE_ENABLED.getBool()) {
            return 0;
        }
        return original;
    }
}