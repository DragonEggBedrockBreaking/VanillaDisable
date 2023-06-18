package uk.debb.vanilla_disable.command.mixin.rule.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(BreedGoal.class)
public abstract class MixinBreedGoal {
    @Shadow @Final protected Animal animal;

    @ModifyReturnValue(method = "canUse", at = @At(value = "RETURN"))
    private boolean canUse(boolean original) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(this.animal.getType());
        return original && DataHandler.getBoolean("entities", entity, "can_breed");
    }

    @ModifyReturnValue(method = "canContinueToUse", at = @At(value = "RETURN"))
    private boolean canContinueToUse(boolean original) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(this.animal.getType());
        return original && DataHandler.getBoolean("entities", entity, "can_breed");
    }
}
