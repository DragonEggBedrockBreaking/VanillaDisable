package uk.debb.vanilla_disable.mixin.spawn_limits;

import java.util.Random;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(HostileEntity.class)
public abstract class MixinHostileEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @param world the access to ServerWorld
     * @param pos the position to spawn at
     * @param random the random number generator
     * @param cir cancellable returnable info
     */
    @Inject(method = "isSpawnDark", at = @At("HEAD"), cancellable = true)
    private static void spawnIsDark(ServerWorldAccess world, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (world.getLightLevel(LightType.SKY, pos) > random.nextInt(32)) {
            cir.setReturnValue(false);
        }
        if (world.getLightLevel(LightType.BLOCK, pos) > world.toServerWorld().getGameRules().getInt(RegisterGamerules.MONSTER_MAX_LIGHT_LEVEL)) {
            cir.setReturnValue(false);
        } else {
            if (!world.toServerWorld().isThundering()) {
                cir.setReturnValue(true);
            } else { // return vanilla value, crashes if I try to modify
                cir.setReturnValue(world.getLightLevel(pos, 10) <= random.nextInt(8));
            }
        }
    }
}