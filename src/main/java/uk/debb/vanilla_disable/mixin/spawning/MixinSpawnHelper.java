package uk.debb.vanilla_disable.mixin.spawning;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.SpawnHelper.Checker;
import net.minecraft.world.SpawnHelper.Runner;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SpawnHelper.class)
public class MixinSpawnHelper {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all spawn groups to their gamerules
     */
    @Unique
    private static final Map<SpawnGroup, GameRules.Key<GameRules.BooleanRule>> spawnGroupMap = new HashMap<SpawnGroup, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private static void addOptionsToMap() {
        spawnGroupMap.put(SpawnGroup.MONSTER, RegisterGamerules.MONSTER_SPAWNING);
        spawnGroupMap.put(SpawnGroup.CREATURE, RegisterGamerules.CREATURE_SPAWNING);
        spawnGroupMap.put(SpawnGroup.AMBIENT, RegisterGamerules.AMBIENT_SPAWNING);
        spawnGroupMap.put(SpawnGroup.AXOLOTLS, RegisterGamerules.AXOLOTL_SPAWNING);
        spawnGroupMap.put(SpawnGroup.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_SPAWNING);
        spawnGroupMap.put(SpawnGroup.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_SPAWNING);
        spawnGroupMap.put(SpawnGroup.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_SPAWNING);
    }

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
        if (spawnGroupMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanRule> gameRule = spawnGroupMap.get(group);
        if (gameRule != null && !world.getGameRules().getBoolean(gameRule)) {
            ci.cancel();
        }
    }
}
