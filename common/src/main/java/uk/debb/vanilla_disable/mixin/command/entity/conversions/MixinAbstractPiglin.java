package uk.debb.vanilla_disable.mixin.command.entity.conversions;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(AbstractPiglin.class)
public abstract class MixinAbstractPiglin {
    @ModifyReturnValue(method = "isImmuneToZombification", at = @At("RETURN"))
    private boolean vanillaDisable$isImmuneToZombification(boolean original) {
        return original || !CommandDataHandler.getCachedBoolean("entities", "minecraft:zombified_piglin", "can_be_converted_to");
    }
}
