package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(FarmBlock.class)
public abstract class MixinFarmBlock {
    /**
     * @param state        the state of the block
     * @param level        the level
     * @param pos          the position of the block
     * @param theWorld     the world
     * @param blockState   the state of the block
     * @param blockPos     the position of the block
     * @param entity       the entity falling on the block
     * @param fallDistance how far the entity fell on the block
     * @author DragonEggBedrockBreaking
     */
    @WrapWithCondition(
            method = "fallOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/FarmBlock;turnToDirt(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
            ),
            require = 0
    )
    private boolean cancelTurningToDirt(BlockState state, Level level, BlockPos pos, Level theWorld, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
        if (VDServer.getServer() == null) return true;
        return GameruleHelper.getBool(Gamerules.CROP_TRAMPLING);
    }
}