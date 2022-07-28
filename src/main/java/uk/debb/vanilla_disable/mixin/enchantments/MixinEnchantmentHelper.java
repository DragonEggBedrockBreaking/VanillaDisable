package uk.debb.vanilla_disable.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper implements Maps {
    @ModifyReturnValue(method = "getItemEnchantmentLevel", at = @At("RETURN"))
    private static int removeEnchantmentLevel(int original, Enchantment enchantment, ItemStack stack) {
        GameRules.Key<GameRules.BooleanValue> gameRule = enchantmentHelperEnchantmentMap.get(enchantment);
        if (!GameruleHelper.getBool(Gamerules.ENCHANTMENTS_ENABLED) ||
                (gameRule != null && !GameruleHelper.getBool(gameRule))) {
            return 0;
        }
        return original;
    }
}