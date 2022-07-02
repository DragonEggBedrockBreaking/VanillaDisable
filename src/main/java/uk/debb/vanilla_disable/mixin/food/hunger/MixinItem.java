package uk.debb.vanilla_disable.mixin.food.hunger;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Item.class)
public abstract class MixinItem {
    /**
     * @param stack the stack of items that the player is holding
     * @param cir   the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason make food eating instant
     */
    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    private void editUseDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER) && stack.getItem().isEdible()) {
            cir.setReturnValue(1);
        }
    }
}