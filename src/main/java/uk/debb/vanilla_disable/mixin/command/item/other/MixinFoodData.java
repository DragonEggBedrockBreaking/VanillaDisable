package uk.debb.vanilla_disable.mixin.command.item.other;

import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(FoodData.class)
public abstract class MixinFoodData {
    @Shadow
    public abstract void eat(int i, float f);

    @Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private void eat(Item item, ItemStack stack, CallbackInfo ci) {
        if (item.isEdible()) {
            String name = CommandDataHandler.getKeyFromItemRegistry(item);
            int nutrition = CommandDataHandler.getCachedInt("items", name, "nutrition");
            float saturation = (float) CommandDataHandler.getCachedDouble("items", name, "saturation");
            this.eat(nutrition, saturation);
        }
        ci.cancel();
    }
}
