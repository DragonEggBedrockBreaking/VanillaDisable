package uk.debb.vanilla_disable.mixin.command.entity.spawning.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Mob.class)
public abstract class MixinMob {
    @ModifyExpressionValue(
            method = "checkDespawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/MobCategory;getNoDespawnDistance()I"
            )
    )
    private int getNoDespawnDistance(int original) {
        Mob mob = (Mob) (Object) this;
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return CommandDataHandler.getCachedInt("entities", entity, "min_despawn_distance");
    }

    @ModifyExpressionValue(
            method = "checkDespawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/MobCategory;getDespawnDistance()I"
            )
    )
    private int getDespawnDistance(int original) {
        Mob mob = (Mob) (Object) this;
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return CommandDataHandler.getCachedInt("entities", entity, "instant_despawn_distance");
    }
}
