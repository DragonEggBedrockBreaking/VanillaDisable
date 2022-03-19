package uk.debb.vanilla_disable.mixin.effects;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameRules;
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
    private static final Object2ObjectMap<MobEffect, GameRules.Key<GameRules.BooleanValue>> statusEffectMap = new Object2ObjectOpenHashMap<MobEffect, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        statusEffectMap.put(MobEffects.ABSORPTION, RegisterGamerules.ABSORPTION_EFFECT);
        statusEffectMap.put(MobEffects.BAD_OMEN, RegisterGamerules.BAD_OMEN_EFFECT);
        statusEffectMap.put(MobEffects.BLINDNESS, RegisterGamerules.BLINDNESS_EFFECT);
        statusEffectMap.put(MobEffects.CONDUIT_POWER, RegisterGamerules.CONDUIT_POWER_EFFECT);
        statusEffectMap.put(MobEffects.DOLPHINS_GRACE, RegisterGamerules.DOLPHINS_GRACE_EFFECT);
        statusEffectMap.put(MobEffects.FIRE_RESISTANCE, RegisterGamerules.FIRE_RESISTANCE_EFFECT);
        statusEffectMap.put(MobEffects.GLOWING, RegisterGamerules.GLOWING_EFFECT);
        statusEffectMap.put(MobEffects.DIG_SPEED, RegisterGamerules.HASTE_EFFECT);
        statusEffectMap.put(MobEffects.HEALTH_BOOST, RegisterGamerules.HEALTH_BOOST_EFFECT);
        statusEffectMap.put(MobEffects.HUNGER, RegisterGamerules.HUNGER_EFFECT);
        statusEffectMap.put(MobEffects.HARM, RegisterGamerules.INSTANT_DAMAGE_EFFECT);
        statusEffectMap.put(MobEffects.HEAL, RegisterGamerules.INSTANT_HEALTH_EFFECT);
        statusEffectMap.put(MobEffects.INVISIBILITY, RegisterGamerules.INVISIBILITY_EFFECT);
        statusEffectMap.put(MobEffects.JUMP, RegisterGamerules.JUMP_BOOST_EFFECT);
        statusEffectMap.put(MobEffects.LEVITATION, RegisterGamerules.LEVITATION_EFFECT);
        statusEffectMap.put(MobEffects.LUCK, RegisterGamerules.LUCK_EFFECT);
        statusEffectMap.put(MobEffects.DIG_SLOWDOWN, RegisterGamerules.MINING_FATIGUE_EFFECT);
        statusEffectMap.put(MobEffects.CONFUSION, RegisterGamerules.NAUSEA_EFFECT);
        statusEffectMap.put(MobEffects.NIGHT_VISION, RegisterGamerules.NIGHT_VISION_EFFECT);
        statusEffectMap.put(MobEffects.POISON, RegisterGamerules.POISON_EFFECT);
        statusEffectMap.put(MobEffects.REGENERATION, RegisterGamerules.REGENERATION_EFFECT);
        statusEffectMap.put(MobEffects.DAMAGE_RESISTANCE, RegisterGamerules.RESISTANCE_EFFECT);
        statusEffectMap.put(MobEffects.SATURATION, RegisterGamerules.SATURATION_EFFECT);
        statusEffectMap.put(MobEffects.MOVEMENT_SLOWDOWN, RegisterGamerules.SLOWNESS_EFFECT);
        statusEffectMap.put(MobEffects.SLOW_FALLING, RegisterGamerules.SLOW_FALLING_EFFECT);
        statusEffectMap.put(MobEffects.MOVEMENT_SPEED, RegisterGamerules.SPEED_EFFECT);
        statusEffectMap.put(MobEffects.DAMAGE_BOOST, RegisterGamerules.STRENGTH_EFFECT);
        statusEffectMap.put(MobEffects.UNLUCK, RegisterGamerules.UNLUCK_EFFECT);
        statusEffectMap.put(MobEffects.WATER_BREATHING, RegisterGamerules.WATER_BREATHING_EFFECT);
        statusEffectMap.put(MobEffects.WEAKNESS, RegisterGamerules.WEAKNESS_EFFECT);
        statusEffectMap.put(MobEffects.WITHER, RegisterGamerules.WITHER_EFFECT);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason disable vanilla effects
     * @param effect the status effect
     * @param cir the returnable callback info
     */
    @Inject(method = "canBeAffected", at = @At("HEAD"), cancellable = true)
    private void canItBeAffected(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (((Object) this) instanceof ServerPlayer) {
            MobEffect statusEffect = effect.getEffect();
            if (statusEffectMap.isEmpty()) {
                addOptionsToMap();
            }
            GameRules.Key<GameRules.BooleanValue> effectGamerule = statusEffectMap.get(statusEffect);
            if ((!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.EFFECTS_ENABLED)) ||
                (effectGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(effectGamerule))) {
                cir.setReturnValue(false);
            }
        }
    }
}
