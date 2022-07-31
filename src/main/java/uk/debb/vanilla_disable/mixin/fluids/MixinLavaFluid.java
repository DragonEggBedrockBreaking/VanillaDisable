package uk.debb.vanilla_disable.mixin.fluids;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

@Mixin(LavaFluid.class)
public abstract class MixinLavaFluid {
    @ModifyReturnValue(method = "getDropOff", at = @At("RETURN"))
    private int getLavaDropOff(int original, LevelReader world) {
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                return BooleanGamerules.LAVA_REACHES_FAR_IN_NETHER.getValue() ? 1 : 2;
            } else {
                return BooleanGamerules.LAVA_REACHES_FAR.getValue() ? 1 : 2;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "getTickDelay", at = @At("RETURN"))
    private int getLavaTickDelay(int original, LevelReader world) {
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                return IntegerGamerules.LAVA_FLOW_SPEED_NETHER.getValue();
            } else {
                return IntegerGamerules.LAVA_FLOW_SPEED.getValue();
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "canConvertToSource", at = @At("RETURN"))
    private boolean canLavaConvertToSource(boolean original) {
        return BooleanGamerules.INFINITE_LAVA.getValue();
    }
}