package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    /**
     * @param itemStack the crossbow
     * @param cir       the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason make crossbows quickly charge
     */
    @Inject(method = "getChargeDuration", at = @At("HEAD"), cancellable = true)
    private static void lowerChargeDuration(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.CROSSBOW_SPAMMING)) {
            cir.setReturnValue(1);
        }
    }

    /**
     * @param itemStack the crossbow
     * @param cir       the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason make crossbows quickly shoot
     */
    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    private void lowerUseDuration(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.CROSSBOW_SPAMMING)) {
            cir.setReturnValue(2);
        }
    }
}