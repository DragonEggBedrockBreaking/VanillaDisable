package uk.debb.vanilla_disable.command.mixin.rule.enchantment.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(Enchantment.class)
public abstract class MixinEnchantment {
    @ModifyReturnValue(method = "canEnchant", at = @At("RETURN"))
    private boolean canEnchant(boolean original, ItemStack itemStack) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (!itemStack.getItem().canBeDepleted()) return original;
        String item = "can_enchant_" + CommandDataHandler.lightCleanup(CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem()));
        return CommandDataHandler.getCachedBoolean("enchantments", CommandDataHandler.getKeyFromEnchantmentRegistry((Enchantment) (Object) this), item);
    }
}
