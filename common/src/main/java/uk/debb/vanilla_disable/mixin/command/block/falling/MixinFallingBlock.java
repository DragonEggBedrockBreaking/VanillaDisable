package uk.debb.vanilla_disable.mixin.command.block.falling;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(FallingBlock.class)
public abstract class MixinFallingBlock {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        String name = CommandDataHandler.getKeyFromBlockRegistry(state.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", name, "can_fall")) {
            ci.cancel();
        }
    }
}
