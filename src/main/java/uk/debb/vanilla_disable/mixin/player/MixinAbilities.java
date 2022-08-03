package uk.debb.vanilla_disable.mixin.player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.player.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Abilities.class)
public abstract class MixinAbilities {
    @Shadow
    private float flyingSpeed;

    @ModifyReturnValue(method = "getFlyingSpeed", at = @At("RETURN"))
    private float modifyFlyingSpeed(float original) {
        return Gamerules.PLAYER_FLYING_SPEED.getValue(Float::parseFloat) / 0.05F * this.flyingSpeed;
    }
}
