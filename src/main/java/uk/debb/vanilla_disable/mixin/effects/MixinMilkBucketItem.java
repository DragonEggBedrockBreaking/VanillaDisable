package uk.debb.vanilla_disable.mixin.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(MilkBucketItem.class)
public class MixinMilkBucketItem {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop milk buckets from removing effects
     * @param livingEntity the entity drinking the milk bucket
     * @return whether the entity has had its effects removed
     */
    @Redirect(
        method = "finishUsingItem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"
        )
    )
    private boolean cancelRemovingEffects(LivingEntity livingEntity) {
        if (VDServer.getServer() != null && VDServer.getServer().getGameRules().getBoolean(Gamerules.MILK_CLEARS_EFFECTS)) {
            return livingEntity.removeAllEffects();
        }
        return false;
    }
}
