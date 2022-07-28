package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.PressurePlateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(PressurePlateBlock.class)
public abstract class MixinPressurePlateBlock {
    @ModifyReturnValue(method = "getSignalStrength", at = @At("RETURN"))
    private int modifySignalStrength(int original) {
        if (!GameruleHelper.getBool(BooleanGamerules.PRESSURE_PLATE_ENABLED)) {
            return 0;
        }
        return original;
    }
}