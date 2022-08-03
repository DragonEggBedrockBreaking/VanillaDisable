package uk.debb.vanilla_disable.mixin.food.hunger;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

import java.util.Objects;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @Inject(method = "eat", at = @At("HEAD"))
    private void changeEating(Level level, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (Gamerules.OLD_HUNGER.getBool() && stack.getItem().isEdible()) {
            ((LivingEntity) (Object) this).setHealth(((LivingEntity) (Object) this).getHealth() +
                    Objects.requireNonNull(stack.getItem().getFoodProperties()).getNutrition());
        }
    }

    @ModifyExpressionValue(
            method = "canEat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"
            )
    )
    private boolean alwaysNeedsFood(boolean original) {
        if (Gamerules.OLD_HUNGER.getBool()) {
            return ((LivingEntity) (Object) this).getHealth() < ((LivingEntity) (Object) this).getMaxHealth();
        }
        return original;
    }
}