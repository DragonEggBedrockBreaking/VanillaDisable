package uk.debb.vanilla_disable.mixin.food.hunger;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Item.class)
public abstract class MixinItem {
    /**
     * @param original the original value
     * @param stack    the stack of items that the player is holding
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int editUseDuration(int original, ItemStack stack) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER) && stack.getItem().isEdible()) {
            return 1;
        }
        return original;
    }
}