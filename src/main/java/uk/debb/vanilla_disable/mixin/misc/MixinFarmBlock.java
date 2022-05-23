package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(FarmBlock.class)
public abstract class MixinFarmBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason disable crop trampling
     * @param state the state of the block
     * @param world the world
     * @param pos the position of the block
     * @param theWorld the world
     * @param blockState the state of the block
     * @param blockPos the position of the block
     * @param entity the entity falling on the block
     * @param fallDistance how far the entity fell on the block
     */
    @Redirect(
        method = "fallOn",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/FarmBlock;turnToDirt(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
        )
    )
    private void cancelTurningToDirt(BlockState state, Level level, BlockPos pos, Level theWorld, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
        if (VDServer.getServer() == null) return;
        if (VDServer.getServer().getGameRules().getBoolean(Gamerules.CROP_TRAMPLING)) {
            level.setBlockAndUpdate(pos, FarmBlock.pushEntitiesUp(state, Blocks.DIRT.defaultBlockState(), level, pos));
        }
    }
}
