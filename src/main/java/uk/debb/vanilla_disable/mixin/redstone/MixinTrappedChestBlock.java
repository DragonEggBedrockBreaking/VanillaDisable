package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(TrappedChestBlock.class)
public abstract class MixinTrappedChestBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     * @param blockState the state of the block
     * @param blockGetter the block getter
     * @param blockPos the position of the block
     * @param direction the direction of the block
     * @param cir the returnable callback info
     */
    @Inject(method = "getSignal", at = @At("HEAD"), cancellable = true)
    private void modifySignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.TRAPPED_CHEST_ENABLED)) {
            cir.setReturnValue(0);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     * @param blockState the state of the block
     * @param blockGetter the block getter
     * @param blockPos the position of the block
     * @param direction the direction of the block
     * @param cir the returnable callback info
     */
    @Inject(method = "getDirectSignal", at = @At("HEAD"), cancellable = true)
    private void modifyDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.TRAPPED_CHEST_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}
