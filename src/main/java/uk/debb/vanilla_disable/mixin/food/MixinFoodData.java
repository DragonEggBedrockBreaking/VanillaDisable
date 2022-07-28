package uk.debb.vanilla_disable.mixin.food;

import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(FoodData.class)
public abstract class MixinFoodData implements Maps {
    @Shadow
    public abstract void eat(int i, float f);

    @Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private void modifyNutrition(Item item, ItemStack itemStack, CallbackInfo ci) {
        if (item.isEdible()) {
            GameRules.Key<GameRules.IntegerValue> nutritionGamerule = foodDataFoodPropertiesMapNutrition.get(item.getFoodProperties());
            GameRules.Key<GameRules.IntegerValue> saturationGamerule = foodDataFoodPropertiesMapSaturation.get(item.getFoodProperties());
            this.eat(GameruleHelper.getInt(nutritionGamerule), (float) GameruleHelper.getInt(saturationGamerule) / 10);
        }
        ci.cancel();
    }
}