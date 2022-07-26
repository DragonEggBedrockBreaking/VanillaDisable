package uk.debb.vanilla_disable.mixin.mob;

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
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Pig.class)
public abstract class MixinPig {
    /**
     * @param original the original value
     * @param stack    the stack of items that the player is holding
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isFood", at = @At("RETURN"))
    private boolean changeFood(boolean original, ItemStack stack) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.PIGS_BREED_WITH_WHEAT)) {
            return Ingredient.of(Items.WHEAT).test(stack);
        }
        return original;
    }

    /**
     * @param goal the original goal
     * @return the goal to go to wheat instead
     * @author DragonEggBedrockBreaking
     * @reason stop pigs being attracted to carrots on sticks
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
        if (VDServer.getServer() == null) return goal;
        if (GameruleHelper.getBool(Gamerules.PIGS_BREED_WITH_WHEAT)) {
            return new TemptGoal((Pig) (Object) this, 1.2D, Ingredient.of(Items.WHEAT), false);
        }
        return goal;
    }

    /**
     * @param goal the original goal
     * @return the goal to go to wheat instead
     * @author DragonEggBedrockBreaking
     * @reason stop pigs being attracted to carrots, potatoes, and beetroot
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
        if (VDServer.getServer() == null) return goal;
        if (GameruleHelper.getBool(Gamerules.PIGS_BREED_WITH_WHEAT)) {
            return new TemptGoal((Pig) (Object) this, 1.2D, Ingredient.of(Items.WHEAT), false);
        }
        return goal;
    }
}