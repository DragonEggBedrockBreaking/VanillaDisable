package uk.debb.vanilla_disable.command.mixin.rule.entity.conversions;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Hoglin.class)
public abstract class MixinHoglin {
    @ModifyReturnValue(method = "isImmuneToZombification", at = @At("RETURN"))
    private boolean isImmuneToZombification(boolean original) {
        return original || !DataHandler.getBoolean("entities", "minecraft:zombified_piglin", "can_be_converted_to");
    }
}
