package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(HuskEntity.class)
public abstract class MixinHuskEntity extends ZombieEntity {
    public MixinHuskEntity(EntityType<? extends HuskEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop husks from converting into zombies
     * @param ci the callback info
     */
    @Inject(method = "convertInWater", at = @At("HEAD"), cancellable = true)
    private void cancelConversionInWater(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.HUSKS_CONVERT_TO_ZOMBIES)) {
            ci.cancel();
        }
    }
}
