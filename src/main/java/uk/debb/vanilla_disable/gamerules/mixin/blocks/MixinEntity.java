package uk.debb.vanilla_disable.gamerules.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @ModifyReturnValue(method = "getDimensionChangingDelay", at = @At("RETURN"))
    private int modifyDimensionChangingDelay(int original) {
        return Gamerules.NETHER_PORTAL_COOLDOWN.getInt();
    }
}