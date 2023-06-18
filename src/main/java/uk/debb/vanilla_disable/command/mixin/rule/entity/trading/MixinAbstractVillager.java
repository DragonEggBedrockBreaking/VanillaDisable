package uk.debb.vanilla_disable.command.mixin.rule.entity.trading;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

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
        String entity = DataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        if (DataHandler.getCachedBoolean("entities", entity, "can_infinitely_trade")) {
            return new MerchantOffer(new CompoundTag());
        }
        return receiver;
    }

    @ModifyReturnValue(method = "getOffers", at = @At("RETURN"))
    private MerchantOffers clearOffers(MerchantOffers original) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        if (!DataHandler.getCachedBoolean("entities", entity, "can_trade")) {
            return new MerchantOffers();
        }
        return original;
    }
}
