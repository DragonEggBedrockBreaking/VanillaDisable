package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(value = AbstractVillager.class, priority = 999)
public abstract class MixinAbstractVillager {
    /**
     * @param tradeOffer the trade offer
     * @param offer      the trade offer
     * @author DragonEggBedrockBreaking
     * @reason allow for infinite trading with villagers
     */
    @Redirect(
            method = "notifyTrade",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/trading/MerchantOffer;increaseUses()V"
            ),
            require = 0
    )
    private void cancelUses(MerchantOffer tradeOffer, MerchantOffer offer) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.INFINITE_TRADING)) {
            offer.increaseUses();
        } else {
            offer.resetUses();
        }
    }

    /**
     * @param cir the returnable callback info (net.minecraft.world.item.trading.MerchantOffers)
     * @author DragonEggBedrockBreaking
     * @reason stop players from trading with villagers
     */
    @Inject(method = "getOffers", at = @At("HEAD"), cancellable = true)
    private void clearOffers(CallbackInfoReturnable<MerchantOffers> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.VILLAGER_TRADING_ENABLED)) {
            cir.setReturnValue(new MerchantOffers());
        }
    }
}