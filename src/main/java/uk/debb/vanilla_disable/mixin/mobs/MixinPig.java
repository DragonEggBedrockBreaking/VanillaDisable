package uk.debb.vanilla_disable.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(Pig.class)
public abstract class MixinPig {
    @ModifyReturnValue(method = "isFood", at = @At("RETURN"))
    private boolean changeFood(boolean original, ItemStack stack) {
        if (BooleanGamerules.PIGS_BREED_WITH_WHEAT.getValue()) {
            return Ingredient.of(Items.WHEAT).test(stack);
        }
        return original;
    }

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
        if (BooleanGamerules.PIGS_BREED_WITH_WHEAT.getValue()) {
            return new TemptGoal((Pig) (Object) this, 1.2D, Ingredient.of(Items.WHEAT), false);
        }
        return goal;
    }

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
        if (BooleanGamerules.PIGS_BREED_WITH_WHEAT.getValue()) {
            return new TemptGoal((Pig) (Object) this, 1.2D, Ingredient.of(Items.WHEAT), false);
        }
        return goal;
    }
}