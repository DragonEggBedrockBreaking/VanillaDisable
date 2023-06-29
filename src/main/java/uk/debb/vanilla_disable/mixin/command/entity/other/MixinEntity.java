package uk.debb.vanilla_disable.mixin.command.entity.other;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    private BlockPos blockPosition;

    @Shadow
    public abstract boolean hurt(DamageSource source, float amount);

    @Shadow
    public abstract Level level();

    @SuppressWarnings("deprecation")
    @Inject(method = "onInsideBlock", at = @At("HEAD"))
    private void onInsideBlock(CallbackInfo ci) {
        if (((Entity) (Object) this) instanceof Boat boat && CommandDataHandler.getCachedBoolean("entities", "minecraft:boat", "alpha_behaviour")) {
            if (!boat.checkInWater()) {
                this.hurt(this.level().damageSources().generic(), Float.MAX_VALUE);
            } else {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockState otherBlockState = this.level().getBlockState(this.blockPosition.relative(direction));
                    if (otherBlockState.isSolid()) {
                        this.hurt(this.level().damageSources().generic(), Float.MAX_VALUE);
                    }
                }
            }
        }
    }
}
