package uk.debb.vanilla_disable.mixin.fluids;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(LavaFluid.class)
public abstract class MixinLavaFluid {
    /**
     * @param original the original value
     * @param world    the world
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDropOff", at = @At("RETURN"))
    private int getLavaDropOff(int original, LevelReader world) {
        if (VDServer.getServer() == null) return original;
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                return GameruleHelper.getBool(Gamerules.LAVA_REACHES_FAR_IN_NETHER) ? 1 : 2;
            } else {
                return GameruleHelper.getBool(Gamerules.LAVA_REACHES_FAR) ? 1 : 2;
            }
        }
        return original;
    }

    /**
     * @param original the original value
     * @param world    the world
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getTickDelay", at = @At("RETURN"))
    private int getLavaTickDelay(int original, LevelReader world) {
        if (VDServer.getServer() == null) return original;
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                return GameruleHelper.getInt(Gamerules.LAVA_FLOW_SPEED_NETHER);
            } else {
                return GameruleHelper.getInt(Gamerules.LAVA_FLOW_SPEED);
            }
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "canConvertToSource", at = @At("RETURN"))
    private boolean canLavaConvertToSource(boolean original) {
        if (VDServer.getServer() == null) return original;
        return GameruleHelper.getBool(Gamerules.INFINITE_LAVA);
    }
}