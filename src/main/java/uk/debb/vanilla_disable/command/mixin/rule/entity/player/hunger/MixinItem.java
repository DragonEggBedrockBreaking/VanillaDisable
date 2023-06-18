package uk.debb.vanilla_disable.command.mixin.rule.entity.player.hunger;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Item.class)
public abstract class MixinItem {
    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int getUseDuration(int original, ItemStack stack) {
        if (stack.getItem().isEdible() && DataHandler.getBoolean("entities", "minecraft:player", "beta_hunger")) {
            return 1;
        }
        return original;
    }
}
