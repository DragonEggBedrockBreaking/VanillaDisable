package uk.debb.vanilla_disable.mixin.spawning;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(BaseSpawner.class)
public abstract class MixinBaseSpawner {
    @Unique
    private Entity spawnedEntity;
    /**
     * @author FallenBreath (https://github.com/TISUnion/Carpet-TIS-Addition/blob/master/LICENSE)
     * @reason get entity
     * @param entity the entity spawned
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
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> spawnerMobMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        spawnerMobMap.put(Pig.class, Gamerules.PIG_SPAWNERS);
        spawnerMobMap.put(CaveSpider.class, Gamerules.CAVE_SPIDER_SPAWNERS);
        spawnerMobMap.put(Silverfish.class, Gamerules.SILVERFISH_SPAWNERS);
        spawnerMobMap.put(Zombie.class, Gamerules.ZOMBIE_SPAWNERS);
        spawnerMobMap.put(Chicken.class, Gamerules.ZOMBIE_SPAWNERS);
        spawnerMobMap.put(Skeleton.class, Gamerules.SKELETON_SPAWNERS);
        spawnerMobMap.put(Blaze.class, Gamerules.BLAZE_SPAWNERS);
        spawnerMobMap.put(Spider.class, Gamerules.SPIDER_SPAWNERS);
        spawnerMobMap.put(MagmaCube.class, Gamerules.MAGMA_CUBE_SPAWNERS);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @param level the level
     * @param pos the position
     * @param ci the callback info
     */
    @Inject(
        method = "serverTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"
        ),
        cancellable = true
    )
    private void cancelSpawningNewEntityAndPassengers(ServerLevel level, BlockPos pos, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (spawnerMobMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = spawnerMobMap.get(this.spawnedEntity.getClass());
        if (!GameruleHelper.getBool(gameRule))  {
            ci.cancel();
        }
    }
}
