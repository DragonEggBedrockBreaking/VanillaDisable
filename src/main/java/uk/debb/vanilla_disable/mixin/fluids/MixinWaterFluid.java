package uk.debb.vanilla_disable.mixin.fluids;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(WaterFluid.class)
public abstract class MixinWaterFluid {
    /**
     * @param original the original value
     * @param world    the world
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDropOff", at = @At("RETURN"))
    private int getWaterDropOff(int original, LevelReader world) {
        if (VDServer.getServer() == null) return original;
        if (world instanceof Level) {
            return GameruleHelper.getBool(Gamerules.WATER_REACHES_FAR) ? 1 : 2;
        }
        return original;
    }

    /**
     * @param original the original value
     * @param world    the world
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getTickDelay", at = @At("RETURN"))
    private int getWaterTickDelay(int original, LevelReader world) {
        if (VDServer.getServer() == null) return original;
        if (world instanceof Level) {
            return GameruleHelper.getInt(Gamerules.WATER_FLOW_SPEED);
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "canConvertToSource", at = @At("RETURN"))
    private boolean canWaterConvertToSource(boolean original) {
        if (VDServer.getServer() == null) return original;
        return GameruleHelper.getBool(Gamerules.INFINITE_WATER);
    }
}