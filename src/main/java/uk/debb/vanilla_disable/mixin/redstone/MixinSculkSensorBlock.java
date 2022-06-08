package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(SculkSensorBlock.class)
public abstract class MixinSculkSensorBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent the sculk sensor from being activated at all
     * @param blockState the state of the sculk sensor
     * @param cir the returnable callback info
     */
    @Inject(method = "canActivate", at = @At("HEAD"), cancellable = true)
    private static void cannotActivate(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.SCULK_SENSOR_ENABLED)) {
            cir.setReturnValue(false);
        }
    }
}