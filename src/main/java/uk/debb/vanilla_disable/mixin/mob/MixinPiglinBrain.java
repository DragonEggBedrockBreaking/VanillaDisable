package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PiglinBrain.class)
public abstract class MixinPiglinBrain {
    /**
     * @author DragonEggBedrockBreaking
     * @reason don't allow bartering with any items
     * @param stack the stack that the player is trying to barter with
     * @param cir the returnable callback info
     */
    @Inject(method = "acceptsForBarter", at = @At("HEAD"), cancellable = true)
    private static void cancelBarter(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGLIN_BARTERING_ENABLED)) {
            cir.setReturnValue(false);
        }
    }
}
