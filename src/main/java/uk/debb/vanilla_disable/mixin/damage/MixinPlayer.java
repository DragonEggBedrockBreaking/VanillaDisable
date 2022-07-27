package uk.debb.vanilla_disable.mixin.damage;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @Unique
    private static final Object2ObjectMap<DamageSource, GameRules.Key<GameRules.BooleanValue>> damageSourceMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        damageSourceMap.put(DamageSource.LIGHTNING_BOLT, Gamerules.LIGHTNING_DAMAGE);
        damageSourceMap.put(DamageSource.IN_WALL, Gamerules.WALL_DAMAGE);
        damageSourceMap.put(DamageSource.CRAMMING, Gamerules.CRAMMING_DAMAGE);
        damageSourceMap.put(DamageSource.STARVE, Gamerules.STARVATION_DAMAGE);
        damageSourceMap.put(DamageSource.CACTUS, Gamerules.CACTUS_DAMAGE);
        damageSourceMap.put(DamageSource.FLY_INTO_WALL, Gamerules.FLY_INTO_WALL_DAMAGE);
        damageSourceMap.put(DamageSource.WITHER, Gamerules.WITHER_DAMAGE);
        damageSourceMap.put(DamageSource.ANVIL, Gamerules.ANVIL_DAMAGE);
        damageSourceMap.put(DamageSource.DRAGON_BREATH, Gamerules.DRAGON_DAMAGE);
        damageSourceMap.put(DamageSource.SWEET_BERRY_BUSH, Gamerules.SWEET_BERRY_BUSH_DAMAGE);
        damageSourceMap.put(DamageSource.FALLING_STALACTITE, Gamerules.FALLING_STALACTITE_DAMAGE);
        damageSourceMap.put(DamageSource.FALLING_BLOCK, Gamerules.FALLING_BLOCK_DAMAGE);
    }

    /**
     * @param original     the original value
     * @param damageSource the source of the damage
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isInvulnerableTo", at = @At(value = "RETURN"))
    private boolean isAlsoInvulnerableTo(boolean original, DamageSource damageSource) {
        if (damageSourceMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> damageGamerule = damageSourceMap.get(damageSource);
        if (!GameruleHelper.getBool(Gamerules.DAMAGE_ENABLED)) {
            return true;
        } else if (damageGamerule != null && !GameruleHelper.getBool(damageGamerule)) {
            return true;
        } else if (damageSource.isProjectile()) {
            return !GameruleHelper.getBool(Gamerules.PROJECTILE_DAMAGE);
        } else if (damageSource.isExplosion()) {
            return !GameruleHelper.getBool(Gamerules.EXPLOSION_DAMAGE);
        } else if (damageSource.isBypassInvul()) {
            return !GameruleHelper.getBool(Gamerules.VOID_DAMAGE);
        } else if (damageSource.isMagic()) {
            return !GameruleHelper.getBool(Gamerules.MAGIC_DAMAGE);
        } else if (damageSource.isCreativePlayer()) {
            return !GameruleHelper.getBool(Gamerules.CREATIVE_PLAYER_DAMAGE);
        }
        return original;
    }
}