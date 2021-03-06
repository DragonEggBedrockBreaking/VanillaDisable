package uk.debb.vanilla_disable.mixin.effects;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Unique
    private static final Object2ObjectMap<MobEffect, GameRules.Key<GameRules.BooleanValue>> statusEffectMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        statusEffectMap.put(MobEffects.ABSORPTION, Gamerules.ABSORPTION_EFFECT);
        statusEffectMap.put(MobEffects.BAD_OMEN, Gamerules.BAD_OMEN_EFFECT);
        statusEffectMap.put(MobEffects.BLINDNESS, Gamerules.BLINDNESS_EFFECT);
        statusEffectMap.put(MobEffects.CONDUIT_POWER, Gamerules.CONDUIT_POWER_EFFECT);
        statusEffectMap.put(MobEffects.DARKNESS, Gamerules.DARKNESS_EFFECT);
        statusEffectMap.put(MobEffects.DOLPHINS_GRACE, Gamerules.DOLPHINS_GRACE_EFFECT);
        statusEffectMap.put(MobEffects.FIRE_RESISTANCE, Gamerules.FIRE_RESISTANCE_EFFECT);
        statusEffectMap.put(MobEffects.GLOWING, Gamerules.GLOWING_EFFECT);
        statusEffectMap.put(MobEffects.DIG_SPEED, Gamerules.HASTE_EFFECT);
        statusEffectMap.put(MobEffects.HEALTH_BOOST, Gamerules.HEALTH_BOOST_EFFECT);
        statusEffectMap.put(MobEffects.HUNGER, Gamerules.HUNGER_EFFECT);
        statusEffectMap.put(MobEffects.HARM, Gamerules.INSTANT_DAMAGE_EFFECT);
        statusEffectMap.put(MobEffects.HEAL, Gamerules.INSTANT_HEALTH_EFFECT);
        statusEffectMap.put(MobEffects.INVISIBILITY, Gamerules.INVISIBILITY_EFFECT);
        statusEffectMap.put(MobEffects.JUMP, Gamerules.JUMP_BOOST_EFFECT);
        statusEffectMap.put(MobEffects.LEVITATION, Gamerules.LEVITATION_EFFECT);
        statusEffectMap.put(MobEffects.LUCK, Gamerules.LUCK_EFFECT);
        statusEffectMap.put(MobEffects.DIG_SLOWDOWN, Gamerules.MINING_FATIGUE_EFFECT);
        statusEffectMap.put(MobEffects.CONFUSION, Gamerules.NAUSEA_EFFECT);
        statusEffectMap.put(MobEffects.NIGHT_VISION, Gamerules.NIGHT_VISION_EFFECT);
        statusEffectMap.put(MobEffects.POISON, Gamerules.POISON_EFFECT);
        statusEffectMap.put(MobEffects.REGENERATION, Gamerules.REGENERATION_EFFECT);
        statusEffectMap.put(MobEffects.DAMAGE_RESISTANCE, Gamerules.RESISTANCE_EFFECT);
        statusEffectMap.put(MobEffects.SATURATION, Gamerules.SATURATION_EFFECT);
        statusEffectMap.put(MobEffects.MOVEMENT_SLOWDOWN, Gamerules.SLOWNESS_EFFECT);
        statusEffectMap.put(MobEffects.SLOW_FALLING, Gamerules.SLOW_FALLING_EFFECT);
        statusEffectMap.put(MobEffects.MOVEMENT_SPEED, Gamerules.SPEED_EFFECT);
        statusEffectMap.put(MobEffects.DAMAGE_BOOST, Gamerules.STRENGTH_EFFECT);
        statusEffectMap.put(MobEffects.UNLUCK, Gamerules.UNLUCK_EFFECT);
        statusEffectMap.put(MobEffects.WATER_BREATHING, Gamerules.WATER_BREATHING_EFFECT);
        statusEffectMap.put(MobEffects.WEAKNESS, Gamerules.WEAKNESS_EFFECT);
        statusEffectMap.put(MobEffects.WITHER, Gamerules.WITHER_EFFECT);
    }

    /**
     * @param original the original value
     * @param effect   the status effect
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "canBeAffected", at = @At("RETURN"))
    private boolean canItBeAffected(boolean original, MobEffectInstance effect) {
        if (VDServer.getServer() == null) return original;
        if (((Object) this) instanceof ServerPlayer) {
            MobEffect statusEffect = effect.getEffect();
            if (statusEffectMap.isEmpty()) {
                addOptionsToMap();
            }
            GameRules.Key<GameRules.BooleanValue> effectGamerule = statusEffectMap.get(statusEffect);
            if ((!GameruleHelper.getBool(Gamerules.EFFECTS_ENABLED)) ||
                    (effectGamerule != null && !GameruleHelper.getBool(effectGamerule))) {
                return false;
            }
        }
        return original;
    }
}