package uk.debb.vanilla_disable.mixin.command.entity.conversions;

import net.minecraft.world.entity.monster.Skeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Skeleton.class)
public abstract class MixinSkeleton {
    @Inject(method = "doFreezeConversion", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$doFreezeConversion(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:stray", "can_be_converted_to")) {
            ci.cancel();
        }
    }
}
