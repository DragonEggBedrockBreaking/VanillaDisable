package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(WitherEntity.class)
public abstract class MixinWitherEntity extends HostileEntity {
    public MixinWitherEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason immediately despawns withers if they are not allowed
     * @param ci the callback info
     */
    @Inject(method = "checkDespawn", at = @At(value = "HEAD"), cancellable = true)
    private void forceDespawn(CallbackInfo ci) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.WITHER_SPAWNS)) {
            this.discard();
            ci.cancel();
        }
    }
}
