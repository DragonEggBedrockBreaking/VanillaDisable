package uk.debb.vanilla_disable.mixin.knockback;

import java.util.HashMap;
import java.util.Map;
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
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow
    private LivingEntity attacker;

    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all relevenat entities to their gamerules
     */
    @Unique
    private static final Map<Class<?>, GameRules.Key<GameRules.BooleanRule>> entityMap = new HashMap<Class<?>, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        entityMap.put(BlazeEntity.class, RegisterGamerules.FIREBALL_KNOCKBACK);
        entityMap.put(GhastEntity.class, RegisterGamerules.FIREBALL_KNOCKBACK);
        entityMap.put(WitherEntity.class, RegisterGamerules.WITHER_SKULL_KNOCKBACK);
        entityMap.put(EnderDragonEntity.class, RegisterGamerules.DRAGON_KNOCKBACK);
        entityMap.put(LlamaEntity.class, RegisterGamerules.LLAMA_SPIT_KNOCKBACK);
        entityMap.put(ShulkerEntity.class, RegisterGamerules.SHULKER_BULLET_KNOCKBACK);
        entityMap.put(ServerPlayerEntity.class, RegisterGamerules.PLAYER_ATTACK_KNOCKBACK);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Prevents knockback if the respective gamerule is disabled
     * @param source The entity that caused the knockback
     * @return the opposite of the gamerule
     */
    @Unique
    private boolean isInvulnerableToKnockback(LivingEntity source) {
        if (entityMap.isEmpty()) {
            this.addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanRule> knockbackGamerule = entityMap.get(this.getClass());
        if ((!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED)) ||
            (knockbackGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(knockbackGamerule))) {
            return true;
        }
        if ((source instanceof SkeletonEntity && !(source instanceof WitherSkeletonEntity)) ||
            (source instanceof PiglinEntity && source.isHolding(Items.CROSSBOW)) ||
            (source instanceof PillagerEntity)) {
            return !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ARROW_KNOCKBACK);
        }
        if (source instanceof DrownedEntity && source.isHolding(Items.TRIDENT)) {
            return !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.TRIDENT_KNOCKBACK);
        }
        return false;
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
        if ((Object)this instanceof PlayerEntity && this.isInvulnerableToKnockback(this.attacker)) {
            ci.cancel();
        }
    }
}