package uk.debb.vanilla_disable.gamerules.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(AbstractCauldronBlock.class)
public abstract class MixinAbstractCauldronBlock implements Maps {
    @ModifyExpressionValue(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack modifyItemStack(ItemStack original) {
        Gamerules gameRule = abstractCauldronBlockItemMap.get(original.getItem());
        if (gameRule != null && !gameRule.getBool()) {
            return ItemStack.EMPTY;
        }
        return original;
    }
}
