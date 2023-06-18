package uk.debb.vanilla_disable.command.mixin.rule.block.experience;

import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow protected abstract Block asBlock();

    @Inject(method = "popExperience", at = @At("HEAD"), cancellable = true)
    private void popExperience(CallbackInfo ci) {
        String block = DataHandler.getKeyFromBlockRegistry(this.asBlock());
        if (!DataHandler.getCachedBoolean("blocks", block, "can_drop_xp")) {
            ci.cancel();
        }
    }
}
