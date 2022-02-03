package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow public World world;
    @Shadow BlockPos blockPos;
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    /**
     * @author DragonEggBedrockBreaking
     * @reason chnge nether portal cooldown for entities
     * @param cir the returnable callback info
     */
    @Inject(method = "getDefaultNetherPortalCooldown", at = @At("HEAD"), cancellable = true)
    private void modifyDefaultNetherPortalCooldown(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.NETHER_PORTAL_COOLDOWN));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason kills boats when they hit blocks
     * @param state the state of the block below the entity
     * @param ci the callback info
     */
    @Inject(method = "onBlockCollision", at = @At("HEAD"), cancellable = true)
    private void killOnHorizontalCollision(BlockState state, CallbackInfo ci) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.OLD_BOATS) &&
            (Object) this instanceof BoatEntity) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockState blockState = world.getBlockState(this.blockPos.offset(direction));
                if (blockState.getMaterial().isSolid()) {
                    this.damage(DamageSource.GENERIC, Float.MAX_VALUE);
                    ci.cancel();;
                }
            }
        }
    }
}
