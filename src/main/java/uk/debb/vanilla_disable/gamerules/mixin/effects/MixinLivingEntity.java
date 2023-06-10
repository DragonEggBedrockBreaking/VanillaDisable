package uk.debb.vanilla_disable.gamerules.mixin.effects;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements Maps {
    @ModifyReturnValue(method = "canBeAffected", at = @At("RETURN"))
    private boolean canItBeAffected(boolean original, MobEffectInstance effect) {
        if (((Entity) (Object) this).getType().equals(EntityType.PLAYER)) {
            MobEffect statusEffect = effect.getEffect();
            Gamerules gameRule = livingEntityMobEffectMap.get(statusEffect);
            if ((!Gamerules.EFFECTS_ENABLED.getBool()) ||
                    (gameRule != null && !gameRule.getBool())) {
                return false;
            }
        }
        return original;
    }
}