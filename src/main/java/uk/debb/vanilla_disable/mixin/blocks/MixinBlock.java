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

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    public abstract float getFriction();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float cancelFriction(float original) {
        if (GameruleHelper.getServer() == null) return original;
        if (!GameruleHelper.getBool(BooleanGamerules.ICE_SLIDING) && this.getFriction() == 0.98F) {
            return Blocks.STONE.getFriction();
        }
        return original;
    }

    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void cancelDestruction(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, ItemStack itemStack, CallbackInfo ci) {
        if (GameruleHelper.getBool(BooleanGamerules.OLD_TNT) && blockState.is(Blocks.TNT)) {
            TntBlock.explode(level, blockPos);
            ci.cancel();
        }
    }
}