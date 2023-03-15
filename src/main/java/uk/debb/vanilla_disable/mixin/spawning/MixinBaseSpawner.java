package uk.debb.vanilla_disable.mixin.spawning;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.SpawnData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

import java.util.function.Function;

@Mixin(BaseSpawner.class)
public abstract class MixinBaseSpawner implements Maps {
    @Shadow private SpawnData nextSpawnData;

    @ModifyExpressionValue(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/BaseSpawner;isNearPlayer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean cancelSpawningNewEntityAndPassengers(boolean original, ServerLevel level, BlockPos blockPos) {
        CompoundTag compoundTag = this.nextSpawnData.getEntityToSpawn();
        Entity entity = EntityType.loadEntityRecursive(compoundTag, level, Function.identity());
        if (entity != null) {
            Gamerules gameRule = baseSpawnerClassMap.get(entity.getClass());
            if (!gameRule.getBool() || !Gamerules.SPAWNERS_ENABLED.getBool()) {
                return false;
            }
        }
        return original;
    }
}