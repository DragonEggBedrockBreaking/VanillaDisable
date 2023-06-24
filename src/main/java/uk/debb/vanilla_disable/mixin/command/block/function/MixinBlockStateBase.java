package uk.debb.vanilla_disable.mixin.command.block.function;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void entityInside(CallbackInfo ci) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "works")) {
            ci.cancel();
        }
    }

    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int getSignal(int original) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "works")) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int getDirectSignal(int original) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "works")) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getAnalogOutputSignal", at = @At("RETURN"))
    private int getAnalogOutputSignal(int original) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(this.getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "works")) {
            return 0;
        }
        return original;
    }
}
