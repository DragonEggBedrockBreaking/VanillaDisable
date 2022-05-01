package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(BuddingAmethystBlock.class)
public abstract class MixinBuddingAmethystBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason allows pistons to push budding amethyst blocks
     * @param blockState the state of the budding amethyst block
     * @param cir the returnable callback info
     */
    @Inject(method = "getPistonPushReaction", at = @At("HEAD"), cancellable = true)
    private void pushableBuddingAmethyst(BlockState blockState, CallbackInfoReturnable<PushReaction> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PUSHABLE_BUDDING_AMETHYST)) {
            cir.setReturnValue(PushReaction.PUSH_ONLY);
        }
    }
}
