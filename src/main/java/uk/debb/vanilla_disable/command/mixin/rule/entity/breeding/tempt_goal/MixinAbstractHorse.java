package uk.debb.vanilla_disable.command.mixin.rule.entity.breeding.tempt_goal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(AbstractHorse.class)
public abstract class MixinAbstractHorse {
    @ModifyArg(
            method = "addBehaviourGoals",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/TemptGoal;<init>(Lnet/minecraft/world/entity/PathfinderMob;DLnet/minecraft/world/item/crafting/Ingredient;Z)V"
            ),
            index = 2
    )
    private Ingredient getIngredient(Ingredient original) {
        if (DataHandler.isConnectionNull()) return original;
        String entity = DataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return DataHandler.getBreedingItems(entity);
    }
}
