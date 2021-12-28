package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.fluid.LavaFluid;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LavaFluid.class)
public abstract class MixinLavaFluid {
    /**
     * @author DragonEggBedrockBreaking
     * @reason edit how far the fluid flows
     * @param world the world
     * @param cir the returnable callback info
     */
    @Inject(method = "getLevelDecreasePerBlock", at = @At("HEAD"), cancellable = true)
    private void getWaterLevelDecreasePerBlock(WorldView world, CallbackInfoReturnable<Integer> cir) {
        if (world instanceof World) {
            if (world.getDimension().isUltrawarm()) {
                cir.setReturnValue(((World) world).getGameRules().getBoolean(RegisterGamerules.LAVA_REACHES_FAR_IN_NETHER) ? 1 : 2);
            } else {
                cir.setReturnValue(((World) world).getGameRules().getBoolean(RegisterGamerules.LAVA_REACHES_FAR) ? 1 : 2);
            }
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit how fast the fluid flows
     * @param world the world
     * @param cir the returnable callback info
     */
    @Inject(method = "getTickRate", at = @At("HEAD"), cancellable = true)
    private void getWaterTickRate(WorldView world, CallbackInfoReturnable<Integer> cir) {
        if (world instanceof World) {
            if (world.getDimension().isUltrawarm()) {
                cir.setReturnValue(((World) world).getGameRules().getInt(RegisterGamerules.LAVA_FLOW_SPEED_NETHER));
            } else {
                cir.setReturnValue(((World) world).getGameRules().getInt(RegisterGamerules.LAVA_FLOW_SPEED));
            }
        }
    }
}
