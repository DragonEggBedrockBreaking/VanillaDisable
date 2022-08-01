package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(DaylightDetectorBlock.class)
public abstract class MixinDaylightDetectorBlock {
    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (!Gamerules.DAYLIGHT_SENSOR_ENABLED.getValue(Boolean::parseBoolean)) {
            return 0;
        }
        return original;
    }
}