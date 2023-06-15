package uk.debb.vanilla_disable.command.mixin.rule.item.spam;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    @ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
    private static int getChargeDuration(int original) {
        return DataHandler.getBoolean("items", "minecraft:crossbow", "can_spam") ? 1 : original;
    }

    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int getUseDuration(int original) {
        return DataHandler.getBoolean("items", "minecraft:crossbow", "can_spam") ? 2 : original;
    }
}
