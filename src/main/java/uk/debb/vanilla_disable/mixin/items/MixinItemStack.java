package uk.debb.vanilla_disable.mixin.items;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements Maps {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUsage(CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        Gamerules gameRule = itemStackClassMap.get(this.getItem());
        if (gameRule != null && !gameRule.getBool()) {
            cir.setReturnValue(InteractionResultHolder.fail(new ItemStack(this.getItem())));
        }
    }
}