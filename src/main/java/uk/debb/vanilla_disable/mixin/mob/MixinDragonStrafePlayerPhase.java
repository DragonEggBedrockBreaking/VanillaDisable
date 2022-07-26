package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.AbstractDragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonStrafePlayerPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(DragonStrafePlayerPhase.class)
public abstract class MixinDragonStrafePlayerPhase extends AbstractDragonPhaseInstance {
    public MixinDragonStrafePlayerPhase(EnderDragon arg) {
        super(arg);
    }

    /**
     * @param original the original value
     * @return whether to spawn the fireball
     * @author DragonEggBedrockBreaking
     */
    @ModifyExpressionValue(
            method = "doServerTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean spawnFreshEntity(boolean original) {
        if (VDServer.getServer() == null) {
            return false;
        }
        if (!GameruleHelper.getBool(Gamerules.DRAGON_FIREBALLS)) {
            return false;
        }
        return original;
    }
}