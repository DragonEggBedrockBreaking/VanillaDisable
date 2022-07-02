package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(RedStoneWireBlock.class)
public abstract class MixinRedStoneWireBlock {
    /**
     * @param blockState the state of the block
     * @param cir        the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason modify the signal outputted
     */
    @Inject(method = "getWireSignal", at = @At("HEAD"), cancellable = true)
    private void modifySignal(BlockState blockState, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.REDSTONE_WIRE_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}