package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.despawning;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(NaturalSpawner.class)
public abstract class MixinNaturalSpawner {
    @ModifyExpressionValue(
            method = "isValidPositionForMob",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;removeWhenFarAway(D)Z"
            )
    )
    private static boolean removeWhenFarAway(boolean original, ServerLevel serverLevel, Mob mob, double d) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return CommandDataHandler.getCachedBoolean("entities", entity, "can_despawn");
    }
}
