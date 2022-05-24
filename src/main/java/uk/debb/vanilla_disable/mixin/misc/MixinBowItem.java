package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    /**
     * @author DragonEggBedrockBreaking
     * @reason make bows quickly shoot
     * @param itemStack the bow
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    private void lowerUseDuration(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.BOW_SPAMMING)) {
            cir.setReturnValue(4);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason make bows shoot with full power
     * @param i ???
     * @param cir the returnable callback info (Float)
     */
    @Inject(method = "getPowerForTime", at = @At("HEAD"), cancellable = true)
    private static void modifyPower(int i, CallbackInfoReturnable<Float> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.BOW_SPAMMING)) {
            cir.setReturnValue(1.0F);
        }
    }
}
