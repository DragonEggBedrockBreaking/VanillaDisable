package uk.debb.vanilla_disable.mixin.misc.hunger;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Player.class)
public abstract class MixinPlayer {
    /**
     * @author DragonEggBedrockBreaking
     * @reason increases your health when you eat food
     */
    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    private void changeEating(Level level, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER) && stack.getItem().isEdible()) {
            ((LivingEntity)(Object)this).setHealth(((LivingEntity)(Object)this).getHealth() + stack.getItem().getFoodProperties().getNutrition());
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason allows you to eat food with a full hunger bar
     * @param hungerManager the hunger manager
     * @param ignoreHunger whether or not to ignore hunger
     * @return whether one can eat food
     */
    @Redirect(
        method = "canEat",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"
        )
    )
    public boolean alwaysNeedsFood(FoodData hungerManager, boolean ignoreHunger) {
        if (VDServer.getServer() == null) {
            return hungerManager.needsFood();
        }
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER)) {
            return ((LivingEntity)(Object)this).getHealth() < ((LivingEntity)(Object)this).getMaxHealth();
        }
        return hungerManager.needsFood();
    }
}
