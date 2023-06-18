package uk.debb.vanilla_disable.command.mixin.rule.block.redstone_timings;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(RepeaterBlock.class)
public abstract class MixinRepeaterBlock {
    @Shadow @Final public static IntegerProperty DELAY;

    @ModifyReturnValue(method = "getDelay", at = @At("RETURN"))
    private int getDelay(int original, BlockState blockState) {
        return blockState.getValue(DELAY) * DataHandler.getCachedInt("blocks", "minecraft:repeater", "redstone_delay");
    }
}
