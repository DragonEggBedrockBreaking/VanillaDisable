package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(BaseSpawner.class)
public abstract class MixinBaseSpawner implements Maps {
    @Unique
    private Entity spawnedEntity;

    @ModifyArg(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"
            ),
            index = 0
    )
    private Entity recordSpawnedEntity(Entity entity) {
        this.spawnedEntity = entity;
        return entity;
    }

    @Inject(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"
            ),
            cancellable = true
    )
    private void cancelSpawningNewEntityAndPassengers(CallbackInfo ci) {
        GameRules.Key<GameRules.BooleanValue> gameRule = baseSpawnerClassMap.get(this.spawnedEntity.getClass());
        if (!GameruleHelper.getBool(gameRule) || !GameruleHelper.getBool(Gamerules.SPAWNERS_ENABLED)) {
            ci.cancel();
        }
    }
}