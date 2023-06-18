package uk.debb.vanilla_disable.command.mixin.rule.item.function;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @ModifyExpressionValue(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"
            )
    )
    private Item getItem(Item original) {
        String item = DataHandler.getKeyFromItemRegistry(original);
        if (!DataHandler.getCachedBoolean("items", item, "works")) {
            return Items.AIR;
        }
        return original;
    }
}
