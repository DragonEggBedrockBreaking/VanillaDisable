package uk.debb.vanilla_disable.command.mixin.rule.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @ModifyReturnValue(method = "getDimensionChangingDelay", at = @At("RETURN"))
    private int getDimensionChangingDelay(int original) {
        return DataHandler.getCachedInt("blocks", "minecraft:nether_portal", "cooldown");
    }
}
