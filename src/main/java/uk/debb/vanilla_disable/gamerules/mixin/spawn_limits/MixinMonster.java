package uk.debb.vanilla_disable.gamerules.mixin.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.gamerules.Gamerules;

@Mixin(Monster.class)
public abstract class MixinMonster {
    @ModifyReturnValue(method = "isDarkEnoughToSpawn", at = @At("RETURN"))
    private static boolean spawnIsDarkEnough(boolean original, ServerLevelAccessor world, BlockPos pos, RandomSource randomSource) {
        if (world.getBrightness(LightLayer.SKY, pos) > randomSource.nextInt(32)) {
            return false;
        }
        if (world.getBrightness(LightLayer.BLOCK, pos) > Gamerules.MONSTER_MAX_LIGHT_LEVEL.getInt()) {
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