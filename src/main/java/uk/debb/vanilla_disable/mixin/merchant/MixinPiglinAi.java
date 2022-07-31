package uk.debb.vanilla_disable.mixin.merchant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(PiglinAi.class)
public abstract class MixinPiglinAi {
    @ModifyReturnValue(method = "isBarterCurrency", at = @At("RETURN"))
    private static boolean cancelBarter(boolean original) {
        if (!BooleanGamerules.PIGLIN_BARTERING_ENABLED.getValue()) {
            return false;
        }
        return original;
    }
}