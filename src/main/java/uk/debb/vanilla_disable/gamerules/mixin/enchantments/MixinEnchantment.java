package uk.debb.vanilla_disable.gamerules.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.gamerules.Gamerules;

@Mixin(Enchantment.class)
public abstract class MixinEnchantment {
    private static final Object2ObjectMap<Pair<Enchantment, Enchantment>, Gamerules> enchantmentPairEnchantmentMap = new Object2ObjectOpenHashMap<>() {{
        put(new ObjectObjectImmutablePair<>(Enchantments.FROST_WALKER, Enchantments.DEPTH_STRIDER), Gamerules.BOOT_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.INFINITY_ARROWS, Enchantments.MENDING), Gamerules.BOW_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.MULTISHOT, Enchantments.PIERCING), Gamerules.CROSSBOW_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.SHARPNESS, Enchantments.SMITE), Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.SHARPNESS, Enchantments.BANE_OF_ARTHROPODS), Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS), Gamerules.DAMAGE_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.BLOCK_FORTUNE, Enchantments.SILK_TOUCH), Gamerules.MINING_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.BLAST_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.PROJECTILE_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.FALL_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.FIRE_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.BLAST_PROTECTION, Enchantments.PROJECTILE_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.BLAST_PROTECTION, Enchantments.FALL_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.BLAST_PROTECTION, Enchantments.FIRE_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.PROJECTILE_PROTECTION, Enchantments.FALL_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.PROJECTILE_PROTECTION, Enchantments.FIRE_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.FALL_PROTECTION, Enchantments.FIRE_PROTECTION), Gamerules.PROTECTION_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.RIPTIDE, Enchantments.LOYALTY), Gamerules.TRIDENT_ENCHANTMENT_CONFLICTS);
        put(new ObjectObjectImmutablePair<>(Enchantments.RIPTIDE, Enchantments.CHANNELING), Gamerules.TRIDENT_ENCHANTMENT_CONFLICTS);
    }};

    @ModifyReturnValue(method = "isCompatibleWith", at = @At("RETURN"))
    private boolean modifyEnchantmentCompatibility(boolean original, Enchantment arg) {
        Pair<Enchantment, Enchantment> pair = new ObjectObjectImmutablePair<>((Enchantment) (Object) this, arg);
        Gamerules gameRule = enchantmentPairEnchantmentMap.get(pair);
        if (gameRule != null && !gameRule.getBool()) {
            return true;
        }
        Pair<Enchantment, Enchantment> reversedPair = new ObjectObjectImmutablePair<>(arg, (Enchantment) (Object) this);
        Gamerules reversedGameRule = enchantmentPairEnchantmentMap.get(reversedPair);
        if (reversedGameRule != null && !reversedGameRule.getBool()) {
            return true;
        }
        return original;
    }
}
