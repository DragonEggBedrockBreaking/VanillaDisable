package uk.debb.vanilla_disable.mixin.misc;

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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow public Level level;
    @Shadow private BlockPos blockPosition;
    @Shadow public abstract boolean hurt(DamageSource source, float amount);

    /**
     * @author DragonEggBedrockBreaking
     * @reason change nether portal cooldown for entities
     * @param cir the returnable callback info (Integer)
     */
    @Inject(method = "getDimensionChangingDelay", at = @At("HEAD"), cancellable = true)
    private void modifyDimensionChangingDelay(CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        cir.setReturnValue(GameruleHelper.getInt(Gamerules.NETHER_PORTAL_COOLDOWN));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason kills boats when they hit blocks
     * @param state the state of the block below the entity
     * @param ci the callback info
     */
    @Inject(method = "onInsideBlock", at = @At("HEAD"), cancellable = true)
    private void killOnHorizontalCollision(BlockState state, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.OLD_BOATS) && (Object) this instanceof Boat) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockState = this.level.getBlockState(this.blockPosition.relative(direction));
                if (blockState.getMaterial().isSolid()) {
                    if (!this.hurt(DamageSource.GENERIC, Float.MAX_VALUE)) return;
                    ci.cancel();
                }
            }
        }
    }
}
