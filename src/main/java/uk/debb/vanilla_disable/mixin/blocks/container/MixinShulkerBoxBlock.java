package uk.debb.vanilla_disable.mixin.blocks.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ShulkerBoxBlock.class)
public abstract class MixinShulkerBoxBlock {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "canOpen", at = @At("RETURN"))
    private static boolean canAlwaysOpen(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.CONTAINER_OPENING_BLOCKED)) {
            return true;
        }
        return original;
    }
}