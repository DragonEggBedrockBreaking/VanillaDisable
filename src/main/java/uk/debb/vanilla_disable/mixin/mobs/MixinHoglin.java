package uk.debb.vanilla_disable.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(Hoglin.class)
public abstract class MixinHoglin {
    @ModifyReturnValue(method = "isImmuneToZombification", at = @At("RETURN"))
    private boolean setImmuneToZombification(boolean original) {
        if (!BooleanGamerules.HOGLINS_CONVERT_TO_ZOGLINS.getValue()) {
            return true;
        }
        return original;
    }
}