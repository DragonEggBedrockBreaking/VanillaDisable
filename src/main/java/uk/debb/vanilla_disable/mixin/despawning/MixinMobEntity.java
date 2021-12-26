package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(value = MobEntity.class, priority = 999)
public abstract class MixinMobEntity extends LivingEntity implements Bucketable {
    protected MixinMobEntity(EntityType<? extends MobEntity> entityType, World world) {
        super((EntityType<? extends LivingEntity>)entityType, world);
    }
    
    @Shadow private boolean persistent;

    /**
     * @author DragonEggBedrockBreaking
     * @reason Change the immediate despawn range based on gamerules
     * @return The immediate despawn range
     */    
    private int getImmediateDespawnRange() {
        MobEntity entity = (MobEntity) (Object) this;
        GameRules gamerules = this.getWorld().getGameRules();
        if (entity instanceof HostileEntity) {
            return gamerules.getInt(RegisterGamerules.MONSTER_MAX_DESPAWN);
        }
        if (entity instanceof AnimalEntity) {
            return gamerules.getInt(RegisterGamerules.CREATURE_MAX_DESPAWN);
        }
        if (entity instanceof AmbientEntity) {
            return gamerules.getInt(RegisterGamerules.AMBIENT_MAX_DESPAWN);
        }
        if (entity instanceof AxolotlEntity) {
            return gamerules.getInt(RegisterGamerules.AXOLOTL_MAX_DESPAWN);
        }
        if (entity instanceof GlowSquidEntity) {
            return gamerules.getInt(RegisterGamerules.GLOWSQUID_MAX_DESPAWN);
        }
        if (entity instanceof FishEntity) {
            return gamerules.getInt(RegisterGamerules.WATER_AMBIENT_MAX_DESPAWN);
        }
        if (entity instanceof WaterCreatureEntity) {
            return gamerules.getInt(RegisterGamerules.WATER_CREATURE_MAX_DESPAWN);
        }
        return 128;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Change the range where mobs can despawn based on gamerules
     * @return The range where mobs can despawn
     */
    private int getDespawnStartRange() {
        MobEntity entity = (MobEntity) (Object) this;
        GameRules gamerules = this.getWorld().getGameRules();
        if (entity instanceof HostileEntity) {
            return gamerules.getInt(RegisterGamerules.MONSTER_MIN_DESPAWN);
        }
        if (entity instanceof AnimalEntity) {
            return gamerules.getInt(RegisterGamerules.CREATURE_MIN_DESPAWN);
        }
        if (entity instanceof AmbientEntity) {
            return gamerules.getInt(RegisterGamerules.AMBIENT_MIN_DESPAWN);
        }
        if (entity instanceof AxolotlEntity) {
            return gamerules.getInt(RegisterGamerules.AXOLOTL_MIN_DESPAWN);
        }
        if (entity instanceof GlowSquidEntity) {
            return gamerules.getInt(RegisterGamerules.GLOWSQUID_MIN_DESPAWN);
        }
        if (entity instanceof FishEntity) {
            return gamerules.getInt(RegisterGamerules.WATER_AMBIENT_MIN_DESPAWN);
        }
        if (entity instanceof WaterCreatureEntity) {
            return gamerules.getInt(RegisterGamerules.WATER_CREATURE_MIN_DESPAWN);
        }
        return 32;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Change whether the mob can despawn based on gamerules
     * @return Whether the mob can despawn
     */
    private boolean canDespawn() {
        MobEntity entity = (MobEntity) (Object) this;
        GameRules gamerules = this.getWorld().getGameRules();
        if (entity instanceof HostileEntity) {
            return gamerules.getBoolean(RegisterGamerules.MONSTERS_DESPAWN);
        }
        if (entity instanceof AnimalEntity) {
            return gamerules.getBoolean(RegisterGamerules.CREATURES_DESPAWN);
        }
        if (entity instanceof AmbientEntity) {
            return gamerules.getBoolean(RegisterGamerules.AMBIENT_DESPAWN);
        }
        if (entity instanceof AxolotlEntity) {
            return gamerules.getBoolean(RegisterGamerules.AXOLOTLS_DESPAWN) &&
                   !this.hasCustomName() && !this.isFromBucket();
        }
        if (entity instanceof GlowSquidEntity) {
            return gamerules.getBoolean(RegisterGamerules.GLOWSQUIDS_DESPAWN);
        }
        if (entity instanceof FishEntity) {
            return gamerules.getBoolean(RegisterGamerules.WATER_AMBIENT_DESPAWN) &&
                   !this.hasCustomName() && !this.isFromBucket();
        }
        if (entity instanceof WaterCreatureEntity) {
            return gamerules.getBoolean(RegisterGamerules.WATER_CREATURES_DESPAWN);
        }
        return true;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Change whether the mob despawns based on gamerules
     */
    @Overwrite
    public void checkDespawn() {
        MobEntity entity = (MobEntity) (Object) this;
        if (this.world.getDifficulty() == Difficulty.PEACEFUL && entity instanceof HostileEntity) {
            this.discard();
            return;
        }
        if (this.persistent || this.hasVehicle()) {
            this.despawnCounter = 0;
            return;
        }
        PlayerEntity player = this.world.getClosestPlayer(this, -1.0);
        if (player != null) {
            int i;
            double d = player.squaredDistanceTo(this);
            if (d > (double)((i = this.getImmediateDespawnRange()) * i) && this.canDespawn()) {
                this.discard();
            }
            int k = this.getDespawnStartRange();
            int l = k * k;
            if (this.despawnCounter > 600 && this.random.nextInt(800) == 0 && d > (double)l && this.canDespawn()) {
                this.discard();
            } else if (d < (double)l) {
                this.despawnCounter = 0;
            }
        }
    }
}