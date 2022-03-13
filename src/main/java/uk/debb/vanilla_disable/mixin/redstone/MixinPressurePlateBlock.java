package uk.debb.vanilla_disable.mixin.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PressurePlateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PressurePlateBlock.class)
public abstract class MixinPressurePlateBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @reason edit the signal strength outputted by the redstone component
     * @param level the level
     * @param blockPos the position of the block
     * @param cir the returnable callback info
     */
    @Inject(method = "getSignalStrength", at = @At("HEAD"), cancellable = true)
    private void modifySignalStrength(Level level, BlockPos blockPos, CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PRESSURE_PLATE_ENABLED)) {
            cir.setReturnValue(0);
        }
    }
}
