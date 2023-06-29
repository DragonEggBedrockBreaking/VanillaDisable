package uk.debb.vanilla_disable.mixin.command.block.piston;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PistonBaseBlock.class)
public abstract class MixinPistonBaseBlock {
    @ModifyExpressionValue(
            method = "isPushable",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            )
    )
    private static boolean is(boolean original, BlockState state, Level level, BlockPos pos, Direction movementDirection, boolean allowDestroy, Direction pistonFacing) {
        Block block = state.getBlock();
        return original && !(block.equals(Blocks.OBSIDIAN) || block.equals(Blocks.CRYING_OBSIDIAN) ||
                block.equals(Blocks.RESPAWN_ANCHOR) || block.equals(Blocks.REINFORCED_DEEPSLATE));
    }
}
