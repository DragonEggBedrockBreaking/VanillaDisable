package uk.debb.vanilla_disable.mixin.misc.hunger;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Item.class)
public abstract class MixinItem {
    /**
     * @author DragonEggBedrockBreaking
     * @reason make food eating instant
     * @param stack the stack of items that the player is holding
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    private void editUseDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (VDServer.getServer().getGameRules().getBoolean(Gamerules.OLD_HUNGER) && stack.getItem().isEdible()) {
            cir.setReturnValue(1);
        }
    }
}
