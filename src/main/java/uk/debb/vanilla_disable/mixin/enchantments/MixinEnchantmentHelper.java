package uk.debb.vanilla_disable.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper implements Maps {
    @ModifyReturnValue(method = "getItemEnchantmentLevel", at = @At("RETURN"))
    private static int removeEnchantmentLevel(int original, Enchantment enchantment, ItemStack stack) {
        BooleanGamerules gameRule = enchantmentHelperEnchantmentMap.get(enchantment);
        if (!BooleanGamerules.ENCHANTMENTS_ENABLED.getValue() ||
                (gameRule != null && !gameRule.getValue())) {
            return 0;
        }
        return original;
    }
}