package uk.debb.vanilla_disable.command.mixin.rule.item.potion;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

import java.util.List;
import java.util.Objects;

@Mixin(PotionUtils.class)
public abstract class MixinPotionUtils {
    @ModifyReturnValue(method = "getMobEffects", at = @At("RETURN"))
    private static List<MobEffectInstance> getMobEffects(List<MobEffectInstance> original, ItemStack itemStack) {
        String item = DataHandler.getKeyFromItemRegistry(itemStack.getItem());
        String potion = Objects.requireNonNull(DataHandler.potionRegistry.getKey(PotionUtils.getPotion(itemStack))) + "_effect";
        if (!DataHandler.getCachedBoolean("items", item, potion)) {
            return new ObjectArrayList<>();
        }
        return original;
    }
}
