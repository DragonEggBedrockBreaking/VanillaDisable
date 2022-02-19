package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Mob.class)
public abstract class MixinMob {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent zombies/skeletons from burning in sunlight
     * @param cir the returnable callback info
     */
    @Inject(method = "isSunBurnTick", at = @At("HEAD"), cancellable = true)
    private void stopBurning(CallbackInfoReturnable<Boolean> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.MOBS_BURN_IN_SUNLIGHT)) {
            cir.setReturnValue(false);
        }
    }
}
