package uk.debb.vanilla_disable.mixin.misc.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(value = EnderChestBlock.class, priority = 1001)
public abstract class MixinEnderChestBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @param blockState the state of the block above the echest
     * @param blockGetter the getter of the block above the echest
     * @param blockPos the position of the block above the echest
     * @param blockState2 the state of the block above the echest
     * @param level the level the echest is in
     * @param blockPos2 the position of the block above the echest
     * @param player the player trying to open the echest
     * @param interactionHand the hand the player is trying to open the echest with
     * @param blockHitResult the result of the player trying to open the echest
     * @return whether the player can open the echest
     */
    @Redirect(
        method = "use",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;isRedstoneConductor(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z"
        ),
        require = 0
    )
    private boolean isFullBlock(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState2, Level level, BlockPos blockPos2, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (VDServer.getServer() == null) return blockState.isRedstoneConductor(blockGetter, blockPos);
        if (!GameruleHelper.getBool(Gamerules.CONTAINER_OPENING_BLOCKED)) {
            return false;
        }
        return blockState.isRedstoneConductor(blockGetter, blockPos);
    }
}
