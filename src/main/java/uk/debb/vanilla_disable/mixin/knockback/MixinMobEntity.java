package uk.debb.vanilla_disable.mixin.knockback;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MobEntity.class)
public abstract class MixinMobEntity extends LivingEntity {
    protected MixinMobEntity(EntityType<? extends MobEntity> entityType, World world) {
        super((EntityType<? extends LivingEntity>)entityType, world);
    }
    /**
     * @author Wesley1808
     * @author DragonEggBedrockBreaking
     * @reason Prevent knockback enchantment from being applied to players based on gamerule
     * @param entity The entity that is attacking
     * @param target The entity that is being attacked
     * @return 0 if should cancel, else the level of getKnockback
     */
    @Redirect(
        method = "tryAttack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/enchantment/EnchantmentHelper;getKnockback(Lnet/minecraft/entity/LivingEntity;)I"
        )
    )
    public int cancelKnockbackFromAttack(LivingEntity entity, Entity target) {
        if (target instanceof ServerPlayerEntity &&
            !this.world.getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENCHANTMENT)) {
            return 0;
        }
        return EnchantmentHelper.getKnockback(entity);
    }
}
