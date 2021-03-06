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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

import java.util.Objects;

@Mixin(Player.class)
public abstract class MixinPlayer {
    /**
     * @author DragonEggBedrockBreaking
     * @reason increases your health when you eat food
     */
    @Inject(method = "eat", at = @At("HEAD"))
    private void changeEating(Level level, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER) && stack.getItem().isEdible()) {
            ((LivingEntity) (Object) this).setHealth(((LivingEntity) (Object) this).getHealth() +
                    Objects.requireNonNull(stack.getItem().getFoodProperties()).getNutrition());
        }
    }

    /**
     * @param original the original value
     * @return whether one can eat food
     * @author DragonEggBedrockBreaking
     */
    @ModifyExpressionValue(
            method = "canEat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"
            )
    )
    private boolean alwaysNeedsFood(boolean original) {
        if (VDServer.getServer() == null) {
            return original;
        }
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER)) {
            return ((LivingEntity) (Object) this).getHealth() < ((LivingEntity) (Object) this).getMaxHealth();
        }
        return original;
    }
}