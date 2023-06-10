package uk.debb.vanilla_disable.gamerules.mixin.blocks.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(ShulkerBoxBlock.class)
public abstract class MixinShulkerBoxBlock {
    @ModifyReturnValue(method = "canOpen", at = @At("RETURN"))
    private static boolean canAlwaysOpen(boolean original) {
        if (!Gamerules.CONTAINER_OPENING_BLOCKED.getBool()) {
            return true;
        }
        return original;
    }
}