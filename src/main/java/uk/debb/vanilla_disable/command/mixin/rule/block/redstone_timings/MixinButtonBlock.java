package uk.debb.vanilla_disable.command.mixin.rule.block.redstone_timings;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ButtonBlock.class)
public abstract class MixinButtonBlock {
    @ModifyArg(
            method = "press",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int editButtonPressDuration(int original) {
        String name = DataHandler.getKeyFromBlockRegistry((Block) (Object) this);
        return DataHandler.getCachedInt("blocks", name, "redstone_duration");
    }

    @ModifyArg(
            method = "checkPressed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int modifyButtonPressDuration(int original) {
        String name = DataHandler.getKeyFromBlockRegistry((Block) (Object) this);
        return DataHandler.getCachedInt("blocks", name, "redstone_duration");
    }
}
