package uk.debb.vanilla_disable.gamerules.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(BlockStateBase.class)
public abstract class MixinBlockStateBase implements Maps {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void modifyUse(CallbackInfoReturnable<InteractionResult> cir) {
        Gamerules gameRule = blockStateBaseBlockMap.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @ModifyReturnValue(method = "getMenuProvider", at = @At("RETURN"))
    private MenuProvider modifyMenuProvider(MenuProvider original) {
        Gamerules gameRule = blockStateBaseBlockMap.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            return null;
        }
        return original;
    }

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void modifyEntityInsideBehaviour(CallbackInfo ci) {
        Gamerules gameRule = blockStateBaseBlockMapPortals.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            ci.cancel();
        }
    }
}
