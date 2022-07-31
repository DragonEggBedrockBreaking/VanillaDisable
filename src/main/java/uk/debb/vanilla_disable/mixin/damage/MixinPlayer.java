package uk.debb.vanilla_disable.mixin.damage;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Player.class)
public abstract class MixinPlayer implements Maps {
    @ModifyReturnValue(method = "isInvulnerableTo", at = @At(value = "RETURN"))
    private boolean isAlsoInvulnerableTo(boolean original, DamageSource damageSource) {
        BooleanGamerules gameRule = playerDamageSourceMap.get(damageSource);
        if (!BooleanGamerules.DAMAGE_ENABLED.getValue()) {
            return true;
        } else if (gameRule != null && !gameRule.getValue()) {
            return true;
        } else if (damageSource.isProjectile()) {
            return !BooleanGamerules.PROJECTILE_DAMAGE.getValue();
        } else if (damageSource.isExplosion()) {
            return !BooleanGamerules.EXPLOSION_DAMAGE.getValue();
        } else if (damageSource.isBypassInvul()) {
            return !BooleanGamerules.VOID_DAMAGE.getValue();
        } else if (damageSource.isMagic()) {
            return !BooleanGamerules.MAGIC_DAMAGE.getValue();
        } else if (damageSource.isCreativePlayer()) {
            return !BooleanGamerules.CREATIVE_PLAYER_DAMAGE.getValue();
        }
        return original;
    }
}