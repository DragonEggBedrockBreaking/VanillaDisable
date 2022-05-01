package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {
    /**
     * @author DragonEggBedrockBreaking
     * @reason make crossbows quickly shoot
     * @param itemStack the crossbow
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    private void lowerUseDuration(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CROSSBOW_SPAMMING)) {
            cir.setReturnValue(2);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason make crossbows quickly charge
     * @param itemStack the crossbow
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getChargeDuration", at = @At("HEAD"), cancellable = true)
    private static void lowerChargeDuration(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CROSSBOW_SPAMMING)) {
            cir.setReturnValue(1);
        }
    }
}
