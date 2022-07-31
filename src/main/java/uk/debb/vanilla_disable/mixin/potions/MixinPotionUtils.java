package uk.debb.vanilla_disable.mixin.potions;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

import java.util.ArrayList;
import java.util.List;

@Mixin(PotionUtils.class)
public abstract class MixinPotionUtils implements Maps {
    @ModifyReturnValue(method = "getMobEffects", at = @At("RETURN"))
    private static List<MobEffectInstance> removeMobEffects(List<MobEffectInstance> original, ItemStack itemStack) {
        Potion potion = PotionUtils.getPotion(itemStack);
        if (itemStack.getItem() instanceof SplashPotionItem) {
            if (!BooleanGamerules.SPLASH_POTIONS_ENABLED.getValue()) {
                return new ArrayList<>();
            }
        } else if (itemStack.getItem() instanceof LingeringPotionItem) {
            if (!BooleanGamerules.LINGERING_POTIONS_ENABLED.getValue()) {
                return new ArrayList<>();
            }
        } else if (!BooleanGamerules.NORMAL_POTIONS_ENABLED.getValue()) {
            return new ArrayList<>();
        }
        BooleanGamerules gameRule = potionUtilsPotionMap.get(potion);
        if (!BooleanGamerules.POTIONS_ENABLED.getValue() || (gameRule != null && !gameRule.getValue())) {
            return new ArrayList<>();
        }
        return original;
    }
}