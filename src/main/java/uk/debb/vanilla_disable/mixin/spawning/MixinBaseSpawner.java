package uk.debb.vanilla_disable.mixin.spawning;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(BaseSpawner.class)
public abstract class MixinBaseSpawner {
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
            target = "Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"
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
    private static final Map<Class<?>, GameRules.Key<GameRules.BooleanValue>> spawnerMobMap = new HashMap<Class<?>, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        spawnerMobMap.put(Pig.class, RegisterGamerules.PIG_SPAWNERS);
        spawnerMobMap.put(CaveSpider.class, RegisterGamerules.CAVE_SPIDER_SPAWNERS);
        spawnerMobMap.put(Silverfish.class, RegisterGamerules.SILVERFISH_SPAWNERS);
        spawnerMobMap.put(Zombie.class, RegisterGamerules.ZOMBIE_SPAWNERS);
        spawnerMobMap.put(Chicken.class, RegisterGamerules.ZOMBIE_SPAWNERS);
        spawnerMobMap.put(Skeleton.class, RegisterGamerules.SKELETON_SPAWNERS);
        spawnerMobMap.put(Blaze.class, RegisterGamerules.BLAZE_SPAWNERS);
        spawnerMobMap.put(Spider.class, RegisterGamerules.SPIDER_SPAWNERS);
        spawnerMobMap.put(MagmaCube.class, RegisterGamerules.MAGMA_CUBE_SPAWNERS);
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
            target = "Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"
        ),
        cancellable = true
    )
    private void cancelSpawningNewEntityAndPassengers(ServerLevel world, BlockPos pos, CallbackInfo ci) {
        if (spawnerMobMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = spawnerMobMap.get(this.spawnedEntity.getClass());
        if (!this.spawnedEntity.getLevel().getGameRules().getBoolean(gameRule))  {
            ci.cancel();
        }
    }
}
