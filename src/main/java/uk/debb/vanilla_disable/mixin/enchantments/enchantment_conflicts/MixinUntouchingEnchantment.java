package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.UntouchingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(UntouchingEnchantment.class)
public abstract class MixinUntouchingEnchantment extends Enchantment {
    public MixinUntouchingEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.DIGGER, equipmentSlots);
    }

    /**
     * @param original    the original value
     * @param enchantment the enchantment to check compatibility with
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "checkCompatibility", at = @At("RETURN"))
    private boolean cancelCompatibility(boolean original, Enchantment enchantment) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.MINING_ENCHANTMENT_CONFLICTS)) {
            return super.checkCompatibility(enchantment);
        }
        return original;
    }
}