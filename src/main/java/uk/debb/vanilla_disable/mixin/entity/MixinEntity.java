package uk.debb.vanilla_disable.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public Level level;
    @Shadow
    private BlockPos blockPosition;

    @Shadow
    public abstract boolean hurt(DamageSource source, float amount);

    @Shadow
    public abstract EntityType<?> getType();

    @Inject(method = "onInsideBlock", at = @At("HEAD"))
    private void killOnHorizontalCollision(CallbackInfo ci) {
        if (Gamerules.OLD_BOATS.getBool() && this.getType().equals(EntityType.BOAT)) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockState = this.level.getBlockState(this.blockPosition.relative(direction));
                if (blockState.getMaterial().isSolid()) {
                    if (!this.hurt(level.damageSources().generic(), Float.MAX_VALUE)) return;
                }
            }
        }
    }
}