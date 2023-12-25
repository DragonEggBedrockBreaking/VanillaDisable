package uk.debb.vanilla_disable.mixin.command.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Mob.class)
public abstract class MixinMob {
    @ModifyReturnValue(method = "isSunBurnTick", at = @At("RETURN"))
    private boolean vanillaDisable$isSunBurnTick(boolean original) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return original && CommandDataHandler.getCachedBoolean("entities", entity, "burns_in_sunlight");
    }
}
