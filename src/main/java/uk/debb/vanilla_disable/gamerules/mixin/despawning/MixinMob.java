package uk.debb.vanilla_disable.gamerules.mixin.despawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Bucketable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(Mob.class)
public abstract class MixinMob implements Maps {
    @Unique
    private boolean additionalRestrictionsMet() {
        if (this instanceof Bucketable bucketable) {
            return !((Entity) (Object) this).hasCustomName() && !bucketable.fromBucket();
        }
        return true;
    }

    @ModifyReturnValue(method = "removeWhenFarAway", at = @At("RETURN"))
    private boolean cancelRemovalWhenFarAway(boolean original) {
        Gamerules gameRule = mobClassMapDespawn.get(this.getClass());
        if (gameRule != null && !(((Entity) (Object) this).getType().equals(EntityType.VILLAGER))) {
            return gameRule.getBool() && additionalRestrictionsMet();
        }
        return original;
    }
}