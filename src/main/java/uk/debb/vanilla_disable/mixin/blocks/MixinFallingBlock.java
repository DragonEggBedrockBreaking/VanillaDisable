package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(FallingBlock.class)
public abstract class MixinFallingBlock implements Maps {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void neverFree(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        BooleanGamerules gameRule = fallingBlockBlockMap.get(blockState.getBlock());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            ci.cancel();
        }
    }
}
