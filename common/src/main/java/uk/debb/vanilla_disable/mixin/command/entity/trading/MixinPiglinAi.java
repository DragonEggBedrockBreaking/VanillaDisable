package uk.debb.vanilla_disable.mixin.command.entity.trading;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(PiglinAi.class)
public abstract class MixinPiglinAi {
    @ModifyReturnValue(method = "isBarterCurrency", at = @At("RETURN"))
    private static boolean vanillaDisable$isBarterCurrency(boolean original) {
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:piglin", "can_trade")) {
            return false;
        }
        return original;
    }
}
