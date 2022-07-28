package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(PistonBaseBlock.class)
public abstract class MixinPistonBaseBlock {
    @ModifyReturnValue(method = "triggerEvent", at = @At("RETURN"))
    private boolean cancelTriggeringEvent(boolean original) {
        if (!GameruleHelper.getBool(Gamerules.PISTON_ENABLED)) {
            return false;
        }
        return original;
    }
}