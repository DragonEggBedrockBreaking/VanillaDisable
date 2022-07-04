package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    public abstract float getFriction();

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason change the friction of blocks to the default (stone)
     */
    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float cancelFriction(float original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.ICE_SLIDING) && this.getFriction() == 0.98F) {
            return Blocks.STONE.getFriction();
        }
        return original;
    }
}