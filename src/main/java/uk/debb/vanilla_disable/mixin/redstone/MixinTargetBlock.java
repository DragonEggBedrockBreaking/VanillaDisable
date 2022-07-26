package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.TargetBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(TargetBlock.class)
public abstract class MixinTargetBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getRedstoneStrength", at = @At("RETURN"))
    private static int modifyRedstoneStrength(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.TARGET_BLOCK_ENABLED)) {
            return 0;
        }
        return original;
    }
}