package uk.debb.vanilla_disable.mixin.command.item.potion;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.List;
import java.util.Objects;

@Mixin(PotionUtils.class)
public abstract class MixinPotionUtils {
    @ModifyReturnValue(method = "getMobEffects", at = @At("RETURN"))
    private static List<MobEffectInstance> getMobEffects(List<MobEffectInstance> original, ItemStack itemStack) {
        String item = CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem());
        String potion = Objects.requireNonNull(CommandDataHandler.potionRegistry.getKey(PotionUtils.getPotion(itemStack))) + "_effect";
        if (!CommandDataHandler.getCachedBoolean("items", item, CommandDataHandler.lightCleanup(potion))) {
            return new ObjectArrayList<>();
        }
        return original;
    }
}
