package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(DamageEnchantment.class)
public abstract class MixinDamageEnchantment extends Enchantment {
    public MixinDamageEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.WEAPON, equipmentSlots);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable compatibility check between sharpness, smite, and bane of arthropods
     * @param enchantment the enchantment to check compatibility with
     * @param cir the returnable callback info (boolean)
     */
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void cancelCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS)) {
            cir.setReturnValue(true);
        }
    }
}
