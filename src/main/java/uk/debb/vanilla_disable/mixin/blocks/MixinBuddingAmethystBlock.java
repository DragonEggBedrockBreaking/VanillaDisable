package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(BuddingAmethystBlock.class)
public abstract class MixinBuddingAmethystBlock {
    @ModifyReturnValue(method = "getPistonPushReaction", at = @At("RETURN"))
    private PushReaction pushableBuddingAmethyst(PushReaction original) {
        if (Gamerules.PUSHABLE_BUDDING_AMETHYST.getValue(Boolean::parseBoolean)) {
            return PushReaction.PUSH_ONLY;
        }
        return original;
    }
}