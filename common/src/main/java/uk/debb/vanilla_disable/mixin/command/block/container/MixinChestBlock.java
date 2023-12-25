package uk.debb.vanilla_disable.mixin.command.block.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ChestBlock.class)
public abstract class MixinChestBlock {
    @ModifyReturnValue(method = "isChestBlockedAt", at = @At("RETURN"))
    private static boolean vanillaDisable$isChestBlockedAt(boolean original, LevelAccessor level, BlockPos pos) {
        String name = CommandDataHandler.getKeyFromBlockRegistry(level.getBlockState(pos).getBlock());
        return original && CommandDataHandler.getCachedBoolean("blocks", name, "opening_blockable");
    }
}
