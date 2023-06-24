package uk.debb.vanilla_disable.mixin.command.entity.player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.player.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Abilities.class)
public abstract class MixinAbilities {
    @Shadow
    private float flyingSpeed;

    @ModifyReturnValue(method = "getFlyingSpeed", at = @At("RETURN"))
    private float getFlyingSpeed(float original) {
        return (float) CommandDataHandler.getCachedDouble("entities", "minecraft:player", "flying_speed") / 0.05F * this.flyingSpeed;
    }
}
