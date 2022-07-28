package uk.debb.vanilla_disable.mixin.effects;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements Maps {
    @ModifyReturnValue(method = "canBeAffected", at = @At("RETURN"))
    private boolean canItBeAffected(boolean original, MobEffectInstance effect) {
        if (((Entity) (Object) this).getType() == EntityType.PLAYER) {
            MobEffect statusEffect = effect.getEffect();
            GameRules.Key<GameRules.BooleanValue> effectGamerule = livingEntityMobEffectMap.get(statusEffect);
            if ((!GameruleHelper.getBool(Gamerules.EFFECTS_ENABLED)) ||
                    (effectGamerule != null && !GameruleHelper.getBool(effectGamerule))) {
                return false;
            }
        }
        return original;
    }
}