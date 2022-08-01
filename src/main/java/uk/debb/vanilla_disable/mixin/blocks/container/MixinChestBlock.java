package uk.debb.vanilla_disable.mixin.blocks.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.ChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ChestBlock.class)
public abstract class MixinChestBlock {
    @ModifyReturnValue(method = "isChestBlockedAt", at = @At("RETURN"))
    private static boolean chestNotBlocked(boolean original) {
        if (!Gamerules.CONTAINER_OPENING_BLOCKED.getValue(Boolean::parseBoolean)) {
            return false;
        }
        return original;
    }
}