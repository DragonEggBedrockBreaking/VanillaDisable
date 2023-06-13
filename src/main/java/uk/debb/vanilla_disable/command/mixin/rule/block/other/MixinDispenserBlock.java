package uk.debb.vanilla_disable.command.mixin.rule.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock {
    @ModifyReturnValue(method = "getDispenseMethod", at = @At("RETURN"))
    private DispenseItemBehavior getDispenseMethod(DispenseItemBehavior original, ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            String name = DataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
            if (!DataHandler.getBoolean("blocks", name, "dispenser_interaction")) {
                return new DefaultDispenseItemBehavior();
            }
        }
        return original;
    }
}
