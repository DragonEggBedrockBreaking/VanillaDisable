package uk.debb.vanilla_disable.mixin.damage;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all damage sources to their gamerules
     */
    @Unique
    private static final Map<DamageSource, GameRules.Key<GameRules.BooleanRule>> damageSourceMap = new HashMap<DamageSource, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        damageSourceMap.put(DamageSource.LIGHTNING_BOLT, RegisterGamerules.LIGHTNING_DAMAGE);
        damageSourceMap.put(DamageSource.IN_WALL, RegisterGamerules.WALL_DAMAGE);
        damageSourceMap.put(DamageSource.CRAMMING, RegisterGamerules.CRAMMING_DAMAGE);
        damageSourceMap.put(DamageSource.STARVE, RegisterGamerules.STARVATION_DAMAGE);
        damageSourceMap.put(DamageSource.CACTUS, RegisterGamerules.CACTUS_DAMAGE);
        damageSourceMap.put(DamageSource.FLY_INTO_WALL, RegisterGamerules.FLY_INTO_WALL_DAMAGE);
        damageSourceMap.put(DamageSource.WITHER, RegisterGamerules.WITHER_DAMAGE);
        damageSourceMap.put(DamageSource.ANVIL, RegisterGamerules.ANVIL_DAMAGE);
        damageSourceMap.put(DamageSource.DRAGON_BREATH, RegisterGamerules.DRAGON_DAMAGE);
        damageSourceMap.put(DamageSource.SWEET_BERRY_BUSH, RegisterGamerules.SWEET_BERRY_BUSH_DAMAGE);
        damageSourceMap.put(DamageSource.FALLING_STALACTITE, RegisterGamerules.FALLING_STALACTITE_DAMAGE);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Removes damage sources
     * @return Opposite of gamerule
     */
    @Inject(method = "isInvulnerableTo", at = @At(value = "TAIL"), cancellable = true)
    private void isAlsoInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (damageSourceMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanRule> damageGamerule = damageSourceMap.get(damageSource);
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.DAMAGE_ENABLED)) {
            cir.setReturnValue(true);
        } else if (damageGamerule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(damageGamerule)) {
            cir.setReturnValue(true);
        } else if (damageSource.isProjectile()) {
            cir.setReturnValue(!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PROJECTILE_DAMAGE));
        } else if (damageSource.isExplosive()) {
            cir.setReturnValue(!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.EXPLOSION_DAMAGE));
        } else if (damageSource.isOutOfWorld()) {
            cir.setReturnValue(!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.VOID_DAMAGE));
        } else if (damageSource.isMagic()) {
            cir.setReturnValue(!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.MAGIC_DAMAGE));
        } else if (damageSource.isSourceCreativePlayer()) {
            cir.setReturnValue(!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CREATIVE_PLAYER_DAMAGE));
        }
    }
}
