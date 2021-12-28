package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(FlowableFluid.class)
public abstract class MixinFlowableFluid {
    /**
     * @author DragonEggBedrockBreaking
     * @reason modify whether or not the fluid can form infinite water sources
     * @param fluid the type of fluid
     * @param world the world
     * @param pos the position of the fluid
     * @param state the state of the fluid
     * @return whether or not the fluid can form infinite water sources
     */
    @Redirect(
        method = "getUpdatedState",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/fluid/FlowableFluid;isInfinite()Z"
        )
    )
    protected boolean isFluidInfinite(FlowableFluid fluid, WorldView world, BlockPos pos, BlockState state) {
        if (fluid instanceof WaterFluid) {
            if (world instanceof World) {
                return ((World) world).getGameRules().getBoolean(RegisterGamerules.INFINITE_WATER);
            } else {
                return true;
            }
        }
        if (fluid instanceof LavaFluid) {
            if (world instanceof World) {
                return ((World) world).getGameRules().getBoolean(RegisterGamerules.INFINITE_LAVA);
            } else {
                return false;
            }
        }
        return false;
    }
}
