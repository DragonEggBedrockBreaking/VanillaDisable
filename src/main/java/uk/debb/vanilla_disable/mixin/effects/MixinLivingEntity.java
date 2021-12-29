package uk.debb.vanilla_disable.mixin.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    protected MixinLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable vanilla effects
     * @param effect the status effect
     * @param cir the returnable callback info
     */
    @Inject(method = "canHaveStatusEffect", at = @At("HEAD"), cancellable = true)
    private void canItHaveStatusEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        StatusEffect statusEffect = effect.getEffectType();
        if (((Object) this) instanceof ServerPlayerEntity) {
            if (!this.world.getGameRules().getBoolean(RegisterGamerules.ABSORPTION_EFFECT) &&
                statusEffect == StatusEffects.ABSORPTION) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.BAD_OMEN_EFFECT) &&
                statusEffect == StatusEffects.BAD_OMEN) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.BLINDNESS_EFFECT) &&
                statusEffect == StatusEffects.BLINDNESS) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.CONDUIT_POWER_EFFECT) &&
                statusEffect == StatusEffects.CONDUIT_POWER) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.DOLPHINS_GRACE_EFFECT) &&
                statusEffect == StatusEffects.DOLPHINS_GRACE) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.FIRE_RESISTANCE_EFFECT) &&
                statusEffect == StatusEffects.FIRE_RESISTANCE) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.GLOWING_EFFECT) &&
                statusEffect == StatusEffects.GLOWING) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.HASTE_EFFECT) &&
                statusEffect == StatusEffects.HASTE) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.HEALTH_BOOST_EFFECT) &&
                statusEffect == StatusEffects.HEALTH_BOOST) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.HUNGER_EFFECT) &&
                statusEffect == StatusEffects.HUNGER) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.INSTANT_DAMAGE_EFFECT) &&
                statusEffect == StatusEffects.INSTANT_DAMAGE) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.INSTANT_HEALTH_EFFECT) &&
                statusEffect == StatusEffects.INSTANT_HEALTH) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.INVISIBILITY_EFFECT) &&
                statusEffect == StatusEffects.INVISIBILITY) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.JUMP_BOOST_EFFECT) &&
                statusEffect == StatusEffects.JUMP_BOOST) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.LEVITATION_EFFECT) &&
                statusEffect == StatusEffects.LEVITATION) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.LUCK_EFFECT) &&
                statusEffect == StatusEffects.LUCK) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.MINING_FATIGUE_EFFECT) &&
                statusEffect == StatusEffects.MINING_FATIGUE) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.NAUSEA_EFFECT) &&
                statusEffect == StatusEffects.NAUSEA) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.NIGHT_VISION_EFFECT) &&
                statusEffect == StatusEffects.NIGHT_VISION) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.POISON_EFFECT) &&
                statusEffect == StatusEffects.POISON) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.REGENERATION_EFFECT) &&
                statusEffect == StatusEffects.REGENERATION) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.RESISTANCE_EFFECT) &&
                statusEffect == StatusEffects.RESISTANCE) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.SATURATION_EFFECT) &&
                statusEffect == StatusEffects.SATURATION) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.SLOWNESS_EFFECT) &&
                statusEffect == StatusEffects.SLOWNESS) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.SLOW_FALLING_EFFECT) &&
                statusEffect == StatusEffects.SLOW_FALLING) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.SPEED_EFFECT) &&
                statusEffect == StatusEffects.SPEED) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.STRENGTH_EFFECT) &&
                statusEffect == StatusEffects.STRENGTH) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.UNLUCK_EFFECT) &&
                statusEffect == StatusEffects.UNLUCK) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.WATER_BREATHING_EFFECT) &&
                statusEffect == StatusEffects.WATER_BREATHING) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.WEAKNESS_EFFECT) &&
                statusEffect == StatusEffects.WEAKNESS) {
                cir.setReturnValue(false);
            } else if (!this.world.getGameRules().getBoolean(RegisterGamerules.WITHER_EFFECT) &&
                statusEffect == StatusEffects.WITHER) {
                cir.setReturnValue(false);
            }
        }
    }
}
