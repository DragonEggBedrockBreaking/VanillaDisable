package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.SculkSensorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(SculkSensorBlock.class)
public abstract class MixinSculkSensorBlock {
    @ModifyReturnValue(method = "canActivate", at = @At("RETURN"))
    private static boolean cannotActivate(boolean original) {
        if (!Gamerules.SCULK_SENSOR_ENABLED.getBool()) {
            return false;
        }
        return original;
    }
}