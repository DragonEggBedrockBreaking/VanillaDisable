package uk.debb.vanilla_disable.mixin.blocks.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(ShulkerBoxBlock.class)
public abstract class MixinShulkerBoxBlock {
    @ModifyReturnValue(method = "canOpen", at = @At("RETURN"))
    private static boolean canAlwaysOpen(boolean original) {
        if (!GameruleHelper.getBool(BooleanGamerules.CONTAINER_OPENING_BLOCKED)) {
            return true;
        }
        return original;
    }
}