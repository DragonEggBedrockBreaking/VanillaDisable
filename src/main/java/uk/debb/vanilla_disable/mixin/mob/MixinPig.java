package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Pig.class)
public abstract class MixinPig {
    /**
     * @author DragonEggBedrockBreaking
     * @reason change the item which the pig cen breed with
     * @param stack the stack of items that the player is holding
     * @param cir the returnable callback info
     */
    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private void changeFood(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGS_BREED_WITH_WHEAT)) {
            cir.setReturnValue(Ingredient.of(Items.WHEAT).test(stack));
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop pigs being attracted to carrots on sticks
     * @param goal the original goal
     * @return the goal to go to wheat instead
     */
    @ModifyArg(
        method = "registerGoals",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
            ordinal = 3
        ),
        index = 1
    )
    private Goal modifyCarrotOnStickGoal(Goal goal) {
        if (RegisterGamerules.getServer() == null) return goal;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGS_BREED_WITH_WHEAT)) {
            return new TemptGoal((Pig)(Object)this, 1.2D, Ingredient.of(Items.WHEAT), false);
        }
        return goal;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop pigs being attracted to carrots, potatoes, and beetroot
     * @param goal the original goal
     * @return the goal to go to wheat instead
     */
    @ModifyArg(
        method = "registerGoals",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
            ordinal = 4
        ),
        index = 1
    )
    private Goal modifyBreedItemsGoal(Goal goal) {
        if (RegisterGamerules.getServer() == null) return goal;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGS_BREED_WITH_WHEAT)) {
            return new TemptGoal((Pig)(Object)this, 1.2D, Ingredient.of(Items.WHEAT), false);
        }
        return goal;
    }
}
