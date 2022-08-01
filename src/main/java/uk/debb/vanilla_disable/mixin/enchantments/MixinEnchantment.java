package uk.debb.vanilla_disable.mixin.enchantments;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Enchantment.class)
public abstract class MixinEnchantment implements Maps {
    @ModifyReturnValue(method = "isCompatibleWith", at = @At("RETURN"))
    private boolean modifyEnchantmentCompatibility(boolean original, Enchantment arg) {
        Pair<Enchantment, Enchantment> pair = new ObjectObjectImmutablePair<>((Enchantment) (Object) this, arg);
        Gamerules gameRule = enchantmentPairEnchantmentMap.get(pair);
        if (gameRule != null && !gameRule.getValue(Boolean::parseBoolean)) {
            return true;
        }
        Pair<Enchantment, Enchantment> reversedPair = new ObjectObjectImmutablePair<>(arg, (Enchantment) (Object) this);
        Gamerules reversedGameRule = enchantmentPairEnchantmentMap.get(reversedPair);
        if (reversedGameRule != null && !reversedGameRule.getValue(Boolean::parseBoolean)) {
            return true;
        }
        return original;
    }
}
