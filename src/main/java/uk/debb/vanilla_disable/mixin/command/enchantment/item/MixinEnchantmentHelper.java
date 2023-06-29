package uk.debb.vanilla_disable.mixin.command.enchantment.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @ModifyReturnValue(method = "getItemEnchantmentLevel", at = @At("RETURN"))
    private static int getItemEnchantmentLevel(int original, Enchantment enchantment, ItemStack stack) {
        if (!enchantment.canEnchant(stack)) return original;
        String item = "can_enchant_" + CommandDataHandler.getKeyFromItemRegistry(stack.getItem());
        if (!CommandDataHandler.getCachedBoolean("enchantments", CommandDataHandler.getKeyFromEnchantmentRegistry(enchantment), item)) {
            return 0;
        }
        return original;
    }
}
