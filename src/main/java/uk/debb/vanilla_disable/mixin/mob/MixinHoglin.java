package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.monster.hoglin.Hoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Hoglin.class)
public abstract class MixinHoglin {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop hoglins from zombifying
     * @param cir the returnable callback info
     */
    @Inject(method = "isImmuneToZombification", at = @At("HEAD"), cancellable = true)
    protected void setImmuneToZombification(CallbackInfoReturnable<Boolean> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.HOGLINS_CONVERT_TO_ZOGLINS)) {
            cir.setReturnValue(true);
        }
    }
}