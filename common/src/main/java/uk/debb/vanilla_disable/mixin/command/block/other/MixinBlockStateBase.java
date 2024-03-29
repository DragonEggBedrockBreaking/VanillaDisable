package uk.debb.vanilla_disable.mixin.command.block.other;

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
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$use(CallbackInfoReturnable<InteractionResult> cir) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_interact")) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @ModifyReturnValue(method = "getMenuProvider", at = @At("RETURN"))
    private MenuProvider vanillaDisable$modifyMenuProvider(MenuProvider original) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_interact")) {
            return null;
        }
        return original;
    }

    @ModifyReturnValue(method = "ignitedByLava", at = @At("RETURN"))
    private boolean vanillaDisable$ignitedByLava(boolean original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        return CommandDataHandler.getCachedBoolean("blocks", block, "ignited_by_lava");
    }

    @ModifyReturnValue(method = "getDestroySpeed", at = @At("RETURN"))
    private float vanillaDisable$getDestroySpeed(float original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        return (float) CommandDataHandler.getCachedDouble("blocks", block, "destroy_speed");
    }

    @ModifyReturnValue(method = "requiresCorrectToolForDrops", at = @At("RETURN"))
    private boolean vanillaDisable$requiresCorrectToolForDrops(boolean original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        return CommandDataHandler.getCachedBoolean("blocks", block, "requires_correct_tool_for_drops");
    }
}
