package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

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
        String entity = DataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return DataHandler.getCachedInt("entities", entity, "min_despawn_distance");
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
        String entity = DataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return DataHandler.getCachedInt("entities", entity, "instant_despawn_distance");
    }
}
