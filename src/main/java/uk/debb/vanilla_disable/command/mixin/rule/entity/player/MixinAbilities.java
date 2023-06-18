package uk.debb.vanilla_disable.command.mixin.rule.entity.player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.player.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Abilities.class)
public abstract class MixinAbilities {
    @Shadow
    private float flyingSpeed;

    @ModifyReturnValue(method = "getFlyingSpeed", at = @At("RETURN"))
    private float getFlyingSpeed(float original) {
        return (float) DataHandler.getDouble("entities", "minecraft:player", "flying_speed") / 0.05F * this.flyingSpeed;
    }
}
