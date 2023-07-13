package uk.debb.vanilla_disable.mixin.command.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.CauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(CauldronBlock.class)
public abstract class MixinCauldronBlock {
    @ModifyReturnValue(method = "shouldHandlePrecipitation", at = @At("RETURN"))
    private static boolean shouldHandlePrecipitation(boolean original) {
        return original && CommandDataHandler.getCachedBoolean("blocks", "minecraft:cauldron", "can_be_filled_by_precipitation");
    }

    @ModifyReturnValue(method = "canReceiveStalactiteDrip", at = @At("RETURN"))
    private boolean canReceiveStalactiteDrip(boolean original) {
        return CommandDataHandler.getCachedBoolean("blocks", "minecraft:cauldron", "can_be_filled_by_dripstone");
    }
}
