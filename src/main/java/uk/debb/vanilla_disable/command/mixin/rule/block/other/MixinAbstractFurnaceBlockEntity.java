package uk.debb.vanilla_disable.command.mixin.rule.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MixinAbstractFurnaceBlockEntity {
    @ModifyReturnValue(method = "isFuel", at = @At("RETURN"))
    private static boolean isFuel(boolean original, ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            String block = DataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
            if (DataHandler.getInt("blocks", block, "fuel_duration") <= 0) {
                return false;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "getBurnDuration", at = @At("RETURN"))
    private int getBurnDuration(int original, ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            String block = DataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
            return DataHandler.getInt("blocks", block, "fuel_duration");
        }
        return original;
    }
}
