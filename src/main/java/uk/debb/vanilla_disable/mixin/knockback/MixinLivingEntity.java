package uk.debb.vanilla_disable.mixin.knockback;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements Maps {
    @Shadow
    private LivingEntity lastHurtByMob;

    @Unique
    private boolean isInvulnerableToKnockback(LivingEntity source) {
        BooleanGamerules gameRule = livingEntityClassMapKnockback.get(this.getClass());
        if ((!BooleanGamerules.KNOCKBACK_ENABLED.getValue()) ||
                (gameRule != null && !gameRule.getValue())) {
            return true;
        }
        if ((source instanceof AbstractSkeleton && !(source instanceof WitherSkeleton)) ||
                (source instanceof Piglin && source.isHolding(Items.CROSSBOW)) ||
                (source instanceof Pillager)) {
            return !BooleanGamerules.ARROW_KNOCKBACK.getValue();
        }
        if (source instanceof Drowned && source.isHolding(Items.TRIDENT)) {
            return !BooleanGamerules.TRIDENT_KNOCKBACK.getValue();
        }
        if (source != null) {
            return !BooleanGamerules.MOB_ATTACK_KNOCKBACK.getValue();
        }
        return false;
    }

    @Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
    public void cancelKnockback(CallbackInfo ci) {
        if (((Entity) (Object) this).getType().equals(EntityType.PLAYER) && isInvulnerableToKnockback(this.lastHurtByMob)) {
            ci.cancel();
        }
    }
}