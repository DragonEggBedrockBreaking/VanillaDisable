package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PigEntity.class)
public abstract class MixinPigEntity extends AnimalEntity {
    public MixinPigEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow @Final private static Ingredient BREEDING_INGREDIENT;

    /**
     * @author DragonEggBedrockBreaking
     * @reason change the item which the pig cen breed with
     * @param stack the stack of items that the player is holding
     * @param cir the returnable callback info
     */
    @Inject(method = "isBreedingItem", at = @At("HEAD"), cancellable = true)
    private void changeBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGS_BREED_WITH_WHEAT)) {
            cir.setReturnValue(Ingredient.ofItems(Items.WHEAT).test(stack));
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason change the item that the pig is attracted to
     */
    @Overwrite
    public void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0));
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGS_BREED_WITH_WHEAT)) {
            this.goalSelector.add(4, new TemptGoal(this, 1.2, Ingredient.ofItems(Items.WHEAT), false));
        } else {
            this.goalSelector.add(4, new TemptGoal(this, 1.2, Ingredient.ofItems(Items.CARROT_ON_A_STICK), false));
            this.goalSelector.add(4, new TemptGoal(this, 1.2, MixinPigEntity.BREEDING_INGREDIENT, false));
        }
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }
}
