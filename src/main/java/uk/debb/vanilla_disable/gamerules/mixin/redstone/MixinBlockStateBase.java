package uk.debb.vanilla_disable.gamerules.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(BlockStateBase.class)
public abstract class MixinBlockStateBase implements Maps {
    @Shadow
    public abstract Block getBlock();

    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        Gamerules gameRule = blockStateBaseRedstoneBlocksMap.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        Gamerules gameRule = blockStateBaseRedstoneBlocksMap.get(this.getBlock());
        if (gameRule != null && !gameRule.getBool()) {
            return 0;
        }
        return original;
    }
}
