package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(NaturalSpawner.class)
public abstract class MixinNaturalSpawner {
    @ModifyExpressionValue(
            method = "isValidPositionForMob",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/MobCategory;getDespawnDistance()I"
            )
    )
    private static int isValidPositionForMob(int original, ServerLevel serverLevel, Mob mob, double d) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return DataHandler.getInt("entities", entity, "instant_despawn_distance");
    }
}
