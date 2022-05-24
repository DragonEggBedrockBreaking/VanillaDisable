package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(WitherBoss.class)
public abstract class MixinWitherBoss {
    /**
     * @author DragonEggBedrockBreaking
     * @reason immediately despawns withers if they are not allowed
     * @param ci the callback info
     */
    @Inject(method = "checkDespawn", at = @At(value = "HEAD"), cancellable = true)
    private void forceDespawn(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.WITHER_SPAWNS)) {
            ((Entity)(Object)this).discard();
            ci.cancel();
        }
    }
}
