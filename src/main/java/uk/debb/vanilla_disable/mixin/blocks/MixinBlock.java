package uk.debb.vanilla_disable.mixin.blocks;

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
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.DoubleGamerules;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    public abstract BlockState defaultBlockState();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float modifyFriction(float original) {
        BlockState blockState = this.defaultBlockState();
        if (blockState.is(Blocks.ICE) || blockState.is(Blocks.BLUE_ICE) ||
                blockState.is(Blocks.FROSTED_ICE) || blockState.is(Blocks.PACKED_ICE)) {
            return (float) GameruleHelper.getDouble(DoubleGamerules.ICE_FRICTION);
        } else if (blockState.is(Blocks.SLIME_BLOCK)) {
            return (float) GameruleHelper.getDouble(DoubleGamerules.SLIME_FRICTION);
        } else {
            return (float) GameruleHelper.getDouble(DoubleGamerules.DEFAULT_BLOCK_FRICTION);
        }
    }

    @ModifyReturnValue(method = "getSpeedFactor", at = @At("RETURN"))
    private float modifySpeedFactor(float original) {
        BlockState blockState = this.defaultBlockState();
        if (blockState.is(Blocks.SOUL_SAND)) {
            return (float) GameruleHelper.getDouble(DoubleGamerules.SOUL_SAND_SPEED);
        } else if (blockState.is(Blocks.HONEY_BLOCK)) {
            return (float) GameruleHelper.getDouble(DoubleGamerules.HONEY_BLOCK_SPEED);
        } else {
            return (float) GameruleHelper.getDouble(DoubleGamerules.DEFAULT_BLOCK_SPEED);
        }
    }

    @ModifyReturnValue(method = "getJumpFactor", at = @At("RETURN"))
    private float modifyJumpFactor(float original) {
        BlockState blockState = this.defaultBlockState();
        if (blockState.is(Blocks.HONEY_BLOCK)) {
            return (float) GameruleHelper.getDouble(DoubleGamerules.HONEY_BLOCK_JUMP);
        } else {
            return (float) GameruleHelper.getDouble(DoubleGamerules.DEFAULT_BLOCK_JUMP);
        }
    }

    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void cancelDestruction(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, ItemStack itemStack, CallbackInfo ci) {
        if (GameruleHelper.getBool(BooleanGamerules.OLD_TNT) && blockState.is(Blocks.TNT)) {
            TntBlock.explode(level, blockPos);
            ci.cancel();
        }
    }
}