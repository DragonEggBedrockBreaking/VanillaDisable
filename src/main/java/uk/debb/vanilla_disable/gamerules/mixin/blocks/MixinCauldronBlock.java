package uk.debb.vanilla_disable.gamerules.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.CauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(CauldronBlock.class)
public abstract class MixinCauldronBlock {
    @ModifyReturnValue(method = "shouldHandlePrecipitation", at = @At("RETURN"))
    private static boolean shouldNotHandlePrecipitation(boolean original) {
        return Gamerules.PRECIPITATION_FILLS_CAULDRONS.getBool();
    }

    @ModifyReturnValue(method = "canReceiveStalactiteDrip", at = @At("RETURN"))
    private boolean cannotReceiveStalactiteDrip(boolean original) {
        return Gamerules.DRIPSTONE_FILLS_CAULDRONS.getBool();
    }
}
