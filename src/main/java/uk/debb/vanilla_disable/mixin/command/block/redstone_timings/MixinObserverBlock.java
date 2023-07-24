package uk.debb.vanilla_disable.mixin.command.block.redstone_timings;

import net.minecraft.world.level.block.ObserverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ObserverBlock.class)
public abstract class MixinObserverBlock {
    @ModifyArg(
            method = "startSignal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelAccessor;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            ),
            index = 2
    )
    private int vanillaDisable$scheduleTick1(int delay) {
        return CommandDataHandler.getCachedInt("blocks", "minecraft:observer", "redstone_delay");
    }

    @ModifyArg(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;scheduleTick(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;I)V"
            )
    )
    private int vanillaDisable$scheduleTick2(int duration) {
        return CommandDataHandler.getCachedInt("blocks", "minecraft:observer", "redstone_duration");
    }
}
