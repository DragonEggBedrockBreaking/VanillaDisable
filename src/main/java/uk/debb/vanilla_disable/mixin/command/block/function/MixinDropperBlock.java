package uk.debb.vanilla_disable.mixin.command.block.function;

import net.minecraft.world.level.block.DropperBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(DropperBlock.class)
public abstract class MixinDropperBlock {
    @Inject(method = "dispenseFrom", at = @At("HEAD"), cancellable = true)
    private void dispenseFrom(CallbackInfo ci) {
        if (!CommandDataHandler.getCachedBoolean("blocks", "minecraft:dropper", "works")) {
            ci.cancel();
        }
    }
}
