package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    public abstract float getFriction();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float cancelFriction(float original) {
        if (GameruleHelper.getServer() == null) return original;
        if (!GameruleHelper.getBool(BooleanGamerules.ICE_SLIDING) && this.getFriction() == 0.98F) {
            return Blocks.STONE.getFriction();
        }
        return original;
    }
}