package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.phase.StrafePlayerPhase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(StrafePlayerPhase.class)
public abstract class MixinStrafePlayerPhase {
    /**
     * @author DragonEggBedrockBreaking
     * @param world the world the dragon is in
     * @param entity the dragon fireball
     * @return whether or not to spawn the fireball
     */
    @Redirect(
        method = "serverTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
        )
    )
    public boolean spawnEntity(World world, Entity entity) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.DRAGON_FIREBALLS)) {
            return false;
        }
        return world.spawnEntity(entity);
    }
}
