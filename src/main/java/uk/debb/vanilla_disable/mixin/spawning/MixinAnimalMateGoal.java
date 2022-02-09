package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.entity.ai.goal.AnimalMateGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(AnimalMateGoal.class)
public abstract class MixinAnimalMateGoal {
    /**
     * @author DragonEggBedrockBreaking
     * @reason Don't allow breeding to start
     * @param cir Returnable callback info
     */
    @Inject(method = "canStart", at = @At(value = "HEAD"), cancellable = true)
    private void cannotStart(CallbackInfoReturnable<Boolean> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ANIMAL_BREEDING)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Don't allow breeding to continue
     * @param cir Returnable callback info
     */
    @Inject(method = "shouldContinue", at = @At(value = "HEAD"), cancellable = true)
    private void shouldNotContinue(CallbackInfoReturnable<Boolean> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ANIMAL_BREEDING)) {
            cir.setReturnValue(false);
        }
    }
}
