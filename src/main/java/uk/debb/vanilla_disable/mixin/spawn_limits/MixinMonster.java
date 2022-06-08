package uk.debb.vanilla_disable.mixin.spawn_limits;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Monster.class)
public abstract class MixinMonster {
    /**
     * @author DragonEggBedrockBreaking
     * @param world the access to ServerWorld
     * @param pos the position to spawn at
     * @param randomSource the random number generator
     * @param cir cancellable returnable info
     */
    @Inject(method = "isDarkEnoughToSpawn", at = @At("HEAD"), cancellable = true)
    private static void spawnIsDarkEnough(ServerLevelAccessor world, BlockPos pos, RandomSource randomSource, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (world.getBrightness(LightLayer.SKY, pos) > randomSource.nextInt(32)) {
            cir.setReturnValue(false);
        }
        if (world.getBrightness(LightLayer.BLOCK, pos) > GameruleHelper.getInt(Gamerules.MONSTER_MAX_LIGHT_LEVEL)) {
            cir.setReturnValue(false);
        } else {
            if (!world.getLevel().isThundering()) {
                cir.setReturnValue(true);
            } else { // return vanilla value, crashes if I try to modify
                cir.setReturnValue(world.getMaxLocalRawBrightness(pos, 10) <= randomSource.nextInt(8));
            }
        }
    }
}