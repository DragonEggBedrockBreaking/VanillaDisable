package uk.debb.vanilla_disable.mixin.knockback;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {
    private MixinPlayerEntity() {
        super(null, null);
    }

    /*
     * @author Wesley1808
     * @author DragonEggBedrockBreaking
     * @param entity The entity that is attacking
     * @param target The entity that is being attacked
     * @return 0 if should cancel, else the level of getKnockback
     */
    @Redirect(
        method = "attack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/enchantment/EnchantmentHelper;getKnockback(Lnet/minecraft/entity/LivingEntity;)I"
        )
    )
    public int cancelKnockbackFromPlayerAttack(LivingEntity entity, Entity target) {
        if (target instanceof ServerPlayerEntity &&
            (!this.world.getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENCHANTMENT) ||
             !this.world.getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED))) {
            return 0;
        }
        return EnchantmentHelper.getKnockback(entity);
    }
}
