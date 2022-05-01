package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow public abstract float getFriction();

    /**
     * @author DragonEggBedrockBreaking
     * @reason change the friction of blocks to the default (stone)
     * @param cir the returnable callback info (Float)
     */
    @Inject(method = "getFriction", at = @At("HEAD"), cancellable = true)
    private void cancelFriction(CallbackInfoReturnable<Float> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.ICE_SLIDING) && this.getFriction() == 0.98F) {
            cir.setReturnValue(Blocks.STONE.getFriction());
        }
    }
}
