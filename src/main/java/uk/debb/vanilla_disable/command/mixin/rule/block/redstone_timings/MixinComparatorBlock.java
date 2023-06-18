package uk.debb.vanilla_disable.command.mixin.rule.block.redstone_timings;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ComparatorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    @ModifyArg(
            method = "checkTickOnNeighbor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;ILnet/minecraft/world/ticks/TickPriority;)V"
            )
    )
    private int modifyComparatorDelay1(int delay) {
        return DataHandler.getCachedInt("blocks", "minecraft:comparator", "redstone_delay");
    }
}
