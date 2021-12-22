package uk.debb.vanilla_disable.mixin.damage;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {
    private MixinPlayerEntity() {
        super(null, null);
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason Removes damage sources
     * @cir.setReturnValue(Opposite of gamerule)
     */
    @Inject(method = "isInvulnerableTo", at = @At(value = "TAIL"), cancellable = true)
    private void isAlsoInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.DAMAGE_ENABLED)) {
            cir.setReturnValue(true);
        }
        if (damageSource.isProjectile()) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.PROJECTILE_DAMAGE));
        }
        if (damageSource.isExplosive()) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.EXPLOSION_DAMAGE));
        }
        if (damageSource.isOutOfWorld()) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.VOID_DAMAGE));
        }
        if (damageSource.isMagic()) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.MAGIC_DAMAGE));
        }
        if (damageSource.isSourceCreativePlayer()) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.CREATIVE_PLAYER_DAMAGE));
        }
        if (damageSource == DamageSource.LIGHTNING_BOLT) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.LIGHTNING_DAMAGE));
        }
        if (damageSource == DamageSource.IN_WALL) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.WALL_DAMAGE));
        }
        if (damageSource == DamageSource.CRAMMING) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.CRAMMING_DAMAGE));
        }
        if (damageSource == DamageSource.STARVE) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.STARVATION_DAMAGE));
        }
        if (damageSource == DamageSource.CACTUS) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.CACTUS_DAMAGE));
        }
        if (damageSource == DamageSource.FLY_INTO_WALL) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.FLY_INTO_WALL_DAMAGE));
        }
        if (damageSource == DamageSource.WITHER) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.WITHER_DAMAGE));
        }
        if (damageSource == DamageSource.ANVIL) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.ANVIL_DAMAGE));
        }
        if (damageSource == DamageSource.DRAGON_BREATH) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.DRAGON_DAMAGE));
        }
        if (damageSource == DamageSource.DRYOUT) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.DRYOUT_DAMAGE));
        }
        if (damageSource == DamageSource.SWEET_BERRY_BUSH) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.SWEET_BERRY_BUSH_DAMAGE));
        }
        if (damageSource == DamageSource.FALLING_STALACTITE) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(RegisterGamerules.FALLING_STALACTITE_DAMAGE));
        }
    }
}
