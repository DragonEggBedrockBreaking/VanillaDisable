package uk.debb.vanilla_disable.command.mixin.rule.item.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

import java.util.Objects;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @ModifyReturnValue(method = "getItemEnchantmentLevel", at = @At("RETURN"))
    private static int getItemEnchantmentLevel(int original, Enchantment enchantment, ItemStack itemStack) {
        if (!enchantment.canEnchant(itemStack)) return original;
        String item = DataHandler.getKeyFromItemRegistry(itemStack.getItem());
        if (!DataHandler.getBoolean("items", item,
                Objects.requireNonNull(DataHandler.enchantmentRegistry.getKey(enchantment)) + "_enchantment")) {
            return 0;
        }

        return original;
    }
}
