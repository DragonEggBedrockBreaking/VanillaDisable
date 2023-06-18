package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.despawning;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Bucketable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Mob.class)
public abstract class MixinMob {
    @Unique
    private boolean additionalRestrictionsMet() {
        if (((Entity) (Object) this).hasCustomName()) return false;
        if (this instanceof Bucketable bucketable) return !bucketable.fromBucket();
        return true;
    }

    @ModifyExpressionValue(
            method = "checkDespawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;removeWhenFarAway(D)Z"
            )
    )
    private boolean removeWhenFarAway(boolean original) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return DataHandler.getBoolean("entities", entity, "can_despawn") && additionalRestrictionsMet();
    }
}
