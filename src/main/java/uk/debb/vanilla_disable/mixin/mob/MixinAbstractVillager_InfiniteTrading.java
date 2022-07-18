package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Restriction(
        conflict = {
                @Condition("tweakeroo")
        }
)
@Mixin(AbstractVillager.class)
public abstract class MixinAbstractVillager_InfiniteTrading {
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
}