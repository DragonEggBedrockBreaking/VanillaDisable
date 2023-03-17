package uk.debb.vanilla_disable.mixin.damage;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.DamageTypeTags;
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
        if (!Gamerules.DAMAGE_ENABLED.getBool()) {
            return true;
        } else if (gameRule != null && !gameRule.getBool()) {
            return true;
        } else if (damageSource.is(DamageTypeTags.IS_PROJECTILE)) {
            return !Gamerules.PROJECTILE_DAMAGE.getBool();
        } else if (damageSource.is(DamageTypeTags.IS_EXPLOSION)) {
            return !Gamerules.EXPLOSION_DAMAGE.getBool();
        } else if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return !Gamerules.VOID_DAMAGE.getBool();
        } else if (damageSource.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            return !Gamerules.MAGIC_DAMAGE.getBool();
        } else if (damageSource.isCreativePlayer()) {
            return !Gamerules.CREATIVE_PLAYER_DAMAGE.getBool();
        } else if (damageSource.isIndirect()) {
            return !Gamerules.ENTITY_DAMAGE.getBool();
        }
        return original;
    }
}