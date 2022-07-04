package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Hoglin.class)
public abstract class MixinHoglin {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason stop hoglins from zombifying
     */
    @ModifyReturnValue(method = "isImmuneToZombification", at = @At("RETURN"))
    private boolean setImmuneToZombification(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.HOGLINS_CONVERT_TO_ZOGLINS)) {
            return true;
        }
        return original;
    }
}