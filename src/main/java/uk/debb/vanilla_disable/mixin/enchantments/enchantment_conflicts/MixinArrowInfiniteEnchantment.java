package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ArrowInfiniteEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ArrowInfiniteEnchantment.class)
public abstract class MixinArrowInfiniteEnchantment extends Enchantment {
    public MixinArrowInfiniteEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.BOW, equipmentSlots);
    }

    /**
     * @param original    the original value
     * @param enchantment the enchantment to check compatibility with
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "checkCompatibility", at = @At("RETURN"))
    private boolean cancelCompatibility(boolean original, Enchantment enchantment) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.BOW_ENCHANTMENT_CONFLICTS)) {
            return super.checkCompatibility(enchantment);
        }
        return original;
    }
}