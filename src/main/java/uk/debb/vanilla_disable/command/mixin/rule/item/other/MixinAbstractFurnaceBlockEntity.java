package uk.debb.vanilla_disable.command.mixin.rule.item.other;

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
        if (!(itemStack.getItem() instanceof BlockItem)) {
            String item = DataHandler.getKeyFromItemRegistry(itemStack.getItem());
            if (DataHandler.getInt("items", item, "fuel_duration") <= 0) {
                return false;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "getBurnDuration", at = @At("RETURN"))
    private int getBurnDuration(int original, ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof BlockItem)) {
            String item = DataHandler.getKeyFromItemRegistry(itemStack.getItem());
            return DataHandler.getInt("items", item, "fuel_duration");
        }
        return original;
    }
}
