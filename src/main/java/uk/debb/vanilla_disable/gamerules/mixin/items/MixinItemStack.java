package uk.debb.vanilla_disable.gamerules.mixin.items;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements Maps {
    @ModifyExpressionValue(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;"
            )
    )
    private Item cancelUsage(Item original) {
        Gamerules gameRule = itemStackClassMap.get(original);
        if (gameRule != null && !gameRule.getBool()) {
            return Items.AIR;
        }
        return original;
    }
}