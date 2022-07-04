package uk.debb.vanilla_disable.mixin.fluids;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LavaFluid.class)
public abstract class MixinLavaFluid {
    /**
     * @param world the world
     * @param cir   the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason edit how far the fluid flows
     */
    @Inject(method = "getDropOff", at = @At("HEAD"), cancellable = true)
    private void getLavaDropOff(LevelReader world, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                cir.setReturnValue(GameruleHelper.getBool(Gamerules.LAVA_REACHES_FAR_IN_NETHER) ? 1 : 2);
            } else {
                cir.setReturnValue(GameruleHelper.getBool(Gamerules.LAVA_REACHES_FAR) ? 1 : 2);
            }
        }
    }

    /**
     * @param world the world
     * @param cir   the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason edit how fast the fluid flows
     */
    @Inject(method = "getTickDelay", at = @At("HEAD"), cancellable = true)
    private void getLavaTickDelay(LevelReader world, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                cir.setReturnValue(GameruleHelper.getInt(Gamerules.LAVA_FLOW_SPEED_NETHER));
            } else {
                cir.setReturnValue(GameruleHelper.getInt(Gamerules.LAVA_FLOW_SPEED));
            }
        }
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason modify whether the fluid can form infinite water sources
     */
    @ModifyReturnValue(method = "canConvertToSource", at = @At("RETURN"))
    private boolean canLavaConvertToSource(boolean original) {
        if (VDServer.getServer() == null) return original;
        return GameruleHelper.getBool(Gamerules.INFINITE_LAVA);
    }
}