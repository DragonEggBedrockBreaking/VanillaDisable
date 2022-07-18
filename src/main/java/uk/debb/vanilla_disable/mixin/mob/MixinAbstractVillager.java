package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(AbstractVillager.class)
public abstract class MixinAbstractVillager {
    /**
     * @param receiver the original receiver
     * @author DragonEggBedrockBreaking
     */
    @ModifyReceiver(
            method = "notifyTrade",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/trading/MerchantOffer;increaseUses()V"
            ),
            require = 0
    )
    private MerchantOffer modifyUses(MerchantOffer receiver) {
        if (VDServer.getServer() == null) return receiver;
        if (GameruleHelper.getBool(Gamerules.INFINITE_TRADING)) {
            return new MerchantOffer(new CompoundTag());
        }
        return receiver;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getOffers", at = @At("RETURN"))
    private MerchantOffers clearOffers(MerchantOffers original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.VILLAGER_TRADING_ENABLED)) {
            return new MerchantOffers();
        }
        return original;
    }
}