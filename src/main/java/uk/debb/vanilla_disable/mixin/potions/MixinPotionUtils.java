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
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

import java.util.ArrayList;
import java.util.List;

@Mixin(PotionUtils.class)
public abstract class MixinPotionUtils implements Maps {
    @ModifyReturnValue(method = "getMobEffects", at = @At("RETURN"))
    private static List<MobEffectInstance> removeMobEffects(List<MobEffectInstance> original, ItemStack itemStack) {
        Potion potion = PotionUtils.getPotion(itemStack);
        if (itemStack.getItem() instanceof SplashPotionItem) {
            if (!Gamerules.SPLASH_POTIONS_ENABLED.getValue(Boolean::parseBoolean)) {
                return new ArrayList<>();
            }
        } else if (itemStack.getItem() instanceof LingeringPotionItem) {
            if (!Gamerules.LINGERING_POTIONS_ENABLED.getValue(Boolean::parseBoolean)) {
                return new ArrayList<>();
            }
        } else if (!Gamerules.NORMAL_POTIONS_ENABLED.getValue(Boolean::parseBoolean)) {
            return new ArrayList<>();
        }
        Gamerules gameRule = potionUtilsPotionMap.get(potion);
        if (!Gamerules.POTIONS_ENABLED.getValue(Boolean::parseBoolean) || (gameRule != null && !gameRule.getValue(Boolean::parseBoolean))) {
            return new ArrayList<>();
        }
        return original;
    }
}