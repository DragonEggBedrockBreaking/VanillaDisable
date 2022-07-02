package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(BuddingAmethystBlock.class)
public abstract class MixinBuddingAmethystBlock {
    /**
     * @param blockState the state of the budding amethyst block
     * @param cir        the returnable callback info (net.minecraft.world.level.material.PushReaction)
     * @author DragonEggBedrockBreaking
     * @reason allows pistons to push budding amethyst blocks
     */
    @Inject(method = "getPistonPushReaction", at = @At("HEAD"), cancellable = true)
    private void pushableBuddingAmethyst(BlockState blockState, CallbackInfoReturnable<PushReaction> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.PUSHABLE_BUDDING_AMETHYST)) {
            cir.setReturnValue(PushReaction.PUSH_ONLY);
        }
    }
}