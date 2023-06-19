package uk.debb.vanilla_disable.command.mixin.rule.item.enchantment;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

import java.util.Objects;

@Mixin(Enchantment.class)
public abstract class MixinEnchantment {
    @ModifyReturnValue(method = "canEnchant", at = @At("RETURN"))
    private boolean canEnchant(boolean original, ItemStack itemStack) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (!itemStack.getItem().canBeDepleted()) return original;
        String enchantment = Objects.requireNonNull(CommandDataHandler.enchantmentRegistry.getKey((Enchantment) (Object) this)) + "_enchantment";
        return CommandDataHandler.getCachedBoolean("items", CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem()), enchantment);
    }
}
