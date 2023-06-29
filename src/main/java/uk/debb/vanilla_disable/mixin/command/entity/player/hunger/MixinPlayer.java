package uk.debb.vanilla_disable.mixin.command.entity.player.hunger;

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
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @Inject(method = "eat", at = @At("HEAD"))
    private void eat(Level level, ItemStack food, CallbackInfoReturnable<ItemStack> cir) {
        FoodProperties properties = food.getItem().getFoodProperties();
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (food.getItem().isEdible() && properties != null && CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "beta_hunger")) {
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
        return CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "beta_hunger") ?
                livingEntity.getHealth() < livingEntity.getMaxHealth() : original;
    }
}
