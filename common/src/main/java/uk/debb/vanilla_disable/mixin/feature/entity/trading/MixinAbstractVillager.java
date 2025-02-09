/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.trading;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

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
    private MerchantOffer vanillaDisable$increaseUses(MerchantOffer receiver) {
        String entity = DataUtils.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        if (SqlManager.getBoolean("entities", entity, "can_infinitely_trade")) {
            receiver.resetUses();
        }
        return receiver;
    }

    @ModifyReturnValue(method = "getOffers", at = @At("RETURN"))
    private MerchantOffers vanillaDisable$getOffers(MerchantOffers original) {
        if (SqlManager.isConnectionNull()) return original;
        String entity = DataUtils.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        if (!SqlManager.getBoolean("entities", entity, "can_trade")) {
            return new MerchantOffers();
        }
        return original;
    }

    @WrapWithCondition(
            method = "notifyTrade",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/npc/AbstractVillager;rewardTradeXp(Lnet/minecraft/world/item/trading/MerchantOffer;)V"
            )
    )
    private boolean vanillaDisable$rewardTradeXp(AbstractVillager instance, MerchantOffer merchantOffer) {
        String entity = DataUtils.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return SqlManager.getBoolean("entities", entity, "trading_can_drop_xp");
    }
}
