package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.RedStoneWireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(RedStoneWireBlock.class)
public abstract class MixinRedStoneWireBlock {
    @ModifyReturnValue(method = "getWireSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (!GameruleHelper.getBool(BooleanGamerules.REDSTONE_WIRE_ENABLED)) {
            return 0;
        }
        return original;
    }
}