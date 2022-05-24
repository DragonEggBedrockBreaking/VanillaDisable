package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ProtectionEnchantment.class)
public abstract class MixinProtectionEnchantment extends Enchantment {
    public MixinProtectionEnchantment(Enchantment.Rarity rarity, ProtectionEnchantment.Type type, EquipmentSlot... equipmentSlots) {
        super(rarity, type == ProtectionEnchantment.Type.FALL ? EnchantmentCategory.ARMOR_FEET : EnchantmentCategory.ARMOR, equipmentSlots);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable compatibility check between protection, blast protection, fire protection, and projectile protection
     * @param enchantment the enchantment to check compatibility with
     * @param cir the returnable callback info (boolean)
     */
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void cancelCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS)) {
            cir.setReturnValue(true);
        }
    }
}
