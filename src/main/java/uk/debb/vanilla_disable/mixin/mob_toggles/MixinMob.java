package uk.debb.vanilla_disable.mixin.mob_toggles;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Mob.class)
public abstract class MixinMob implements Maps {
    @Inject(method = "checkDespawn", at = @At("HEAD"))
    private void checkIfEnabled(CallbackInfo ci) {
        Gamerules gameRule = mobClassMapToggle.get(this.getClass());
        if (gameRule != null && !gameRule.getBool()) {
            ((Entity) (Object) this).discard();
        }
    }
}