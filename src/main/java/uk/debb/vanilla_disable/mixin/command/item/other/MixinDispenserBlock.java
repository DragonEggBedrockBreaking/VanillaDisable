package uk.debb.vanilla_disable.mixin.command.item.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock {
    @ModifyReturnValue(method = "getDispenseMethod", at = @At("RETURN"))
    private DispenseItemBehavior getDispenseMethod(DispenseItemBehavior original, ItemStack itemStack) {
        String name = CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem());
        if (!CommandDataHandler.getCachedBoolean("items", name, "dispenser_interaction")) {
            return new DefaultDispenseItemBehavior();
        }
        return original;
    }
}
