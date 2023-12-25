package uk.debb.vanilla_disable.mixin.command.item.spam;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    @ModifyReturnValue(method = "getPowerForTime", at = @At("RETURN"))
    private static float vanillaDisable$getPowerForTime(float original) {
        return CommandDataHandler.getCachedBoolean("items", "minecraft:bow", "can_spam") ? 1.0F : original;
    }

    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int vanillaDisable$getUseDuration(int original) {
        return CommandDataHandler.getCachedBoolean("items", "minecraft:bow", "can_spam") ? 4 : original;
    }
}
