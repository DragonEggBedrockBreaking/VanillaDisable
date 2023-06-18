package uk.debb.vanilla_disable.command.mixin.rule.block.experience;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(targets = {"net.minecraft.world.inventory.GrindstoneMenu$4"})
public abstract class MixinGrindstoneMenu {
    @ModifyReturnValue(method = "getExperienceAmount", at = @At("RETURN"))
    private int getExperienceAmount(int original) {
        if (!DataHandler.getCachedBoolean("blocks", "minecraft:grindstone", "can_drop_xp")) {
            return 0;
        }
        return original;
    }
}
