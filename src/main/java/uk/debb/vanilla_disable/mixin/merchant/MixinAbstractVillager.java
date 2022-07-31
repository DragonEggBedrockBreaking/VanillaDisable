package uk.debb.vanilla_disable.mixin.merchant;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(AbstractVillager.class)
public abstract class MixinAbstractVillager {
    @ModifyReceiver(
            method = "notifyTrade",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/trading/MerchantOffer;increaseUses()V"
            ),
            require = 0
    )
    private MerchantOffer modifyUses(MerchantOffer receiver) {
        if (BooleanGamerules.INFINITE_TRADING.getValue()) {
            return new MerchantOffer(new CompoundTag());
        }
        return receiver;
    }

    @ModifyReturnValue(method = "getOffers", at = @At("RETURN"))
    private MerchantOffers clearOffers(MerchantOffers original) {
        if (!BooleanGamerules.VILLAGER_TRADING_ENABLED.getValue()) {
            return new MerchantOffers();
        }
        return original;
    }
}