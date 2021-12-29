package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MerchantEntity.class)
public abstract class MixinMerchantEntity extends PassiveEntity {
    public MixinMerchantEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

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
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.INFINITE_TRADING)) {
            offer.use();
        } else {
            offer.resetUses();
        }
    }
}
