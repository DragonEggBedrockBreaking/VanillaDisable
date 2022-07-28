package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(NaturalSpawner.class)
public class MixinNaturalSpawner implements Maps {
    @Inject(method = "spawnCategoryForChunk", at = @At(value = "HEAD"), cancellable = true)
    private static void cancelSpawningCategoryForChunk(MobCategory mobCategory, ServerLevel serverLevel, LevelChunk levelChunk, NaturalSpawner.SpawnPredicate spawnPredicate, NaturalSpawner.AfterSpawnCallback afterSpawnCallback, CallbackInfo ci) {
        BooleanGamerules gameRule = naturalSpawnerMobCategoryMap.get(mobCategory);
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            ci.cancel();
        }
    }
}