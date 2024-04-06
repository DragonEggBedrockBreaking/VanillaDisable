package uk.debb.vanilla_disable.mixin.command.entity.player.hunger;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
    private void vanillaDisable$eat(Level level, ItemStack food, CallbackInfoReturnable<ItemStack> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (food.get(DataComponents.FOOD) != null && CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "beta_hunger")) {
            int nutrition = CommandDataHandler.getCachedInt("items", CommandDataHandler.getKeyFromItemRegistry(food.getItem()), "nutrition");
            livingEntity.setHealth(livingEntity.getHealth() + nutrition);
        }
    }

    @ModifyExpressionValue(
            method = "canEat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"
            )
    )
    private boolean vanillaDisable$needsFood(boolean original) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        return CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "beta_hunger") ?
                livingEntity.getHealth() < livingEntity.getMaxHealth() : original;
    }
}
