package uk.debb.vanilla_disable.mixin.knockback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    protected MixinLivingEntity (EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow
    private LivingEntity attacker;
    @Shadow
    public LivingEntity getAttacker() {
        return this.attacker;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Prevents knockback if the respective gamerule is disabled
     * @param source The entity that caused the knockback
     * @return the opposite of the gamerule
     */
    @Unique
    private boolean isInvulnerableToKnockback(LivingEntity source) {
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED)) {
            return true;
        }
        if (source instanceof BlazeEntity || source instanceof GhastEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.FIREBALL_KNOCKBACK);
        }
        if (source instanceof WitherEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.WITHER_SKULL_KNOCKBACK);
        }
        if (source instanceof EnderDragonEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.DRAGON_KNOCKBACK);
        }
        if ((source instanceof SkeletonEntity && !(source instanceof WitherSkeletonEntity)) ||
            (source instanceof PiglinEntity && source.isHolding(Items.CROSSBOW)) ||
            (source instanceof PillagerEntity)) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.ARROW_KNOCKBACK);
        }
        if (source instanceof DrownedEntity && source.isHolding(Items.TRIDENT)) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.TRIDENT_KNOCKBACK);
        }
        if (source instanceof LlamaEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.LLAMA_SPIT_KNOCKBACK);
        }
        if (source instanceof ShulkerEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.SHULKER_BULLET_KNOCKBACK);
        }
        if (source instanceof ServerPlayerEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.PLAYER_ATTACK_KNOCKBACK);
        }
        if (source instanceof LivingEntity) {
            return !this.world.getGameRules().getBoolean(RegisterGamerules.MOB_ATTACK_KNOCKBACK);
        }
        return false;
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason Figures out what entity is being attacked
     * @return the entity that is being attacked
     */
    @Unique
    private Object getTarget() {
        return (Object)this;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Cancels knockback onto a player if the respective gamerule is disabled
     * @param strength The strength of the knockback
     * @param x The x-coordinate of the knockback
     * @param z The z-coordinate of the knockback
     * @param ci The CallbackInfo
     */
    @Inject(method = "takeKnockback", at = @At("HEAD"), cancellable = true)
    public void cancelKnockback(double strength, double x, double z, CallbackInfo ci) {
        if (this.getTarget() instanceof PlayerEntity && this.isInvulnerableToKnockback(this.getAttacker())) {
            ci.cancel();
        }
    }
}
