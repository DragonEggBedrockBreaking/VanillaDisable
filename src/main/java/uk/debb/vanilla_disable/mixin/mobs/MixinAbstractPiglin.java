package uk.debb.vanilla_disable.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(AbstractPiglin.class)
public abstract class MixinAbstractPiglin {
    @ModifyReturnValue(method = "isImmuneToZombification", at = @At("RETURN"))
    private boolean setImmuneToZombification(boolean original) {
        if (!GameruleHelper.getBool(BooleanGamerules.PIGLINS_CONVERT_TO_ZIGLINS)) {
            return true;
        }
        return original;
    }
}