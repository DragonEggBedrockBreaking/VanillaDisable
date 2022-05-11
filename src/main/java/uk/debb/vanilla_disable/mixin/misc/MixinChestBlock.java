package uk.debb.vanilla_disable.mixin.misc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ChestBlock;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ChestBlock.class)
public abstract class MixinChestBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason prevent containers from being blocked
     * @param levelAccessor the accessor for the level
     * @param blockPos the position of the chest
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isChestBlockedAt", at = @At("HEAD"), cancellable = true)
    private static void chestNotBlocked(LevelAccessor levelAccessor, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CONTAINER_OPENING_BLOCKED)) {
            cir.setReturnValue(false);
        }
    }
}
