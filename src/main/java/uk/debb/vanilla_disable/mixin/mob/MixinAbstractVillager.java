package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(value = AbstractVillager.class, priority = 999)
public abstract class MixinAbstractVillager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason allow for infintie trading with villagers
     * @param tradeOffer the trade offer
     * @param offer the trade offer
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
        if (!VDServer.getServer().getGameRules().getBoolean(Gamerules.INFINITE_TRADING)) {
            offer.increaseUses();
        } else {
            offer.resetUses();
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from trading with villagers
     * @param cir the returnable callback info (net.minecraft.world.item.trading.MerchantOffers)
     */
    @Inject(method = "getOffers", at = @At("HEAD"), cancellable = true)
    private void clearOffers(CallbackInfoReturnable<MerchantOffers> cir) {
        if (VDServer.getServer() == null) return;
        if (!VDServer.getServer().getGameRules().getBoolean(Gamerules.VILLAGER_TRADING_ENABLED)) {
            cir.setReturnValue(new MerchantOffers());
        }
    }
}
