package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(EnderPearlEntity.class)
public abstract class MixinEnderPearlEntity extends ThrownItemEntity {
    public MixinEnderPearlEntity(EntityType<? extends EnderPearlEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason don't delete ender pearls on player death
     * @param ci
     */
    @Inject(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/projectile/thrown/EnderPearlEntity;discard()V"
        ),
        cancellable = true
    )
    private void cancelDiscard(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.ENDER_PEARLS_DESPAWN_ON_DEATH)) {
            ci.cancel();
        }
    }
}
