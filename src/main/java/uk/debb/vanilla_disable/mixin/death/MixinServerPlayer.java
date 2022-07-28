package uk.debb.vanilla_disable.mixin.death;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer implements Maps {
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void cancelDeath(DamageSource damageSource, CallbackInfo ci) {
        GameRules.Key<GameRules.BooleanValue> gameRule = serverPlayerStringMap.get(damageSource.getMsgId());
        if (!GameruleHelper.getBool(Gamerules.DEATH_ENABLED) || (gameRule != null && !GameruleHelper.getBool(gameRule))) {
            ((Player) (Object) this).setHealth(((Player) (Object) this).getHealth() + 1);
            ci.cancel();
        }
    }
}
