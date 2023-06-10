package uk.debb.vanilla_disable.gamerules.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(targets = {"net.minecraft.world.inventory.GrindstoneMenu$4"})
public abstract class MixinGrindstoneMenu {
    @ModifyReturnValue(method = "getExperienceAmount", at = @At("RETURN"))
    private int modifyExperienceAmount(int original) {
        if (!Gamerules.GRINDSTONES_DROP_XP.getBool()) {
            return 0;
        }
        return original;
    }
}
