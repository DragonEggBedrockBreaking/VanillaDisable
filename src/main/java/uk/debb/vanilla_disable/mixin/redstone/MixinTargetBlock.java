package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.TargetBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(TargetBlock.class)
public abstract class MixinTargetBlock {
    @ModifyReturnValue(method = "getRedstoneStrength", at = @At("RETURN"))
    private static int modifyRedstoneStrength(int original) {
        if (!Gamerules.TARGET_BLOCK_ENABLED.getValue(Boolean::parseBoolean)) {
            return 0;
        }
        return original;
    }
}