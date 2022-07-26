package uk.debb.vanilla_disable.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @Unique
    private static final Object2ObjectMap<Enchantment, GameRules.Key<GameRules.BooleanValue>> enchantmentMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private static void addOptionsToMap() {
        enchantmentMap.put(Enchantments.AQUA_AFFINITY, Gamerules.AQUA_AFFINITY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BANE_OF_ARTHROPODS, Gamerules.BANE_OF_ARTHROPODS_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BLAST_PROTECTION, Gamerules.BLAST_PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.CHANNELING, Gamerules.CHANNELING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.DEPTH_STRIDER, Gamerules.DEPTH_STRIDER_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BLOCK_EFFICIENCY, Gamerules.EFFICIENCY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FALL_PROTECTION, Gamerules.FEATHER_FALLING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FIRE_ASPECT, Gamerules.FIRE_ASPECT_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FIRE_PROTECTION, Gamerules.FIRE_PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FLAMING_ARROWS, Gamerules.FLAME_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BLOCK_FORTUNE, Gamerules.FORTUNE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FROST_WALKER, Gamerules.FROST_WALKER_ENCHANTMENT);
        enchantmentMap.put(Enchantments.IMPALING, Gamerules.IMPALING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.INFINITY_ARROWS, Gamerules.INFINITY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.KNOCKBACK, Gamerules.KNOCKBACK_ENCHANTMENT);
        enchantmentMap.put(Enchantments.MOB_LOOTING, Gamerules.LOOTING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.LOYALTY, Gamerules.LOYALTY_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FISHING_LUCK, Gamerules.LUCK_OF_THE_SEA_ENCHANTMENT);
        enchantmentMap.put(Enchantments.FISHING_SPEED, Gamerules.LURE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.MENDING, Gamerules.MENDING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.MULTISHOT, Gamerules.MULTISHOT_ENCHANTMENT);
        enchantmentMap.put(Enchantments.PIERCING, Gamerules.PIERCING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.POWER_ARROWS, Gamerules.POWER_ENCHANTMENT);
        enchantmentMap.put(Enchantments.PROJECTILE_PROTECTION, Gamerules.PROJECTILE_PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.ALL_DAMAGE_PROTECTION, Gamerules.PROTECTION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.PUNCH_ARROWS, Gamerules.PUNCH_ENCHANTMENT);
        enchantmentMap.put(Enchantments.QUICK_CHARGE, Gamerules.QUICK_CHARGE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.RESPIRATION, Gamerules.RESPIRATION_ENCHANTMENT);
        enchantmentMap.put(Enchantments.RIPTIDE, Gamerules.RIPTIDE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SHARPNESS, Gamerules.SHARPNESS_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SILK_TOUCH, Gamerules.SILK_TOUCH_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SMITE, Gamerules.SMITE_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SOUL_SPEED, Gamerules.SOUL_SPEED_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SWEEPING_EDGE, Gamerules.SWEEPING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.SWIFT_SNEAK, Gamerules.SWIFT_SNEAK_ENCHANTMENT);
        enchantmentMap.put(Enchantments.THORNS, Gamerules.THORNS_ENCHANTMENT);
        enchantmentMap.put(Enchantments.UNBREAKING, Gamerules.UNBREAKING_ENCHANTMENT);
        enchantmentMap.put(Enchantments.BINDING_CURSE, Gamerules.BINDING_CURSE);
        enchantmentMap.put(Enchantments.VANISHING_CURSE, Gamerules.VANISHING_CURSE);
    }

    /**
     * @param original    the original value
     * @param enchantment the enchantment on the item to disable
     * @param stack       the stack of items with that enchantment
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getItemEnchantmentLevel", at = @At("RETURN"))
    private static int removeEnchantmentLevel(int original, Enchantment enchantment, ItemStack stack) {
        if (VDServer.getServer() == null) return original;
        if (enchantmentMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = enchantmentMap.get(enchantment);
        if (!GameruleHelper.getBool(Gamerules.ENCHANTMENTS_ENABLED) ||
                (gameRule != null && !GameruleHelper.getBool(gameRule))) {
            return 0;
        }
        return original;
    }
}