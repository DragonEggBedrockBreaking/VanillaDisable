package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason edit the delay of the redstone component
     * @param blockState the state of the block
     * @param cir the returnable callback info
     */
    @Inject(method = "getDelay", at = @At("HEAD"), cancellable = true)
    private void modifyDelay(BlockState blockState, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.COMPARATOR_BASE_DELAY));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit the signal that is outputted by the redstone component
     * @param blockGetter the block getter
     * @param blockPos the position of the block
     * @param blockState the state of the block
     * @param cir the returnable callback info
     */
    @Inject(method = "getOutputSignal", at = @At("HEAD"), cancellable = true)
    private void modifyOutputSignal(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.COMPARATOR_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}
