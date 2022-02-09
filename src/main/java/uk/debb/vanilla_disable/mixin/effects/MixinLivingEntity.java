package uk.debb.vanilla_disable.mixin.effects;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity{
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all status effects to their gamerules
     */
    @Unique
    private static final Map<StatusEffect, GameRules.Key<GameRules.BooleanRule>> statusEffectMap = new HashMap<StatusEffect, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        statusEffectMap.put(StatusEffects.ABSORPTION, RegisterGamerules.ABSORPTION_EFFECT);
        statusEffectMap.put(StatusEffects.BAD_OMEN, RegisterGamerules.BAD_OMEN_EFFECT);
        statusEffectMap.put(StatusEffects.BLINDNESS, RegisterGamerules.BLINDNESS_EFFECT);
        statusEffectMap.put(StatusEffects.CONDUIT_POWER, RegisterGamerules.CONDUIT_POWER_EFFECT);
        statusEffectMap.put(StatusEffects.DOLPHINS_GRACE, RegisterGamerules.DOLPHINS_GRACE_EFFECT);
        statusEffectMap.put(StatusEffects.FIRE_RESISTANCE, RegisterGamerules.FIRE_RESISTANCE_EFFECT);
        statusEffectMap.put(StatusEffects.GLOWING, RegisterGamerules.GLOWING_EFFECT);
        statusEffectMap.put(StatusEffects.HASTE, RegisterGamerules.HASTE_EFFECT);
        statusEffectMap.put(StatusEffects.HEALTH_BOOST, RegisterGamerules.HEALTH_BOOST_EFFECT);
        statusEffectMap.put(StatusEffects.HUNGER, RegisterGamerules.HUNGER_EFFECT);
        statusEffectMap.put(StatusEffects.INSTANT_DAMAGE, RegisterGamerules.INSTANT_DAMAGE_EFFECT);
        statusEffectMap.put(StatusEffects.INSTANT_HEALTH, RegisterGamerules.INSTANT_HEALTH_EFFECT);
        statusEffectMap.put(StatusEffects.INVISIBILITY, RegisterGamerules.INVISIBILITY_EFFECT);
        statusEffectMap.put(StatusEffects.JUMP_BOOST, RegisterGamerules.JUMP_BOOST_EFFECT);
        statusEffectMap.put(StatusEffects.LEVITATION, RegisterGamerules.LEVITATION_EFFECT);
        statusEffectMap.put(StatusEffects.LUCK, RegisterGamerules.LUCK_EFFECT);
        statusEffectMap.put(StatusEffects.MINING_FATIGUE, RegisterGamerules.MINING_FATIGUE_EFFECT);
        statusEffectMap.put(StatusEffects.NAUSEA, RegisterGamerules.NAUSEA_EFFECT);
        statusEffectMap.put(StatusEffects.NIGHT_VISION, RegisterGamerules.NIGHT_VISION_EFFECT);
        statusEffectMap.put(StatusEffects.POISON, RegisterGamerules.POISON_EFFECT);
        statusEffectMap.put(StatusEffects.REGENERATION, RegisterGamerules.REGENERATION_EFFECT);
        statusEffectMap.put(StatusEffects.RESISTANCE, RegisterGamerules.RESISTANCE_EFFECT);
        statusEffectMap.put(StatusEffects.SATURATION, RegisterGamerules.SATURATION_EFFECT);
        statusEffectMap.put(StatusEffects.SLOWNESS, RegisterGamerules.SLOWNESS_EFFECT);
        statusEffectMap.put(StatusEffects.SLOW_FALLING, RegisterGamerules.SLOW_FALLING_EFFECT);
        statusEffectMap.put(StatusEffects.SPEED, RegisterGamerules.SPEED_EFFECT);
        statusEffectMap.put(StatusEffects.STRENGTH, RegisterGamerules.STRENGTH_EFFECT);
        statusEffectMap.put(StatusEffects.UNLUCK, RegisterGamerules.UNLUCK_EFFECT);
        statusEffectMap.put(StatusEffects.WATER_BREATHING, RegisterGamerules.WATER_BREATHING_EFFECT);
        statusEffectMap.put(StatusEffects.WEAKNESS, RegisterGamerules.WEAKNESS_EFFECT);
        statusEffectMap.put(StatusEffects.WITHER, RegisterGamerules.WITHER_EFFECT);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable vanilla effects
     * @param effect the status effect
     * @param cir the returnable callback info
     */
    @Inject(method = "canHaveStatusEffect", at = @At("HEAD"), cancellable = true)
    private void canItHaveStatusEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if (((Object) this) instanceof ServerPlayerEntity) {
            StatusEffect statusEffect = effect.getEffectType();
            if (statusEffectMap.isEmpty()) {
                addOptionsToMap();
            }
            GameRules.Key<GameRules.BooleanRule> effectGamerule = statusEffectMap.get(statusEffect);
            if ((!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.EFFECTS_ENABLED)) ||
                (effectGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(effectGamerule))) {
                cir.setReturnValue(false);
            }
        }
    }
}
