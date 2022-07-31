package uk.debb.vanilla_disable.mixin.food.hunger;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(Item.class)
public abstract class MixinItem {
    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int editUseDuration(int original, ItemStack stack) {
        if (BooleanGamerules.OLD_HUNGER.getValue() && stack.getItem().isEdible()) {
            return 1;
        }
        return original;
    }
}