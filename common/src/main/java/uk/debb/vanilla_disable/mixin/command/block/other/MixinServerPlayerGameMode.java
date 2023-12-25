package uk.debb.vanilla_disable.mixin.command.block.other;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ServerPlayerGameMode.class)
public abstract class MixinServerPlayerGameMode {
    @Shadow
    protected ServerLevel level;

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$destroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(
                this.level.getBlockState(pos).getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_break")) {
            cir.setReturnValue(false);
        }
    }
}
