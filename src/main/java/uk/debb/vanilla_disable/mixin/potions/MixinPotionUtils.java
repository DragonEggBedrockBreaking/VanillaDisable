package uk.debb.vanilla_disable.mixin.potions;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
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
            if (!GameruleHelper.getBool(Gamerules.SPLASH_POTIONS_ENABLED)) {
                return new ArrayList<>();
            }
        } else if (itemStack.getItem() instanceof LingeringPotionItem) {
            if (!GameruleHelper.getBool(Gamerules.LINGERING_POTIONS_ENABLED)) {
                return new ArrayList<>();
            }
        } else if (!GameruleHelper.getBool(Gamerules.NORMAL_POTIONS_ENABLED)) {
            return new ArrayList<>();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = potionUtilsPotionMap.get(potion);
        if (!GameruleHelper.getBool(Gamerules.POTIONS_ENABLED) || (gameRule != null && !GameruleHelper.getBool(gameRule))) {
            return new ArrayList<>();
        }
        return original;
    }
}