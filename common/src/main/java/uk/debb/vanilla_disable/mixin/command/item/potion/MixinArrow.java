package uk.debb.vanilla_disable.mixin.command.item.potion;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;

@Mixin(Arrow.class)
public abstract class MixinArrow {
    @Shadow
    public abstract void setEffectsFromItem(ItemStack itemStack);

    @Inject(method = "setEffectsFromItem", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$setEffectsFromItem(ItemStack stack, CallbackInfo ci) {
        if (stack.is(Items.TIPPED_ARROW)) {
            String item = CommandDataHandler.getKeyFromItemRegistry(stack.getItem());
            String potion = Objects.requireNonNull(CommandDataHandler.potionRegistry.getKey(PotionUtils.getPotion(stack))) + "_effect";
            if (!CommandDataHandler.getCachedBoolean("items", item, CommandDataHandler.lightCleanup(potion))) {
                this.setEffectsFromItem(new ItemStack(Items.ARROW));
                ci.cancel();
            }
        }
    }
}
