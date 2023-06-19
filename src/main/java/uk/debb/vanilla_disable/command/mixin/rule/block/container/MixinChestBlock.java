package uk.debb.vanilla_disable.command.mixin.rule.block.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(ChestBlock.class)
public abstract class MixinChestBlock {
    @ModifyReturnValue(method = "isChestBlockedAt", at = @At("RETURN"))
    private static boolean isChestBlockedAt(boolean original, LevelAccessor levelAccessor, BlockPos blockPos) {
        String name = CommandDataHandler.getKeyFromBlockRegistry(levelAccessor.getBlockState(blockPos).getBlock());
        return original && CommandDataHandler.getCachedBoolean("blocks", name, "opening_blockable");
    }
}
