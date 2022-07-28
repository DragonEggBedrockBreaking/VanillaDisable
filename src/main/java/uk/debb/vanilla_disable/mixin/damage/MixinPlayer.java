package uk.debb.vanilla_disable.mixin.damage;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Player.class)
public abstract class MixinPlayer implements Maps {
    @ModifyReturnValue(method = "isInvulnerableTo", at = @At(value = "RETURN"))
    private boolean isAlsoInvulnerableTo(boolean original, DamageSource damageSource) {
        BooleanGamerules gameRule = playerDamageSourceMap.get(damageSource);
        if (!GameruleHelper.getBool(BooleanGamerules.DAMAGE_ENABLED)) {
            return true;
        } else if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            return true;
        } else if (damageSource.isProjectile()) {
            return !GameruleHelper.getBool(BooleanGamerules.PROJECTILE_DAMAGE);
        } else if (damageSource.isExplosion()) {
            return !GameruleHelper.getBool(BooleanGamerules.EXPLOSION_DAMAGE);
        } else if (damageSource.isBypassInvul()) {
            return !GameruleHelper.getBool(BooleanGamerules.VOID_DAMAGE);
        } else if (damageSource.isMagic()) {
            return !GameruleHelper.getBool(BooleanGamerules.MAGIC_DAMAGE);
        } else if (damageSource.isCreativePlayer()) {
            return !GameruleHelper.getBool(BooleanGamerules.CREATIVE_PLAYER_DAMAGE);
        }
        return original;
    }
}