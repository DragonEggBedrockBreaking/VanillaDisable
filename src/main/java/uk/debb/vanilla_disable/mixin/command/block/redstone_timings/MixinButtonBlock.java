package uk.debb.vanilla_disable.mixin.command.block.redstone_timings;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(value = ButtonBlock.class, priority = 999)
public abstract class MixinButtonBlock {
    @ModifyArg(
            method = "press",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int vanillaDisable$scheduleTick1(int original) {
        String name = CommandDataHandler.getKeyFromBlockRegistry((Block) (Object) this);
        return CommandDataHandler.getCachedInt("blocks", name, "redstone_duration");
    }

    @ModifyArg(
            method = "checkPressed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int vanillaDisable$scheduleTick2(int original) {
        String name = CommandDataHandler.getKeyFromBlockRegistry((Block) (Object) this);
        return CommandDataHandler.getCachedInt("blocks", name, "redstone_duration");
    }
}
