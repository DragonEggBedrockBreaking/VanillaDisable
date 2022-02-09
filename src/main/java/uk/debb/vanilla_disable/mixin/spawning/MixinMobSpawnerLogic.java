package uk.debb.vanilla_disable.mixin.spawning;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MobSpawnerLogic.class)
public abstract class MixinMobSpawnerLogic {
    @Unique
    private Entity spawnedEntity;
    /**
     * @author FallenBreath (https://github.com/TISUnion/Carpet-TIS-Addition/blob/master/LICENSE)
     * @reason get entity
     * @param spawnedEntity the entity spawned
     * @return the entity spawned
     */
    @ModifyArg(
        method = "serverTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;spawnNewEntityAndPassengers(Lnet/minecraft/entity/Entity;)Z"
        ),
        index = 0
    )
    private Entity recordSpawnedEntity(Entity entity) {
        this.spawnedEntity = entity;
        return entity;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all damage sources to their gamerules
     */
    @Unique
    private static final Map<Class<?>, GameRules.Key<GameRules.BooleanRule>> spawnerMobMap = new HashMap<Class<?>, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        spawnerMobMap.put(PigEntity.class, RegisterGamerules.PIG_SPAWNERS);
        spawnerMobMap.put(CaveSpiderEntity.class, RegisterGamerules.CAVE_SPIDER_SPAWNERS);
        spawnerMobMap.put(SilverfishEntity.class, RegisterGamerules.SILVERFISH_SPAWNERS);
        spawnerMobMap.put(ZombieEntity.class, RegisterGamerules.ZOMBIE_SPAWNERS);
        spawnerMobMap.put(ChickenEntity.class, RegisterGamerules.ZOMBIE_SPAWNERS);
        spawnerMobMap.put(SkeletonEntity.class, RegisterGamerules.SKELETON_SPAWNERS);
        spawnerMobMap.put(BlazeEntity.class, RegisterGamerules.BLAZE_SPAWNERS);
        spawnerMobMap.put(SpiderEntity.class, RegisterGamerules.SPIDER_SPAWNERS);
        spawnerMobMap.put(MagmaCubeEntity.class, RegisterGamerules.MAGMA_CUBE_SPAWNERS);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @param entity the entity to spawn
     * @param ci the callback info
     * @param world the world
     */
    @Inject(
        method = "serverTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;spawnNewEntityAndPassengers(Lnet/minecraft/entity/Entity;)Z"
        ),
        cancellable = true
    )
    private void cancelSpawningNewEntityAndPassengers(ServerWorld world, BlockPos pos, CallbackInfo ci) {
        if (spawnerMobMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanRule> gameRule = spawnerMobMap.get(this.spawnedEntity.getClass());
        if (!this.spawnedEntity.getWorld().getGameRules().getBoolean(gameRule))  {
            ci.cancel();
        }
    }
}
