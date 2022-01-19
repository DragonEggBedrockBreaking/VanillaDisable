package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MerchantEntity.class)
public abstract class MixinMerchantEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason allow for infintie trading with villagers
     * @param tradeOffer the trade offer
     * @param offer the trade offer
     */
    @Redirect(
        method = "trade",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/village/TradeOffer;use()V"
        )
    )
    private void cancelUse(TradeOffer tradeOffer, TradeOffer offer) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.INFINITE_TRADING)) {
            offer.use();
        } else {
            offer.resetUses();
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from trading with villagers
     * @param cir the returnable callback info
     */
    @Inject(method = "getOffers", at = @At("HEAD"), cancellable = true)
    private void clearOffers(CallbackInfoReturnable<TradeOfferList> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.VILLAGER_TRADING_ENABLED)) {
            cir.setReturnValue(new TradeOfferList());
        }
    }
}
