package uk.debb.vanilla_disable.mixin.command.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @ModifyReturnValue(method = "getDimensionChangingDelay", at = @At("RETURN"))
    private int vanillaDisable$getDimensionChangingDelay(int original) {
        return CommandDataHandler.getCachedInt("blocks", "minecraft:nether_portal", "cooldown");
    }
}
