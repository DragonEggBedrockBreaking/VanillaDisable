package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.CauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(CauldronBlock.class)
public abstract class MixinCauldronBlock {
    @ModifyReturnValue(method = "shouldHandlePrecipitation", at = @At("RETURN"))
    private static boolean shouldNotHandlePrecipitation(boolean original) {
        return Gamerules.PRECIPITATION_FILLS_CAULDRONS.getValue(Boolean::parseBoolean);
    }

    @ModifyReturnValue(method = "canReceiveStalactiteDrip", at = @At("RETURN"))
    private boolean cannotReceiveStalactiteDrip(boolean original) {
        return Gamerules.DRIPSTONE_FILLS_CAULDRONS.getValue(Boolean::parseBoolean);
    }
}
