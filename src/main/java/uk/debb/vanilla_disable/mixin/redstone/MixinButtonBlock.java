package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.world.level.block.ButtonBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

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
    private int editButtonPressDuration(int original) {
        if (original == 20) {
            return Gamerules.STONE_BUTTON_PRESS_DURATION.getInt();
        }
        return Gamerules.WOOD_BUTTON_PRESS_DURATION.getInt();
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
        if (original == 20) {
            return Gamerules.STONE_BUTTON_PRESS_DURATION.getInt();
        }
        return Gamerules.WOOD_BUTTON_PRESS_DURATION.getInt();
    }
}
