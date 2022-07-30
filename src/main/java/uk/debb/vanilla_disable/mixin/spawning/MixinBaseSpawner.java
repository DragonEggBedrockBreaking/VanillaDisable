package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

import java.util.Objects;

@Mixin(BaseSpawner.class)
public abstract class MixinBaseSpawner implements Maps {
    @Shadow
    private @Nullable Entity displayEntity;

    @Inject(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"
            ),
            cancellable = true
    )
    private void cancelSpawningNewEntityAndPassengers(CallbackInfo ci) {
        BooleanGamerules gameRule = baseSpawnerClassMap.get(Objects.requireNonNull(this.displayEntity).getClass());
        if (!GameruleHelper.getBool(gameRule) || !GameruleHelper.getBool(BooleanGamerules.SPAWNERS_ENABLED)) {
            ci.cancel();
        }
    }
}