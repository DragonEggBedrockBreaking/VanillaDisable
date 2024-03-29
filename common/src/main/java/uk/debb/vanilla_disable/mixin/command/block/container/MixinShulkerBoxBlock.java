package uk.debb.vanilla_disable.mixin.command.block.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ShulkerBoxBlock.class)
public abstract class MixinShulkerBoxBlock {
    @ModifyReturnValue(method = "canOpen", at = @At("RETURN"))
    private static boolean vanillaDisable$canOpen(boolean original, BlockState state, Level level, BlockPos pos, ShulkerBoxBlockEntity blockEntity) {
        String name = CommandDataHandler.getKeyFromBlockRegistry(state.getBlock());
        return original || !CommandDataHandler.getCachedBoolean("blocks", name, "opening_blockable");
    }
}
