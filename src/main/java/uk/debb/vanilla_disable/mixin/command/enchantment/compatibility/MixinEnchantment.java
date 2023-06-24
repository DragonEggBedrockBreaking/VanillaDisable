package uk.debb.vanilla_disable.mixin.command.enchantment.compatibility;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Enchantment.class)
public abstract class MixinEnchantment {
    @ModifyReturnValue(method = "isCompatibleWith", at = @At("RETURN"))
    private boolean isCompatibleWith(boolean original, Enchantment other) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String enchantment = CommandDataHandler.getKeyFromEnchantmentRegistry((Enchantment) (Object) this);
        String otherEnchantment = "compatible_with_" + CommandDataHandler.lightCleanup(CommandDataHandler.getKeyFromEnchantmentRegistry(other));
        String reversedEnchantment = enchantment.replace("minecraft:", "compatible_with_");
        String reversedOtherEnchantment = otherEnchantment.replace("compatible_with_", "minecraft:");
        return CommandDataHandler.getCachedBoolean("enchantments", enchantment, otherEnchantment) ||
                CommandDataHandler.getCachedBoolean("enchantments", reversedOtherEnchantment, reversedEnchantment);
    }
}
