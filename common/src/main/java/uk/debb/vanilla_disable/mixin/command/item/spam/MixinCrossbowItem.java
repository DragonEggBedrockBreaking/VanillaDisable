package uk.debb.vanilla_disable.mixin.command.item.spam;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    @ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
    private static int vanillaDisable$getChargeDuration(int original) {
        return CommandDataHandler.getCachedBoolean("items", "minecraft:crossbow", "can_spam") ? 1 : original;
    }

    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int vanillaDisable$getUseDuration(int original) {
        return CommandDataHandler.getCachedBoolean("items", "minecraft:crossbow", "can_spam") ? 2 : original;
    }
}
