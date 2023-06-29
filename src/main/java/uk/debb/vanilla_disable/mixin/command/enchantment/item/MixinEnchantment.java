package uk.debb.vanilla_disable.mixin.command.enchantment.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Enchantment.class)
public abstract class MixinEnchantment {
    @ModifyReturnValue(method = "canEnchant", at = @At("RETURN"))
    private boolean canEnchant(boolean original, ItemStack stack) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (!stack.getItem().canBeDepleted()) return original;
        String item = "can_enchant_" + CommandDataHandler.lightCleanup(CommandDataHandler.getKeyFromItemRegistry(stack.getItem()));
        return CommandDataHandler.getCachedBoolean("enchantments", CommandDataHandler.getKeyFromEnchantmentRegistry((Enchantment) (Object) this), item);
    }
}
