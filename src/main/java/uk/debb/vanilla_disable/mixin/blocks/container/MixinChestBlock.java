package uk.debb.vanilla_disable.mixin.blocks.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ChestBlock.class)
public abstract class MixinChestBlock {
    /**
     * @param levelAccessor the accessor for the level
     * @param blockPos      the position of the chest
     * @param cir           the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason prevent containers from being blocked
     */
    @Inject(method = "isChestBlockedAt", at = @At("HEAD"), cancellable = true)
    private static void chestNotBlocked(LevelAccessor levelAccessor, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.CONTAINER_OPENING_BLOCKED)) {
            cir.setReturnValue(false);
        }
    }
}