package uk.debb.vanilla_disable.command.mixin.rule.entity.trading;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(PiglinAi.class)
public abstract class MixinPiglinAi {
    @ModifyReturnValue(method = "isBarterCurrency", at = @At("RETURN"))
    private static boolean cancelBarter(boolean original) {
        if (!DataHandler.getCachedBoolean("entities", "minecraft:piglin", "can_trade")) {
            return false;
        }
        return original;
    }
}
