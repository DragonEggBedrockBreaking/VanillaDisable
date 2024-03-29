package uk.debb.vanilla_disable.mixin.command.entity.spawning.spawn_limits;

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
                    target = "Lnet/minecraft/world/entity/MobCategory;getDespawnDistance()I"
            )
    )
    private static int vanillaDisable$getDespawnDistance(int original, ServerLevel serverLevel, Mob mob, double d) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return CommandDataHandler.getCachedInt("entities", entity, "instant_despawn_distance");
    }
}
