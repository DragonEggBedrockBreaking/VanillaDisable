package uk.debb.vanilla_disable.mixin.spawning;

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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MobSpawnerLogic.class)
public abstract class MixinMobSpawnerLogic {
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
        GameRules grs = this.spawnedEntity.getWorld().getGameRules();
        if (this.spawnedEntity instanceof PigEntity && !grs.getBoolean(RegisterGamerules.PIG_SPAWNERS)) {
            ci.cancel();
        }
        if (this.spawnedEntity instanceof CaveSpiderEntity && !grs.getBoolean(RegisterGamerules.CAVE_SPIDER_SPAWNERS)) {
            ci.cancel();
        }
        if (this.spawnedEntity instanceof SilverfishEntity && !grs.getBoolean(RegisterGamerules.SILVERFISH_SPAWNERS)) {
            ci.cancel();
        }
        if ((this.spawnedEntity instanceof ZombieEntity || this.spawnedEntity instanceof ChickenEntity) &&
            !grs.getBoolean(RegisterGamerules.ZOMBIE_SPAWNERS)) {
            ci.cancel();
        }
        if (this.spawnedEntity instanceof SkeletonEntity && !grs.getBoolean(RegisterGamerules.SKELETON_SPAWNERS)) {
            ci.cancel();
        }
        if (this.spawnedEntity instanceof BlazeEntity && !grs.getBoolean(RegisterGamerules.BLAZE_SPAWNERS)) {
            ci.cancel();
        }
        if (this.spawnedEntity instanceof SpiderEntity && !grs.getBoolean(RegisterGamerules.SPIDER_SPAWNERS)) {
            ci.cancel();
        }
        if (this.spawnedEntity instanceof MagmaCubeEntity && !grs.getBoolean(RegisterGamerules.MAGMA_CUBE_SPAWNERS)) {
            ci.cancel();
        }
    }
}
