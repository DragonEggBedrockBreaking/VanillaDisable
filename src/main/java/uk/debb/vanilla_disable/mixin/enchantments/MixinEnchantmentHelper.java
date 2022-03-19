package uk.debb.vanilla_disable.mixin.enchantments;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all enchantments to their gamerules
     */
    @Unique
    private static final Object2ObjectMap<Enchantment, GameRules.Key<GameRules.BooleanValue>> enchantmentMap = new Object2ObjectOpenHashMap<Enchantment, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private static void addOptionsToMap() {
        enchantmentMap.put(Enchantments.AQUA_AFFINITY, RegisterGamerules.AQUA_AFFINITY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BANE_OF_ARTHROPODS, RegisterGamerules.BANE_OF_ARTHROPODS_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BLAST_PROTECTION, RegisterGamerules.BLAST_PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.CHANNELING, RegisterGamerules.CHANNELING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.DEPTH_STRIDER, RegisterGamerules.DEPTH_STRIDER_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BLOCK_EFFICIENCY, RegisterGamerules.EFFICIENCY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FALL_PROTECTION, RegisterGamerules.FEATHER_FALLING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FIRE_ASPECT, RegisterGamerules.FIRE_ASPECT_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FIRE_PROTECTION, RegisterGamerules.FIRE_PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FLAMING_ARROWS, RegisterGamerules.FLAME_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BLOCK_FORTUNE, RegisterGamerules.FORTUNE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FROST_WALKER, RegisterGamerules.FROST_WALKER_ENCHANTMENT);
        enchantmentMap.put(Enchantments.IMPALING, RegisterGamerules.IMPALING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.INFINITY_ARROWS, RegisterGamerules.INFINITY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.KNOCKBACK, RegisterGamerules.KNOCKBACK_ENCHANTMENT);
        enchantmentMap.put(Enchantments.MOB_LOOTING, RegisterGamerules.LOOTING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.LOYALTY, RegisterGamerules.LOYALTY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FISHING_LUCK, RegisterGamerules.LUCK_OF_THE_SEA_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FISHING_SPEED, RegisterGamerules.LURE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.MENDING, RegisterGamerules.MENDING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.MULTISHOT, RegisterGamerules.MULTISHOT_ENCHANTMENT);
        enchantmentMap.put(Enchantments.PIERCING, RegisterGamerules.PIERCING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.POWER_ARROWS, RegisterGamerules.POWER_ENCHANTMENT);
        enchantmentMap.put(Enchantments.PROJECTILE_PROTECTION, RegisterGamerules.PROJECTILE_PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.ALL_DAMAGE_PROTECTION, RegisterGamerules.PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.PUNCH_ARROWS, RegisterGamerules.PUNCH_ENCHANTMENT);
        enchantmentMap.put(Enchantments.QUICK_CHARGE, RegisterGamerules.QUICK_CHARGE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.RESPIRATION, RegisterGamerules.RESPIRATION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.RIPTIDE, RegisterGamerules.RIPTIDE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SHARPNESS, RegisterGamerules.SHARPNESS_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SILK_TOUCH, RegisterGamerules.SILK_TOUCH_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SMITE, RegisterGamerules.SMITE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SOUL_SPEED, RegisterGamerules.SOUL_SPEED_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SWEEPING_EDGE, RegisterGamerules.SWEEPING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.THORNS, RegisterGamerules.THORNS_ENCHANTMENT);
        enchantmentMap.put(Enchantments.UNBREAKING, RegisterGamerules.UNBREAKING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BINDING_CURSE, RegisterGamerules.BINDING_CURSE);
        enchantmentMap.put(Enchantments.VANISHING_CURSE, RegisterGamerules.VANISHING_CURSE);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable vanilla enchantments
     * @param enchantment the enchantment on the item to disable
     * @param stack the stack of items with that enchantment
     * @param cir the returnable callback info
     */
    @Inject(method = "getItemEnchantmentLevel", at = @At("HEAD"), cancellable = true)
    private static void removeEnchantmentLevel(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (enchantmentMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = enchantmentMap.get(enchantment);
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ENCHANTMENTS_ENABLED) ||
            (gameRule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(gameRule))) {
            cir.setReturnValue(0);
        }
    }
}
