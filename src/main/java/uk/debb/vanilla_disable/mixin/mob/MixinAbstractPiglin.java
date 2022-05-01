package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(AbstractPiglin.class)
public abstract class MixinAbstractPiglin {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop piglins from zombifying
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isImmuneToZombification", at = @At("HEAD"), cancellable = true)
    protected void setImmuneToZombification(CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PIGLINS_CONVERT_TO_ZIGLINS)) {
            cir.setReturnValue(true);
        }
    }
}
