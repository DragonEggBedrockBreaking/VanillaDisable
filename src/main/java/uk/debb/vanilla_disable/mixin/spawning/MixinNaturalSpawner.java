package uk.debb.vanilla_disable.mixin.spawning;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.NaturalSpawner.AfterSpawnCallback;
import net.minecraft.world.level.NaturalSpawner.SpawnPredicate;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(NaturalSpawner.class)
public class MixinNaturalSpawner {
    @Unique
    private static final Object2ObjectMap<MobCategory, GameRules.Key<GameRules.BooleanValue>> spawnGroupMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private static void addOptionsToMap() {
        spawnGroupMap.put(MobCategory.MONSTER, Gamerules.MONSTER_SPAWNING);
        spawnGroupMap.put(MobCategory.CREATURE, Gamerules.CREATURE_SPAWNING);
        spawnGroupMap.put(MobCategory.AMBIENT, Gamerules.AMBIENT_SPAWNING);
        spawnGroupMap.put(MobCategory.AXOLOTLS, Gamerules.AXOLOTL_SPAWNING);
        spawnGroupMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, Gamerules.GLOWSQUID_SPAWNING);
        spawnGroupMap.put(MobCategory.WATER_AMBIENT, Gamerules.WATER_AMBIENT_SPAWNING);
        spawnGroupMap.put(MobCategory.WATER_CREATURE, Gamerules.WATER_CREATURE_SPAWNING);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @param group the type of entity
     * @param level the level
     * @param chunk the chunk
     * @param checker profiling/testing
     * @param runner profiling/testing
     * @param ci the callback info
     */
    @Inject(method = "spawnCategoryForChunk", at = @At(value = "HEAD"), cancellable = true)
    private static void cancelSpawningCategoryForChunk(MobCategory group, ServerLevel level, LevelChunk chunk, SpawnPredicate checker, AfterSpawnCallback runner, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (spawnGroupMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = spawnGroupMap.get(group);
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            ci.cancel();
        }
    }
}
