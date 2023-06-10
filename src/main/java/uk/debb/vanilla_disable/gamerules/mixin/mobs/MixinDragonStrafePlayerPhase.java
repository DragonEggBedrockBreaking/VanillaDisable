package uk.debb.vanilla_disable.gamerules.mixin.mobs;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonStrafePlayerPhase;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(DragonStrafePlayerPhase.class)
public abstract class MixinDragonStrafePlayerPhase {
    @Redirect(
            method = "doServerTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean spawnFreshEntity(Level instance, Entity entity) {
        if (!Gamerules.DRAGON_FIREBALLS.getBool()) {
            return false;
        }
        return instance.addFreshEntity(entity);
    }
}