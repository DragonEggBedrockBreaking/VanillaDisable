package uk.debb.vanilla_disable.mixin.command.block.function;

import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock {
    @Inject(method = "dispenseFrom", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$dispenseFrom(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("blocks", "minecraft:dispenser", "works")) {
            ci.cancel();
        }
    }
}
