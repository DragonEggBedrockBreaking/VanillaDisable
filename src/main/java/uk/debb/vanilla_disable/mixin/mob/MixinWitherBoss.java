package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(WitherBoss.class)
public abstract class MixinWitherBoss extends Entity {
    public MixinWitherBoss(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason immediately despawns withers if they are not allowed
     * @param ci the callback info
     */
    @Inject(method = "checkDespawn", at = @At(value = "HEAD"), cancellable = true)
    private void forceDespawn(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.WITHER_SPAWNS)) {
            this.discard();
            ci.cancel();
        }
    }
}
