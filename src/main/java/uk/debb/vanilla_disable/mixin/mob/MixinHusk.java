package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.monster.Husk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Husk.class)
public abstract class MixinHusk {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop husks from converting into zombies
     * @param ci the callback info
     */
    @Inject(method = "doUnderWaterConversion", at = @At("HEAD"), cancellable = true)
    private void cancelConversionInWater(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.HUSKS_CONVERT_TO_ZOMBIES)) {
            ci.cancel();
        }
    }
}
