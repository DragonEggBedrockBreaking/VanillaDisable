package uk.debb.vanilla_disable.mixin.command.enchantment.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @ModifyReturnValue(method = "getItemEnchantmentLevel", at = @At("RETURN"))
    private static int vanillaDisable$getItemEnchantmentLevel(int original, Enchantment enchantment, ItemStack stack) {
        String item = "can_enchant_" + CommandDataHandler.lightCleanup(CommandDataHandler.getKeyFromItemRegistry(stack.getItem()));
        if (!CommandDataHandler.getCachedBoolean("enchantments", CommandDataHandler.getKeyFromEnchantmentRegistry(enchantment), item)) {
            ItemEnchantments itemEnchantments = stack.getEnchantments();
            itemEnchantments.enchantments = itemEnchantments.enchantments.object2IntEntrySet().stream()
                    .filter(e -> CommandDataHandler.getCachedBoolean("enchantments", CommandDataHandler.getKeyFromEnchantmentRegistry(e.getKey().value()), item))
                    .collect(Object2IntOpenHashMap::new, (m, e) -> m.put(e.getKey(), e.getIntValue()), Object2IntOpenHashMap::putAll);
            EnchantmentHelper.setEnchantments(stack, itemEnchantments);
            return 0;
        }
        return original;
    }
}
