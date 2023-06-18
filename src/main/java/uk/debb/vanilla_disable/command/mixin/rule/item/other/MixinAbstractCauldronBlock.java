package uk.debb.vanilla_disable.command.mixin.rule.item.other;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(AbstractCauldronBlock.class)
public abstract class MixinAbstractCauldronBlock {
    @ModifyExpressionValue(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack getItemInHand(ItemStack original) {
        String name = DataHandler.getKeyFromItemRegistry(original.getItem());
        if (!DataHandler.getCachedBoolean("items", name, "cauldron_interaction")) {
            return ItemStack.EMPTY;
        }
        return original;
    }
}
