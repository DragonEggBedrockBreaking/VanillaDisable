package uk.debb.vanilla_disable.mixin.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ProtectionEnchantment.class)
public abstract class MixinProtectionEnchantment extends Enchantment {
    public MixinProtectionEnchantment(Enchantment.Rarity rarity, ProtectionEnchantment.Type type, EquipmentSlot... equipmentSlots) {
        super(rarity, type == ProtectionEnchantment.Type.FALL ? EnchantmentCategory.ARMOR_FEET : EnchantmentCategory.ARMOR, equipmentSlots);
    }
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void cancelCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PROTECTION_ENCHANTMENT_CONFLICTS)) {
            cir.setReturnValue(true);
        }
    }
}
