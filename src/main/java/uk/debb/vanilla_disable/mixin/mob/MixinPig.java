package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Pig.class)
public abstract class MixinPig extends Animal {
    public MixinPig(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow @Final private static Ingredient FOOD_ITEMS;

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
     * @reason change the item that the pig is attracted to
     */
    @Overwrite
    public void registerGoals() {
        if (RegisterGamerules.getServer() == null) return;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGS_BREED_WITH_WHEAT)) {
            this.goalSelector.addGoal(4, new TemptGoal(this, 1.2, Ingredient.of(Items.WHEAT), false));
        } else {
            this.goalSelector.addGoal(4, new TemptGoal(this, 1.2, Ingredient.of(Items.CARROT_ON_A_STICK), false));
            this.goalSelector.addGoal(4, new TemptGoal(this, 1.2, MixinPig.FOOD_ITEMS, false));
        }
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }
}
