package uk.debb.vanilla_disable.mixin.redstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ButtonBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ButtonBlock.class)
public abstract class MixinButtonBlock {
    @Shadow
    @Final
    private boolean sensitive;

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getPressDuration", at = @At("RETURN"))
    private int modifyPressDuration(int original) {
        if (VDServer.getServer() == null) return original;
        if (this.sensitive) {
            return GameruleHelper.getInt(Gamerules.WOOD_BUTTON_PRESS_DURATION);
        } else {
            return GameruleHelper.getInt(Gamerules.STONE_BUTTON_PRESS_DURATION);
        }
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int modifySignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.BUTTON_ENABLED)) {
            return 0;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int modifyDirectSignal(int original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.BUTTON_ENABLED)) {
            return 0;
        }
        return original;
    }
}