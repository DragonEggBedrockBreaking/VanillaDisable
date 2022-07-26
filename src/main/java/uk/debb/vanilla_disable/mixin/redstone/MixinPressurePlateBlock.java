package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.PressurePlateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(PressurePlateBlock.class)
public abstract class MixinPressurePlateBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getSignalStrength", at = @At("RETURN"))
    private int modifySignalStrength(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.PRESSURE_PLATE_ENABLED)) {
            return 0;
        }
        return original;
    }
}