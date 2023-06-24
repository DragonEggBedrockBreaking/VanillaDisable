package uk.debb.vanilla_disable.mixin.command.entity.other;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonStrafePlayerPhase;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

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
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:ender_dragon", "can_shoot_fireballs")) {
            return false;
        }
        return instance.addFreshEntity(entity);
    }
}
