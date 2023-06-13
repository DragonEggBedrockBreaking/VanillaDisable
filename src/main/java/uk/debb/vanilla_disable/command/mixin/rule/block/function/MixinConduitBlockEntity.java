package uk.debb.vanilla_disable.command.mixin.rule.block.function;

import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ConduitBlockEntity.class)
public abstract class MixinConduitBlockEntity {
    @Inject(method = "applyEffects", at = @At("HEAD"), cancellable = true)
    private static void applyEffects(CallbackInfo ci) {
        if (!DataHandler.getBoolean("blocks", "minecraft:conduit", "works")) {
            ci.cancel();
        }
    }
}
