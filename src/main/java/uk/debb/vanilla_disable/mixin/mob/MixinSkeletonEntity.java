package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SkeletonEntity.class)
public abstract class MixinSkeletonEntity extends AbstractSkeletonEntity {
    public MixinSkeletonEntity(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop skeletons from converting into strays
     * @param ci the callback info
     */
    @Inject(method = "convertToStray", at = @At("HEAD"), cancellable = true)
    private void cancelConversionToStray(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.SKELETONS_CONVERT_TO_STRAYS)) {
            ci.cancel();
        }
    }
}
