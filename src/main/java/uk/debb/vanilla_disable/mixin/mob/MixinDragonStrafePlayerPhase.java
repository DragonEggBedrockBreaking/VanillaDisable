package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonStrafePlayerPhase;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(DragonStrafePlayerPhase.class)
public abstract class MixinDragonStrafePlayerPhase {
    /**
     * @author DragonEggBedrockBreaking
     * @param world the world the dragon is in
     * @param entity the dragon fireball
     * @return whether or not to spawn the fireball
     */
    @Redirect(
        method = "doServerTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
        )
    )
    public boolean spawnFreshEntity(Level world, Entity entity) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.DRAGON_FIREBALLS)) {
            return false;
        }
        return world.addFreshEntity(entity);
    }
}
