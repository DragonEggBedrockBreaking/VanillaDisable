package uk.debb.vanilla_disable.mixin.enchantments.enchantment_conflicts;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ArrowInfiniteEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ArrowInfiniteEnchantment.class)
public abstract class MixinArrowInfiniteEnchantment extends Enchantment {
    public MixinArrowInfiniteEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.BOW, equipmentSlots);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable compatibility check between infinity and mending
     * @param enchantment the enchantment to check compatibility with
     * @param cir the returnable callback info (boolean)
     */
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void cancelCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!VDServer.getServer().getGameRules().getBoolean(Gamerules.BOW_ENCHANTMENT_CONFLICTS)) {
            cir.setReturnValue(super.checkCompatibility(enchantment));
        }
    }
}
