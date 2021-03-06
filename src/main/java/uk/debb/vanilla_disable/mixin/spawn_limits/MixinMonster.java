package uk.debb.vanilla_disable.mixin.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Monster.class)
public abstract class MixinMonster {
    /**
     * @param original     the original value
     * @param world        the access to ServerWorld
     * @param pos          the position to spawn at
     * @param randomSource the random number generator
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isDarkEnoughToSpawn", at = @At("RETURN"))
    private static boolean spawnIsDarkEnough(boolean original, ServerLevelAccessor world, BlockPos pos, RandomSource randomSource) {
        if (VDServer.getServer() == null) return original;
        if (world.getBrightness(LightLayer.SKY, pos) > randomSource.nextInt(32)) {
            return false;
        }
        if (world.getBrightness(LightLayer.BLOCK, pos) > GameruleHelper.getInt(Gamerules.MONSTER_MAX_LIGHT_LEVEL)) {
            return false;
        } else {
            if (!world.getLevel().isThundering()) {
                return true;
            } else { // return vanilla value, crashes if I try to modify
                return world.getMaxLocalRawBrightness(pos, 10) <= randomSource.nextInt(8);
            }
        }
    }
}