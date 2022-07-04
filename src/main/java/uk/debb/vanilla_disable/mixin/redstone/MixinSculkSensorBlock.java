package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.SculkSensorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(SculkSensorBlock.class)
public abstract class MixinSculkSensorBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason prevent the sculk sensor from being activated at all
     */
    @ModifyReturnValue(method = "canActivate", at = @At("RETURN"))
    private static boolean cannotActivate(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.SCULK_SENSOR_ENABLED)) {
            return false;
        }
        return original;
    }
}