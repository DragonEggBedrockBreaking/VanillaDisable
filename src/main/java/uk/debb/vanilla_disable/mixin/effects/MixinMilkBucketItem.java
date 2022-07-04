package uk.debb.vanilla_disable.mixin.effects;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(MilkBucketItem.class)
public class MixinMilkBucketItem {
    /**
     * @param livingEntity the entity drinking the milk bucket
     * @return whether the entity has had its effects removed
     * @author DragonEggBedrockBreaking
     * @reason stop milk buckets from removing effects
     */
    @WrapWithCondition(
            method = "finishUsingItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"
            )
    )
    private boolean cancelRemovingEffects(LivingEntity livingEntity) {
        if (VDServer.getServer() == null) return true;
        return GameruleHelper.getBool(Gamerules.MILK_CLEARS_EFFECTS);
    }
}