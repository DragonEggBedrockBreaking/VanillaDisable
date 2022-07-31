package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.CauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(CauldronBlock.class)
public abstract class MixinCauldronBlock {
    @ModifyReturnValue(method = "shouldHandlePrecipitation", at = @At("RETURN"))
    private static boolean shouldNotHandlePrecipitation(boolean original) {
        return BooleanGamerules.PRECIPITATION_FILLS_CAULDRONS.getValue();
    }

    @ModifyReturnValue(method = "canReceiveStalactiteDrip", at = @At("RETURN"))
    private boolean cannotReceiveStalactiteDrip(boolean original) {
        return BooleanGamerules.DRIPSTONE_FILLS_CAULDRONS.getValue();
    }
}
