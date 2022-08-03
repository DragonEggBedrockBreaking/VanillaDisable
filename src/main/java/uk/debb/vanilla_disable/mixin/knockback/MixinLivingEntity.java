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
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements Maps {
    @Shadow
    private LivingEntity lastHurtByMob;

    @Unique
    private boolean isInvulnerableToKnockback(LivingEntity source) {
        Gamerules gameRule = livingEntityClassMapKnockback.get(this.getClass());
        if ((!Gamerules.KNOCKBACK_ENABLED.getBool()) ||
                (gameRule != null && !gameRule.getBool())) {
            return true;
        }
        if ((source instanceof AbstractSkeleton && !(source instanceof WitherSkeleton)) ||
                (source instanceof Piglin && source.isHolding(Items.CROSSBOW)) ||
                (source instanceof Pillager)) {
            return !Gamerules.ARROW_KNOCKBACK.getBool();
        }
        if (source instanceof Drowned && source.isHolding(Items.TRIDENT)) {
            return !Gamerules.TRIDENT_KNOCKBACK.getBool();
        }
        if (source != null) {
            return !Gamerules.MOB_ATTACK_KNOCKBACK.getBool();
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