package uk.debb.vanilla_disable.mixin.command.entity.spawning.despawning;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(NaturalSpawner.class)
public abstract class MixinNaturalSpawner {
    @ModifyExpressionValue(
            method = "isValidPositionForMob",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;removeWhenFarAway(D)Z"
            )
    )
    private static boolean removeWhenFarAway(boolean original, ServerLevel level, Mob mob, double distance) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return CommandDataHandler.getCachedBoolean("entities", entity, "can_despawn");
    }
}
