package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(DamageEnchantment.class)
public abstract class MixinDamageEnchantment extends Enchantment {
    public MixinDamageEnchantment(Enchantment.Rarity rarity, int i, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.WEAPON, equipmentSlots);
    }
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void cancelCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.DAMAGE_ENCHANTMENT_CONFLICTS)) {
            cir.setReturnValue(true);
        }
    }
}
