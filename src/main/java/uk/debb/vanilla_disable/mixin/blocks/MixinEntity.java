package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDimensionChangingDelay", at = @At("RETURN"))
    private int modifyDimensionChangingDelay(int original) {
        return GameruleHelper.getInt(Gamerules.NETHER_PORTAL_COOLDOWN);
    }
}