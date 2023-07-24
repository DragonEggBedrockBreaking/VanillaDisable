package uk.debb.vanilla_disable.mixin.command.block.experience;

import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    protected abstract Block asBlock();

    @Inject(method = "popExperience", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$popExperience(CallbackInfo ci) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.asBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_drop_xp")) {
            ci.cancel();
        }
    }
}
