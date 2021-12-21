package uk.debb.vanilla_disable.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import uk.debb.vanilla_disable.VanillaDisableGamerules;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {
    private MixinPlayerEntity() {
        super(null, null);
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason Removes damage sources
     * @return Opposite of gamerule
     */
    @Overwrite
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (!this.world.getGameRules().getBoolean(VanillaDisableGamerules.DAMAGE_ENABLED)) {
            return true;
        }
        if (damageSource.isFromFalling()) {
            return !this.world.getGameRules().getBoolean(GameRules.FALL_DAMAGE);
        }
        if (damageSource.isFire()) {
            return !this.world.getGameRules().getBoolean(GameRules.FIRE_DAMAGE);
        }
        if (damageSource.isProjectile()) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.PROJECTILE_DAMAGE);
        }
        if (damageSource.isExplosive()) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.EXPLOSION_DAMAGE);
        }
        if (damageSource.isOutOfWorld()) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.VOID_DAMAGE);
        }
        if (damageSource.isMagic()) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.MAGIC_DAMAGE);
        }
        if (damageSource.isSourceCreativePlayer()) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.CREATIVE_PLAYER_DAMAGE);
        }
        if (damageSource == DamageSource.DROWN) {
            return !this.world.getGameRules().getBoolean(GameRules.DROWNING_DAMAGE);
        }
        if (damageSource == DamageSource.FREEZE) {
            return !this.world.getGameRules().getBoolean(GameRules.FREEZE_DAMAGE);
        }
        if (damageSource == DamageSource.LIGHTNING_BOLT) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.LIGHTNING_DAMAGE);
        }
        if (damageSource == DamageSource.IN_WALL) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.WALL_DAMAGE);
        }
        if (damageSource == DamageSource.CRAMMING) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.CRAMMING_DAMAGE);
        }
        if (damageSource == DamageSource.STARVE) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.STARVATION_DAMAGE);
        }
        if (damageSource == DamageSource.CACTUS) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.CACTUS_DAMAGE);
        }
        if (damageSource == DamageSource.FLY_INTO_WALL) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.FLY_INTO_WALL_DAMAGE);
        }
        if (damageSource == DamageSource.WITHER) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.WITHER_DAMAGE);
        }
        if (damageSource == DamageSource.ANVIL) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.ANVIL_DAMAGE);
        }
        if (damageSource == DamageSource.DRAGON_BREATH) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.DRAGON_DAMAGE);
        }
        if (damageSource == DamageSource.DRYOUT) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.DRYOUT_DAMAGE);
        }
        if (damageSource == DamageSource.SWEET_BERRY_BUSH) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.SWEET_BERRY_BUSH_DAMAGE);
        }
        if (damageSource == DamageSource.FALLING_STALACTITE) {
            return !this.world.getGameRules().getBoolean(VanillaDisableGamerules.FALLING_STALACTITE_DAMAGE);
        }
        return false;
    }
}
