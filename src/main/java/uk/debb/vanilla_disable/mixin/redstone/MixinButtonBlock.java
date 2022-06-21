package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ButtonBlock.class)
public abstract class MixinButtonBlock {
    @Shadow @Final private boolean sensitive;

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify how long a button is held down for
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getPressDuration", at = @At("HEAD"), cancellable = true)
    private void modifyPressDuration(CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (this.sensitive) {
            cir.setReturnValue(GameruleHelper.getInt(Gamerules.WOOD_BUTTON_PRESS_DURATION));
        } else {
            cir.setReturnValue(GameruleHelper.getInt(Gamerules.STONE_BUTTON_PRESS_DURATION));
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     * @param blockState the state of the block
     * @param blockGetter the block getter
     * @param blockPos the position of the block
     * @param direction the direction of the block
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getSignal", at = @At("HEAD"), cancellable = true)
    private void modifySignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.BUTTON_ENABLED)) {
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
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getDirectSignal", at = @At("HEAD"), cancellable = true)
    private void modifyDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.BUTTON_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}
