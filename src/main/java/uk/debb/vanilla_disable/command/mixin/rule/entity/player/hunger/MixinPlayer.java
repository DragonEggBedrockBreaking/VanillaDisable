package uk.debb.vanilla_disable.command.mixin.rule.entity.player.hunger;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @Inject(method = "eat", at = @At("HEAD"))
    private void eat(Level level, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        FoodProperties properties = stack.getItem().getFoodProperties();
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (stack.getItem().isEdible() && properties != null && DataHandler.getBoolean("entities", "minecraft:player", "beta_hunger")) {
            livingEntity.setHealth(livingEntity.getHealth() + properties.getNutrition());
        }
    }

    @ModifyExpressionValue(
            method = "canEat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"
            )
    )
    private boolean needsFood(boolean original) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        return DataHandler.getBoolean("entities", "minecraft:player", "beta_hunger") ?
                livingEntity.getHealth() < livingEntity.getMaxHealth() : original;
    }
}
