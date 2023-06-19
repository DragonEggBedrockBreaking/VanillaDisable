package uk.debb.vanilla_disable.command.mixin.rule.item.potion;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

import java.util.Objects;

@Mixin(Arrow.class)
public abstract class MixinArrow {
    @Shadow public abstract void setEffectsFromItem(ItemStack itemStack);

    @Inject(method = "setEffectsFromItem", at = @At("HEAD"), cancellable = true)
    private void setEffectsFromItem(ItemStack itemStack, CallbackInfo ci) {
        if (itemStack.is(Items.TIPPED_ARROW)) {
            String item = CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem());
            String potion = Objects.requireNonNull(CommandDataHandler.potionRegistry.getKey(PotionUtils.getPotion(itemStack))) + "_effect";
            if (!CommandDataHandler.getCachedBoolean("items", item, potion)) {
                this.setEffectsFromItem(new ItemStack(Items.ARROW));
                ci.cancel();
            }
        }
    }
}
