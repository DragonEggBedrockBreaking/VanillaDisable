package uk.debb.vanilla_disable.mixin.knockback;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow private LivingEntity lastHurtByMob;

    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all relevenat entities to their gamerules
     */
    @Unique
    private static final Map<Class<?>, GameRules.Key<GameRules.BooleanValue>> entityMap = new HashMap<Class<?>, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        entityMap.put(Blaze.class, RegisterGamerules.FIREBALL_KNOCKBACK);
        entityMap.put(Ghast.class, RegisterGamerules.FIREBALL_KNOCKBACK);
        entityMap.put(WitherBoss.class, RegisterGamerules.WITHER_SKULL_KNOCKBACK);
        entityMap.put(EnderDragon.class, RegisterGamerules.DRAGON_KNOCKBACK);
        entityMap.put(Llama.class, RegisterGamerules.LLAMA_SPIT_KNOCKBACK);
        entityMap.put(Shulker.class, RegisterGamerules.SHULKER_BULLET_KNOCKBACK);
        entityMap.put(ServerPlayer.class, RegisterGamerules.PLAYER_ATTACK_KNOCKBACK);
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
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> knockbackGamerule = entityMap.get(this.getClass());
        if ((!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED)) ||
            (knockbackGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(knockbackGamerule))) {
            return true;
        }
        if ((source instanceof Skeleton && !(source instanceof WitherSkeleton)) ||
            (source instanceof Piglin && source.isHolding(Items.CROSSBOW)) ||
            (source instanceof Pillager)) {
            return !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ARROW_KNOCKBACK);
        }
        if (source instanceof Drowned && source.isHolding(Items.TRIDENT)) {
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
    @Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
    public void cancelKnockback(double strength, double x, double z, CallbackInfo ci) {
        if ((Object)this instanceof Player && isInvulnerableToKnockback(this.lastHurtByMob)) {
            ci.cancel();
        }
    }
}