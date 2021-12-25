package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.SpawnHelper.Checker;
import net.minecraft.world.SpawnHelper.Runner;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SpawnHelper.class)
public class MixinSpawnHelper {
    /**
     * @author DragonEggBedrockBreaking
     * @param group the type of entity
     * @param world the world
     * @param chunk the chunk
     * @param checker profiling/testing
     * @param runner profiling/testing
     * @param ci the callback info
     */
    @Inject(method = "spawnEntitiesInChunk", at = @At(value = "HEAD"), cancellable = true)
    private static void cancelSpawningEntitiesInChunk(SpawnGroup group, ServerWorld world, WorldChunk chunk, Checker checker, Runner runner, CallbackInfo ci) {
        if (group == SpawnGroup.MONSTER &&
            !world.getGameRules().getBoolean(RegisterGamerules.MONSTER_SPAWNING)) {
            ci.cancel();
        }
        if (group == SpawnGroup.CREATURE &&
            !world.getGameRules().getBoolean(RegisterGamerules.CREATURE_SPAWNING)) {
            ci.cancel();
        }
        if (group == SpawnGroup.AMBIENT &&
            !world.getGameRules().getBoolean(RegisterGamerules.AMBIENT_SPAWNING)) {
            ci.cancel();
        }
        if (group == SpawnGroup.AXOLOTLS &&
            !world.getGameRules().getBoolean(RegisterGamerules.AXOLOTL_SPAWNING)) {
            ci.cancel();
        }
        if (group == SpawnGroup.UNDERGROUND_WATER_CREATURE &&
            !world.getGameRules().getBoolean(RegisterGamerules.GLOWSQUID_SPAWNING)) {
            ci.cancel();
        }
        if (group == SpawnGroup.WATER_CREATURE &&
            !world.getGameRules().getBoolean(RegisterGamerules.WATER_CREATURE_SPAWNING)) {
            ci.cancel();
        }
        if (group == SpawnGroup.WATER_AMBIENT &&
            !world.getGameRules().getBoolean(RegisterGamerules.WATER_AMBIENT_SPAWNING)) {
            ci.cancel();
        }
    }
}
