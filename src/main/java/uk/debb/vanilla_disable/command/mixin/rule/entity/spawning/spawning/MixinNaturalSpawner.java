package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.spawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(NaturalSpawner.class)
public abstract class MixinNaturalSpawner {
    @ModifyReturnValue(method = "isValidPositionForMob", at = @At("RETURN"))
    private static boolean isValidPositionForMob(boolean original, ServerLevel serverLevel, Mob mob, double d) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(mob.getType());
        return original && DataHandler.getCachedBoolean("entities", entity, "can_spawn");
    }
}
