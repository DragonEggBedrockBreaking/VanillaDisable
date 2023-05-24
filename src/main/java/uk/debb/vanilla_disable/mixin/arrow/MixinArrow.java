package uk.debb.vanilla_disable.mixin.arrow;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Arrow.class)
public abstract class MixinArrow implements Maps { 
    @Shadow
    public abstract void setEffectsFromItem(ItemStack arg);

    @Inject(method = "setEffectsFromItem", at = @At("HEAD"), cancellable = true)
    private void clearEffectsFromItem(ItemStack itemStack, CallbackInfo ci) {
        if (itemStack.is(Items.TIPPED_ARROW)) {
            Gamerules gameRule = arrowPotionMap.get(PotionUtils.getPotion(itemStack));
            if (!Gamerules.TIPPED_ARROWS_ENABLED.getBool() || (gameRule != null && !gameRule.getBool())) {
                this.setEffectsFromItem(new ItemStack(Items.ARROW));
                ci.cancel();
            }
        }
    }
}
