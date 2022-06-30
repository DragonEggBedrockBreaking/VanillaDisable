package uk.debb.vanilla_disable.mixin.knockback;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow private LivingEntity lastHurtByMob;

    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> entityMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        entityMap.put(Blaze.class, Gamerules.FIREBALL_KNOCKBACK);
        entityMap.put(Ghast.class, Gamerules.FIREBALL_KNOCKBACK);
        entityMap.put(WitherBoss.class, Gamerules.WITHER_SKULL_KNOCKBACK);
        entityMap.put(EnderDragon.class, Gamerules.DRAGON_KNOCKBACK);
        entityMap.put(Llama.class, Gamerules.LLAMA_SPIT_KNOCKBACK);
        entityMap.put(Shulker.class, Gamerules.SHULKER_BULLET_KNOCKBACK);
        entityMap.put(ServerPlayer.class, Gamerules.PLAYER_ATTACK_KNOCKBACK);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @param source The entity that caused the knockback
     * @return the opposite of the gamerule
     */
    @Unique
    private boolean isInvulnerableToKnockback(LivingEntity source) {
        if (entityMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> knockbackGamerule = entityMap.get(this.getClass());
        if ((!GameruleHelper.getBool(Gamerules.KNOCKBACK_ENABLED)) ||
            (knockbackGamerule != null && !GameruleHelper.getBool(knockbackGamerule))) {
            return true;
        }
        if ((source instanceof Skeleton && !(source instanceof WitherSkeleton)) ||
            (source instanceof Piglin && source.isHolding(Items.CROSSBOW)) ||
            (source instanceof Pillager)) {
            return !GameruleHelper.getBool(Gamerules.ARROW_KNOCKBACK);
        }
        if (source instanceof Drowned && source.isHolding(Items.TRIDENT)) {
            return !GameruleHelper.getBool(Gamerules.TRIDENT_KNOCKBACK);
        }
        if (source != null) {
            return !GameruleHelper.getBool(Gamerules.MOB_ATTACK_KNOCKBACK);
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
        if (VDServer.getServer() == null) return;
        if ((Object)this instanceof Player && isInvulnerableToKnockback(this.lastHurtByMob)) {
            ci.cancel();
        }
    }
}