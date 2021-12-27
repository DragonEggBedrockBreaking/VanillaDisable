package uk.debb.vanilla_disable.mixin.spawn_limits;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.stream.Stream;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.SpawnHelper.Info;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;


@Mixin(SpawnHelper.class)
public abstract class MixinSpawnHelper implements IMixinSpawnHelper$Info {
    @Shadow static final int CHUNK_AREA = (int)Math.pow(17.0, 2.0);
    @Shadow public static final SpawnGroup[] SPAWNABLE_GROUPS = (SpawnGroup[])Stream.of(SpawnGroup.values()).filter(spawnGroup -> spawnGroup != SpawnGroup.MISC).toArray(SpawnGroup[]::new);

    /**
     * @author DragonEggBedrockBreaking
     * @reason change mobcap of different groups of mobs
     * @param info the spawn helper information
     * @param group the mob group
     * @param pos the position to spawn at
     * @param world the world
     * @return whether the number of mobs is below the mobcap
     */
    @Redirect(
        method = "spawn",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/SpawnHelper$Info;isBelowCap(Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/util/math/ChunkPos;)Z"
        )
    )
    private static boolean isUnderCap(Info info, SpawnGroup group, ChunkPos pos, ServerWorld world) {
        GameRules gamerules = world.getGameRules();
        if (group == SpawnGroup.MONSTER &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.MONSTER_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        if (group == SpawnGroup.CREATURE &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.CREATURE_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        if (group == SpawnGroup.AMBIENT &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.AMBIENT_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        if (group == SpawnGroup.AXOLOTLS &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.AXOLOTL_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        if (group == SpawnGroup.UNDERGROUND_WATER_CREATURE &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.GLOWSQUID_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        if (group == SpawnGroup.WATER_CREATURE &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.WATER_CREATURE_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        if (group == SpawnGroup.WATER_AMBIENT &&
            info.getGroupToCount().getInt(group) >= 
            gamerules.getInt(RegisterGamerules.WATER_AMBIENT_MOBCAP) * info.getSpawningChunkCount() / CHUNK_AREA) {
            return false;
        }
        final Object2IntMap<SpawnGroup> spawnGroupsToDensity = new Object2IntOpenHashMap<SpawnGroup>(SpawnGroup.values().length);
        if (group == SpawnGroup.MONSTER) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.MONSTER_MOBCAP);
        }
        if (group == SpawnGroup.CREATURE) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.CREATURE_MOBCAP);
        }
        if (group == SpawnGroup.AMBIENT) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.AMBIENT_MOBCAP);
        }
        if (group == SpawnGroup.AXOLOTLS) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.AXOLOTL_MOBCAP);
        }
        if (group == SpawnGroup.UNDERGROUND_WATER_CREATURE) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.GLOWSQUID_MOBCAP);
        }
        if (group == SpawnGroup.WATER_CREATURE) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.WATER_CREATURE_MOBCAP);
        }
        if (group == SpawnGroup.WATER_AMBIENT) {
            return spawnGroupsToDensity.getOrDefault((Object)group, 0) < gamerules.getInt(RegisterGamerules.WATER_AMBIENT_MOBCAP);
        }
        return false;
    }
}
