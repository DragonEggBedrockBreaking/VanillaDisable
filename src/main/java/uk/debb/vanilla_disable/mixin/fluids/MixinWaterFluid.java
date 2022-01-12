package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.fluid.WaterFluid;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(WaterFluid.class)
public abstract class MixinWaterFluid {
    /**
     * @author DragonEggBedrockBreaking
     * @reason edit how far the fluid flows
     * @param world the world
     * @param cir the returnable callback info
     */
    @Inject(method = "getLevelDecreasePerBlock", at = @At("HEAD"), cancellable = true)
    private void getWaterLevelDecreasePerBlock(WorldView world, CallbackInfoReturnable<Integer> cir) {
        if (world instanceof World) {
            cir.setReturnValue(((World) world).getGameRules().getBoolean(RegisterGamerules.WATER_REACHES_FAR) ? 1 : 2);
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
            cir.setReturnValue(((World) world).getGameRules().getInt(RegisterGamerules.WATER_FLOW_SPEED));
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify whether or not the fluid can form infinite water sources
     * @param cir the returnable callback info
     */
    @Inject(method = "isInfinite", at = @At("HEAD"), cancellable = true)
    private void changeInfinite(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.INFINITE_WATER));
    }
}
