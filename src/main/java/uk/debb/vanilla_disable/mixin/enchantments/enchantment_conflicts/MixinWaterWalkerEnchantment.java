package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.WaterWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(WaterWalkerEnchantment.class)
public abstract class MixinWaterWalkerEnchantment extends Enchantment {
    public MixinWaterWalkerEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.ARMOR_FEET, equipmentSlots);
    }

    /**
     * @param original    the original value
     * @param enchantment the enchantment to check compatibility with
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "checkCompatibility", at = @At("RETURN"))
    private boolean cancelCompatibility(boolean original, Enchantment enchantment) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.BOOT_ENCHANTMENT_CONFLICTS)) {
            return super.checkCompatibility(enchantment);
        }
        return original;
    }
}