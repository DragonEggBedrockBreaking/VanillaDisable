package uk.debb.vanilla_disable.mixin.damage;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Player.class)
public abstract class MixinPlayer implements Maps {
    @ModifyReturnValue(method = "isInvulnerableTo", at = @At(value = "RETURN"))
    private boolean isAlsoInvulnerableTo(boolean original, DamageSource damageSource) {
        Gamerules gameRule = playerDamageSourceMap.get(damageSource);
        if (!Gamerules.DAMAGE_ENABLED.getValue(Boolean::parseBoolean)) {
            return true;
        } else if (gameRule != null && !gameRule.getValue(Boolean::parseBoolean)) {
            return true;
        } else if (damageSource.isProjectile()) {
            return !Gamerules.PROJECTILE_DAMAGE.getValue(Boolean::parseBoolean);
        } else if (damageSource.isExplosion()) {
            return !Gamerules.EXPLOSION_DAMAGE.getValue(Boolean::parseBoolean);
        } else if (damageSource.isBypassInvul()) {
            return !Gamerules.VOID_DAMAGE.getValue(Boolean::parseBoolean);
        } else if (damageSource.isMagic()) {
            return !Gamerules.MAGIC_DAMAGE.getValue(Boolean::parseBoolean);
        } else if (damageSource.isCreativePlayer()) {
            return !Gamerules.CREATIVE_PLAYER_DAMAGE.getValue(Boolean::parseBoolean);
        }
        return original;
    }
}