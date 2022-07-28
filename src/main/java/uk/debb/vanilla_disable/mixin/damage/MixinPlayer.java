package uk.debb.vanilla_disable.mixin.damage;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Player.class)
public abstract class MixinPlayer implements Maps {
    @ModifyReturnValue(method = "isInvulnerableTo", at = @At(value = "RETURN"))
    private boolean isAlsoInvulnerableTo(boolean original, DamageSource damageSource) {
        GameRules.Key<GameRules.BooleanValue> damageGamerule = playerDamageSourceMap.get(damageSource);
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