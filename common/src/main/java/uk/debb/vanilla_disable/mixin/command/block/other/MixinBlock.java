package uk.debb.vanilla_disable.mixin.command.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    protected abstract Block asBlock();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float vanillaDisable$getFriction(float original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.asBlock());
        return (float) CommandDataHandler.getCachedDouble("blocks", block, "friction_factor");
    }

    @ModifyReturnValue(method = "getSpeedFactor", at = @At("RETURN"))
    private float vanillaDisable$getSpeedFactor(float original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.asBlock());
        return (float) CommandDataHandler.getCachedDouble("blocks", block, "speed_factor");
    }

    @ModifyReturnValue(method = "getJumpFactor", at = @At("RETURN"))
    private float vanillaDisable$getJumpFactor(float original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.asBlock());
        return (float) CommandDataHandler.getCachedDouble("blocks", block, "jump_factor");
    }


    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if (state.is(Blocks.TNT) && CommandDataHandler.getCachedBoolean("blocks", "minecraft:tnt", "alpha_behaviour")) {
            TntBlock.explode(level, pos);
            ci.cancel();
        }
    }
}
