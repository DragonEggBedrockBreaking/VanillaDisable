package uk.debb.vanilla_disable.mixin.command.block.redstone_timings;

import net.minecraft.world.level.block.ComparatorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ComparatorBlock.class)
public abstract class MixinComparatorBlock {
    @ModifyArg(
            method = "checkTickOnNeighbor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;ILnet/minecraft/world/ticks/TickPriority;)V"
            )
    )
    private int scheduleTick(int delay) {
        return CommandDataHandler.getCachedInt("blocks", "minecraft:comparator", "redstone_delay");
    }
}
