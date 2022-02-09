package uk.debb.vanilla_disable.mixin.misc.hunger;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Item.class)
public abstract class MixinItem {
    /**
     * @author DragonEggBedrockBreaking
     * @reason make food eating instant
     * @param stack the stack of items that the player is holding
     * @param cir the returnable callback info
     */
    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void removeMaxUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.OLD_HUNGER) && stack.getItem().isFood()) {
            cir.setReturnValue(1);
        }
    }
}
