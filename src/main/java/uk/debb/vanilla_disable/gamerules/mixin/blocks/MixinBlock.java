package uk.debb.vanilla_disable.gamerules.mixin.blocks;

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
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    public abstract BlockState defaultBlockState();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float modifyFriction(float original) {
        BlockState blockState = this.defaultBlockState();
        if (blockState.is(Blocks.ICE) || blockState.is(Blocks.BLUE_ICE) ||
                blockState.is(Blocks.FROSTED_ICE) || blockState.is(Blocks.PACKED_ICE)) {
            return Gamerules.ICE_FRICTION_FACTOR.getFloat();
        } else if (blockState.is(Blocks.SLIME_BLOCK)) {
            return Gamerules.SLIME_FRICTION_FACTOR.getFloat();
        } else {
            return Gamerules.DEFAULT_BLOCK_FRICTION_FACTOR.getFloat();
        }
    }

    @ModifyReturnValue(method = "getSpeedFactor", at = @At("RETURN"))
    private float modifySpeedFactor(float original) {
        BlockState blockState = this.defaultBlockState();
        if (blockState.is(Blocks.SOUL_SAND)) {
            return Gamerules.SOUL_SAND_SPEED_FACTOR.getFloat();
        } else if (blockState.is(Blocks.HONEY_BLOCK)) {
            return Gamerules.HONEY_BLOCK_SPEED_FACTOR.getFloat();
        } else {
            return Gamerules.DEFAULT_BLOCK_SPEED_FACTOR.getFloat();
        }
    }

    @ModifyReturnValue(method = "getJumpFactor", at = @At("RETURN"))
    private float modifyJumpFactor(float original) {
        BlockState blockState = this.defaultBlockState();
        if (blockState.is(Blocks.HONEY_BLOCK)) {
            return Gamerules.HONEY_BLOCK_JUMP_FACTOR.getFloat();
        } else {
            return Gamerules.DEFAULT_BLOCK_JUMP_FACTOR.getFloat();
        }
    }

    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void cancelDestruction(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, ItemStack itemStack, CallbackInfo ci) {
        if (Gamerules.OLD_TNT.getBool() && blockState.is(Blocks.TNT)) {
            TntBlock.explode(level, blockPos);
            ci.cancel();
        }
    }
}