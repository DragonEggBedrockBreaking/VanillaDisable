package uk.debb.vanilla_disable.command.mixin.rule.block.other;

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
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow protected abstract Block asBlock();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float getFriction(float original) {
        String block = DataHandler.getKeyFromBlockRegistry(this.asBlock());
        return (float) DataHandler.getDouble("blocks", block, "friction_factor");
    }

    @ModifyReturnValue(method = "getSpeedFactor", at = @At("RETURN"))
    private float getSpeedFactor(float original) {
        String block = DataHandler.getKeyFromBlockRegistry(this.asBlock());
        return (float) DataHandler.getDouble("blocks", block, "speed_factor");
    }

    @ModifyReturnValue(method = "getJumpFactor", at = @At("RETURN"))
    private float getJumpFactor(float original) {
        String block = DataHandler.getKeyFromBlockRegistry(this.asBlock());
        return (float) DataHandler.getDouble("blocks", block, "jump_factor");
    }


    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, ItemStack itemStack, CallbackInfo ci) {
        if (blockState.is(Blocks.TNT) && DataHandler.getBoolean("blocks", "minecraft:tnt", "alpha_behaviour")) {
            TntBlock.explode(level, blockPos);
            ci.cancel();
        }
    }
}
