package uk.debb.vanilla_disable.command.mixin.rule.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow public abstract Block getBlock();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void modifyUse(CallbackInfoReturnable<InteractionResult> cir) {
        String block = DataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!DataHandler.getBoolean("blocks", block, "can_interact")) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @ModifyReturnValue(method = "getMenuProvider", at = @At("RETURN"))
    private MenuProvider modifyMenuProvider(MenuProvider original) {
        String block = DataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!DataHandler.getBoolean("blocks", block, "can_interact")) {
            return null;
        }
        return original;
    }

    @ModifyReturnValue(method = "ignitedByLava", at = @At("RETURN"))
    private boolean ignitedByLava(boolean original) {
        if (DataHandler.isConnectionNull()) return original;
        String block = DataHandler.getKeyFromBlockRegistry(this.getBlock());
        return DataHandler.getBoolean("blocks", block, "ignited_by_lava");
    }

    @ModifyReturnValue(method = "getDestroySpeed", at = @At("RETURN"))
    private float getDestroySpeed(float original) {
        if (DataHandler.isConnectionNull()) return original;
        String block = DataHandler.getKeyFromBlockRegistry(this.getBlock());
        return (float) DataHandler.getDouble("blocks", block, "destroy_speed");
    }

    @ModifyReturnValue(method = "requiresCorrectToolForDrops", at = @At("RETURN"))
    private boolean requiresCorrectToolForDrops(boolean original) {
        if (DataHandler.isConnectionNull()) return original;
        String block = DataHandler.getKeyFromBlockRegistry(this.getBlock());
        return DataHandler.getBoolean("blocks", block, "requires_correct_tool_for_drops");
    }
}
