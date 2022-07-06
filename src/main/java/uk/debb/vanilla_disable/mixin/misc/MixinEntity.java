package uk.debb.vanilla_disable.mixin.misc;

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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

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

    /**
     * @param state the state of the block below the entity
     * @param ci    the callback info
     * @author DragonEggBedrockBreaking
     * @reason kills boats when they hit blocks
     */
    @Inject(method = "onInsideBlock", at = @At("HEAD"))
    private void killOnHorizontalCollision(BlockState state, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.OLD_BOATS) && this.getType() == EntityType.BOAT) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockState = this.level.getBlockState(this.blockPosition.relative(direction));
                if (blockState.getMaterial().isSolid()) {
                    if (!this.hurt(DamageSource.GENERIC, Float.MAX_VALUE)) return;
                }
            }
        }
    }
}