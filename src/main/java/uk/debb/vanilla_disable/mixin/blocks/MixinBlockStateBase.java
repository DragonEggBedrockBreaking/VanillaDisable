package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(BlockStateBase.class)
public abstract class MixinBlockStateBase implements Maps {
    @Shadow
    public abstract Block getBlock();

    @ModifyReturnValue(method = "use", at = @At("RETURN"))
    private InteractionResult modifyUse(InteractionResult original) {
        Gamerules gameRule = blockStateBaseBlockMap.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            return InteractionResult.FAIL;
        }
        return original;
    }

    @ModifyReturnValue(method = "getMenuProvider", at = @At("RETURN"))
    private MenuProvider modifyMenuProvider(MenuProvider original) {
        Gamerules gameRule = blockStateBaseBlockMap.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            return null;
        }
        return original;
    }
}
