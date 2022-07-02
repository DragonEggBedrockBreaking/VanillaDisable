package uk.debb.vanilla_disable.mixin.fluids;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(WaterFluid.class)
public abstract class MixinWaterFluid {
    /**
     * @param world the world
     * @param cir   the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason edit how far the fluid flows
     */
    @Inject(method = "getDropOff", at = @At("HEAD"), cancellable = true)
    private void getWaterDropOff(LevelReader world, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (world instanceof Level) {
            cir.setReturnValue(GameruleHelper.getBool(Gamerules.WATER_REACHES_FAR) ? 1 : 2);
        }
    }

    /**
     * @param world the world
     * @param cir   the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason edit how fast the fluid flows
     */
    @Inject(method = "getTickDelay", at = @At("HEAD"), cancellable = true)
    private void getWaterTickDelay(LevelReader world, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (world instanceof Level) {
            cir.setReturnValue(GameruleHelper.getInt(Gamerules.WATER_FLOW_SPEED));
        }
    }

    /**
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason modify whether the fluid can form infinite water sources
     */
    @Inject(method = "canConvertToSource", at = @At("HEAD"), cancellable = true)
    private void canWaterConvertToSource(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        cir.setReturnValue(GameruleHelper.getBool(Gamerules.INFINITE_WATER));
    }
}